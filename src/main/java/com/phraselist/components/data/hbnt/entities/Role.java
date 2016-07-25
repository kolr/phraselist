package com.phraselist.components.data.hbnt.entities;

import javax.persistence.*;

/**
 * 25.07.2016
 * Created by Rodion.
 */

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "role")
    private String role;

    public Role() {

    }

    public Role(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
