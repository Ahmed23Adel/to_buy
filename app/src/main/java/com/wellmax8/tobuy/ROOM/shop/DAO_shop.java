package com.wellmax8.tobuy.ROOM.shop;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wellmax8.tobuy.DTO.shop;

import java.util.List;

@Dao
public interface DAO_shop {

    @Insert
    void insert(shop shop);

    @Update
    void update(shop shop);

    @Delete
    void delete(shop shop);

    @Query("SELECT * FROM shop WHERE name = :nameOfShop")
    LiveData<List<shop>> getShopAtName(String nameOfShop);

    @Query("SELECT * FROM shop WHERE id = :id")
    LiveData<List<shop>> getShopAtID(int id);

}
