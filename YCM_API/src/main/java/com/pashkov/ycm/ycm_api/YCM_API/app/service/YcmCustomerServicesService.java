package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomerService;

import java.util.List;
import java.util.Set;

/**
 * Roman Pashkov created on 25.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */
public interface YcmCustomerServicesService {

    List<YcmCustomerService> getYcmCustomerServices(Long id);
    Set<YcmCustomerService> getAllServices();
}
