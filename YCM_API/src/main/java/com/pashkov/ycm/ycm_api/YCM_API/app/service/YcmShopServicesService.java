package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShopService;

import java.util.List;
import java.util.Optional;

public interface YcmShopServicesService {
    List<YcmShopService> getAllShopServices();

    Optional<YcmShopService> getShopServiceByServiceId(Long id);
}
