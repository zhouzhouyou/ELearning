package com.yuri.elearning.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.yuri.elearning.data.database.AppDatabase;
import com.yuri.elearning.data.database.datasource.LocalLessonDataSource;
import com.yuri.elearning.data.datasource.LessonDataSource;
import com.yuri.elearning.data.network.datasource.RemoteLessonDataSource;
import com.yuri.elearning.model.Lesson;
import com.yuri.elearning.model.LessonBriefInfo;

import java.util.List;

public class LessonRepository implements LessonDataSource {
    private static final String TAG = "LessonRepository";
    private final RemoteLessonDataSource remote;
    private final LocalLessonDataSource local;

    public LessonRepository(AppDatabase appDatabase) {
        remote = new RemoteLessonDataSource();
        local = new LocalLessonDataSource(appDatabase);
    }

    @Override
    public void getLesson(Context context, GetLessonCallback callback, int id) {
        Log.d(TAG, "getLesson: ");
        NetworkInfo info = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            Log.d(TAG, "getLesson: remote");
            remote.getLesson(context, new GetLessonCallback() {
                @Override
                public void onSuccess(Lesson lesson) {
                    callback.onSuccess(lesson);
                    local.insertLesson(lesson);
                }

                @Override
                public void onFailed(String errorMessage) {
                }
            }, id);
        } else {
            Log.d(TAG, "getLesson: local");
            local.getLesson(context, new GetLessonCallback() {
                @Override
                public void onSuccess(Lesson lesson) {
                    callback.onSuccess(lesson);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            }, id);
        }
    }

    @Override
    public void getCalendar(Context context, int uid, int year, int month, GetCalendarCallback callback) {
        Log.d(TAG, "getCalendar: ");
        NetworkInfo info = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            remote.getCalendar(context, uid, year, month, new GetCalendarCallback() {
                @Override
                public void onSuccess(List<LessonBriefInfo> lessons) {
                    callback.onSuccess(lessons);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            });
        } else {
            local.getCalendar(context, uid, year, month, new GetCalendarCallback() {
                @Override
                public void onSuccess(List<LessonBriefInfo> lessons) {
                    callback.onSuccess(lessons);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            });
        }
    }
}
