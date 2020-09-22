package com.wellmax8.tobuy.ROOM.shop_contact;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.DTO.shop_contact;
import com.wellmax8.tobuy.ROOM.shop.DAO_shop;
import com.wellmax8.tobuy.ROOM.shop.repo_shop;
import com.wellmax8.tobuy.ROOM.to_buy_db;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class repo_shop_contact {
    private to_buy_db db;
    private DAO_shop_contact dao;

    public repo_shop_contact(Context context) {
        db=to_buy_db.getInstance(context);
        dao=db.getDaoShopContact();
    }

    public void insert(shop_contact shop){
        new insertShop_contact(dao).execute(shop);
    }

    public void insertAll(shop_contact... shop_contacts){
        try {
            new insertAll_Shop_contact(dao).execute(shop_contacts).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(shop_contact shop){
        new updateShop_contact(dao).execute(shop);
    }

    public void deleted(shop_contact shop){
        new deleteShop_contact(dao).execute(shop);
    }



    private static class insertShop_contact extends AsyncTask<shop_contact,Void,Void> {
        public final DAO_shop_contact dao;

        public insertShop_contact(DAO_shop_contact dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(shop_contact... shop_contacts) {
            dao.insert(shop_contacts[0]);
            return null;
        }
    }

    private static class insertAll_Shop_contact extends AsyncTask<shop_contact,Void,Long[]> {
        public final DAO_shop_contact dao;

        public insertAll_Shop_contact(DAO_shop_contact dao) {
            this.dao = dao;
        }

        @Override
        protected Long[] doInBackground(shop_contact... shop_contacts) {
            return dao.insertAll(shop_contacts);
        }
    }

    private static class updateShop_contact extends AsyncTask<shop_contact,Void,Void>{
        public final DAO_shop_contact dao;

        public updateShop_contact(DAO_shop_contact dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(shop_contact... shop_contacts) {
            dao.update(shop_contacts[0]);
            return null;
        }
    }

    private static class deleteShop_contact extends AsyncTask<shop_contact,Void,Void>{
        public final DAO_shop_contact dao;

        public deleteShop_contact(DAO_shop_contact dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(shop_contact... shop_contacts) {
            dao.delete(shop_contacts[0]);
            return null;
        }
    }
}
