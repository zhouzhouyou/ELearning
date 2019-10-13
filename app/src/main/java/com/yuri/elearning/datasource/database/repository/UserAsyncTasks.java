package com.yuri.elearning.datasource.database.repository;

import android.os.AsyncTask;

import com.yuri.elearning.datasource.database.AppDatabase;
import com.yuri.elearning.datasource.database.dao.UserDao;
import com.yuri.elearning.datasource.database.entity.User;

import java.util.Date;

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

    public static class queryUserTask extends AsyncTask<Integer, Void, User> {
        private UserDao mUserDao;

        public queryUserTask(UserDao userDao) {
            mUserDao = userDao;
        }

        @Override
        protected User doInBackground(Integer... integers) {
            return mUserDao.queryUser(integers[0]);
        }
    }

    public static class populateUserDbTask extends AsyncTask<Void, Void, Void> {
        private final UserDao mUserDao;

        public populateUserDbTask(AppDatabase database) {
            mUserDao = database.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mUserDao.deleteAll();
            for (int i = 0; i < 10; i++) {
                User user = new User(i, "f#" + i, "l#" + i, String.valueOf(i), "desc#" + i, new Date());
                mUserDao.insertUsers(user);
            }
            return null;
        }
    }
}
