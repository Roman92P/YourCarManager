package com.pashkov.ycm.ycm_api.app.service;

import com.pashkov.ycm.ycm_api.app.model.ServiceEnum;
import com.pashkov.ycm.ycm_api.app.model.YcmShopWorker;
import com.pashkov.ycm.ycm_api.app.repository.YcmShopWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Roman Pashkov created on 26.12.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */
@Service
public class YcmShopWorkerServiceImpl implements YcmShopWorkerService{

    @Autowired
    YcmShopWorkerRepository ycmShopWorkerRepository;

    @Override
    public Set<YcmShopWorker> getAllWorkersByShopIdAndSpecialization(long shopId, String specialization) {
        return ycmShopWorkerRepository.getAllShopWorkersWithSpecificSpecialization(shopId, ServiceEnum.valueOf(specialization));
    }
}
