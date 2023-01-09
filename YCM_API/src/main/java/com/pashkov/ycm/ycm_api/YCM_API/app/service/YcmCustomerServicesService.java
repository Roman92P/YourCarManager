package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomerNewAppointmentDTO;
import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomerService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Roman Pashkov created on 25.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */
public interface YcmCustomerServicesService {

    List<YcmCustomerService> getYcmCustomerServices(Long id);
    Set<YcmCustomerService> getAllServices();

    Optional<YcmCustomerService> getParticularCustomerService(String nick, String serviceDay, String serviceHour);

    void removeCustomerService(String nick, String serviceDay, String serviceHour);

    List<YcmCustomerService> getAllShopCustomerServices(String shopName);

    void updateYcmCustomerService (YcmCustomerService ycmCustomerService);

    YcmCustomerService scheduleNewAppointment(String nick, YcmCustomerNewAppointmentDTO ycmCustomerNewAppointmentDTO);

    boolean dateForServiceInShopIsNotAvailable(long shopId, String shortServiceName, String serviceDay, String serviceHour);
}
