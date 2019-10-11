package com.jontromanob.app.retrofit_rxjava.roomdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.jontromanob.app.retrofit_rxjava.roomdb.dao.UserModelDao;


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract  class AppDatabase extends RoomDatabase {


    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"tracker")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract UserModelDao userModelDao();

}
