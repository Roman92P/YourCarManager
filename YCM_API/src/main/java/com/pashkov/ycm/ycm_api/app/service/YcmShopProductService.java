package com.pashkov.ycm.ycm_api.app.service;

import com.pashkov.ycm.ycm_api.app.model.YcmCustomerAppointment;
import com.pashkov.ycm.ycm_api.app.model.YcmShopProductEntity;

import java.util.List;
import java.util.Optional;

public interface YcmShopProductService {
    List<YcmShopProductEntity> getAllShopServices();

    Optional<YcmShopProductEntity> getShopServiceByServiceId(Long id);

    List<YcmShopProductEntity> getShopServicesByShopNick(String shopNick);

    List<YcmShopProductEntity> addServiceToShop(String shopNick, YcmShopProductEntity ycmShopProductEntity);

    List<YcmShopProductEntity> removeServiceFromShopServices(String shopNick, YcmShopProductEntity ycmShopProductEntity);

    boolean selectedServiceIsAvailableInSelectedShop(YcmCustomerAppointment ycmCustomerNewService);

    Optional<YcmShopProductEntity> getShopServiceByServiceShortName(String shortName, long shopId);
}
