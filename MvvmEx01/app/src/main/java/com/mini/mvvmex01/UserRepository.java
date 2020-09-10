package com.mini.mvvmex01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserRepository {

    //select 할때만 쿼리적고 나머지 insert, delete, update 어노테이션 걸면된다.

    @Query("SELECT * FROM user")
    List<User> findAll();

    @Query(("SELECT * FROM user WHERE uid=:uid"))
    User findById(int uid);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
