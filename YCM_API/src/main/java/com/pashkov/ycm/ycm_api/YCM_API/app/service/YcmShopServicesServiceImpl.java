package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShop;
import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShopServiceEntity;
import com.pashkov.ycm.ycm_api.YCM_API.app.repository.YcmShopRepository;
import com.pashkov.ycm.ycm_api.YCM_API.app.repository.YcmShopServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class YcmShopServicesServiceImpl implements YcmShopServicesService {

    @Autowired
    private YcmShopServiceRepository ycmShopServiceRepository;

    @Autowired
    YcmShopRepository ycmShopRepository;

    @Override
    public List<YcmShopServiceEntity> getAllShopServices() {
        Iterable<YcmShopServiceEntity> allServices = ycmShopServiceRepository.getAllServicesWithShopId();
        List<YcmShopServiceEntity> servicesList = new ArrayList<>();
        for (YcmShopServiceEntity ycmShopService : allServices) {
            servicesList.add(ycmShopService);
        }
        return servicesList;
    }

    @Override
    public Optional<YcmShopServiceEntity> getShopServiceByServiceId(Long id) {
        return ycmShopServiceRepository.findById(id);
    }

    @Override
    public List<YcmShopServiceEntity> getShopServicesByShopNick(String shopNick) {
        return ycmShopServiceRepository.getYcmShopServicesByShopNick(shopNick);
    }

    @Override
    public List<YcmShopServiceEntity> addServiceToShop(String shopNick, YcmShopServiceEntity ycmShopServiceEntity) {
        Optional<YcmShop> ycmShopByNick = ycmShopRepository.getYcmShopByNick(shopNick);
        if (!ycmShopByNick.isPresent()) {
            throw new EntityNotFoundException();
        }
        List<YcmShopServiceEntity> shopServices = ycmShopServiceRepository.getYcmShopServicesByShopNick(shopNick);
        shopServices.add(ycmShopServiceEntity);
        YcmShopServiceEntity savedNewService = ycmShopServiceRepository.save(ycmShopServiceEntity);
        YcmShop ycmShop = ycmShopByNick.get();
        ycmShop.setServices(shopServices);
        ycmShopRepository.save(ycmShop);
        return shopServices;
    }

    @Override
    public List<YcmShopServiceEntity> removeServiceFromShopServices(String shopNick, YcmShopServiceEntity ycmShopServiceEntity) {
        Optional<YcmShop> ycmShopByNick = ycmShopRepository.getYcmShopByNick(shopNick);
        if (!ycmShopByNick.isPresent()) {
            throw new EntityNotFoundException();
        }
        YcmShop ycmShop = ycmShopByNick.get();
        List<YcmShopServiceEntity> services = ycmShop.getServices();
        List<YcmShopServiceEntity> updatedShopServiceList = services.stream()
                .filter(service -> !service.equals(ycmShopServiceEntity))
                .collect(Collectors.toList());
        ycmShop.setServices(updatedShopServiceList);
        ycmShopRepository.save(ycmShop);
        return updatedShopServiceList;
    }
}
