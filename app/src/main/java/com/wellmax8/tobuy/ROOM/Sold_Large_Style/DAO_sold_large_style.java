package com.wellmax8.tobuy.ROOM.Sold_Large_Style;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.wellmax8.tobuy.DTO.sold_large_style;

import java.util.List;

@Dao
public interface DAO_sold_large_style {

    @Query("SELECT sold.*,shop.* FROM sold INNER JOIN shop ON sold.id_shop_=shop.id_shop ")
    LiveData<List<sold_large_style>> getAll();


    @Query("SELECT sold.*,shop.* FROM sold LEFT JOIN shop ON sold.id_shop_=shop.id_shop WHERE sold.id_category=:id_category ORDER BY last_edit DESC")
    LiveData<List<sold_large_style>> getAtIdCategoryLargeStyleOrderLastEditDesc(int id_category);


}
