package com.yuri.elearning.model;

import java.sql.Date;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;

@Data
@Entity
public class LessonBriefInfo {
    public String title;

    public Date time;

    @PrimaryKey
    @NonNull
    public Integer id;

    public Integer cid;

    public LessonBriefInfo(String title, Date time, Integer id, Integer cid) {
        this.title = title;
        this.time = time;
        this.id = id;
        this.cid = cid;
    }
}
