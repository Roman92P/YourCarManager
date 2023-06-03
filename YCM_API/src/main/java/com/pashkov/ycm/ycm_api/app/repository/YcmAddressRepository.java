package com.pashkov.ycm.ycm_api.app.repository;

import com.pashkov.ycm.ycm_api.app.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.service
 */
@RepositoryRestResource
public interface YcmAddressRepository extends CrudRepository<Address, Long> {

}
