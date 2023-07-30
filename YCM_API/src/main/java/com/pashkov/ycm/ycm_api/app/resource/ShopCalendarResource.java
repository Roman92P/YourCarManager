package com.pashkov.ycm.ycm_api.app.resource;

import com.pashkov.ycm.ycm_api.app.model.YcmCustomerAppointment;
import com.pashkov.ycm.ycm_api.app.model.YcmCustomerAppointmentModel;
import com.pashkov.ycm.ycm_api.app.model.YcmCustomerServiceRepresentationModelAssembler;
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

    @GetMapping(path = "/{shopName}/calendar/currentMonth", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<YcmCustomerAppointmentModel>> getCurrentMonthShopCalendar(@PathVariable String shopName) {
        List<YcmCustomerAppointmentModel> shopsAppointmentsForCurrentMonth = ycmCustomerAppointmentService.getShopAppointmentsForCurrentMonth(shopName).stream()
                .map(ycmCustomerServiceRepresentationModelAssembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(shopsAppointmentsForCurrentMonth);
    }

    @GetMapping(path = "/{shopName}/calendar/{appointmentTimestamp}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<YcmCustomerAppointmentModel>> getAppointmentsForDay(@PathVariable String shopName, @PathVariable String appointmentTimestamp) {
        List<YcmCustomerAppointment> result = ycmCustomerAppointmentService.getAllShopAppointmentsInSpecificDay(shopName, appointmentTimestamp);
        List<YcmCustomerAppointmentModel> mappedResult = result.stream().map(ycmCustomerServiceRepresentationModelAssembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mappedResult);
    }

    @GetMapping(path = "/{shopName}/calendar/{appointmentTimestampStart}/{appointmentTimestampEnd}")
    @ResponseBody
    public ResponseEntity<List<YcmCustomerAppointmentModel>> getAllAppointmentsBetweenDay() {
        return null;
    }

    //To do: probably no need
    @PostMapping(path = "/{shopName}/calendar", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> addNewUserServiceAppointmentToCalendar(@RequestBody YcmCustomerAppointment ycmCustomerAppointment) {
        return null;
    }

    @DeleteMapping(path = "/{shopName}/calendar", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> removeUserServiceAppointmentFromShopCalendar(@RequestBody YcmCustomerAppointmentModel ycmCustomerAppointmentModel) {
        String serviceAppointmentDay = ycmCustomerAppointmentModel.getServiceAppointmentDay();
        String serviceHour = ycmCustomerAppointmentModel.getServiceHour();
        String customerNick = ycmCustomerAppointmentModel.getYcmCustomer().getNick();
        ycmCustomerAppointmentService.removeCustomerService(customerNick, serviceAppointmentDay, serviceHour);
        return ResponseEntity.ok("Appointment removed!");
    }
}
