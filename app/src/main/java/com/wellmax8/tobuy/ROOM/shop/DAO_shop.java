package com.wellmax8.tobuy.ROOM.shop;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.DTO.shop_contact;

import java.util.List;

@Dao
public interface DAO_shop {

    @Insert
    Long insert(shop shop);

    @Update
    void update(shop shop);

    @Delete
    void delete(shop shop);

    @Query("SELECT * FROM shop WHERE name = :nameOfShop")
    List<shop> getShopAtName(String nameOfShop);

    @Query("SELECT * FROM shop WHERE id = :id")
    LiveData<List<shop>> getShopAtID(int id);

    @Query("SELECT * FROM shop")
    LiveData<List<shop>> getAll();



}
