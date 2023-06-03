package com.pashkov.ycm.ycm_api.app.util.mapper;

import com.pashkov.ycm.ycm_api.app.model.To.YcmCustomerNewAppointmentDTO;
import com.pashkov.ycm.ycm_api.app.model.YcmCustomer;
import com.pashkov.ycm.ycm_api.app.model.YcmCustomerAppointment;
import com.pashkov.ycm.ycm_api.app.model.YcmShop;
import com.pashkov.ycm.ycm_api.app.model.YcmShopProductEntity;
import com.pashkov.ycm.ycm_api.app.service.YcmShopService;
import com.pashkov.ycm.ycm_api.app.service.YcmShopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

/**
 * Roman Pashkov created on 27.11.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity.mapper
 */
@Component
public class CustomerAppointmentToYcmCustomerServiceMapper {

    @Autowired
    YcmShopService ycmShopService;

    @Autowired
    YcmShopProductService ycmShopProductService;

    public YcmCustomerAppointment mapCustomerAppointmentDtoToYcmCustomerService
            (YcmCustomerNewAppointmentDTO ycmCustomerNewAppointmentDTO, YcmCustomer ycmCustomer) {
        if (ycmCustomer == null) {
            throw new EntityNotFoundException("Ycm Customer is null");
        }

        String shopName = ycmCustomerNewAppointmentDTO.getShopName();
        YcmShop shopByName = ycmShopService.getShopByName(shopName);

        Optional<YcmShopProductEntity> serviceOptional = ycmShopProductService.getShopServiceByServiceShortName(ycmCustomerNewAppointmentDTO.getYcmShopShortServiceName(), shopByName.getId());
        if (!serviceOptional.isPresent()) {
            throw new EntityNotFoundException("Unknown service name");
        }
        YcmShopProductEntity ycmShopProductEntity = serviceOptional.get();
        String timingHours = ycmShopProductEntity.getTimingHours();
        String serviceAppointmentDay = ycmCustomerNewAppointmentDTO.getServiceAppointmentDay();
        String serviceAppointmentHour = ycmCustomerNewAppointmentDTO.getServiceAppointmentHour();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(serviceAppointmentDay, formatter);
        LocalDateTime localDateTime = LocalDateTime.parse(localDate.toString() + "T" + serviceAppointmentHour);
        Timestamp startTimestamp = Timestamp.valueOf(localDateTime);
        double appointmentDuration = Double.parseDouble(timingHours);
        int durationInSeconds = (int) (appointmentDuration * 3600);
        LocalDateTime localDateTime1 = localDateTime.plusSeconds(durationInSeconds);
        Timestamp endTimestamp = Timestamp.valueOf(localDateTime1);
        YcmCustomerAppointment ycmCustomerAppointment = new YcmCustomerAppointment();
        ycmCustomerAppointment.setYcmCustomerPurchaseService(ycmCustomer);
        ycmCustomerAppointment.setYcmShop(shopByName);
        ycmCustomerAppointment.setServiceHour(serviceAppointmentHour);
        ycmCustomerAppointment.setServiceAppointmentDay(serviceAppointmentDay);
        ycmCustomerAppointment.setServiceDescription(ycmShopProductEntity.getServiceDescription());
        ycmCustomerAppointment.setStartTimestamp(String.valueOf(startTimestamp));
        ycmCustomerAppointment.setEndTimestamp(String.valueOf(endTimestamp));
        ycmCustomerAppointment.setCurrency(ycmShopProductEntity.getCurrency());
        ycmCustomerAppointment.setServicePrice(ycmShopProductEntity.getServicePrice());
        ycmCustomerAppointment.setServiceType(ycmShopProductEntity.getServiceType());
        ycmCustomerAppointment.setShortServiceName(ycmShopProductEntity.getShortServiceName());
        return ycmCustomerAppointment;
    }

}
