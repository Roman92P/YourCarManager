package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "ycmshopservice")
public class YcmShopService extends YcmService implements Serializable {

    private String timingHours;

//    @Column(nullable = true)
//    @ManyToMany(mappedBy = "services")
////    @JsonManagedReference
//    private List<YcmShop> ycmShops;

    @Override
    public String toString() {
        return "YcmShopService{" +
                ", timingHours='" + timingHours + '\'' +
                //", ycmShops=" + ycmShops +
                '}';
    }
}
