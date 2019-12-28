package com.yuri.elearning.view.authentication;

import android.app.Application;

import com.yuri.elearning.data.datasource.UserDataSource;
import com.yuri.elearning.util.App;
import com.yuri.elearning.util.base.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class RegistrationViewModel extends BaseViewModel {
    private MutableLiveData<RegistrationState> registrationState =
            new MutableLiveData<>(RegistrationState.COLLECT_USER_PASSWORD);

    public RegistrationViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<RegistrationState> getRegistrationState() {
        return registrationState;
    }

    public void createAccountAndLogin(String account, String password) {
        mRepository.user.signUp(App.getContext(), new UserDataSource.SignUpCallback() {
            @Override
            public void onSuccess(Integer integer) {
                registrationState.postValue(RegistrationState.REGISTRATION_COMPLETED);
            }

            @Override
            public void onFailed(String errorMessage) {
                registrationState.postValue(RegistrationState.REGISTRATION_FAIL);
            }
        }, account, password);
    }

    @Override
    protected void init() {

    }

    public enum RegistrationState {
        COLLECT_USER_PASSWORD,
        REGISTRATION_COMPLETED,
        REGISTRATION_FAIL
    }
}
