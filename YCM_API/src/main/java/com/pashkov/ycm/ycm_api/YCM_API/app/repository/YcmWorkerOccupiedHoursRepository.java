package com.pashkov.ycm.ycm_api.YCM_API.app.repository;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmWorkerOccupiedHours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Roman Pashkov created on 27.12.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.repository
 */
@RepositoryRestResource
public interface YcmWorkerOccupiedHoursRepository extends CrudRepository<YcmWorkerOccupiedHours, Long> {
}
