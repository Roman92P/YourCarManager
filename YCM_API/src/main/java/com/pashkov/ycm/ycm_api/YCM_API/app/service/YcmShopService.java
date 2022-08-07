package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShop;

import java.util.Optional;

/**
 * Roman Pashkov created on 28.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */
public interface YcmShopService {

    YcmShop getShopById(long shopId);
    
    void createShop(YcmShop ycmShop);
    
    Optional<YcmShop> getShopByName(String shopName);

    void removeYcmShop(YcmShop ycmShop);
}
