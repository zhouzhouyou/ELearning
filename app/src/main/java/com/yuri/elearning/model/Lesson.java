package com.yuri.elearning.model;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;

/**
 * 一节课
 */
@Data
@Entity
public class Lesson {
    /**
     * 这节课的ID
     */
    @PrimaryKey
    @NonNull
    public Integer id;

    /**
     * 这节课的名字
     */
    @NonNull
    public String title;

    /**
     * 这节课的内容
     */
    public String content;

    /**
     * 对应课程的ID
     */
    @NonNull
    public Integer cid;

    /**
     * 课的时间
     */
    @NonNull
    public Date time;

    /**
     * 视频资源
     */
    public String video;

    public Lesson(@NonNull Integer id, @NonNull String title, String content, @NonNull Integer cid, @NonNull Date time, String video) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.cid = cid;
        this.time = time;
        this.video = video;
    }
}
