package com.yuri.elearning.util.base;

import android.app.Application;
import android.util.Log;

import com.yuri.elearning.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public abstract class BaseViewModel extends AndroidViewModel {
    protected final String TAG = getClass().getSimpleName();
    protected Repository mRepository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        Log.i(TAG, "BaseViewModel: init repository");
        mRepository = new Repository(application);
        Log.i(TAG, "BaseViewModel: init");
        init();
    }

    protected abstract void init();
}
