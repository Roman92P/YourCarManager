package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
