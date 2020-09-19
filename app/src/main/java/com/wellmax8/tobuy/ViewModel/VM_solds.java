package com.wellmax8.tobuy.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.sold;
import com.wellmax8.tobuy.ROOM.sold.repo_sold;

import java.util.List;

public class VM_solds extends ViewModel {
    private Context context;
    private repo_sold repo;

    public VM_solds() {
    }

    public void setContext(Context context) {
        this.context = context;
        repo=new repo_sold(context);
    }

    public LiveData<List<sold>> getSoldsOrderedCreatedAtDESCWHEREIdCategory(int id_category){
        return repo.getSoldsOrderedCreatedAtDESCWHEREIdCategory(id_category);
    }
}
