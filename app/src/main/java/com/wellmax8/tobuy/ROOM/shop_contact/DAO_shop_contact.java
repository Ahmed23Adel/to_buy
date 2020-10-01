package com.wellmax8.tobuy.ROOM.shop_contact;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.wellmax8.tobuy.DTO.shop_contact;

import java.util.List;

@Dao
public interface DAO_shop_contact {

    @Insert
    void insert(shop_contact shop_contact);

    @Insert
    Long[] insertAll(shop_contact... shop_contacts);

    @Update
    void update(shop_contact shop_contact);

    @Delete
    void delete(shop_contact shop_contact);

    @Query("DELETE FROM shop_contact WHERE id_shop=:id_shop")
    void deleteAtIdShop(int  id_shop);

    @Query("SELECT * FROM shop_contact WHERE id_shop= :id_shop")
    LiveData<List<shop_contact>> getAllAtIDShop(int id_shop);

    @Query("SELECT * FROM shop_contact WHERE id_contact= :id_contact")
    LiveData<List<shop_contact>> getAllAtIDContact(int id_contact);


}
