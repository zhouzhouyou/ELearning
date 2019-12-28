package com.yuri.elearning.data.database.dao;

import com.yuri.elearning.model.Course;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public abstract class CourseDao {
    @Query("select * from course where id == :id")
    public abstract Course queryCourse(Integer id);

    @Query("select * from course")
    public abstract LiveData<List<Course>> queryAllCourses();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertCourses(Course... courses);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public abstract int updateCourses(Course... courses);

    @Query("delete from course")
    public abstract void deleteAll();
}
