package com.yuri.elearning.datasource.database.entity;

import android.graphics.Bitmap;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    public Integer uid;

    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;

    public String password;

    public String description;

    public Date birthday;

    public User(@NonNull Integer uid, String firstName, String lastName, String password, String description, Date birthday) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.description = description;
        this.birthday = birthday;
    }

    @Ignore
    Bitmap profilePicture;


}
