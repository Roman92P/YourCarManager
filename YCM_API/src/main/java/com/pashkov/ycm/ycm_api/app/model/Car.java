package com.pashkov.ycm.ycm_api.app.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 * Roman Pashkov created on 23.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.entity
 */
@Data
@Entity
public class Car implements Serializable {

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
