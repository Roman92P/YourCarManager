package com.pashkov.ycm.ycm_api.YCM_API.app.resource;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.*;
import com.pashkov.ycm.ycm_api.YCM_API.app.exceptions.CustomerAppointmentAlreadyScheduledException;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmAddressService;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmCustomerServicesService;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmUserCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
    YcmCustomerServicesService ycmCustomerServicesService;

    @Autowired
    private YcmUserCustomerService ycmUserCustomerService;

    @Autowired
    private YcmCustomerServiceRepresentationModelAssembler ycmCustomerServiceRepresentationModelAssembler;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YcmCustomer> registerYcmCustomer(
            @RequestBody YcmCustomer ycmCustomer) {
        ycmUserCustomerService.registerYcmUserCustomer(ycmCustomer);
        return ResponseEntity.ok(ycmCustomer);
    }

    @GetMapping(path = "/{nick}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YcmCustomerModel> getYcmCustomerDetails(@PathVariable String nick) {
        return ycmUserCustomerService.getYcmCustomerByNick(nick)
                .map(ycmCustomerRepresentationModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{nick}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YcmCustomer> removeYcmCustomer(@PathVariable String nick) {
        YcmCustomer ycmCustomer = ycmUserCustomerService.getYcmCustomerByNick(nick).orElseThrow(EntityNotFoundException::new);
        long ycmCustomerToRemoveId = ycmCustomer.getId();
        ycmUserCustomerService.removeYcmCustomer(ycmCustomerToRemoveId);
        return ResponseEntity.ok(ycmCustomer);
    }

    @GetMapping(path = "/{nick}/services", produces = "application/json")
    @ResponseBody
    public ResponseEntity<CollectionModel<YcmCustomerServiceModel>> returnUserScheduledServices(@PathVariable String nick) {
        YcmCustomer ycmCustomer = ycmUserCustomerService.getYcmCustomerByNick(nick).orElseThrow(EntityNotFoundException::new);
        long id = ycmCustomer.getId();
        List<YcmCustomerService> ycmCustomerServices = ycmCustomerServicesService.getYcmCustomerServices(id);
        return ResponseEntity.ok(ycmCustomerServiceRepresentationModelAssembler.toCollectionModel(
                ycmCustomerServices));
    }

    @GetMapping(path = "/{nick}/services/{serviceDay}/{serviceHour}")
    @ResponseBody
    public ResponseEntity<YcmCustomerServiceModel> getParticularCustomerService(@PathVariable String nick, @PathVariable String serviceDay, @PathVariable String serviceHour) {
        Optional<YcmCustomerService> particularCustomerService = ycmCustomerServicesService.getParticulaCustomerService(nick, serviceDay, serviceHour);
        return particularCustomerService.map(ycmCustomerService -> ResponseEntity.ok(ycmCustomerServiceRepresentationModelAssembler.toModel(ycmCustomerService))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{nick}/services/{serviceDay}/{serviceHour}")
    @ResponseBody
    public ResponseEntity<YcmCustomerServiceModel> removeParticularCustomerService(@PathVariable String nick,
                                                                                   @PathVariable String serviceDay,
                                                                                   @PathVariable String serviceHour) {
        Optional<YcmCustomerService> particularCustomerService = ycmCustomerServicesService
                .getParticulaCustomerService(nick, serviceDay, serviceHour);
        if (!particularCustomerService.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ycmCustomerServicesService.removeCustomerService(nick, serviceDay, serviceHour);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/{nick}/services", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YcmCustomerServiceModel> scheduleNewShopServiceAppointment(
            @Validated @RequestBody YcmCustomerNewAppointmentDTO ycmCustomerNewAppointmentDTO,
            @PathVariable String nick) {
        YcmCustomerService newCustomerAppointment = ycmCustomerServicesService
                .scheduleNewAppointment(nick, ycmCustomerNewAppointmentDTO);
        return ResponseEntity.ok(ycmCustomerServiceRepresentationModelAssembler.toModel(newCustomerAppointment));
    }

    @ExceptionHandler ({CustomerAppointmentAlreadyScheduledException.class})
    public ResponseEntity handleDuplicateServiceSchedulingException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
//{
//        "shopName":"ONE SHOP",
//        "ycmShopServiceEntity" :
//        {
//             "currency" : 0,
//             "serviceDescription" : "Some test description",
//             "servicePrice" : 300,
//             "serviceType" : 9,
//             "timingHours" : 2
//        },
//        "serviceAppointmentDay" : "01-Dec-2022",
//        "serviceAppointmentHour" : "13:00"
//    }

    // post -  http://localhost:8081/ycm_api/user/Roman2/services
    //{
    //        "shopName":"ONE SHOP",
    //        "ycmShopShortServiceName" : "Motor oil",
    //        "serviceAppointmentDay" : "01-Dec-2022",
    //        "serviceAppointmentHour" : "13:30"
    //    }
}
