package com.yuri.elearning.datasource.database.repository;

import android.os.AsyncTask;

import com.yuri.elearning.datasource.database.dao.UserDao;
import com.yuri.elearning.datasource.database.entity.User;

public class UserAsyncTasks {
    public static class insertUserTask extends AsyncTask<User, Void, Void> {
        private UserDao mUserDao;

        public insertUserTask(UserDao userDao) {
            mUserDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.insertUsers(users);
            return null;
        }
    }
}
