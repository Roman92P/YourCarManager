package com.pashkov.ycm.ycm_api.app.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Roman Pashkov created on 26.12.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Entity
@Data
@Table(name = "YcmWorkerJobs")
public class YcmWorkerJobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ycmShopWorker_id")
    private YcmShopWorker ycmShopWorker;

    @OneToOne
    private YcmCustomerAppointment ycmCustomerAppointment;

    @Override
    public String toString() {
        return "YcmWorkerOccupiedHours{" +
                "id=" + id +
                ", ycmCustomerService=" + ycmCustomerAppointment +
                '}';
    }
}
