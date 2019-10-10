package com.yuri.elearning.viewmodel;

import android.app.Application;

import com.yuri.elearning.util.base.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class RegistrationViewModel extends BaseViewModel {
    private MutableLiveData<RegistrationState> registrationState =
            new MutableLiveData<>(RegistrationState.COLLECT_PROFILE_DATA);
    // Simulation of real-world scenario, where an auth token may be provided as
    // an alternate authentication mechanism instead of passing the password
    // around. This is set at the end of the registration process.
    private String authToken;

    public RegistrationViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<RegistrationState> getRegistrationState() {
        return registrationState;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void collectProfileData(String firstName, String lastName) {
        // ... validate and store data

        // Change State to collecting username and password
        registrationState.setValue(RegistrationState.COLLECT_USER_PASSWORD);
    }

    public void createAccountAndLogin(int account, String password) {
        // ... create account
        // ... authenticate
        this.authToken = "";// token
        // Change State to registration completed
        registrationState.setValue(RegistrationState.REGISTRATION_COMPLETED);
    }

    public boolean userCancelledRegistration() {
        // Clear existing registration data
        registrationState.setValue(RegistrationState.COLLECT_PROFILE_DATA);
        authToken = "";
        return true;
    }

    @Override
    protected void init() {

    }

    public enum RegistrationState {
        COLLECT_PROFILE_DATA,
        COLLECT_USER_PASSWORD,
        REGISTRATION_COMPLETED
    }
}
