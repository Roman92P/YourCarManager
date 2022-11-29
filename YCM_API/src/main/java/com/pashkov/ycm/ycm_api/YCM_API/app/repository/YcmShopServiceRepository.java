package com.pashkov.ycm.ycm_api.YCM_API.app.repository;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShopServiceEntity;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmShopService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface YcmShopServiceRepository extends CrudRepository<YcmShopServiceEntity, Long> {

    @Query("SELECT s FROM  YcmShopServiceEntity s")
    List<YcmShopServiceEntity> getAllServicesWithShopId();

    @Query("SELECT s FROM YcmShopServiceEntity s inner join YcmShop shop on shop.nick = ?1")
    List<YcmShopServiceEntity> getYcmShopServicesByShopNick(String shopNick);

    @Query("SELECT s FROM YcmShopServiceEntity s inner join YcmShop shop on shop.id = ?2 Where s.shortServiceName = ?1")
    YcmShopServiceEntity findShopServiceByShopIdAndShortServiceName(String shortServiceName, long shopId);

}
