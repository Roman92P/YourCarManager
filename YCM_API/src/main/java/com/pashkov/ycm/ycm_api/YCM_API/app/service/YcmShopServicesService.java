package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShopServiceEntity;

import java.util.List;
import java.util.Optional;

public interface YcmShopServicesService {
    List<YcmShopServiceEntity> getAllShopServices();

    Optional<YcmShopServiceEntity> getShopServiceByServiceId(Long id);

    List<YcmShopServiceEntity> getShopServicesByShopNick(String shopNick);

    List<YcmShopServiceEntity> addServiceToShop(String shopNick, YcmShopServiceEntity ycmShopServiceEntity);

    List<YcmShopServiceEntity> removeServiceFromShopServices(String shopNick, YcmShopServiceEntity ycmShopServiceEntity);
}
