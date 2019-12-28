package com.yuri.elearning.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;

/**
 * 不同的课程种类
 */
@Data
@Entity
public class Category {
    /**
     * 种类的ID
     */
    @PrimaryKey
    @NonNull
    public Integer id;

    /**
     * 种类的名字
     */
    public String name;

    /**
     * 种类的描述
     */
    public String description;
}
