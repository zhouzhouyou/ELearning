package com.yuri.elearning.view.myCourse;

import android.app.Application;

import com.yuri.elearning.data.datasource.CourseDataSource;
import com.yuri.elearning.data.repository.UserRepository;
import com.yuri.elearning.model.CourseBriefInfo;
import com.yuri.elearning.util.App;
import com.yuri.elearning.util.base.BaseViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class MyCourseViewModel extends BaseViewModel {
    private MutableLiveData<List<CourseBriefInfo>> courseLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> login = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLogin() {
        return login;
    }

    public MyCourseViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<CourseBriefInfo>> getCourseLiveData() {
        return courseLiveData;
    }

    @Override
    protected void init() {

    }

    public void refresh() {
        Integer uid = UserRepository.ID;
        if (uid == null) {
            login.postValue(false);
            return;
        } else login.postValue(true);
        mRepository.course.getMyCourse(App.getContext(), new CourseDataSource.GetMyCoursesCallback() {
            @Override
            public void onSuccess(List<CourseBriefInfo> courseBriefInfos) {
                courseLiveData.postValue(courseBriefInfos);
            }

            @Override
            public void onFailed(String errorMessage) {

            }
        }, uid);

    }
}
