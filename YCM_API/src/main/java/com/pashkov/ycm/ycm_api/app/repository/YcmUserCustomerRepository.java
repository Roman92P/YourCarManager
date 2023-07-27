package com.pashkov.ycm.ycm_api.app.repository;

import com.pashkov.ycm.ycm_api.app.model.YcmCustomer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Roman Pashkov created on 30.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.repository
 */
@RepositoryRestResource
public interface YcmUserCustomerRepository extends CrudRepository<YcmCustomer, Long> {

    @Query(value = "SELECT y FROM YcmCustomer y JOIN y.address WHERE y.nick = ?1")
    Optional<YcmCustomer> findByYcmCustomer_nick(String nick);
}
