package com.wellmax8.tobuy.ViewModel.add;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.DTO.shop_contact;
import com.wellmax8.tobuy.DTO.sold;
import com.wellmax8.tobuy.ROOM.contact.repo_contact;
import com.wellmax8.tobuy.ROOM.shop.repo_shop;
import com.wellmax8.tobuy.ROOM.shop_contact.repo_shop_contact;
import com.wellmax8.tobuy.ROOM.sold.repo_sold;

import java.util.ArrayList;
import java.util.List;

public class VM_add_sold extends ViewModel {
    private Context context;
    private repo_sold repo;
    private repo_contact repo_contact;
    private repo_shop repo_shop;
    private repo_shop_contact repo_shop_contact;

    public VM_add_sold() {
    }

    public void setContext(Context context) {
        this.context = context;
        repo = new repo_sold(context);
        repo_contact = new repo_contact(context);
        repo_shop = new repo_shop(context);
        repo_shop_contact = new repo_shop_contact(context);
    }

    public List<shop> isNameShopDuplicated(String name){
        return repo_shop.getShopAtName(name);
    }


    public Long insertShop(shop shop){
        return repo_shop.insert(shop);
    }

    public void insertShop_contacts(shop_contact shop_contact){
        repo_shop_contact.insert(shop_contact);
    }

    public void insertSold(sold sold){
        repo.insert(sold);
    }

        public String getCurrentTime() {
        Long timeInSecond = System.currentTimeMillis();
        String timeInSecondeText = timeInSecond.toString();
        return timeInSecondeText;
    }

    public LiveData<List<shop>> testGetAllShop(){
        return repo_shop.getAll();
    }

}
