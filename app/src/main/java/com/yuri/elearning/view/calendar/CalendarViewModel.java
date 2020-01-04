package com.yuri.elearning.view.calendar;

import android.app.Application;

import com.yuri.elearning.data.datasource.LessonDataSource;
import com.yuri.elearning.data.repository.UserRepository;
import com.yuri.elearning.model.LessonBriefInfo;
import com.yuri.elearning.util.App;
import com.yuri.elearning.util.base.BaseViewModel;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class CalendarViewModel extends BaseViewModel {
    final MutableLiveData<List<LessonBriefInfo>> mLessons = new MutableLiveData<>();
    final MutableLiveData<String> message = new MutableLiveData<>();

    @Override
    protected void init() {

    }

    public CalendarViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        mRepository.lesson.getCalendar(App.getContext(), UserRepository.ID, year, month, new LessonDataSource.GetCalendarCallback() {
            @Override
            public void onSuccess(List<LessonBriefInfo> lessons) {
                mLessons.postValue(lessons);
            }

            @Override
            public void onFailed(String errorMessage) {
                message.postValue(errorMessage);
            }
        });
    }
}
