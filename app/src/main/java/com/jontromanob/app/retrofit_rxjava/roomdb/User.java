package com.jontromanob.app.retrofit_rxjava.roomdb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Hi on 8/20/2019.
 */

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = "user_name")
    private String userName;
    @ColumnInfo(name = "user_age")
    private Integer userAge;
    @ColumnInfo(name = "user_address")
    private String userAddress;

    public User(String userName, Integer userAge, String userAddress) {
        this.userName = userName;
        this.userAge = userAge;
        this.userAddress = userAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
