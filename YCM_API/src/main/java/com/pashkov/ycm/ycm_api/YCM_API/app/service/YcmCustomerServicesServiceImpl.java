package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomerService;
import com.pashkov.ycm.ycm_api.YCM_API.app.repository.YcmCustomerServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Roman Pashkov created on 25.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */

@Service
public class YcmCustomerServicesServiceImpl implements YcmCustomerServicesService{

    @Autowired
    private YcmCustomerServicesRepository ycmCustomerServicesRepository;
    @Override
    public Set<YcmCustomerService> getYcmCustomerServices(Long usedId) {
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
}
