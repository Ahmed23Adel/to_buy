package com.wellmax8.tobuy.ROOM;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.item;
import com.wellmax8.tobuy.ROOM.category.DAO_category;

@Database(entities = {item.class,category.class},version = 1,exportSchema = false)
public abstract class to_buy_db extends RoomDatabase {
    private volatile static to_buy_db toBuyDbInstance;
    public abstract DAO_category getDaoCategory();


    public static to_buy_db getInstance(Context context){
        if (toBuyDbInstance ==null){
            synchronized (to_buy_db.class){
                if (toBuyDbInstance==null){
                    toBuyDbInstance= Room.databaseBuilder(context,to_buy_db.class,"to_buy")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return toBuyDbInstance;
    }


}
