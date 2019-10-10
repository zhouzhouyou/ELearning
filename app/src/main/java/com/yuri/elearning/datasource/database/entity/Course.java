package com.yuri.elearning.datasource.database.entity;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey
    public Integer cid;

    public String name;

    public String description;

    public String syllabus;

    public String teacher;

    public Double cost;
    @Ignore
    Bitmap cover;

    public Course(Integer cid, String name, String description, String syllabus, String teacher, Double cost) {
        this.cid = cid;
        this.name = name;
        this.description = description;
        this.syllabus = syllabus;
        this.teacher = teacher;
        this.cost = cost;
    }
}
