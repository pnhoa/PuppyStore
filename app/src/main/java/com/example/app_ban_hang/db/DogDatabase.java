package com.example.app_ban_hang.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.app_ban_hang.model.DogClass;

@Database(entities = DogClass.class  , version = 1)
public abstract class DogDatabase extends RoomDatabase {
    public abstract DogDao  dogDao();
    public static DogDatabase instance;
    public static DogDatabase getInstance(Context mContext){
        if(instance == null){
            instance = Room.databaseBuilder(mContext,
                    DogDatabase.class, "database_dog").build();
        }
        return instance;
    }
}
