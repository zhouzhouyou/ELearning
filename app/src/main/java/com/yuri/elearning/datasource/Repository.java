package com.yuri.elearning.datasource;

import android.app.Application;

import com.yuri.elearning.datasource.database.AppDatabase;
import com.yuri.elearning.datasource.database.dao.CourseDao;
import com.yuri.elearning.datasource.database.dao.UserDao;
import com.yuri.elearning.datasource.database.entity.Course;
import com.yuri.elearning.datasource.database.entity.User;
import com.yuri.elearning.datasource.database.repository.UserAsyncTasks.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

import static com.yuri.elearning.datasource.database.repository.CourseAsyncTasks.*;

public class Repository {
    private UserDao mUserDao;
    private CourseDao mCourseDao;
    private LiveData<List<User>> mAllUsers;
    private LiveData<List<Course>> mAllCourses;

    public Repository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        mUserDao = database.userDao();
        mCourseDao = database.courseDao();
        mAllUsers = mUserDao.queryAllUser();
        mAllCourses = mCourseDao.queryAllCourses();
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    public LiveData<List<Course>> getAllCourses() {
        return mAllCourses;
    }

    public Course queryCourse(int cid) {
        try {
            return new queryCourseTask(mCourseDao).execute(cid).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertUser(User... users) {
        new insertUserTask(mUserDao).execute(users);
    }

    public void insertCourse(Course... courses) {
        new insertCourseTask(mCourseDao).execute(courses);
    }

    public User queryUser(Integer uid) {
        try {
            return new queryUserTask(mUserDao).execute(uid).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
