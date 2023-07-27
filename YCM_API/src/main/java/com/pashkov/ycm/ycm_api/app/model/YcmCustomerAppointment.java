package com.pashkov.ycm.ycm_api.app.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
@Entity
@Table(name = "ycmcustomerappointment")
public class YcmCustomerAppointment extends YcmService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String serviceAppointmentDay;
    private String serviceHour;
    private String startTimestamp;
    private String endTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ycmcustomer_id")
    private YcmCustomer ycmCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private YcmShop ycmShop;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_id")
    private YcmShopWorker ycmShopWorker;

    @Override
    public String toString() {
        return "YcmCustomerAppointment{" +
                ", serviceAppointmentDay='" + serviceAppointmentDay + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        YcmCustomerAppointment that = (YcmCustomerAppointment) o;
        return startTimestamp.equals(that.startTimestamp) && ycmCustomer.getId() == that.ycmCustomer.getId() && ycmShop.getId() == that.ycmShop.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), serviceAppointmentDay, serviceHour, startTimestamp, endTimestamp, ycmCustomer, ycmShop);
    }
}
