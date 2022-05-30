package com.pashkov.ycm.ycm_api.YCM_API.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Roman Pashkov created on 30.05.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.entity
 */
@Data
@Entity
public class YcmService implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String servicePrice;
    private ServiceEnum serviceType;
    private YcmCurrency currency;
    private String timingHours;
    private String serviceDescription;

    @ManyToMany(mappedBy = "services")
    private Set<YcmShop> ycmShops = new HashSet<>();

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", servicePrice='" + servicePrice + '\'' +
                ", serviceType=" + serviceType +
                ", currency=" + currency +
                ", timingHours='" + timingHours + '\'' +
                ", serviceDescription='" + serviceDescription + '\'' +
                '}';
    }
}
