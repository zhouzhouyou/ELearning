package com.yuri.elearning.data.datasource;

import android.content.Context;

import com.yuri.elearning.model.Lesson;

public interface LessonDataSource {
    void getLesson(Context context, GetLessonCallback callback, int id);

    interface GetLessonCallback extends BasicCallback<Lesson>{}
}
