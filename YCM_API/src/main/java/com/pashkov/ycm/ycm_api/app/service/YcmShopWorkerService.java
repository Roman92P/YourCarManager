package com.pashkov.ycm.ycm_api.app.service;

import com.pashkov.ycm.ycm_api.app.model.YcmShopWorker;

import java.util.Set;

/**
 * Roman Pashkov created on 26.12.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */
public interface YcmShopWorkerService {
    Set<YcmShopWorker> getAllWorkersByShopIdAndSpecialization(long shopId, String Specialization);
}
