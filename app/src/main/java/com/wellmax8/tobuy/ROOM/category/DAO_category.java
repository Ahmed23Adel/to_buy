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
    void insert(category item);

    @Update
    void update(category item);

    @Delete
    void delete(category item);

    @Query("SELECT * from category ORDER BY created_at_category DESC")
    LiveData<List<category>> getCategoriesOrderedCreatedAtDESC();

    @Query("SELECT * FROM category WHERE name_category = :name AND related_to = :relatedTo")
    LiveData<List<category>> getCategoriesByNameAndRelatedTo(String name,String relatedTo);


}
