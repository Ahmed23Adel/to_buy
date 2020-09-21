package com.wellmax8.tobuy.ViewModel;

import android.content.Context;
import android.util.Log;

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

public class VM_solds extends ViewModel {
    private Context context;
    private repo_sold repo;
    private repo_contact repo_contact;
    private repo_shop repo_shop;
    private repo_shop_contact repo_shop_contact;

    public VM_solds() {
    }

    public void setContext(Context context) {
        this.context = context;
        repo = new repo_sold(context);
        repo_contact = new repo_contact(context);
        repo_shop = new repo_shop(context);
        repo_shop_contact = new repo_shop_contact(context);
    }

    public LiveData<List<sold>> getSoldsOrderedCreatedAtDESCWHEREIdCategory(int id_category) {
        return repo.getSoldsOrderedCreatedAtDESCWHEREIdCategory(id_category);
    }

    public void insertSold(sold sold) {
        repo.insert(sold);
    }

    public String getCurrentTime() {
        Long timeInSecond = System.currentTimeMillis();
        String timeInSecondeText = timeInSecond.toString();
        return timeInSecondeText;
    }

    public void insertContacts(ArrayList<contact> contactsList) {
        if (contactsList.size() > 0) {
            contact[] contacts = new contact[contactsList.size()];
            for (int i = 0; i < contactsList.size(); i++) {
                contacts[i] = contactsList.get(i);
            }
            repo_contact.insertAllContacts(contacts);
        }

    }

    public void insertShop(shop shop) {
        repo_shop.insert(shop);

    }

    public LiveData<List<contact>> getLastAddedContactsByLimit(int limit) {
        return repo_contact.getLastAddedByLimit(limit);
    }

    public LiveData<List<shop>> getLastAddedShop() {
        return repo_shop.getLastAdded();
    }

    public void insertShopContact(shop_contact shop_contact) {
        repo_shop_contact.insert(shop_contact);
    }

    public LiveData<List<sold>> testGetAllSolds(int id_category){
        return repo.getSoldsOrderedCreatedAtDESCWHEREIdCategory(id_category);
    }



}
