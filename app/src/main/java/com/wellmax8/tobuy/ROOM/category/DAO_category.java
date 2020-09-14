package com.wellmax8.tobuy.ROOM.category;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.ROOM.DAO;

import java.util.List;

@Dao
public interface DAO_category extends DAO {

    @Query("SELECT * from category ORDER BY created_at DESC")
    LiveData<List<category>> getCategoriesOrderedCreatedAtDESC();


}
