package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ycmcustomer_id")
    private YcmCustomer ycmCustomerPurchaseService;

//    @ManyToMany(mappedBy = "customerServices")
//    private Set<YcmShop> ycmServiceShops = new HashSet<>();
    @Override
    public String toString() {
        return "YcmCustomerService{" +
                ", serviceAppointmentDay='" + serviceAppointmentDay + '\'' +
                '}';
    }
}
