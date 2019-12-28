package com.yuri.elearning.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;

/**
 * 课程的基本信息（用来展示在Recycler View中）
 */
@Entity
@Data
public class CourseBriefInfo {
    /**
     * 课程名
     */
    public String name;

    /**
     * 课程号
     */
    @PrimaryKey
    @NonNull
    public Integer id;

    /**
     * 课程封面
     */
    public String cover;

    public CourseBriefInfo(String name, @NonNull Integer id, String cover) {
        this.name = name;
        this.id = id;
        this.cover = cover;
    }
}
