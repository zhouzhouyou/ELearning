package com.yuri.elearning.data.database.datasource;

import com.yuri.elearning.data.database.AppDatabase;

public abstract class AbstractLocalDataSource {
    protected final String TAG = getClass().getSimpleName();
    protected final AppDatabase mAppDatabase;

    public AbstractLocalDataSource(AppDatabase appDatabase) {
        mAppDatabase = appDatabase;
    }
}
