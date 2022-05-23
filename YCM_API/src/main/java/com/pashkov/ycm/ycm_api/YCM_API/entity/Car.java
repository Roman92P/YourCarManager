package com.pashkov.ycm.ycm_api.YCM_API.entity;

import lombok.Data;

/**
 * Roman Pashkov created on 23.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.entity
 */
@Data
public class Car {

    private long id;
    private String manufacturer;
    private String model;
    private String vin;
    private int productionYear;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", vin='" + vin + '\'' +
                ", productionYear=" + productionYear +
                '}';
    }
}
