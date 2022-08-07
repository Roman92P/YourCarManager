package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShop;
import com.pashkov.ycm.ycm_api.YCM_API.app.repository.YcmShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * Roman Pashkov created on 28.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */
@Service
public class YcmShopServiceImpl implements YcmShopService{

    @Autowired
    YcmShopRepository ycmShopRepository;

    @Override
    public YcmShop getShopById(long shopId) {
        return ycmShopRepository.findById(shopId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void createShop(YcmShop ycmShop) {
        ycmShopRepository.save(ycmShop);
    }

    @Override
    public Optional<YcmShop> getShopByName(String shopName) {
        return ycmShopRepository.getYcmShopByShopNameIs(shopName);
    }

    @Override
    public void removeYcmShop(YcmShop ycmShop) {
        ycmShopRepository.delete(ycmShop);
    }
}
