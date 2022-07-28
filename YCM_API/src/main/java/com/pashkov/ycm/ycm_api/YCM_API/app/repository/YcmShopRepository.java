package com.pashkov.ycm.ycm_api.YCM_API.app.repository;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Roman Pashkov created on 28.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.repository
 */
@RepositoryRestResource
public interface YcmShopRepository extends CrudRepository<YcmShop, Long> {
}
