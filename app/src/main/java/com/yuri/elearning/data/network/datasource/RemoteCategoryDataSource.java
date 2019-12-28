package com.yuri.elearning.data.network.datasource;

import android.content.Context;
import android.util.Log;

import com.yuri.elearning.data.datasource.CategoryDataSource;
import com.yuri.elearning.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteCategoryDataSource extends AbstractRemoteDataSource implements CategoryDataSource {
    @Override
    public void getCategories(Context context, GetCategoriesCallback callback) {
        Log.d(TAG, "getCategories: ");
        Call<List<Category>> call = mApiInterface.allCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Log.d(TAG, "onResponse: " + response);
                if (response.body() != null) callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }
}
