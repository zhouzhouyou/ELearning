package com.yuri.elearning.view.course;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.yuri.elearning.MobileNavigationDirections;
import com.yuri.elearning.data.datasource.CourseDataSource;
import com.yuri.elearning.data.datasource.PurchaseDataSource;
import com.yuri.elearning.data.datasource.UserDataSource;
import com.yuri.elearning.data.repository.UserRepository;
import com.yuri.elearning.model.Course;
import com.yuri.elearning.model.CourseDetailInfo;
import com.yuri.elearning.util.App;
import com.yuri.elearning.util.base.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class CourseViewModel extends BaseViewModel {
    private MutableLiveData<CourseDetailInfo> courseDetail = new MutableLiveData<>();
    private MutableLiveData<Integer> needBuy = new MutableLiveData<>();
    private MutableLiveData<Boolean> bought = new MutableLiveData<>();
    private MutableLiveData<Integer> needLogin = new MutableLiveData<>();
    private MutableLiveData<Integer> noMoney = new MutableLiveData<>();

    public MutableLiveData<Boolean> getBought() {
        return bought;
    }

    public MutableLiveData<Integer> getNeedLogin() {
        return needLogin;
    }

    public MutableLiveData<Integer> getNoMoney() {
        return noMoney;
    }

    public MutableLiveData<Integer> getNeedBuy() {
        return needBuy;
    }

    public CourseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void init() {

    }

    public MutableLiveData<CourseDetailInfo> getCourseDetail() {
        return courseDetail;
    }

    public void jumpToLesson(View view, int id) {
        if (bought.getValue() != null && bought.getValue()) {
            NavController navController = Navigation.findNavController(view);
            MobileNavigationDirections.ActionGlobalNavLesson action = MobileNavigationDirections.actionGlobalNavLesson(id);
            navController.navigate(action);
        } else {
            if (needBuy.getValue() == null) needBuy.postValue(0);
            else needBuy.postValue(needBuy.getValue() + 1);
        }
    }

    public void buy() {
        Log.d(TAG, "buy: ");
        if (UserRepository.ID == null) {
            if (needLogin.getValue() == null) needLogin.postValue(0);
            else needLogin.postValue(needLogin.getValue() + 1);
            return;
        }

        Course course = courseDetail.getValue().course;
        if (UserRepository.MONEY < course.cost) {
            if (noMoney.getValue() == null) noMoney.postValue(0);
            else noMoney.postValue(noMoney.getValue() + 1);
            return;
        }
        mRepository.purchase.purchase(App.getContext(), new PurchaseDataSource.PurchaseCallback() {
            @Override
            public void onSuccess(Double aDouble) {
                bought.setValue(true);
            }

            @Override
            public void onFailed(String errorMessage) {
                if (noMoney.getValue() == null) noMoney.postValue(0);
                else noMoney.postValue(noMoney.getValue() + 1);
            }
        }, course.id, UserRepository.ID);
    }

    public void setCourseDetail(int cid) {
        mRepository.course.getCourseDetail(App.getContext(), new CourseDataSource.GetCourseDetailCallback() {
            @Override
            public void onSuccess(CourseDetailInfo courseDetailInfo) {
                Log.d(TAG, "onSuccess: " + courseDetailInfo);
                courseDetail.postValue(courseDetailInfo);
            }

            @Override
            public void onFailed(String errorMessage) {
            }
        }, cid);
        if (UserRepository.ID != null) {
            mRepository.user.didIBought(App.getContext(), new UserDataSource.DidIBoughtCallback() {
                @Override
                public void onSuccess(Boolean aBoolean) {
                    bought.postValue(aBoolean);
                }

                @Override
                public void onFailed(String errorMessage) {

                }
            }, UserRepository.ID, cid);
        }
    }
}
