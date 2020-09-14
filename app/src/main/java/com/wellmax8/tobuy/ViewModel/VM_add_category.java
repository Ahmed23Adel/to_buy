package com.wellmax8.tobuy.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.ROOM.category.DAO_category;
import com.wellmax8.tobuy.ROOM.category.repo_category;
import com.wellmax8.tobuy.ROOM.repo;
import com.wellmax8.tobuy.ROOM.to_buy_db;

import java.util.List;

public class VM_add_category extends VM {


    public VM_add_category() {
        super(new repo_category(context));

    }

    public LiveData<List<category>> getCategoriesOrderedCreatedAtDESC() {
       return ((repo_category)repo).getCategoriesOrderedCreatedAtDESC();
    }
}
