package com.yuri.elearning.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 课程详情页面会显示的内容
 */
@Data
@AllArgsConstructor
public class CourseDetailInfo {
    /**
     * 课程的具体内容
     */
    public Course course;

    /**
     * 每节课的基本内容
     */
    public List<LessonBriefInfo> lessonBriefInfos;

}
