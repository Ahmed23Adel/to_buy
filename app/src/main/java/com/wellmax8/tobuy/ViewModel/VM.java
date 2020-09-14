package com.wellmax8.tobuy.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.item;
import com.wellmax8.tobuy.ROOM.DAO;
import com.wellmax8.tobuy.ROOM.repo;

public class VM extends ViewModel  {

    protected repo repo;

    public VM(repo repo) {
        this.repo= repo;
    }



    public void insert(item iterm){
        repo.insert(iterm);
    }
    public void update(item item){
        repo.update(item);
    }
    public void delete(item item){
        repo.delete(item);
    }


    protected static Context context;
    public static void setContext(Context c) {
        context = c;
    }
}
