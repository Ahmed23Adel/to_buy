package com.wellmax8.tobuy.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.ROOM.shop.repo_shop;
import com.wellmax8.tobuy.ROOM.sold.repo_sold;

import java.util.List;

public class VM_shop_details extends ViewModel {

    private Context context;
    private repo_shop repo_shop;
    private repo_sold repo_sold;

    public VM_shop_details() {
    }

    public void setContext(Context context) {
        this.context = context;
        repo_sold=new repo_sold(context);
        repo_shop=new repo_shop(context);
    }

    public LiveData<List<shop>> getShopAtId(int id_shp){
        return repo_shop.getShopAtID(id_shp);
    }




}
