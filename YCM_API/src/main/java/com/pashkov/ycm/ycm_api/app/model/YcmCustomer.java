package com.pashkov.ycm.ycm_api.app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author - Roman Pashkov
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ycmcustomer")
public class YcmCustomer extends YcmUser implements Serializable {

    private String name;
    private String lastName;

    @Override
    public String toString() {
        return "YcmCustomer{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                "id='" + this.getId() + '\'' +
                '}';
    }
}
