package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomer;
import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomerService;
import com.pashkov.ycm.ycm_api.YCM_API.app.repository.YcmCustomerServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

/**
 * Roman Pashkov created on 25.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */

@Service
public class YcmCustomerServicesServiceImpl implements YcmCustomerServicesService{

    @Autowired
    private  YcmUserCustomerService ycmUserCustomerService;
    @Autowired
    private YcmCustomerServicesRepository ycmCustomerServicesRepository;
    @Override
    public List<YcmCustomerService> getYcmCustomerServices(Long usedId) {
        return ycmCustomerServicesRepository.findAllYcmCustomerService(usedId);
    }

    @Override
    public Set<YcmCustomerService> getAllServices() {
        Iterator<YcmCustomerService> all = ycmCustomerServicesRepository.findAll().iterator();
        Set<YcmCustomerService> result = new HashSet<>();
        while (all.hasNext()) {
            result.add((YcmCustomerService) all.next());
        }
        return result;
    }

    @Override
    public Optional<YcmCustomerService> getParticulaCustomerService(String nick, String serviceDay, String serviceHour) {
        return ycmCustomerServicesRepository.findCustomerServiceByDate(nick, serviceDay, serviceHour);
    }

    @Override
    public void removeCustomerService(String nick, String serviceDay, String serviceHour) {
        YcmCustomer ycmCustomer = ycmUserCustomerService.getYcmCustomerByNick(nick).orElseThrow(EntityNotFoundException::new);
        YcmCustomerService ycmCustomerService = ycmCustomerServicesRepository.findCustomerServiceByDate(nick, serviceDay, serviceHour).orElseThrow(EntityNotFoundException::new);
        ycmCustomerService.setYcmCustomerPurchaseService(ycmCustomer);
        ycmCustomerServicesRepository.deleteById(ycmCustomerService.getId());
    }

    @Override
    public List<YcmCustomerService> getAllShopCustomerServices(String shopName) {
        return ycmCustomerServicesRepository.findAllByYcmServiceShops_ShopName(shopName);
    }

    @Override
    public void updateYcmCustomerService(YcmCustomerService ycmCustomerService) {
        ycmCustomerServicesRepository.deleteById(ycmCustomerService.getId());
        ycmCustomerServicesRepository.save(ycmCustomerService);
    }
}
