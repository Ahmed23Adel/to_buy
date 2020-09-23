package com.wellmax8.tobuy.ROOM.Sold_Large_Style;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.wellmax8.tobuy.DTO.sold_large_style;
import com.wellmax8.tobuy.ROOM.to_buy_db;

import java.util.List;

public class repo_sold_large_style {

    private to_buy_db db;
    private DAO_sold_large_style dao;

    public repo_sold_large_style(Context context) {
        db=to_buy_db.getInstance(context);
        dao=db.getDaoSoldLargeStyle();
    }

    public LiveData<List<sold_large_style>> getAll(){
        return dao.getAll();
    }

    public LiveData<List<sold_large_style>> getAtIdCategoryLargeStyleOrderLastEditDesc(int id_category){
        return dao.getAtIdCategoryLargeStyleOrderLastEditDesc(id_category);
    }
}
