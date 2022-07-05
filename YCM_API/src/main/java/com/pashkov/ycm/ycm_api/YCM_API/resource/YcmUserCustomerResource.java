package com.pashkov.ycm.ycm_api.YCM_API.resource;

import com.pashkov.ycm.ycm_api.YCM_API.entity.YcmCustomer;
import com.pashkov.ycm.ycm_api.YCM_API.entity.YcmCustomerModel;
import com.pashkov.ycm.ycm_api.YCM_API.entity.YcmCustomerRepresentationModelAssembler;
import com.pashkov.ycm.ycm_api.YCM_API.service.YcmUserCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Roman Pashkov created on 30.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.resource
 */

@RestController
@RequestMapping(value = "/user")
public class YcmUserCustomerResource {

    @Autowired
    private YcmCustomerRepresentationModelAssembler ycmCustomerRepresentationModelAssembler;

    @Autowired
    private YcmUserCustomerService ycmUserCustomerService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<YcmCustomer> registerYcmCustomer(
            @RequestBody YcmCustomer ycmCustomer){
        ycmUserCustomerService.registerYcmUserCustomer(ycmCustomer);
        return ResponseEntity.ok(ycmCustomer);
    }

    @GetMapping(path = "/{nick}", produces = "application/json")
    public ResponseEntity<YcmCustomerModel> getYcmCustomerDetails(@PathVariable String nick){
        return ycmUserCustomerService.getYcmCustomerByNick(nick)
                .map(ycmCustomerRepresentationModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
