package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Roman Pashkov created on 23.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.entity
 */
@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
