package com.yuri.elearning.datasource.database.dao;

import com.yuri.elearning.datasource.database.entity.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public abstract class UserDao {
    @Query("select * from user where uid == :id ")
    public abstract User queryUser(Integer id);

    @Query("select * from user")
    public abstract LiveData<List<User>> queryAllUser();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insertUsers(User... users);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public abstract int updateUsers(User... users);

    @Query("delete from user")
    public abstract void deleteAll();
}
