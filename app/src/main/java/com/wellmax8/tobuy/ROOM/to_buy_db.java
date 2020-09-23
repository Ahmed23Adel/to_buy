package com.wellmax8.tobuy.ROOM;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.DTO.shop_contact;
import com.wellmax8.tobuy.DTO.sold;
import com.wellmax8.tobuy.ROOM.Sold_Large_Style.DAO_sold_large_style;
import com.wellmax8.tobuy.ROOM.category.DAO_category;
import com.wellmax8.tobuy.ROOM.contact.DAO_contact;
import com.wellmax8.tobuy.ROOM.shop.DAO_shop;
import com.wellmax8.tobuy.ROOM.shop_contact.DAO_shop_contact;
import com.wellmax8.tobuy.ROOM.sold.DAO_sold;

@Database(entities = {category.class, contact.class, shop.class, sold.class, shop_contact.class},version = 1,exportSchema = false)
public abstract class to_buy_db extends RoomDatabase {
    private volatile static to_buy_db toBuyDbInstance;

    public abstract DAO_category getDaoCategory();
    public abstract DAO_sold getDaoSold();
    public abstract DAO_shop getDaoShop();
    public abstract DAO_contact getDaoContact();
    public abstract DAO_shop_contact getDaoShopContact();
    public abstract DAO_sold_large_style getDaoSoldLargeStyle();


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
