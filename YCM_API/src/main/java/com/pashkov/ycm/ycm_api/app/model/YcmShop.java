package com.pashkov.ycm.ycm_api.app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author - Roman Pashkov
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ycmshop")
public class YcmShop extends YcmUser implements Serializable {

    private String shopName;

    @Column(nullable = true)
    @ManyToMany
    @JoinTable(name = "Shop_Services",
            joinColumns = {@JoinColumn(name = "ycm_shop_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<YcmShopProductEntity> services;

//    @OneToMany(mappedBy = "ycmShop", fetch = FetchType.LAZY)
//    private List<YcmCustomerAppointment> customerAppointments;

    @Override
    public String toString() {
        return "YcmShop{" +
                "shopName='" + shopName + '\'' +
                '}';
    }
}
