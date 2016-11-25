package com.iyurenko.dao.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by iyurenko on 22.11.16.
 */

@Entity(name = "thing")
public class Thing implements Serializable{

    public Thing() {}

    public Thing(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


    @Override
    public String toString() {
        return "id = " + id.toString() + " name=" + name + " owner=" + owner;
    }
}
