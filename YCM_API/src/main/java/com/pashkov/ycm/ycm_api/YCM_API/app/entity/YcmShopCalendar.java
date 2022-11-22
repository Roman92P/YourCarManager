package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
@Entity
public class YcmShopCalendar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //must be many to one
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ycmShop_id")
    private YcmShop ycmShop;


    // to do probably will be better to have List<YcmCustomerServices>
    // probably we don't need it
//    @Transient
//    private String currentDate;
//
//    @Transient
//    private String currentMonth;
//
//    @Transient
//    private int currentYear;
//
//    private int numberOfWorkingHours;

}
