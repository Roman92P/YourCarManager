package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ycmcustomerservice")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class YcmCustomerService extends YcmService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String serviceAppointmentDay;
    private String serviceHour;
    private String startTimestamp;
    private String endTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ycmcustomer_id")
    private YcmCustomer ycmCustomerPurchaseService;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private YcmShop ycmShop;

    @Override
    public String toString() {
        return "YcmCustomerService{" +
                ", serviceAppointmentDay='" + serviceAppointmentDay + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        YcmCustomerService that = (YcmCustomerService) o;
        return  startTimestamp.equals(that.startTimestamp) && ycmCustomerPurchaseService.getId() == that.ycmCustomerPurchaseService.getId() && ycmShop.getId() == that.ycmShop.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), serviceAppointmentDay, serviceHour, startTimestamp, endTimestamp, ycmCustomerPurchaseService, ycmShop);
    }
}
