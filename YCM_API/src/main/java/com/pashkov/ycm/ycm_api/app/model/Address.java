package com.pashkov.ycm.ycm_api.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 * @author - Roman Pashkov
 */
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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
