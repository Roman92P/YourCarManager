package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.entity
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "ycmshopservice")
public class YcmShopServiceEntity extends YcmService implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String timingHours;

    @Override
    public String toString() {
        return "YcmShopService{" +
                ", timingHours='" + timingHours + '\'' +
                //", ycmShops=" + ycmShops +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        YcmShopServiceEntity that = (YcmShopServiceEntity) o;
        return Objects.equals(timingHours, that.timingHours) && getServiceDescription().equals(that.getServiceDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, timingHours);
    }
}
