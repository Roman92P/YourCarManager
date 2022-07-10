package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
@Entity
public class YcmCalendar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ycmShop_id")
    private YcmShop ycmShop;

    @Transient
    private String currentDate;

    @Transient
    private String currentMonth;

    @Transient
    private int currentYear;

    private int numberOfWorkingHours;

}
