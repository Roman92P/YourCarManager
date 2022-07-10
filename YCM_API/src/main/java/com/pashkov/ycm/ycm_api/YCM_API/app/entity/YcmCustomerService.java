package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    @OneToOne
    @JoinColumn(name = "ycmshop_id")
    private YcmShop ycmShopOfferingService;

}
