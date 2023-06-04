package com.pashkov.ycm.ycm_api.app.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Roman Pashkov created on 29.11.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
@Entity
public class YcmShopWorker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String shopWorkerName;

    private String shopWorkerLastname;

    @OneToOne
    private YcmShop ycmShop;

    @ElementCollection(targetClass = ServiceEnum.class)
    @CollectionTable(name = "ycmworker_servicetype", joinColumns = @JoinColumn(name = "worker_id"))
    @Enumerated(EnumType.STRING)
    private List<ServiceEnum> workerSpecialization;

    @OneToMany(mappedBy = "ycmShopWorker", fetch = FetchType.LAZY)
    private List<YcmWorkerJobs> ycmWorkerOccupiedHours;

    @Override
    public String toString() {
        return "YcmShopWorker{" +
                "id=" + id +
                ", shopWorkerName='" + shopWorkerName + '\'' +
                ", shopWorkerLastname='" + shopWorkerLastname + '\'' +
                ", ycmShop=" + ycmShop +
                ", workerSpecialization=" + workerSpecialization +
                ", ycmWorkerOccupiedHours=" + ycmWorkerOccupiedHours +
                '}';
    }
}
