package com.yuri.elearning.view.lesson;

import android.app.Application;
import android.util.Log;

import com.yuri.elearning.data.datasource.LessonDataSource;
import com.yuri.elearning.model.Lesson;
import com.yuri.elearning.util.App;
import com.yuri.elearning.util.base.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class LessonViewModel extends BaseViewModel {
    private MutableLiveData<Lesson> mLesson = new MutableLiveData<>();

    public LessonViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Lesson> getLesson() {
        return mLesson;
    }

    @Override
    protected void init() {

    }

    public void setLesson(int id) {
        mRepository.lesson.getLesson(App.getContext(), new LessonDataSource.GetLessonCallback() {
            @Override
            public void onSuccess(Lesson lesson) {
                Log.d(TAG, "onSuccess: " + lesson);
                mLesson.postValue(lesson);
            }

            @Override
            public void onFailed(String errorMessage) {

            }
        }, id);
    }
}
