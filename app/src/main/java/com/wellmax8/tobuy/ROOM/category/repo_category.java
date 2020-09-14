package com.wellmax8.tobuy.ROOM.category;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.item;
import com.wellmax8.tobuy.ROOM.DAO;
import com.wellmax8.tobuy.ROOM.repo;
import com.wellmax8.tobuy.ROOM.to_buy_db;

import java.util.List;

public class repo_category extends repo {

    public repo_category(Context context) {
        super(to_buy_db.getInstance(context).getDaoCategory());
    }

    public LiveData<List<category>> getCategoriesOrderedCreatedAtDESC() throws ClassCastException{
        if (dao instanceof DAO_category) {
            return ((DAO_category) dao).getCategoriesOrderedCreatedAtDESC();
        }else{
            throw new ClassCastException("dao should be an instance fo dao_cateogory");
        }
    }








}
