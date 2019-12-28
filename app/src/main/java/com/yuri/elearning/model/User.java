package com.yuri.elearning.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    public Integer id;

    public String name;

    public String password;

    public Double money;

    public User(@NonNull Integer id, String name, String password, Double money) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.money = money;
    }
}
