package com.yuri.elearning.data.database.datasource;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.yuri.elearning.data.database.AppDatabase;
import com.yuri.elearning.data.database.dao.CategoryDao;
import com.yuri.elearning.data.database.dao.TypeDao;
import com.yuri.elearning.data.datasource.CategoryDataSource;
import com.yuri.elearning.model.Category;
import com.yuri.elearning.model.Type;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.RequiresApi;

public class LocalCategoryDataSource extends AbstractLocalDataSource implements CategoryDataSource {
    private final CategoryDao mCategoryDao;
    private final TypeDao mTypeDao;

    public LocalCategoryDataSource(AppDatabase appDatabase) {
        super(appDatabase);
        mCategoryDao = appDatabase.categoryDao();
        mTypeDao = appDatabase.typeDao();
    }

    private static class queryAllCategoriesTask extends AsyncTask<Void, Void, List<Category>> {
        private final CategoryDao mCategoryDao;

        public queryAllCategoriesTask(CategoryDao categoryDao) {
            mCategoryDao = categoryDao;
        }

        @Override
        protected List<Category> doInBackground(Void... voids) {
            return mCategoryDao.selectAll();
        }
    }

    private static class insertCategoriesTask extends AsyncTask<Category, Void, Void> {
        private final CategoryDao mCategoryDao;

        public insertCategoriesTask(CategoryDao categoryDao) {
            mCategoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            mCategoryDao.insert(categories);
            return null;
        }
    }

    @Override
    public void getCategories(Context context, GetCategoriesCallback callback) {
        List<Category> list;
        try {
            list = new queryAllCategoriesTask(mCategoryDao).execute().get();
            callback.onSuccess(list);
        } catch (InterruptedException | ExecutionException e) {
            callback.onFailed(e.toString());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void insertCategories(List<Category> list) {
        new insertCategoriesTask(mCategoryDao).execute(list.stream().toArray(Category[]::new));
    }

    private static class insertTypeTask extends AsyncTask<Type, Void, Void> {
        private final TypeDao mTypeDao;

        public insertTypeTask(TypeDao typeDao) {
            mTypeDao = typeDao;
        }

        @Override
        protected Void doInBackground(Type... types) {
            mTypeDao.insert(types);
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void insertType(List<Type> list) {
        new insertTypeTask(mTypeDao).execute(list.stream().toArray(Type[]::new));
    }
}
