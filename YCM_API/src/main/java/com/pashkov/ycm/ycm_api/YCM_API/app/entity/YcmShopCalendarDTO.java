package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
public class YcmShopCalendarDTO implements Serializable {

    private YcmShop ycmShop;

    private List<YcmCustomerService> ycmCustomerServices;

}
