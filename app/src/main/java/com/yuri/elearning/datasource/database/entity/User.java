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
    public String secondName;

    public String password;

    public String description;

    public Date birthday;

    @Ignore
    Bitmap profilePicture;


}
