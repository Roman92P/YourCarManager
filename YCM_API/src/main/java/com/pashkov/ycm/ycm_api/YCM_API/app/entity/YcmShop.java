package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author - Roman Pashkov
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "ycmshop")
public class YcmShop extends YcmUser implements Serializable {

    private String shopName;
    @ManyToMany
    @JoinTable(name = "Shop_Services",
        joinColumns = {@JoinColumn(name="ycm_shop_id")},
        inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    @JsonBackReference
    private List<YcmShopService> services;

//    @OneToMany
//    @JoinColumn(name = "ycmShop_id")
//    Set<YcmCalendar> ycmCalendars;

    @Override
    public String toString() {
        return "YcmShop{" +
                "shopName='" + shopName + '\'' +
                '}';
    }
}
