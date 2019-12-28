package com.yuri.elearning.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity(primaryKeys = {"course", "category"})
@AllArgsConstructor
public class Type {
    @NonNull
    public Integer course;

    @NonNull
    public Integer category;
}
