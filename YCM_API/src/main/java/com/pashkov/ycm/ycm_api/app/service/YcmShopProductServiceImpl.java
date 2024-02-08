package com.pashkov.ycm.ycm_api.app.service;

import com.pashkov.ycm.ycm_api.app.model.YcmCustomerAppointment;
import com.pashkov.ycm.ycm_api.app.model.YcmShop;
import com.pashkov.ycm.ycm_api.app.model.YcmShopProductEntity;
import com.pashkov.ycm.ycm_api.app.repository.YcmShopProductRepository;
import com.pashkov.ycm.ycm_api.app.repository.YcmShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class YcmShopProductServiceImpl implements YcmShopProductService {

    @Autowired
    private YcmShopProductRepository ycmShopProductRepository;

    @Autowired
    YcmShopRepository ycmShopRepository;

    @Override
    public List<YcmShopProductEntity> getAllShopServices() {
        Iterable<YcmShopProductEntity> allServices = ycmShopProductRepository.getAllServicesWithShopId();
        List<YcmShopProductEntity> servicesList = new ArrayList<>();
        for (YcmShopProductEntity ycmShopService : allServices) {
            servicesList.add(ycmShopService);
        }
        return servicesList;
    }

    @Override
    public Optional<YcmShopProductEntity> getShopServiceByServiceId(Long id) {
        return ycmShopProductRepository.findById(id);
    }

    @Override
    public List<YcmShopProductEntity> getShopServicesByShopNick(String shopNick) {
        return ycmShopProductRepository.getYcmShopServicesByShopNick(shopNick);
    }

    @Override
    public List<YcmShopProductEntity> addServiceToShop(String shopNick, YcmShopProductEntity ycmShopProductEntity) {
        Optional<YcmShop> ycmShopByNick = ycmShopRepository.getYcmShopByNick(shopNick);
        if (!ycmShopByNick.isPresent()) {
            throw new EntityNotFoundException();
        }
        List<YcmShopProductEntity> shopServices = ycmShopProductRepository.getYcmShopServicesByShopNick(shopNick);
        shopServices.add(ycmShopProductEntity);
        YcmShopProductEntity savedNewService = ycmShopProductRepository.save(ycmShopProductEntity);
        YcmShop ycmShop = ycmShopByNick.get();
        ycmShop.setServices(shopServices);
        ycmShopRepository.save(ycmShop);
        return shopServices;
    }

    @Override
    public List<YcmShopProductEntity> removeServiceFromShopServices
            (String shopNick, YcmShopProductEntity ycmShopProductEntity) {
        Optional<YcmShop> ycmShopByNick = ycmShopRepository.getYcmShopByNick(shopNick);
        if (!ycmShopByNick.isPresent()) {
            throw new EntityNotFoundException();
        }
        YcmShop ycmShop = ycmShopByNick.get();
        List<YcmShopProductEntity> services = ycmShop.getServices();
        List<YcmShopProductEntity> updatedShopServiceList = services.stream()
                .filter(service -> !service.equals(ycmShopProductEntity))
                .collect(Collectors.toList());
        ycmShop.setServices(updatedShopServiceList);
        ycmShopRepository.save(ycmShop);
        return updatedShopServiceList;
    }

    @Override
    public boolean selectedServiceIsAvailableInSelectedShop(YcmCustomerAppointment ycmCustomerNewService) {
        List<YcmShopProductEntity> ycmShopServicesByShopNick =
                ycmShopProductRepository.getYcmShopServicesByShopNick(ycmCustomerNewService.getYcmShop().getNick());
        long numberOfSuchServices = ycmShopServicesByShopNick.stream()
                .filter(ycmShopServiceEntity -> ycmShopServiceEntity.getServiceDescription()
                        .equals(ycmShopServiceEntity.getServiceDescription()))
                .count();
        return numberOfSuchServices != 0;
    }

    @Override
    public Optional<YcmShopProductEntity> getShopServiceByServiceShortName(String shortName, long shopId) {
        return Optional.ofNullable(ycmShopProductRepository.findShopServiceByShopIdAndShortServiceName(shortName, shopId));
    }
}
