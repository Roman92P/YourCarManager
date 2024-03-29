package com.pashkov.ycm.ycm_api.app.service;

import com.pashkov.ycm.ycm_api.app.model.YcmCustomer;
import com.pashkov.ycm.ycm_api.app.repository.YcmUserCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * Roman Pashkov created on 30.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.service
 */

@Service
public class YcmUserCustomerImpl implements YcmUserCustomerService {

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

    @Override
    public void removeYcmCustomer(long ycmCustomerToRemoveId) {
        ycmUserCustomerRepository.delete(ycmUserCustomerRepository.findById(ycmCustomerToRemoveId).orElseThrow(EntityNotFoundException::new));
    }
}
