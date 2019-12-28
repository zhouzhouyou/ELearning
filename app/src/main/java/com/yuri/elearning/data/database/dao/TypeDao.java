package com.yuri.elearning.data.database.dao;

import com.yuri.elearning.model.Type;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public abstract class TypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(Type... types);

    @Query("select course from type where category == :category")
    public abstract List<Integer> selectByCategory(Integer category);
}
