package com.pashkov.ycm.ycm_api.YCM_API.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author - Roman Pashkov
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class YcmShop extends YcmUser {

    private String shopName;
    //private Set<String> services;

    @Override
    public String toString() {
        return "YcmShop{" +
                "shopName='" + shopName + '\'' +
                '}';
    }
}
