package com.pashkov.ycm.ycm_api.YCM_API.app.repository;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShopService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface YcmShopServiceRepository extends CrudRepository<YcmShopService, Long> {

    @Query("SELECT s FROM  YcmShopService s")
     List<YcmShopService> getAllServicesWithShopId();
}
