package com.pashkov.ycm.ycm_api.app.repository;

import com.pashkov.ycm.ycm_api.app.model.YcmShopProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface YcmShopProductRepository extends CrudRepository<YcmShopProductEntity, Long> {

    @Query("SELECT s FROM  YcmShopProductEntity s")
    List<YcmShopProductEntity> getAllServicesWithShopId();

    @Query("SELECT s FROM YcmShopProductEntity s inner join YcmShop shop on shop.nick = ?1")
    List<YcmShopProductEntity> getYcmShopServicesByShopNick(String shopNick);

    @Query("SELECT s FROM YcmShopProductEntity s inner join YcmShop shop on shop.id = ?2 Where s.shortServiceName = ?1")
    YcmShopProductEntity findShopServiceByShopIdAndShortServiceName(String shortServiceName, long shopId);

}
