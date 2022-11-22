package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "ycmcustomerservice")
public class YcmCustomerService extends YcmService implements Serializable {
    private String serviceAppointmentDay;
    private String serviceHour;
    private String startTimestamp;
    private String endTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ycmcustomer_id")
    private YcmCustomer ycmCustomerPurchaseService;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private YcmShop ycmServiceShops;
    @Override
    public String toString() {
        return "YcmCustomerService{" +
                ", serviceAppointmentDay='" + serviceAppointmentDay + '\'' +
                '}';
    }
}
