package com.pashkov.ycm.ycm_api.app.service;

import com.pashkov.ycm.ycm_api.app.model.ServiceEnum;
import com.pashkov.ycm.ycm_api.app.model.YcmShop;
import com.pashkov.ycm.ycm_api.app.model.YcmShopWorker;

import java.util.Optional;
import java.util.Set;

/**
 * Roman Pashkov created on 28.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */
public interface YcmShopService {

    YcmShop getShopById(long shopId);

    void createShop(YcmShop ycmShop);

    YcmShop getShopByName(String shopName);

    void removeYcmShop(YcmShop ycmShop);

    Optional<YcmShop> getShopByShopNick(String shopNick);

    Set<YcmShopWorker> getAllShopWorkersWithNeededSpecialization(String shopNick, ServiceEnum serviceType);
}
