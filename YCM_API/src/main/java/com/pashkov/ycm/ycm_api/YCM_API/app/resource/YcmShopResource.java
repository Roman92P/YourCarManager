package com.pashkov.ycm.ycm_api.YCM_API.app.resource;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomerService;
import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShop;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmCustomerServicesService;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmShopService;
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

    @GetMapping(path = "/{shopName}")
    @ResponseBody
    public ResponseEntity<YcmShop> getUserShop(@PathVariable String shopName) {
        Optional<YcmShop> shopByName = ycmShopService.getShopByName(shopName);
        return shopByName.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YcmShop> createNewYcmShop (@RequestBody YcmShop ycmShop) {
        ycmShopService.createShop(ycmShop);
        return ResponseEntity.ok(ycmShop);
    }

    @DeleteMapping(path = "/{shopName}")
    @ResponseBody
    public ResponseEntity<YcmShop> removeUserShop(@PathVariable String shopName) {
        Optional<YcmShop> shopByName = ycmShopService.getShopByName(shopName);
        if(!shopByName.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<YcmCustomerService> shopCustomerServices = ycmCustomerServicesService.getAllShopCustomerServices(shopName);
        for (YcmCustomerService ycmCustomerService : shopCustomerServices) {
            ycmCustomerService.setYcmServiceShops(null);
            ycmCustomerServicesService.updateYcmCustomerService(ycmCustomerService);
        }
        ycmShopService.removeYcmShop(shopByName.get());
        return ResponseEntity.ok().build();
    }
}
//{
//    "id": 1,
//    "nick": "shopOne",
//    "email": "rp@gmail.com",
//    "address": {
//        "id": 2,
//        "town": "Santa Barbara",
//        "postCode": "00-002",
//        "street": "East route",
//        "buildNumber": "1",
//        "apartment": "4",
//        "country": "USA"
//    },
//    "role": "SHOP_USER",
//    "shopName": "ONE SHOP"
//}