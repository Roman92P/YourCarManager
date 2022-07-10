package com.pashkov.ycm.ycm_api.YCM_API.app.converter;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.Address;
import com.pashkov.ycm.ycm_api.YCM_API.app.repository.YcmAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import javax.persistence.EntityNotFoundException;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.converter
 */
public class AddressConverter implements Converter<Long, Address> {
    @Autowired
    private YcmAddressRepository ycmAddressRepository;

    @Override
    public Address convert(Long source) {
        return ycmAddressRepository.findById(source).orElseThrow(EntityNotFoundException::new);
    }
}
