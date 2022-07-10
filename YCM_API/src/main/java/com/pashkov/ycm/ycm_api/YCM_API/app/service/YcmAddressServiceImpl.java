package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.Address;
import com.pashkov.ycm.ycm_api.YCM_API.app.repository.YcmAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.service
 */
@Service
public class YcmAddressServiceImpl implements YcmAddressService {

    @Autowired
    YcmAddressRepository ycmAddressRepository;

    @Override
    public Address getAddressByID(long id) {
        return ycmAddressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
