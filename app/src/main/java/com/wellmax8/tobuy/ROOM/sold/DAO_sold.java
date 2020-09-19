package com.wellmax8.tobuy.ROOM.sold;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wellmax8.tobuy.DTO.sold;

import java.util.List;

@Dao
public interface DAO_sold {
    @Insert
    void insert(sold sold);

    @Update
    void update(sold sold);

    @Delete
    void delete(sold sold);

    @Query("SELECT * FROM sold WHERE id_category = :id_category ORDER BY created_at DESC")
    LiveData<List<sold>> getSoldsOrderedCreatedAtDESCWHEREIdCategory(int id_category);
}
