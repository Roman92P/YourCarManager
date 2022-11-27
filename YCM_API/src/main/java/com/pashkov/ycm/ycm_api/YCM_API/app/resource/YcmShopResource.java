package com.pashkov.ycm.ycm_api.YCM_API.app.resource;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomerService;
import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShop;
import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShopServiceEntity;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmCustomerServicesService;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmShopService;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmShopServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/shop")
public class YcmShopResource {

    @Autowired
    YcmShopService ycmShopService;

    @Autowired
    YcmCustomerServicesService ycmCustomerServicesService;

    @Autowired
    YcmShopServicesService ycmShopServicesService;

    @GetMapping(path = "/{shopName}")
    @ResponseBody
    public ResponseEntity<YcmShop> getUserShop(@PathVariable String shopName) {
        Optional<YcmShop> shopByName = ycmShopService.getShopByName(shopName);
        return shopByName.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<YcmShop> createNewYcmShop(@RequestBody YcmShop ycmShop) {
        if (ycmShopService.getShopByName(ycmShop.getShopName()).isPresent() || ycmShopService.getShopByShopNick(ycmShop.getNick()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        ycmShopService.createShop(ycmShop);
        return ResponseEntity.ok(ycmShop);
    }

    @DeleteMapping(path = "/{shopName}", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<YcmShop> removeUserShop(@PathVariable String shopName) {
        Optional<YcmShop> shopByName = ycmShopService.getShopByName(shopName);
        if (!shopByName.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<YcmCustomerService> shopCustomerServices = ycmCustomerServicesService.getAllShopCustomerServices(shopName);
        for (YcmCustomerService ycmCustomerService : shopCustomerServices) {
            ycmCustomerService.setYcmShop(null);
            ycmCustomerServicesService.updateYcmCustomerService(ycmCustomerService);
        }
        ycmShopService.removeYcmShop(shopByName.get());
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{shopNick}/services", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<YcmShopServiceEntity>> getShopAvailableServices(@PathVariable String shopNick) {
        List<YcmShopServiceEntity> shopServicesByShopNick = ycmShopServicesService.getShopServicesByShopNick(shopNick);
        return ResponseEntity.ok(shopServicesByShopNick);
    }

    @PutMapping(path = "/{shopNick}/services", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<YcmShopServiceEntity>> addNewServiceToShop(@PathVariable String shopNick,
                                                                          @RequestBody YcmShopServiceEntity ycmShopServiceEntity) {
        List<YcmShopServiceEntity> updateShopServices = ycmShopServicesService.addServiceToShop(shopNick, ycmShopServiceEntity);
        return ResponseEntity.ok(updateShopServices);
    }

    @DeleteMapping(path = "/{shopNick}/services", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<YcmShopServiceEntity>> removeServiceFromShop(@PathVariable String shopNick,
                                                                          @RequestBody YcmShopServiceEntity ycmShopServiceEntity) {
        List<YcmShopServiceEntity> updateShopServices = ycmShopServicesService.removeServiceFromShopServices(shopNick, ycmShopServiceEntity);
        return ResponseEntity.ok(updateShopServices);
    }

}