package com.yuri.elearning.view.category;

import android.app.Application;

import com.yuri.elearning.data.datasource.CourseDataSource;
import com.yuri.elearning.model.CourseBriefInfo;
import com.yuri.elearning.util.App;
import com.yuri.elearning.util.base.BaseViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class CategoryDetailViewModel extends BaseViewModel {
    private MutableLiveData<List<CourseBriefInfo>> courseLiveData = new MutableLiveData<>();

    public CategoryDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<CourseBriefInfo>> getCourseLiveData() {
        return courseLiveData;
    }

    @Override
    protected void init() {
    }

    public void refresh(int id) {
        mRepository.course.getCourseByCategory(App.getContext(), new CourseDataSource.GetCourseByCategoryCallback() {
            @Override
            public void onSuccess(List<CourseBriefInfo> list) {
                courseLiveData.postValue(list);
            }

            @Override
            public void onFailed(String errorMessage) {
                courseLiveData.postValue(null);
            }
        }, id);
    }
}
