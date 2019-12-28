package com.yuri.elearning.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;

/**
 * 课程
 */
@Entity
@Data
public class Course {
    /**
     * 课程的ID
     */
    @PrimaryKey
    @NonNull
    public Integer id;

    /**
     * 课程名
     */
    public String name;

    /**
     * 课程描述
     */
    public String description;

    /**
     * 课程大纲
     */
    public String syllabus;

    /**
     * 授课老师
     */
    public String teacher;

    /**
     * 课程费用
     */
    public Double cost;

    /**
     * 封面图片
     */
    public String cover;

    public Course(@NonNull Integer id, String name, String description, String syllabus, String teacher, Double cost, String cover) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.syllabus = syllabus;
        this.teacher = teacher;
        this.cost = cost;
        this.cover = cover;
    }
}
