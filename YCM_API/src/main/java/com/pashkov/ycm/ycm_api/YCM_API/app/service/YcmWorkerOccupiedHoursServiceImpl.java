package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmWorkerOccupiedHours;
import com.pashkov.ycm.ycm_api.YCM_API.app.repository.YcmWorkerOccupiedHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Roman Pashkov created on 27.12.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */
@Service
public class YcmWorkerOccupiedHoursServiceImpl implements YcmWorkerOccupiedHoursService {

    @Autowired
    YcmWorkerOccupiedHoursRepository ycmWorkerOccupiedHoursRepository;

    @Override
    public void save(YcmWorkerOccupiedHours workerOccupiedHours) {
        ycmWorkerOccupiedHoursRepository.save(workerOccupiedHours);
    }
}
