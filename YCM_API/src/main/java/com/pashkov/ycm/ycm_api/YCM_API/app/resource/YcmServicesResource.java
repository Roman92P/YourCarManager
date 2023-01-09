package com.pashkov.ycm.ycm_api.YCM_API.app.resource;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShopServiceEntity;
import com.pashkov.ycm.ycm_api.YCM_API.app.service.YcmShopServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/service")
public class YcmServicesResource {

    @Autowired
    private YcmShopServicesService ycmShopServicesService;

    @GetMapping(path = "/services", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<YcmShopServiceEntity>> getAllAvailableServices() {
        List<YcmShopServiceEntity> allShopServices = ycmShopServicesService.getAllShopServices();
        return ResponseEntity.ok(allShopServices);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YcmShopServiceEntity> getYcmServiceById(@PathVariable Long id) {
        Optional<YcmShopServiceEntity> shopServiceByServiceId = ycmShopServicesService.getShopServiceByServiceId(id);
        return shopServiceByServiceId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
