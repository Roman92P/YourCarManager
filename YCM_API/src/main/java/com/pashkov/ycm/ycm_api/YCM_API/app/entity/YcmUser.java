package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author - Roman Pashkov
 */
@Data
@Table(name = "ycmuser")
@MappedSuperclass
public abstract class YcmUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
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
