package com.wellmax8.tobuy.ViewModel.shops;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.ROOM.shop.repo_shop;

import java.util.List;

public class VM_shops extends ViewModel {

    private Context context;
    private repo_shop  repo_shop;

    public VM_shops() {
    }

    public void setContext(Context context) {
        this.context = context;
        repo_shop=new repo_shop(context);
    }

    public LiveData<List<shop>> getShopList(){
        return repo_shop.getAll();
    }
}
