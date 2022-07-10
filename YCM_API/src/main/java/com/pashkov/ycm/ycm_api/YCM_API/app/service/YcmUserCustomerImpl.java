package com.pashkov.ycm.ycm_api.YCM_API.app.service;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomer;
import com.pashkov.ycm.ycm_api.YCM_API.app.repository.YcmUserCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Roman Pashkov created on 30.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.service
 */

@Service
public class YcmUserCustomerImpl implements YcmUserCustomerService{

    @Autowired
    private YcmUserCustomerRepository ycmUserCustomerRepository;

    @Override
    public void registerYcmUserCustomer(YcmCustomer ycmCustomer) {
        ycmUserCustomerRepository.save(ycmCustomer);
    }

    @Override
    public Optional<YcmCustomer> getYcmCustomerByNick(String nick) {
        return ycmUserCustomerRepository.findByYcmCustomer_nick(nick);
    }
}
