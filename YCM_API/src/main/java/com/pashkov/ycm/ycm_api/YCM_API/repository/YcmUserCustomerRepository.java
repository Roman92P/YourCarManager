package com.pashkov.ycm.ycm_api.YCM_API.repository;

import com.pashkov.ycm.ycm_api.YCM_API.entity.YcmCustomer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Roman Pashkov created on 30.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.repository
 */
@RepositoryRestResource
public interface YcmUserCustomerRepository extends CrudRepository<YcmCustomer,Long> {

}
