package com.yuri.elearning.viewmodel;

import android.app.Application;
import android.util.Log;

import com.yuri.elearning.datasource.database.entity.Course;
import com.yuri.elearning.util.base.BaseViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class CourseViewModel extends BaseViewModel {
    private LiveData<List<Course>> allCourses;

    public CourseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void init() {
        allCourses = mRepository.getAllCourses();
        Log.i(TAG, "init: course ");
    }

    public void insert(Course... courses) {
        mRepository.insertCourse(courses);
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public Course queryCourse(int cid) {
        if (allCourses != null && allCourses.getValue() != null) {
            for (Course course : allCourses.getValue()) {
                if (cid == course.cid) return course;
            }
        }
        return mRepository.queryCourse(cid);
    }
}
