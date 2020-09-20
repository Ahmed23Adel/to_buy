package com.wellmax8.tobuy.ROOM.contact;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wellmax8.tobuy.DTO.contact;

import java.util.List;

@Dao
public interface DAO_contact {
    @Insert
    void insert(contact contact);

    @Update()
    void update(contact contact);

    @Delete()
    void delete(contact contact);


    @Query("SELECT * FROM contact ORDER BY id DESC LIMIT :limit")
    LiveData<List<contact>> getLastAddedByLimit(int limit);

}
