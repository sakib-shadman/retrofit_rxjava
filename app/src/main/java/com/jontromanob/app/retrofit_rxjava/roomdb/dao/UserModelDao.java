package com.jontromanob.app.retrofit_rxjava.roomdb.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.jontromanob.app.retrofit_rxjava.roomdb.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;


@Dao
public interface UserModelDao {

    @Query("select * from user")
    Flowable<List<User>> getAllUser();

    @Insert
    void addUser(User user);

    @Delete
    void deleteUser(User user);

    /*@Query ("delete from user_location where id>=:firstLimit and id<=:lastLimit")
    void deleteLocationData(Integer firstLimit, Integer lastLimit);*/
}
