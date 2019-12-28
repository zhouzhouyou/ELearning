package com.yuri.elearning.view.home.category;

import android.app.Application;

import com.yuri.elearning.data.datasource.CategoryDataSource;
import com.yuri.elearning.model.Category;
import com.yuri.elearning.util.App;
import com.yuri.elearning.util.base.BaseViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class CategoryViewModel extends BaseViewModel {
    private final MutableLiveData<List<Category>> categoryLiveData;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Category>> getCategoryLiveData() {
        return categoryLiveData;
    }

    @Override
    protected void init() {
    }

    public void refresh() {
        mRepository.category.getCategories(App.getContext(), new CategoryDataSource.GetCategoriesCallback() {
            @Override
            public void onSuccess(List<Category> list) {
                categoryLiveData.postValue(list);
            }

            @Override
            public void onFailed(String errorMessage) {
                categoryLiveData.postValue(null);
            }
        });
    }
}
