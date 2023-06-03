package com.pashkov.ycm.ycm_api.app.model.To;

import lombok.Data;

import java.io.Serializable;

/**
 * Roman Pashkov created on 27.11.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
public class YcmCustomerNewAppointmentDTO implements Serializable {
    private String shopName;
    //private YcmShopServiceEntity ycmShopServiceEntity;
    private String ycmShopShortServiceName;
    private String serviceAppointmentDay;
    private String serviceAppointmentHour;
}
