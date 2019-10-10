package com.yuri.elearning.viewmodel;

import android.app.Application;

import com.yuri.elearning.util.base.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends BaseViewModel {
    public MutableLiveData<AuthenticationState> authenticationState;
    public int account;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void authenticate(String authToken) {
        //TODO judge if true
        authenticationState.setValue(AuthenticationState.AUTHENTICATED);
    }

    public void authenticate(int account, String password) {
        if (passwordIsValidateForUsername(account, password)) {
            this.account = account;
            authenticationState.setValue(AuthenticationState.AUTHENTICATED);
        } else {
            authenticationState.setValue(AuthenticationState.INVALID_AUTHENTICATION);
        }
    }

    private boolean passwordIsValidateForUsername(int account, String password) {
        return true;
    }

    public void refuseAuthentication() {
        authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
    }

    @Override
    protected void init() {
        authenticationState = new MutableLiveData<>();
        authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
        account = 0;
    }

    public enum AuthenticationState {
        UNAUTHENTICATED,
        AUTHENTICATED,
        INVALID_AUTHENTICATION
    }
}
