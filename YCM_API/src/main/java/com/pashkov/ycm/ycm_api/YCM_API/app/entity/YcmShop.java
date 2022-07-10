package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author - Roman Pashkov
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "ycmshop")
public class YcmShop extends YcmUser implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    private String shopName;
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "Shop_Services",
        joinColumns = {@JoinColumn(name="ycm_shop_id")},
        inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private Set<YcmService> services = new HashSet<>();

    @Override
    public String toString() {
        return "YcmShop{" +
                "shopName='" + shopName + '\'' +
                '}';
    }
}
