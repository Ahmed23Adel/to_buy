package com.wellmax8.tobuy.ROOM.category;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wellmax8.tobuy.DTO.category;

import java.util.List;

@Dao
public interface DAO_category {
    
    @Insert
    void insertCategory(category category);

    @Update
    void updateCategory(category category);

    @Delete
    void deleteCategory(category category);

    @Query("SELECT * from category ORDER BY created_at DESC")
    LiveData<List<category>> getCategoriesOrderedCreatedAtDESC();


}
