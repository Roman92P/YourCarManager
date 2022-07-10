package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomer;

import java.util.Optional;

/**
 * Roman Pashkov created on 30.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.service
 */
public interface YcmUserCustomerService {

    void registerYcmUserCustomer(YcmCustomer ycmCustomer);

    Optional<YcmCustomer> getYcmCustomerByNick(String Nick);
}
