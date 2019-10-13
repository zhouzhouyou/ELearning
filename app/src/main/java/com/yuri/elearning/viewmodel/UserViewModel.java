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

    public User queryUser(Integer uid) {
        if (allUsers != null && allUsers.getValue() != null) {
            for (User user : allUsers.getValue()) {
                if (user.uid.equals(uid)) return user;
            }
        }
        return mRepository.queryUser(uid);
    }

    public void insert(User... users) {
        mRepository.insertUser(users);
    }
}
