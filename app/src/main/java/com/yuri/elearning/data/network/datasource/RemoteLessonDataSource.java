package com.yuri.elearning.data.network.datasource;

import android.content.Context;
import android.util.Log;

import com.yuri.elearning.data.datasource.LessonDataSource;
import com.yuri.elearning.model.Lesson;
import com.yuri.elearning.model.LessonBriefInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteLessonDataSource extends AbstractRemoteDataSource implements LessonDataSource {
    @Override
    public void getLesson(Context context, GetLessonCallback callback, int id) {
        Log.d(TAG, "getLesson: ");
        Call<Lesson> call = mApiInterface.lessonDetail(id);
        call.enqueue(new Callback<Lesson>() {
            @Override
            public void onResponse(Call<Lesson> call, Response<Lesson> response) {
                Log.d(TAG, "onResponse: " + response);
                if (response.body() != null) callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Lesson> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getCalendar(Context context, int uid, int year, int month, GetCalendarCallback callback) {
        mApiInterface.queryCalendar(uid, year, month).enqueue(new Callback<List<LessonBriefInfo>>() {
            @Override
            public void onResponse(Call<List<LessonBriefInfo>> call, Response<List<LessonBriefInfo>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onSuccess(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<LessonBriefInfo>> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }
}
