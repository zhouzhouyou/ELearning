package com.yuri.elearning.datasource.database;

import android.content.Context;

import com.yuri.elearning.datasource.database.dao.CourseDao;
import com.yuri.elearning.datasource.database.dao.UserDao;
import com.yuri.elearning.datasource.database.entity.Course;
import com.yuri.elearning.datasource.database.entity.User;
import com.yuri.elearning.datasource.database.repository.CourseAsyncTasks;
import com.yuri.elearning.datasource.database.repository.UserAsyncTasks;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Course.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static RoomDatabase.Callback sCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new CourseAsyncTasks.populateCourseDbTask(INSTANCE).execute();
            new UserAsyncTasks.populateUserDbTask(INSTANCE).execute();
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();

    public abstract CourseDao courseDao();

}
