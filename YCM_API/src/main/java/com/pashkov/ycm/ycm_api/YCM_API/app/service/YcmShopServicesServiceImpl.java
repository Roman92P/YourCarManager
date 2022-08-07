package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShop;
import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShopService;
import com.pashkov.ycm.ycm_api.YCM_API.app.repository.YcmShopServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class YcmShopServicesServiceImpl implements YcmShopServicesService {

    @Autowired
    private YcmShopServiceRepository ycmShopServiceRepository;

    @Override
    public List<YcmShopService> getAllShopServices() {
        Iterable<YcmShopService> allServices = ycmShopServiceRepository.getAllServicesWithShopId();
        List<YcmShopService> servicesList = new ArrayList<>();
        for (YcmShopService ycmShopService : allServices) {
            servicesList.add(ycmShopService);
        }
        return servicesList;
    }

    @Override
    public Optional<YcmShopService> getShopServiceByServiceId(Long id) {
        return ycmShopServiceRepository.findById(id);
    }
}
