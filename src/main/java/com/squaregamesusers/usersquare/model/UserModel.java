package com.squaregamesusers.usersquare.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserModel {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private UUID uuid;

    public UserModel(String name){
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public UserModel(){
        this.name = null;
        this.uuid = null;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public UUID getUuid() {
        return uuid;
    }
}
