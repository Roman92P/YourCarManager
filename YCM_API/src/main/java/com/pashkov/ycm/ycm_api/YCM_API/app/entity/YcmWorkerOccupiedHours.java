package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Roman Pashkov created on 26.12.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Entity
@Data
public class YcmWorkerOccupiedHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ycmShopWorker_id")
    private YcmShopWorker ycmShopWorker;

    @OneToOne
    private YcmCustomerService ycmCustomerService;

    @Override
    public String toString() {
        return "YcmWorkerOccupiedHours{" +
                "id=" + id +
                ", ycmCustomerService=" + ycmCustomerService +
                '}';
    }
}
