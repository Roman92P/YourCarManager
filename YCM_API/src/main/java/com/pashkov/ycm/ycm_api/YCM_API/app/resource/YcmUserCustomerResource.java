package com.pashkov.ycm.ycm_api.YCM_API.app.resource;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomer;
import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomerModel;
import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomerServiceModel;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmAddressService;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmUserCustomerService;
import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomerRepresentationModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;

/**
 * Roman Pashkov created on 30.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.resource
 */

@RestController
@RequestMapping(value = "/user")
public class YcmUserCustomerResource {

    @Autowired
    private YcmCustomerRepresentationModelAssembler ycmCustomerRepresentationModelAssembler;

    @Autowired
    YcmAddressService addressService;

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

    @DeleteMapping(path = "/{nick}", produces = "application/json")
    public ResponseEntity<YcmCustomer> removeYcmCustomer(@PathVariable String nick){
        YcmCustomer ycmCustomer = ycmUserCustomerService.getYcmCustomerByNick(nick).orElseThrow(EntityNotFoundException::new);
        long ycmCustomerToRemoveId = ycmCustomer.getId();
        ycmUserCustomerService.removeYcmCustomer(ycmCustomerToRemoveId);
        return ResponseEntity.ok(ycmCustomer);
    }

    @GetMapping(path = "/{nick}/services", produces = "application/json")
    public ResponseEntity<CollectionModel<YcmCustomerServiceModel>> returnUserScheduledServices(@PathVariable String nick){
        return null;
    }
}
