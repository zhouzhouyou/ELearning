package com.yuri.elearning.data.network.datasource;

import android.content.Context;
import android.util.Log;

import com.yuri.elearning.data.datasource.CourseDataSource;
import com.yuri.elearning.model.CourseBriefInfo;
import com.yuri.elearning.model.CourseDetailInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteCourseDataSource extends AbstractRemoteDataSource implements CourseDataSource {

    @Override
    public void getCourseDetail(Context context, GetCourseDetailCallback callback, int cid) {
        Log.d(TAG, "getCourseDetail: ");
        Call<CourseDetailInfo> call = mApiInterface.courseDetail(cid);
        call.enqueue(new Callback<CourseDetailInfo>() {
            @Override
            public void onResponse(Call<CourseDetailInfo> call, Response<CourseDetailInfo> response) {
                Log.d(TAG, "onResponse: " + response);
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body());
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<CourseDetailInfo> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
                callback.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getAllCourses(Context context, GetAllCourseCallback callback) {
        Log.d(TAG, "getAllCourses: ");
        Call<List<CourseBriefInfo>> call = mApiInterface.queryAllCourses();
        call.enqueue(new Callback<List<CourseBriefInfo>>() {
            @Override
            public void onResponse(Call<List<CourseBriefInfo>> call, Response<List<CourseBriefInfo>> response) {
                Log.d(TAG, "onResponse: " + response);
                if (response.body() != null) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CourseBriefInfo>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
                callback.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getCourseByCategory(Context context, GetCourseByCategoryCallback callback, int id) {
        Log.d(TAG, "getCourseByCategory: ");
        Call<List<CourseBriefInfo>> call = mApiInterface.queryAllCourseByCategory(id);
        call.enqueue(new Callback<List<CourseBriefInfo>>() {
            @Override
            public void onResponse(Call<List<CourseBriefInfo>> call, Response<List<CourseBriefInfo>> response) {
                Log.d(TAG, "onResponse: " + response);
                if (response.body() != null) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CourseBriefInfo>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
                callback.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getMyCourse(Context context, GetMyCoursesCallback callback, int id) {
        Log.d(TAG, "getMyCourse: ");
        Call<List<CourseBriefInfo>> call = mApiInterface.queryMyCourses(id);
        call.enqueue(new Callback<List<CourseBriefInfo>>() {
            @Override
            public void onResponse(Call<List<CourseBriefInfo>> call, Response<List<CourseBriefInfo>> response) {
                Log.d(TAG, "onResponse: " + response);
                if (response.body() != null) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CourseBriefInfo>> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }
}
