package com.wellmax8.tobuy.ROOM.sold;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wellmax8.tobuy.DTO.sold;
import com.wellmax8.tobuy.DTO.sold_at_shopId;

import java.util.List;

@Dao
public interface DAO_sold {
    @Insert
    void insert(sold sold);

    @Update
    void update(sold sold);

    @Query("UPDATE sold SET id_shop_=-1 WHERE id_shop_=:shopId")
    void updateShopIdAtShopID(int shopId);

    @Delete
    void delete(sold sold);

    @Query("SELECT * FROM sold WHERE id_category = :id_category ORDER BY created_at_sold DESC")
    LiveData<List<sold>> getSoldsOrderedCreatedAtDESCWHEREIdCategory(int id_category);

    @Query("SELECT sold.*, category.* From sold LEFT JOIN category ON sold.id_category=category.id WHERE sold.id_shop_=:id_shop")
    LiveData<List<sold_at_shopId>> getSoldsAtIdShop(int id_shop);
}
