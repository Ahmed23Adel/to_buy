package com.wellmax8.tobuy.ROOM.shop;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.DTO.shop_contact;
import com.wellmax8.tobuy.ROOM.to_buy_db;

import java.util.List;

public class repo_shop {

    private to_buy_db db;
    private DAO_shop dao;

    public repo_shop(Context context) {
        db=to_buy_db.getInstance(context);
        dao=db.getDaoShop();
    }

    public void insert(shop shop){
        new insertShop(dao).execute(shop);
    }
    public void update(shop shop){
        new updateShop(dao).execute(shop);
    }

    public void deleted(shop shop){
        new deleteShop(dao).execute(shop);
    }

    public LiveData<List<shop>> getLastAdded(){
        return dao.getLastAdded();
    }
    public LiveData<List<shop>> getShopAtName(String name){
        return dao.getShopAtName(name);
    }
    public LiveData<List<shop>> getShopAtID(int  id){
        return dao.getShopAtID(id);
    }

    private static class insertShop extends AsyncTask<shop,Void,Void> {
        public final DAO_shop dao;

        public insertShop(DAO_shop dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(shop... shops) {
            dao.insert(shops[0]);
            return null;
        }
    }

    private static class updateShop extends AsyncTask<shop,Void,Void>{
        public final DAO_shop dao;

        public updateShop(DAO_shop dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(shop... shops) {
            dao.update(shops[0]);
            return null;
        }
    }

    private static class deleteShop extends AsyncTask<shop,Void,Void>{
        public final DAO_shop dao;

        public deleteShop(DAO_shop dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(shop... shops) {
            dao.delete(shops[0]);
            return null;
        }
    }
}
