package com.pashkov.ycm.ycm_api.app.resource;

import com.pashkov.ycm.ycm_api.app.model.YcmCustomerAppointment;
import com.pashkov.ycm.ycm_api.app.model.YcmShop;
import com.pashkov.ycm.ycm_api.app.model.YcmShopProductEntity;
import com.pashkov.ycm.ycm_api.app.service.YcmCustomerAppointmentService;
import com.pashkov.ycm.ycm_api.app.service.YcmShopService;
import com.pashkov.ycm.ycm_api.app.service.YcmShopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/shop")
public class YcmShopResource {

    @Autowired
    YcmShopService ycmShopService;

    @Autowired
    YcmCustomerAppointmentService ycmCustomerAppointmentService;

    @Autowired
    YcmShopProductService ycmShopProductService;

    @GetMapping(path = "/{shopName}")
    @ResponseBody
    public ResponseEntity<YcmShop> getUserShop(@PathVariable String shopName) {
        YcmShop shopByName = ycmShopService.getShopByName(shopName);
        return ResponseEntity.ok(shopByName);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<YcmShop> createNewYcmShop(@RequestBody YcmShop ycmShop) {
        ycmShopService.getShopByName(ycmShop.getShopName());
        ycmShopService.createShop(ycmShop);
        return ResponseEntity.ok(ycmShop);
    }

    @DeleteMapping(path = "/{shopName}", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<YcmShop> removeUserShop(@PathVariable String shopName) {
        YcmShop shopByName = ycmShopService.getShopByName(shopName);
        List<YcmCustomerAppointment> shopCustomerServices = ycmCustomerAppointmentService.getAllShopCustomerServices(shopName);
        for (YcmCustomerAppointment ycmCustomerAppointment : shopCustomerServices) {
            ycmCustomerAppointment.setYcmShop(null);
            ycmCustomerAppointmentService.updateYcmCustomerService(ycmCustomerAppointment);
        }
        ycmShopService.removeYcmShop(shopByName);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{shopNick}/services", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<YcmShopProductEntity>> getShopAvailableServices(@PathVariable String shopNick) {
        List<YcmShopProductEntity> shopServicesByShopNick = ycmShopProductService.getShopServicesByShopNick(shopNick);
        return ResponseEntity.ok(shopServicesByShopNick);
    }

    @PutMapping(path = "/{shopNick}/services", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<YcmShopProductEntity>> addNewServiceToShop(@PathVariable String shopNick,
                                                                          @RequestBody YcmShopProductEntity ycmShopProductEntity) {
        List<YcmShopProductEntity> updateShopServices = ycmShopProductService.addServiceToShop(shopNick, ycmShopProductEntity);
        return ResponseEntity.ok(updateShopServices);
    }

    @DeleteMapping(path = "/{shopNick}/services", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<YcmShopProductEntity>> removeServiceFromShop(@PathVariable String shopNick,
                                                                            @RequestBody YcmShopProductEntity ycmShopProductEntity) {
        List<YcmShopProductEntity> updateShopServices = ycmShopProductService.removeServiceFromShopServices(shopNick, ycmShopProductEntity);
        return ResponseEntity.ok(updateShopServices);
    }

}