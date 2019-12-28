package com.yuri.elearning.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import com.yuri.elearning.data.database.AppDatabase;
import com.yuri.elearning.data.database.datasource.LocalCategoryDataSource;
import com.yuri.elearning.data.datasource.CategoryDataSource;
import com.yuri.elearning.data.network.datasource.RemoteCategoryDataSource;
import com.yuri.elearning.model.Category;

import java.util.List;

import androidx.annotation.RequiresApi;

public class CategoryRepository implements CategoryDataSource {
    private static final String TAG = "CategoryRepository";
    private final RemoteCategoryDataSource remote;
    private final LocalCategoryDataSource local;

    public CategoryRepository(AppDatabase appDatabase) {
        remote = new RemoteCategoryDataSource();
        local = new LocalCategoryDataSource(appDatabase);
    }

    @Override
    public void getCategories(Context context, CategoryDataSource.GetCategoriesCallback callback) {
        Log.d(TAG, "getCategories: ");
        NetworkInfo info = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            remote.getCategories(context, new CategoryDataSource.GetCategoriesCallback() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onSuccess(List<Category> list) {
                    callback.onSuccess(list);
                    local.insertCategories(list);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            });
        } else {
            local.getCategories(context, new CategoryDataSource.GetCategoriesCallback() {
                @Override
                public void onSuccess(List<Category> list) {
                    callback.onSuccess(list);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            });
        }
    }
}
