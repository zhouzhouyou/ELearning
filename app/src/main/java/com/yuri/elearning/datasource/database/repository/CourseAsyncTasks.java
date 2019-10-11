package com.yuri.elearning.datasource.database.repository;

import android.os.AsyncTask;

import com.yuri.elearning.datasource.database.AppDatabase;
import com.yuri.elearning.datasource.database.dao.CourseDao;
import com.yuri.elearning.datasource.database.entity.Course;

public class CourseAsyncTasks {
    public static class insertCourseTask extends AsyncTask<Course, Void, Void> {
        private CourseDao mCourseDao;

        public insertCourseTask(CourseDao courseDao) {
            mCourseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            mCourseDao.insertCourses(courses);
            return null;
        }
    }

    public static class queryCourseTask extends AsyncTask<Integer, Void, Course> {
        private CourseDao mCourseDao;

        public queryCourseTask(CourseDao courseDao) {
            mCourseDao = courseDao;
        }

        @Override
        protected Course doInBackground(Integer... integers) {
            return mCourseDao.queryCourse(integers[0]);
        }
    }

    public static class populateCourseDbTask extends AsyncTask<Void, Void, Void> {
        private final CourseDao mCourseDao;

        public populateCourseDbTask(AppDatabase database) {
            mCourseDao = database.courseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mCourseDao.deleteAll();
            for (int i = 0; i < 10; i++) {
                Course course = new Course(i, "course#" + i, "desc#" + i, "syllabus#" + i, "teacher#" + i, (double) i);
                mCourseDao.insertCourses(course);
            }
            return null;
        }
    }
}
