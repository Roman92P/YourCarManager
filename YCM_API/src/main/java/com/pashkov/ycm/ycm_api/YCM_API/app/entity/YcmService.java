package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Roman Pashkov created on 30.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.entity
 */
@Data
@MappedSuperclass
@Table(name = "ycmservice")
public abstract class YcmService implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private ServiceEnum serviceType;
    private String serviceDescription;

    private String servicePrice;

    private YcmCurrency currency;

    @Override
    public String toString() {
        return "YcmService{" +
                "id=" + id +
                ", serviceType=" + serviceType +
                ", serviceDescription='" + serviceDescription + '\'' +
                '}';
    }
}
