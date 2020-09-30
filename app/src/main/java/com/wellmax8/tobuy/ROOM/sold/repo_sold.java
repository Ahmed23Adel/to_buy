package com.wellmax8.tobuy.ROOM.sold;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.wellmax8.tobuy.DTO.sold;
import com.wellmax8.tobuy.DTO.sold_at_shopId;
import com.wellmax8.tobuy.ROOM.to_buy_db;

import java.util.List;

public class repo_sold {

    private to_buy_db db;
    private DAO_sold dao;

    public repo_sold(Context context) {
        db=to_buy_db.getInstance(context);
        dao=db.getDaoSold();
    }

    public void insert(sold sold){
        new insertSold(dao).execute(sold);
    }
    public void update(sold sold){
        new updateSold(dao).execute(sold);
    }
    public void delete(sold sold){
        new deleteSold(dao).execute(sold);
    }

    public LiveData<List<sold>> getSoldsOrderedCreatedAtDESCWHEREIdCategory(int id_category){
        return dao.getSoldsOrderedCreatedAtDESCWHEREIdCategory(id_category);
    }
    public LiveData<List<sold_at_shopId>> getSoldsAtIdShop(int id_shop){
        return dao.getSoldsAtIdShop(id_shop);
    }

    private static class insertSold extends AsyncTask<sold,Void,Void> {
        public final DAO_sold dao;

        public insertSold(DAO_sold dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(sold... solds) {
            dao.insert(solds[0]);
            return null;
        }
    }

    private static class updateSold extends AsyncTask<sold,Void,Void>{
        public final DAO_sold dao;

        public updateSold(DAO_sold dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(sold... solds) {
            dao.update(solds[0]);
            return null;
        }
    }

    private static class deleteSold extends AsyncTask<sold,Void,Void>{
        public final DAO_sold dao;

        public deleteSold(DAO_sold dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(sold... solds) {
            dao.delete(solds[0]);
            return null;
        }
    }
}
