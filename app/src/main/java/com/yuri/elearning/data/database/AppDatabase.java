package com.yuri.elearning.data.database;

import android.content.Context;

import com.yuri.elearning.data.database.dao.CategoryDao;
import com.yuri.elearning.data.database.dao.CourseBriefInfoDao;
import com.yuri.elearning.data.database.dao.CourseDao;
import com.yuri.elearning.data.database.dao.LessonDao;
import com.yuri.elearning.data.database.dao.PurchaseDao;
import com.yuri.elearning.data.database.dao.TypeDao;
import com.yuri.elearning.data.database.dao.UserDao;
import com.yuri.elearning.data.database.datasource.LocalPurchaseDataSource;
import com.yuri.elearning.model.Category;
import com.yuri.elearning.model.Course;
import com.yuri.elearning.model.CourseBriefInfo;
import com.yuri.elearning.model.Lesson;
import com.yuri.elearning.model.Purchase;
import com.yuri.elearning.model.Type;
import com.yuri.elearning.model.User;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Course.class, CourseBriefInfo.class, Lesson.class, Category.class, Type.class, Purchase.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static RoomDatabase.Callback sCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new LocalPurchaseDataSource(INSTANCE).deleteAll();
//            new CourseAsyncTasks.populateCourseDbTask(INSTANCE).execute();
//            new UserAsyncTasks.populateUserDbTask(INSTANCE).execute();
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
                            .allowMainThreadQueries()
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

    public abstract CourseBriefInfoDao courseBasicMessageDao();

    public abstract LessonDao lessonDao();

    public abstract CategoryDao categoryDao();

    public abstract TypeDao typeDao();

    public abstract PurchaseDao purchaseDao();
}
