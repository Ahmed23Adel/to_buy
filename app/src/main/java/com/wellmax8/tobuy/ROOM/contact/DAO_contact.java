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
    Long insert(contact contact);


    @Insert()
    Long[] insertAll(contact ...contact);

    @Update()
    void update(contact contact);

    @Delete()
    void delete(contact contact);

    @Query("SELECT * FROM contact ORDER BY id DESC")
    LiveData<List<contact>> getAllOrderedIdDesc();

    @Query("SELECT * FROM contact WHERE phoneNumber=:phoneNumber")
    List<contact> getAtPhoneNumber(String phoneNumber);


}
