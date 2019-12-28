package com.yuri.elearning.data.datasource;

import android.content.Context;

import com.yuri.elearning.model.Category;

import java.util.List;

public interface CategoryDataSource {
    void getCategories(Context context, GetCategoriesCallback callback);

    interface GetCategoriesCallback extends BasicCallback<List<Category>> {}
}
