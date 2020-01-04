package com.yuri.elearning.data.datasource;

import android.content.Context;

import com.yuri.elearning.model.Lesson;
import com.yuri.elearning.model.LessonBriefInfo;

import java.util.List;

public interface LessonDataSource {
    void getLesson(Context context, GetLessonCallback callback, int id);

    void getCalendar(Context context, int uid, int year, int month, GetCalendarCallback callback);

    interface GetLessonCallback extends BasicCallback<Lesson>{}

    interface GetCalendarCallback extends BasicCallback<List<LessonBriefInfo>> {}
}
