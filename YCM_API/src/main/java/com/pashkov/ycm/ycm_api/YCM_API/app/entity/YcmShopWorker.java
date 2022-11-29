package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Roman Pashkov created on 29.11.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
@Entity
public class YcmShopWorker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String shopWorkerName;

    private String shopWorkerLastname;

    @ElementCollection(targetClass = ServiceEnum.class)
    @CollectionTable(name = "ycmworker_servicetype", joinColumns = @JoinColumn(name = "worker_id"))
    @Enumerated(EnumType.STRING)
    private Set<ServiceEnum> workerSpecialization;
}
