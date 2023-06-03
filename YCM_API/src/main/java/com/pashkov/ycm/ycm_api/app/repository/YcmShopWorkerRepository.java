package com.pashkov.ycm.ycm_api.app.repository;

import com.pashkov.ycm.ycm_api.app.model.ServiceEnum;
import com.pashkov.ycm.ycm_api.app.model.YcmShopWorker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/**
 * Roman Pashkov created on 26.12.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.repository
 */
@RepositoryRestResource
public interface YcmShopWorkerRepository extends CrudRepository<YcmShopWorker, Long> {

    @Query("SELECT w FROM YcmShopWorker w Inner JOIN w.workerSpecialization s ON s = ?2 WHERE w.ycmShop.id = ?1")
    Set<YcmShopWorker> getAllShopWorkersWithSpecificSpecialization(long shopId, ServiceEnum serviceType);
}
