package com.yuri.elearning.data;

import android.content.Context;

import com.yuri.elearning.data.database.AppDatabase;
import com.yuri.elearning.data.repository.CategoryRepository;
import com.yuri.elearning.data.repository.CourseRepository;
import com.yuri.elearning.data.repository.LessonRepository;
import com.yuri.elearning.data.repository.PurchaseRepository;
import com.yuri.elearning.data.repository.UserRepository;

public class Repository{
    public final CategoryRepository category;
    public final CourseRepository course;
    public final LessonRepository lesson;
    public final UserRepository user;
    public final PurchaseRepository purchase;

    public Repository(Context application) {
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        category = new CategoryRepository(appDatabase);
        course = new CourseRepository(appDatabase);
        lesson = new LessonRepository(appDatabase);
        user = new UserRepository(appDatabase);
        purchase = new PurchaseRepository(appDatabase);
    }
}
