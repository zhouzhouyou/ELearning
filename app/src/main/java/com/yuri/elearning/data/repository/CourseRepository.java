package com.yuri.elearning.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import com.yuri.elearning.data.database.AppDatabase;
import com.yuri.elearning.data.database.datasource.LocalCategoryDataSource;
import com.yuri.elearning.data.database.datasource.LocalCourseDataSource;
import com.yuri.elearning.data.datasource.CourseDataSource;
import com.yuri.elearning.data.network.datasource.RemoteCourseDataSource;
import com.yuri.elearning.model.CourseBriefInfo;
import com.yuri.elearning.model.CourseDetailInfo;
import com.yuri.elearning.model.Type;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;

public class CourseRepository implements CourseDataSource {
    private static final String TAG = "CourseRepository";
    private final RemoteCourseDataSource remote;
    private final LocalCourseDataSource local;
    private final LocalCategoryDataSource mLocalCategoryDataSource;

    public CourseRepository(AppDatabase appDatabase) {
        remote = new RemoteCourseDataSource();
        local = new LocalCourseDataSource(appDatabase);
        mLocalCategoryDataSource = new LocalCategoryDataSource(appDatabase);
    }

    @Override
    public void getCourseDetail(Context context, GetCourseDetailCallback callback, int cid) {
        Log.d(TAG, "getCourseDetail: ");
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            Log.d(TAG, "getCourseDetail: remote");
            remote.getCourseDetail(context, new GetCourseDetailCallback() {
                @Override
                public void onSuccess(CourseDetailInfo courseDetailInfo) {
                    Log.d(TAG, "onSuccess: " + courseDetailInfo);
                    callback.onSuccess(courseDetailInfo);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            }, cid);
        }
    }

    @Override
    public void getAllCourses(Context context, GetAllCourseCallback callback) {
        Log.d(TAG, "getAllCourses: ");
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            Log.d(TAG, "getAllCourses: remote");
            remote.getAllCourses(context, new GetAllCourseCallback() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onSuccess(List<CourseBriefInfo> courseBriefInfos) {
                    callback.onSuccess(courseBriefInfos);
                    local.insertAllCourses(courseBriefInfos);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            });
        } else {
            Log.d(TAG, "getAllCourses: local");
            local.getAllCourses(context, new GetAllCourseCallback() {
                @Override
                public void onSuccess(List<CourseBriefInfo> list) {
                    callback.onSuccess(list);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            });
        }
    }

    @Override
    public void getCourseByCategory(Context context, GetCourseByCategoryCallback callback, int id) {
        Log.d(TAG, "getCourseByCategory: ");
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            Log.d(TAG, "getCourseByCategory: remote");
            remote.getCourseByCategory(context, new GetCourseByCategoryCallback() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onSuccess(List<CourseBriefInfo> courseBriefInfos) {
                    callback.onSuccess(courseBriefInfos);
                    List<Type> list = new ArrayList<>();
                    courseBriefInfos.forEach(courseBriefInfo -> list.add(new Type(courseBriefInfo.id, id)));
                    mLocalCategoryDataSource.insertType(list);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            }, id);
        } else {
            Log.d(TAG, "getCourseByCategory: local");
            local.getCourseByCategory(context, new GetCourseByCategoryCallback() {
                @Override
                public void onSuccess(List<CourseBriefInfo> courseBriefInfos) {
                    callback.onSuccess(courseBriefInfos);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            }, id);
        }
    }

    @Override
    public void getMyCourse(Context context, GetMyCoursesCallback callback, int id) {
        Log.d(TAG, "getMyCourse: ");
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            remote.getMyCourse(context, new GetMyCoursesCallback() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onSuccess(List<CourseBriefInfo> courseBriefInfos) {
                    callback.onSuccess(courseBriefInfos);
                    local.insertAllCourses(courseBriefInfos);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            }, id);
        } else {
            local.getMyCourse(context, new GetMyCoursesCallback() {
                @Override
                public void onSuccess(List<CourseBriefInfo> courseBriefInfos) {
                    callback.onSuccess(courseBriefInfos);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            }, id);
        }
    }
}
