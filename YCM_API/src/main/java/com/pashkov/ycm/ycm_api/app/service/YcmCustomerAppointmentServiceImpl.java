package com.pashkov.ycm.ycm_api.app.service;

import com.pashkov.ycm.ycm_api.app.model.*;
import com.pashkov.ycm.ycm_api.app.model.To.YcmCustomerNewAppointmentDTO;
import com.pashkov.ycm.ycm_api.app.util.mapper.CustomerAppointmentToYcmCustomerServiceMapper;
import com.pashkov.ycm.ycm_api.app.exceptions.CustomerAppointmentAlreadyScheduledException;
import com.pashkov.ycm.ycm_api.app.exceptions.DateHourForSelectedServiceIsNotAvailable;
import com.pashkov.ycm.ycm_api.app.repository.YcmCustomerAppointmentRepository;
import com.pashkov.ycm.ycm_api.app.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Roman Pashkov created on 25.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */

@Service
public class YcmCustomerAppointmentServiceImpl implements YcmCustomerAppointmentService {
    @Autowired
    YcmWorkerOccupiedHoursService ycmWorkerOccupiedHoursService;
    @Autowired
    YcmUserCustomerService ycmUserCustomerService;
    @Autowired
    YcmCustomerAppointmentRepository ycmCustomerAppointmentRepository;
    @Autowired
    CustomerAppointmentToYcmCustomerServiceMapper customerAppointmentToYcmCustomerServiceMapper;
    @Autowired
    YcmShopProductService ycmShopProductService;
    @Autowired
    YcmShopService ycmShopService;

    @Override
    public List<YcmCustomerAppointment> getYcmCustomerServices(Long usedId) {
        return ycmCustomerAppointmentRepository.findAllYcmCustomerService(usedId);
    }

    @Override
    public Set<YcmCustomerAppointment> getAllServices() {
        Iterator<YcmCustomerAppointment> all = ycmCustomerAppointmentRepository.findAll().iterator();
        Set<YcmCustomerAppointment> result = new HashSet<>();
        while (all.hasNext()) {
            result.add((YcmCustomerAppointment) all.next());
        }
        return result;
    }

    @Override
    public Optional<YcmCustomerAppointment> getParticularCustomerService(String nick, String serviceDay, String serviceHour) {
        return ycmCustomerAppointmentRepository.findCustomerServiceByDate(nick, serviceDay, serviceHour);
    }

    @Override
    public void removeCustomerService(String nick, String serviceDay, String serviceHour) {
        YcmCustomer ycmCustomer = ycmUserCustomerService.getYcmCustomerByNick(nick).orElseThrow(EntityNotFoundException::new);
        YcmCustomerAppointment ycmCustomerAppointment = ycmCustomerAppointmentRepository.findCustomerServiceByDate(nick, serviceDay, serviceHour).orElseThrow(EntityNotFoundException::new);
        ycmCustomerAppointment.setYcmCustomer(ycmCustomer);
        ycmCustomerAppointmentRepository.deleteById(ycmCustomerAppointment.getId());
    }

    @Override
    public List<YcmCustomerAppointment> getAllShopCustomerServices(String shopName) {
        return ycmCustomerAppointmentRepository.findAllByYcmShop_ShopName(shopName);
    }

    @Override
    public void updateYcmCustomerService(YcmCustomerAppointment ycmCustomerAppointment) {
        ycmCustomerAppointmentRepository.deleteById(ycmCustomerAppointment.getId());
        ycmCustomerAppointmentRepository.save(ycmCustomerAppointment);
    }

    @Override
    @Transactional
    public YcmCustomerAppointment scheduleNewAppointment
            (String nick, YcmCustomerNewAppointmentDTO ycmCustomerNewAppointmentDTO) {
        Optional<YcmCustomer> ycmCustomerByNick = ycmUserCustomerService.getYcmCustomerByNick(nick);
        if (!ycmCustomerByNick.isPresent()) {
            throw new EntityNotFoundException("Ycm Customer not found. Please check your request!");
        }
        YcmCustomer ycmCustomer = ycmCustomerByNick.get();
        YcmCustomerAppointment ycmCustomerNewService =
                customerAppointmentToYcmCustomerServiceMapper.mapCustomerAppointmentDtoToYcmCustomerService
                        (ycmCustomerNewAppointmentDTO, ycmCustomer);
        List<YcmCustomerAppointment> allYcmCustomerAppointment = ycmCustomerAppointmentRepository.findAllYcmCustomerService(ycmCustomer.getId());
        boolean throwDuplicate = false;
        for (YcmCustomerAppointment service : allYcmCustomerAppointment) {
            if (service.equals(ycmCustomerNewService)) {
                throwDuplicate = true;
                break;
            }
        }
        if (throwDuplicate) {
            throw new CustomerAppointmentAlreadyScheduledException(String.format("Appointment in %s for %s already in your calender!",
                    ycmCustomerNewService.getYcmShop().getShopName(), ycmCustomerNewService.getStartTimestamp()));
        }
        if (!ycmShopProductService.selectedServiceIsAvailableInSelectedShop(ycmCustomerNewService)) {
            throw new EntityNotFoundException("Selected service is not available");
        }

        //check if shop have available worker for chosen service
        ServiceEnum serviceType = ycmCustomerNewService.getServiceType();
        Set<YcmShopWorker> allShopWorkersWithNeededSpecialization = ycmShopService.getAllShopWorkersWithNeededSpecialization(ycmCustomerNewService.getYcmShop().getNick(), serviceType);
        Set<YcmShopWorker> readyToWork = filterWorkersNotBusy(allShopWorkersWithNeededSpecialization, ycmCustomerNewService.getStartTimestamp(), ycmCustomerNewService.getEndTimestamp());

        if (dateForServiceInShopIsNotAvailable(ycmCustomerNewService.getYcmShop().getId(),
                ycmCustomerNewService.getShortServiceName(),
                ycmCustomerNewService.getServiceAppointmentDay(), ycmCustomerNewService.getServiceHour()) || readyToWork.isEmpty()) {
            throw new DateHourForSelectedServiceIsNotAvailable("Select another day or/and time");
        }
        YcmShopWorker worker = readyToWork.iterator().next();
        ycmCustomerNewService.setYcmShopWorker(worker);
        ycmCustomerAppointmentRepository.save(ycmCustomerNewService);
        YcmWorkerJobs hours = new YcmWorkerJobs();
        hours.setYcmShopWorker(worker);
        hours.setYcmCustomerAppointment(ycmCustomerNewService);
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

    private boolean workerIsAvailable(LocalDateTime startTimeOfNewWork, LocalDateTime endTimeOfNewWork, List<YcmWorkerJobs> ycmWorkerOccupiedHours) {
        List<LocalDateTime> allWorkTiming = new ArrayList<>();
        for (YcmWorkerJobs work : ycmWorkerOccupiedHours) {
            allWorkTiming.add(LocalDateTime.parse(work.getYcmCustomerAppointment().getStartTimestamp(), DateTimeFormatter.ofPattern(Const.DATE_TIME_PARSE_FORMAT)));
            allWorkTiming.add(LocalDateTime.parse(work.getYcmCustomerAppointment().getEndTimestamp(), DateTimeFormatter.ofPattern(Const.DATE_TIME_PARSE_FORMAT)));
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
        return ycmCustomerAppointmentRepository.existsByShopIdShortServiceNameAndDate(shopId, shortServiceName, serviceDay, serviceHour);
    }
}
