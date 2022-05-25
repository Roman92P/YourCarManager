package com.pashkov.ycm.ycm_api.YCM_API.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author - Roman Pashkov
 */
@Data
@Entity
@Table(name = "ycmuser")
public class YcmUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nick;
    private String email;
    @OneToOne(fetch = FetchType.LAZY)
    private Address address;
    private Role role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
