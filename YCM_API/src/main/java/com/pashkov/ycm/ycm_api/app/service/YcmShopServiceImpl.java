package com.pashkov.ycm.ycm_api.app.service;

import com.pashkov.ycm.ycm_api.app.model.ServiceEnum;
import com.pashkov.ycm.ycm_api.app.model.YcmShop;
import com.pashkov.ycm.ycm_api.app.model.YcmShopWorker;
import com.pashkov.ycm.ycm_api.app.repository.YcmCustomerAppointmentRepository;
import com.pashkov.ycm.ycm_api.app.repository.YcmShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

/**
 * Roman Pashkov created on 28.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */
@Service
public class YcmShopServiceImpl implements YcmShopService{

    @Autowired
    YcmShopRepository ycmShopRepository;

    @Autowired
    YcmShopWorkerService ycmShopWorkerService;

    @Autowired
    YcmCustomerAppointmentRepository ycmCustomerAppointmentRepository;

    @Override
    public YcmShop getShopById(long shopId) {
        return ycmShopRepository.findById(shopId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void createShop(YcmShop ycmShop) {
        ycmShopRepository.save(ycmShop);
    }

    @Override
    public YcmShop getShopByName(String shopName) {
        Optional<YcmShop> ycmShopByShopNameIs = ycmShopRepository.getYcmShopByShopNameIs(shopName);
        return ycmShopByShopNameIs.orElseThrow(() -> {
            return new EntityNotFoundException("Could not found such YCM Shop");
        });
    }

    @Override
    public void removeYcmShop(YcmShop ycmShop) {
        ycmShopRepository.delete(ycmShop);
    }

    @Override
    public Optional<YcmShop> getShopByShopNick(String shopNick) {
        return ycmShopRepository.getYcmShopByNick(shopNick);
    }

    @Override
    public Set<YcmShopWorker> getAllShopWorkersWithNeededSpecialization(String shopNick, ServiceEnum serviceType) {
        Optional<YcmShop> ycmShopByNick = ycmShopRepository.getYcmShopByNick(shopNick);
        if (!ycmShopByNick.isPresent()) {
            throw new EntityNotFoundException();
        }
        return ycmShopWorkerService.getAllWorkersByShopIdAndSpecialization(ycmShopByNick.get().getId(), serviceType.toString());
    }

}
