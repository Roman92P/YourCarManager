package com.pashkov.ycm.ycm_api.YCM_API.app.resource;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmShopService;
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
    public ResponseEntity<List<YcmShopService>> getAllAvailableServices() {
        List<YcmShopService> allShopServices = ycmShopServicesService.getAllShopServices();
        return ResponseEntity.ok(allShopServices);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YcmShopService> getYcmServiceById(@PathVariable Long id) {
        Optional<YcmShopService> shopServiceByServiceId = ycmShopServicesService.getShopServiceByServiceId(id);
        if(!shopServiceByServiceId.isPresent()) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shopServiceByServiceId.get());
    }
}
