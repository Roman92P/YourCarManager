package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "ycmcustomerservice")
public class YcmCustomerService extends YcmService {

    private String serviceCost;

    private String serviceAppointmentDay;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ycmshop_id")
//    private YcmShop ycmShopOfferingService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ycmcustomer_id")
    private YcmCustomer ycmCustomerPurchaseService;

    @Override
    public String toString() {
        return "YcmCustomerService{" +
                "serviceCost='" + serviceCost + '\'' +
                ", serviceAppointmentDay='" + serviceAppointmentDay + '\'' +
                //", ycmShopOfferingService=" + ycmShopOfferingService +
                '}';
    }
}
