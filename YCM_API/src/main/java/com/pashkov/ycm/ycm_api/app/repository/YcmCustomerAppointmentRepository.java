package com.pashkov.ycm.ycm_api.app.repository;

import com.pashkov.ycm.ycm_api.app.model.YcmCustomerAppointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Roman Pashkov created on 25.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.repository
 */
@RepositoryRestResource
public interface YcmCustomerAppointmentRepository extends CrudRepository<YcmCustomerAppointment, Long> {

    @Query("select s from YcmCustomerAppointment s where s.ycmCustomer.id = ?1")
    List<YcmCustomerAppointment> findAllYcmCustomerService(@Param("customerId") Long customerId);

    @Query("select s from  YcmCustomerAppointment s where s.ycmCustomer.nick = ?1 and  s.serviceAppointmentDay = ?2 and  s.serviceHour = ?3")
    Optional<YcmCustomerAppointment> findCustomerServiceByDate(String nick, String serviceDay, String serviceHour1);

    List<YcmCustomerAppointment> findAllByYcmShop_ShopName(String shopName);

    @Query("select s from YcmCustomerAppointment s where s.ycmShop.shopName = ?1 and s.serviceAppointmentDay like %?2%")
    List<YcmCustomerAppointment> findAllCustomerAppointmentsInShopForMonth(String shopName, String month);

    @Query(value = "SELECT CASE WHEN EXISTS ( SELECT * FROM YcmCustomerAppointment  WHERE shop_id = :shopId  AND short_service_name = :shortServiceName AND service_appointment_day = :day AND service_hour = :hour) THEN 'TRUE' ELSE 'FALSE' END", nativeQuery = true)
    boolean existsByShopIdShortServiceNameAndDate(@Param("shopId") long shopId, @Param("shortServiceName") String shortServiceName,
                                                  @Param("day") String serviceDay, @Param("hour") String serviceHour);

    @Query("select s from YcmCustomerAppointment s where s.ycmShop.shopName = ?1 and s.serviceAppointmentDay = ?2")
    List<YcmCustomerAppointment> findAllByServiceAppointmentDay(String shopName, String day);

    @Query("select s from YcmCustomerAppointment s where s.ycmShop.shopName = ?1 and s.serviceAppointmentDay = ?2")
    List<YcmCustomerAppointment> findAllCustomerAppointmentsDuringPeriod(String shopName, String start, String end);
}
