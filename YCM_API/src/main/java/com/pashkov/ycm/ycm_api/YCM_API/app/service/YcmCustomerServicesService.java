package com.pashkov.ycm.ycm_api.YCM_API.app.service;

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

    Optional<YcmCustomerService> getParticulaCustomerService(String nick, String serviceDay, String serviceHour);

    void removeCustomerService(String nick, String serviceDay, String serviceHour);

    List<YcmCustomerService> getAllShopCustomerServices(String shopName);

    void updateYcmCustomerService (YcmCustomerService ycmCustomerService);
}
