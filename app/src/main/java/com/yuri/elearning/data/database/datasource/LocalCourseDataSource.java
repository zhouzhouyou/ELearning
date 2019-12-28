package com.yuri.elearning.data.database.datasource;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.yuri.elearning.data.database.AppDatabase;
import com.yuri.elearning.data.database.dao.CourseBriefInfoDao;
import com.yuri.elearning.data.database.dao.TypeDao;
import com.yuri.elearning.data.datasource.CourseDataSource;
import com.yuri.elearning.model.CourseBriefInfo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.RequiresApi;

public class LocalCourseDataSource extends AbstractLocalDataSource implements CourseDataSource {
    private final CourseBriefInfoDao mCourseBriefInfoDao;
    private final TypeDao mTypeDao;

    public LocalCourseDataSource(AppDatabase appDatabase) {
        super(appDatabase);
        mCourseBriefInfoDao = appDatabase.courseBasicMessageDao();
        mTypeDao = appDatabase.typeDao();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void insertAllCourses(List<CourseBriefInfo> courseBriefInfos) {
        new insertAllCoursesTask(mCourseBriefInfoDao).execute(courseBriefInfos.stream().toArray(CourseBriefInfo[]::new));
    }

    private static class queryAllCoursesTask extends AsyncTask<Void, Void, List<CourseBriefInfo>> {
        private final CourseBriefInfoDao mCourseBriefInfoDao;

        public queryAllCoursesTask(CourseBriefInfoDao courseBriefInfoDao) {
            mCourseBriefInfoDao = courseBriefInfoDao;
        }

        @Override
        protected List<CourseBriefInfo> doInBackground(Void... voids) {
            return mCourseBriefInfoDao.queryAll();
        }
    }

    private static class insertAllCoursesTask extends AsyncTask<CourseBriefInfo, Void, Void> {
        private final CourseBriefInfoDao mCourseBriefInfoDao;

        public insertAllCoursesTask(CourseBriefInfoDao courseBriefInfoDao) {
            mCourseBriefInfoDao = courseBriefInfoDao;
        }

        @Override
        protected Void doInBackground(CourseBriefInfo... courseBriefInfos) {
            mCourseBriefInfoDao.insert(Arrays.asList(courseBriefInfos));
            return null;
        }
    }

    @Override
    public void getCourseDetail(Context context, GetCourseDetailCallback callback, int cid) {
        //ignore
    }

    @Override
    public void getAllCourses(Context context, GetAllCourseCallback callback) {
        List<CourseBriefInfo> list;
        try {
            list = new queryAllCoursesTask(mCourseBriefInfoDao).execute().get();
            callback.onSuccess(list);
        } catch (ExecutionException | InterruptedException e) {
            callback.onFailed("local database is empty");
        }
    }

    private static class queryCourseByCategory extends AsyncTask<Integer, Void, List<CourseBriefInfo>> {
        private final TypeDao mTypeDao;
        private final CourseBriefInfoDao mCourseBriefInfoDao;

        public queryCourseByCategory(TypeDao typeDao,CourseBriefInfoDao courseBriefInfoDao) {
            mTypeDao = typeDao;
            mCourseBriefInfoDao = courseBriefInfoDao;
        }

        @Override
        protected List<CourseBriefInfo> doInBackground(Integer... integers) {
            List<Integer> ids = mTypeDao.selectByCategory(integers[0]);
            return mCourseBriefInfoDao.queryById(ids);
        }
    }

    @Override
    public void getCourseByCategory(Context context, GetCourseByCategoryCallback callback, int id) {
        List<CourseBriefInfo> list;
        try {
            list = new queryCourseByCategory(mTypeDao, mCourseBriefInfoDao).execute(id).get();
            callback.onSuccess(list);
        } catch (ExecutionException | InterruptedException e) {
            callback.onFailed(e.toString());
        }
    }

    @Override
    public void getMyCourse(Context context, GetMyCoursesCallback callback, int id) {
        //ignore
    }
}
