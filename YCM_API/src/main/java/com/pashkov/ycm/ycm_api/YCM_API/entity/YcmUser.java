package com.pashkov.ycm.ycm_api.YCM_API.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author - Roman Pashkov
 */
@Data
@Entity
@Table(name = "ycmuser")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class YcmUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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
