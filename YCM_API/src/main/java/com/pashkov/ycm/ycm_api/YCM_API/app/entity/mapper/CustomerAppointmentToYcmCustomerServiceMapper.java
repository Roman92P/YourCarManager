package com.pashkov.ycm.ycm_api.YCM_API.app.entity.mapper;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.*;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.Optional;

/**
 * Roman Pashkov created on 27.11.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity.mapper
 */
@Component
public class CustomerAppointmentToYcmCustomerServiceMapper {

    @Autowired
    private  YcmShopService ycmShopService;

    public YcmCustomerService mapCustomerAppointmentDtoToYcmCustomerService
            (YcmCustomerNewAppointmentDTO ycmCustomerNewAppointmentDTO, YcmCustomer ycmCustomer) {
        if (ycmCustomer == null || ycmCustomerNewAppointmentDTO.getYcmShopServiceEntity() == null) {
            throw new EntityNotFoundException();
        }

        YcmShopServiceEntity ycmShopServiceEntity = ycmCustomerNewAppointmentDTO.getYcmShopServiceEntity();
        String shopName = ycmCustomerNewAppointmentDTO.getShopName();
        Optional<YcmShop> shopByName = ycmShopService.getShopByName(shopName);
        if (!shopByName.isPresent()) {
            throw new EntityNotFoundException();
        }
        String timingHours = ycmShopServiceEntity.getTimingHours();
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
        YcmCustomerService ycmCustomerService = new YcmCustomerService();
        ycmCustomerService.setYcmCustomerPurchaseService(ycmCustomer);
        ycmCustomerService.setYcmShop(shopByName.get());
        ycmCustomerService.setServiceHour(serviceAppointmentHour);
        ycmCustomerService.setServiceAppointmentDay(serviceAppointmentDay);
        ycmCustomerService.setServiceDescription(ycmShopServiceEntity.getServiceDescription());
        ycmCustomerService.setStartTimestamp(String.valueOf(startTimestamp));
        ycmCustomerService.setEndTimestamp(String.valueOf(endTimestamp));
        ycmCustomerService.setCurrency(ycmShopServiceEntity.getCurrency());
        ycmCustomerService.setServicePrice(ycmShopServiceEntity.getServicePrice());
        ycmCustomerService.setServiceType(ycmShopServiceEntity.getServiceType());
        return ycmCustomerService;
    }

}
