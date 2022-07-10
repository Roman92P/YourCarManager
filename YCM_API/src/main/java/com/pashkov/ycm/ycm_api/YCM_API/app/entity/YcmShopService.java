package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "ycmshopservice")
public class YcmShopService extends YcmService{

    private String servicePrice;

    private YcmCurrency currency;

    private String timingHours;

    @ManyToMany(mappedBy = "services")
    private Set<YcmShop> ycmShops = new HashSet<>();

}
