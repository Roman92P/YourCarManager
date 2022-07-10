package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author - Roman Pashkov
 */
@Data
@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String town;
    private String postCode;
    private String street;
    private String buildNumber;
    private String apartment;
    private CountryEnum country;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", town='" + town + '\'' +
                ", postCode='" + postCode + '\'' +
                ", street='" + street + '\'' +
                ", buildNumber='" + buildNumber + '\'' +
                ", apartment='" + apartment + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
