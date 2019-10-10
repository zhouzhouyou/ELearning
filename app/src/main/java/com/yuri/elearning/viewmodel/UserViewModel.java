package com.yuri.elearning.viewmodel;

import android.app.Application;

import com.yuri.elearning.datasource.database.entity.User;
import com.yuri.elearning.util.base.BaseViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class UserViewModel extends BaseViewModel {
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void init() {
        allUsers = mRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User... users) {
        mRepository.insertUser(users);
    }
}
