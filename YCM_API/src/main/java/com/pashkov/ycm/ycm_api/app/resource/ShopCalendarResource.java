package com.pashkov.ycm.ycm_api.app.resource;

import com.pashkov.ycm.ycm_api.app.model.YcmCustomerAppointment;
import com.pashkov.ycm.ycm_api.app.model.YcmCustomerAppointmentModel;
import com.pashkov.ycm.ycm_api.app.model.YcmCustomerServiceRepresentationModelAssembler;
import com.pashkov.ycm.ycm_api.app.model.YcmShop;
import com.pashkov.ycm.ycm_api.app.service.YcmCustomerAppointmentService;
import com.pashkov.ycm.ycm_api.app.service.YcmShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Roman Pashkov created on 22.11.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.resource
 */
@RestController
@RequestMapping("/shop")
public class ShopCalendarResource {

    @Autowired
    private YcmShopService ycmShopService;

    @Autowired
    private YcmCustomerAppointmentService ycmCustomerAppointmentService;

    @Autowired
    private YcmCustomerServiceRepresentationModelAssembler ycmCustomerServiceRepresentationModelAssembler;

    @GetMapping(path = "/{shopName}/calendar/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<YcmCustomerAppointmentModel>> getAllShopsReservedAppointments(@PathVariable String shopName) {
        List<YcmCustomerAppointmentModel> ycmCustomerAppointmentModels = ycmCustomerAppointmentService.getAllShopCustomerServices(shopName)
                .stream()
                .map(ycmCustomerServiceRepresentationModelAssembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ycmCustomerAppointmentModels);
    }

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
