package com.pashkov.ycm.ycm_api.YCM_API.entity;

import lombok.Data;

import javax.persistence.OneToOne;

/**
 * @author - Roman Pashkov
 */
@Data
public class User {

    private long id;
    private String nick;
    private boolean enabled;
    private String email;
    @OneToOne
    private Address address;
    private Role role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
