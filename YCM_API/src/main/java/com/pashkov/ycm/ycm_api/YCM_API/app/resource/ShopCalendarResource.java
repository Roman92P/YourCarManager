package com.pashkov.ycm.ycm_api.YCM_API.app.resource;

import com.pashkov.ycm.ycm_api.YCM_API.app.entity.YcmCustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * Roman Pashkov created on 22.11.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.resource
 */
@RestController
@RequestMapping("/shop")
public class ShopCalendarResource {

    @GetMapping(path = "/{shopName}/calendar", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> getCurrentMonthShopCalendar(@PathVariable String shopName) {
        return null;
    }

    @PostMapping(path = "/{shopName}/calendar", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> addNewUserServiceAppointmentToCalendar (@RequestBody YcmCustomerService ycmCustomerService) {
        return null;
    }

    @DeleteMapping(path = "/{shopName}/calendar", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> removeUserServiceAppointmentFromShopCalendar(@RequestBody YcmCustomerService ycmCustomerService) {
        return null;
    }
}
