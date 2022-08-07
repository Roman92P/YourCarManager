package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShopService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface YcmShopServicesService {
    List<YcmShopService> getAllShopServices();

    Optional<YcmShopService> getShopServiceByServiceId(Long id);

    List<YcmShopService> getShopServicesByShopNick(String shopNick);
}
