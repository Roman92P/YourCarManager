package com.pashkov.ycm.ycm_api.YCM_API.resource;

import com.pashkov.ycm.ycm_api.YCM_API.entity.YcmCustomer;
import com.pashkov.ycm.ycm_api.YCM_API.service.YcmUserCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

/**
 * Roman Pashkov created on 30.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.resource
 */

@RestController
@RequestMapping(value = "/user/register")
public class YcmUserCustomerRegister {

    @Autowired
    private YcmUserCustomerService ycmUserCustomerService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<YcmCustomer> registerYcmCustomer(
            @RequestBody YcmCustomer ycmCustomer){
        ycmUserCustomerService.registerYcmUserCustomer(ycmCustomer);
        return ResponseEntity.ok(ycmCustomer);
    }
}
