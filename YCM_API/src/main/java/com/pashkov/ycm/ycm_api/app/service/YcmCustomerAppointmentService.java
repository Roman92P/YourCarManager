package com.pashkov.ycm.ycm_api.app.service;

import com.pashkov.ycm.ycm_api.app.model.To.YcmCustomerNewAppointmentDTO;
import com.pashkov.ycm.ycm_api.app.model.YcmCustomerAppointment;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Roman Pashkov created on 25.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.service
 */
public interface YcmCustomerAppointmentService {

    List<YcmCustomerAppointment> getYcmCustomerServices(Long id);

    Set<YcmCustomerAppointment> getAllServices();

    Optional<YcmCustomerAppointment> getParticularCustomerService(String nick, String serviceDay, String serviceHour);

    void removeCustomerService(String nick, String serviceDay, String serviceHour);

    List<YcmCustomerAppointment> getAllShopCustomerServices(String shopName);

    void updateYcmCustomerService(YcmCustomerAppointment ycmCustomerAppointment);

    YcmCustomerAppointment scheduleNewAppointment(String nick, YcmCustomerNewAppointmentDTO ycmCustomerNewAppointmentDTO);

    boolean dateForServiceInShopIsNotAvailable(long shopId, String shortServiceName, String serviceDay, String serviceHour);

    List<YcmCustomerAppointment> getShopAppointmentsForCurrentMonth(String shopName);

    List<YcmCustomerAppointment> getAllShopAppointmentsInSpecificDay(String shopName, String appointmentTimestamp);

    List<YcmCustomerAppointment> getBetweenDays(String shopName, String from, String until);
}
