package com.pashkov.ycm.ycm_api.YCM_API.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author - Roman Pashkov
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "ycmcustomer")
public class YcmCustomer extends YcmUser implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    private String name;
    private String lastName;

    @Override
    public String toString() {
        return "YcmCustomer{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                "id='" + this.getId() + '\''+
                '}';
    }
}
