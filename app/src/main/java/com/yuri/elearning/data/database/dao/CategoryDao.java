package com.yuri.elearning.data.database.dao;

import com.yuri.elearning.model.Category;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public abstract class CategoryDao {
    @Query("select * from category")
    public abstract List<Category> selectAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(Category... categories);
}
