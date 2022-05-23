package com.pashkov.ycm.ycm_api.YCM_API.entity;

import lombok.Data;

/**
 * @author - Roman Pashkov
 */
@Data
public class Address {

    private long id;
    private String town;
    private String postCode;
    private String street;
    private String buildNumber;
    private String apartment;
    private Country country;

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
