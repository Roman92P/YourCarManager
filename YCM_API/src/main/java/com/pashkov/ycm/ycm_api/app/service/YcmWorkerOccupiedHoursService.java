package com.pashkov.ycm.ycm_api.app.service;

import com.pashkov.ycm.ycm_api.app.model.YcmWorkerJobs;

/**
 * Roman Pashkov created on 27.12.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */
public interface YcmWorkerOccupiedHoursService {

    void save(YcmWorkerJobs workerOccupiedHours);
}
