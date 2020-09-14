package com.wellmax8.tobuy.ROOM;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Update;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.item;
@Dao
public interface DAO {

    @Insert
    void insert(item item);

    @Update
    void update(item item);

    @Delete
    void delete(item item);
}
