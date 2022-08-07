package com.pashkov.ycm.ycm_api.YCM_API.app.repository;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomerService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Roman Pashkov created on 25.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.repository
 */
@RepositoryRestResource
public interface YcmCustomerServicesRepository extends CrudRepository <YcmCustomerService, Long> {

    @Query("select s from YcmCustomerService s where s.ycmCustomerPurchaseService.id = ?1")
    List<YcmCustomerService> findAllYcmCustomerService(@Param("customerId") Long customerId);

   @Query("select s from  YcmCustomerService s where s.ycmCustomerPurchaseService.nick = ?1 and  s.serviceAppointmentDay = ?2 and  s.serviceHour = ?3")
    Optional <YcmCustomerService> findCustomerServiceByDate(String nick, String serviceDay, String serviceHour1);
}
