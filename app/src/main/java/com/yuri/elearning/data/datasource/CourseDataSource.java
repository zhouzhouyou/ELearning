package com.yuri.elearning.data.datasource;

import android.content.Context;

import com.yuri.elearning.model.CourseBriefInfo;
import com.yuri.elearning.model.CourseDetailInfo;

import java.util.List;

public interface CourseDataSource {
    void getCourseDetail(Context context, GetCourseDetailCallback callback, int cid);
    void getAllCourses(Context context, GetAllCourseCallback callback);
    void getCourseByCategory(Context context, GetCourseByCategoryCallback callback, int id);
    void getMyCourse(Context context, GetMyCoursesCallback callback, int id);

    interface GetCourseDetailCallback extends BasicCallback<CourseDetailInfo> {}

    interface GetAllCourseCallback extends BasicCallback<List<CourseBriefInfo>> {}

    interface GetCourseByCategoryCallback extends BasicCallback<List<CourseBriefInfo>> {}

    interface GetMyCoursesCallback extends BasicCallback<List<CourseBriefInfo>>{}
}
