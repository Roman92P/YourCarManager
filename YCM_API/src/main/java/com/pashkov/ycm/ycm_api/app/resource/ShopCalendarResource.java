package com.pashkov.ycm.ycm_api.app.resource;

import com.pashkov.ycm.ycm_api.app.model.YcmCustomerAppointment;
import com.pashkov.ycm.ycm_api.app.model.YcmShop;
import com.pashkov.ycm.ycm_api.app.service.YcmShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Roman Pashkov created on 22.11.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.resource
 */
@RestController
@RequestMapping("/shop")
public class ShopCalendarResource {

    private YcmShopService ycmShopService;

    @GetMapping(path = "/{shopName}/calendar/{month}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> getCurrentMonthShopCalendar(@PathVariable String shopName, @PathVariable String month) {
        YcmShop shopByName = ycmShopService.getShopByName(shopName);
        return null;
    }

    @PostMapping(path = "/{shopName}/calendar", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> addNewUserServiceAppointmentToCalendar (@RequestBody YcmCustomerAppointment ycmCustomerAppointment) {
        return null;
    }

    @DeleteMapping(path = "/{shopName}/calendar", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> removeUserServiceAppointmentFromShopCalendar(@RequestBody YcmCustomerAppointment ycmCustomerAppointment) {
        return null;
    }
}
