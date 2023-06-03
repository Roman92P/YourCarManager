package com.pashkov.ycm.ycm_api.app.resource;

import com.pashkov.ycm.ycm_api.app.model.YcmShopProductEntity;
import com.pashkov.ycm.ycm_api.app.service.YcmShopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/service")
public class YcmServicesResource {

    @Autowired
    private YcmShopProductService ycmShopProductService;

    @GetMapping(path = "/services", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<YcmShopProductEntity>> getAllAvailableServices() {
        List<YcmShopProductEntity> allShopServices = ycmShopProductService.getAllShopServices();
        return ResponseEntity.ok(allShopServices);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YcmShopProductEntity> getYcmServiceById(@PathVariable Long id) {
        Optional<YcmShopProductEntity> shopServiceByServiceId = ycmShopProductService.getShopServiceByServiceId(id);
        return shopServiceByServiceId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
