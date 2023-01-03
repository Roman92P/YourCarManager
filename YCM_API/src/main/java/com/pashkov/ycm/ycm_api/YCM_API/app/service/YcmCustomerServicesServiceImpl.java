package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.*;
import com.pashkov.ycm.ycm_api.YCM_API.app.entity.mapper.CustomerAppointmentToYcmCustomerServiceMapper;
import com.pashkov.ycm.ycm_api.YCM_API.app.exceptions.CustomerAppointmentAlreadyScheduledException;
import com.pashkov.ycm.ycm_api.YCM_API.app.exceptions.DateHourForSelectedServiceIsNotAvailable;
import com.pashkov.ycm.ycm_api.YCM_API.app.exceptions.SelectedServiceIsNotAvailableInThisShop;
import com.pashkov.ycm.ycm_api.YCM_API.app.repository.YcmCustomerServicesRepository;
import com.pashkov.ycm.ycm_api.YCM_API.app.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Roman Pashkov created on 25.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */

@Service
public class YcmCustomerServicesServiceImpl implements YcmCustomerServicesService {
    @Autowired
    YcmWorkerOccupiedHoursService ycmWorkerOccupiedHoursService;
    @Autowired
    YcmUserCustomerService ycmUserCustomerService;
    @Autowired
    YcmCustomerServicesRepository ycmCustomerServicesRepository;
    @Autowired
    CustomerAppointmentToYcmCustomerServiceMapper customerAppointmentToYcmCustomerServiceMapper;
    @Autowired
    YcmShopServicesService ycmShopServicesService;
    @Autowired
    YcmShopService ycmShopService;

    @Override
    public List<YcmCustomerService> getYcmCustomerServices(Long usedId) {
        return ycmCustomerServicesRepository.findAllYcmCustomerService(usedId);
    }

    @Override
    public Set<YcmCustomerService> getAllServices() {
        Iterator<YcmCustomerService> all = ycmCustomerServicesRepository.findAll().iterator();
        Set<YcmCustomerService> result = new HashSet<>();
        while (all.hasNext()) {
            result.add((YcmCustomerService) all.next());
        }
        return result;
    }

    @Override
    public Optional<YcmCustomerService> getParticulaCustomerService(String nick, String serviceDay, String serviceHour) {
        return ycmCustomerServicesRepository.findCustomerServiceByDate(nick, serviceDay, serviceHour);
    }

    @Override
    public void removeCustomerService(String nick, String serviceDay, String serviceHour) {
        YcmCustomer ycmCustomer = ycmUserCustomerService.getYcmCustomerByNick(nick).orElseThrow(EntityNotFoundException::new);
        YcmCustomerService ycmCustomerService = ycmCustomerServicesRepository.findCustomerServiceByDate(nick, serviceDay, serviceHour).orElseThrow(EntityNotFoundException::new);
        ycmCustomerService.setYcmCustomerPurchaseService(ycmCustomer);
        ycmCustomerServicesRepository.deleteById(ycmCustomerService.getId());
    }

    @Override
    public List<YcmCustomerService> getAllShopCustomerServices(String shopName) {
        return ycmCustomerServicesRepository.findAllByYcmShop_ShopName(shopName);
    }

    @Override
    public void updateYcmCustomerService(YcmCustomerService ycmCustomerService) {
        ycmCustomerServicesRepository.deleteById(ycmCustomerService.getId());
        ycmCustomerServicesRepository.save(ycmCustomerService);
    }

