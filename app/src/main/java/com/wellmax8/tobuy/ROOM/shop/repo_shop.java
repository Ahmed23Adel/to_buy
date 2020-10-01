package com.wellmax8.tobuy.ROOM.shop;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.DTO.shop_contact;
import com.wellmax8.tobuy.ROOM.to_buy_db;
import com.wellmax8.tobuy.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class repo_shop {

    private to_buy_db db;
    private DAO_shop dao;

    public repo_shop(Context context) {
        db=to_buy_db.getInstance(context);
        dao=db.getDaoShop();
    }

    public Long insert(shop shop){
        try {
            return new insertShop(dao).execute(shop).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return constants.shop.SHOP_NOT_INSERTED;
    }
    public void update(shop shop){
        new updateShop(dao).execute(shop);
    }

    public void delete(shop shop){
        new deleteShop(dao).execute(shop);
    }

    public void deleteAtId(int  shop_id){
        new deleteShopAtId(dao).execute(shop_id);
    }

    public List<shop> getShopAtName(String name){
        try {
            return new isNameShopDuplicated(dao).execute(name).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<shop>();
    }
    public LiveData<List<shop>> getShopAtID(int  id){
        return dao.getShopAtID(id);
    }

    public LiveData<List<shop>> getAll(){
        return dao.getAll();
    }

    private static class insertShop extends AsyncTask<shop,Void,Long> {
        public final DAO_shop dao;

        public insertShop(DAO_shop dao) {

            this.dao = dao;
        }

        @Override
        protected Long doInBackground(shop... shops) {
            return dao.insert(shops[0]);
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

    private static class deleteShopAtId extends AsyncTask<Integer,Void,Void>{
        public final DAO_shop dao;

        public deleteShopAtId(DAO_shop dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Integer... ids) {
            dao.deleteAtId(ids[0]);
            return null;
        }
    }

    private static class isNameShopDuplicated extends AsyncTask<String,Void,List<shop>>{
        public final DAO_shop dao;

        public isNameShopDuplicated(DAO_shop dao) {
            this.dao = dao;
        }

        @Override
        protected List<shop> doInBackground(String... names) {
            return dao.getShopAtName(names[0]);
        }
    }
}
