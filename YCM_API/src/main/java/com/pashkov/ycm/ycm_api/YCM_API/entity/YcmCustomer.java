package com.pashkov.ycm.ycm_api.YCM_API.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author - Roman Pashkov
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class YcmCustomer extends User {

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