    @Override
    @Transactional
    public YcmCustomerService scheduleNewAppointment
            (String nick, YcmCustomerNewAppointmentDTO ycmCustomerNewAppointmentDTO) {
        Optional<YcmCustomer> ycmCustomerByNick = ycmUserCustomerService.getYcmCustomerByNick(nick);
        if (!ycmCustomerByNick.isPresent()) {
            throw new EntityNotFoundException();
        }
        YcmCustomer ycmCustomer = ycmCustomerByNick.get();
        YcmCustomerService ycmCustomerNewService =
                customerAppointmentToYcmCustomerServiceMapper.mapCustomerAppointmentDtoToYcmCustomerService
                        (ycmCustomerNewAppointmentDTO, ycmCustomer);
        List<YcmCustomerService> allYcmCustomerService = ycmCustomerServicesRepository.findAllYcmCustomerService(ycmCustomer.getId());
        boolean throwDuplicate = false;
        for (YcmCustomerService service : allYcmCustomerService) {
            if (service.equals(ycmCustomerNewService)) {
                throwDuplicate = true;
                break;
            }
        }
        if (throwDuplicate) {
            throw new CustomerAppointmentAlreadyScheduledException(String.format("Appointment in %s for %s already in your calender",
                    ycmCustomerNewService.getYcmShop().getShopName(), ycmCustomerNewService.getStartTimestamp()));
        }
        if (!ycmShopServicesService.selectedServiceIsAvailableInSelectedShop(ycmCustomerNewService)) {
            throw new SelectedServiceIsNotAvailableInThisShop("Selected service is not available");
        }

        //check if shop have available worker for chosen service
        ServiceEnum serviceType = ycmCustomerNewService.getServiceType();
        Set<YcmShopWorker> allShopWorkersWithNeededSpecialization = ycmShopService.getAllShopWorkersWithNeededSpecialization(ycmCustomerNewService.getYcmShop().getNick(), serviceType);
        Set<YcmShopWorker> readyToWork = filterWorkersNotBusy(allShopWorkersWithNeededSpecialization, ycmCustomerNewService.getStartTimestamp(), ycmCustomerNewService.getEndTimestamp());

        System.out.println(readyToWork);
        if (dateForServiceInShopIsNotAvailable(ycmCustomerNewService.getYcmShop().getId(),
                ycmCustomerNewService.getShortServiceName(),
                ycmCustomerNewService.getServiceAppointmentDay(), ycmCustomerNewService.getServiceHour()) || readyToWork.isEmpty()) {
            throw new DateHourForSelectedServiceIsNotAvailable("Select another day or/and time");
        }
        YcmShopWorker worker = readyToWork.iterator().next();
        ycmCustomerNewService.setYcmShopWorker(worker);
        ycmCustomerServicesRepository.save(ycmCustomerNewService);
        YcmWorkerOccupiedHours hours = new YcmWorkerOccupiedHours();
        hours.setYcmShopWorker(worker);
        hours.setYcmCustomerService(ycmCustomerNewService);
        ycmWorkerOccupiedHoursService.save(hours);
        return ycmCustomerNewService;
    }

    private Set<YcmShopWorker> filterWorkersNotBusy(Set<YcmShopWorker> allShopWorkersWithNeededSpecialization, String startTimestamp, String endTimestamp) {

        LocalDateTime startTimeOfNewWork = LocalDateTime.parse(startTimestamp, DateTimeFormatter.ofPattern(Const.DATE_TIME_PARSE_FORMAT));
        LocalDateTime endTimeOfNewWork = LocalDateTime.parse(endTimestamp, DateTimeFormatter.ofPattern(Const.DATE_TIME_PARSE_FORMAT));
        Set<YcmShopWorker> filteredWorkers = new HashSet<>();
        for (YcmShopWorker ycmShopWorker : allShopWorkersWithNeededSpecialization) {
            if (workerIsAvailable(startTimeOfNewWork, endTimeOfNewWork, ycmShopWorker.getYcmWorkerOccupiedHours())) {
                filteredWorkers.add(ycmShopWorker);
            }
        }
        return filteredWorkers;
    }

    private boolean workerIsAvailable(LocalDateTime startTimeOfNewWork, LocalDateTime endTimeOfNewWork, List<YcmWorkerOccupiedHours> ycmWorkerOccupiedHours) {
        List<LocalDateTime> allWorkTiming = new ArrayList<>();
        for (YcmWorkerOccupiedHours work : ycmWorkerOccupiedHours) {
            allWorkTiming.add(LocalDateTime.parse(work.getYcmCustomerService().getStartTimestamp(), DateTimeFormatter.ofPattern(Const.DATE_TIME_PARSE_FORMAT)));
            allWorkTiming.add(LocalDateTime.parse(work.getYcmCustomerService().getEndTimestamp(), DateTimeFormatter.ofPattern(Const.DATE_TIME_PARSE_FORMAT)));
        }
        for (int i = 0; i < allWorkTiming.size(); i = i+2) {
            LocalDateTime existingWorkStart = allWorkTiming.get(i);
            LocalDateTime existingWorkEnd = allWorkTiming.get(i + 1);
            if (startTimeOfNewWork.isAfter(existingWorkStart) && startTimeOfNewWork.isBefore(existingWorkEnd)) {
                System.out.println("Start between existing");
                return false;
            }
            if (endTimeOfNewWork.isAfter(existingWorkStart) && endTimeOfNewWork.isBefore(existingWorkEnd)) {
                System.out.println("Ends between existing");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean dateForServiceInShopIsNotAvailable(long shopId, String shortServiceName, String serviceDay, String serviceHour) {
        return ycmCustomerServicesRepository.existsByShopIdShortServiceNameAndDate(shopId, shortServiceName, serviceDay, serviceHour);
    }
}
