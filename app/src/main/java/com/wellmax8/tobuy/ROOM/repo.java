package com.wellmax8.tobuy.ROOM;

import android.os.AsyncTask;
import android.util.Log;

import com.wellmax8.tobuy.DTO.item;

public abstract class repo {
    protected DAO dao;

    public repo(DAO dao) {
        this.dao = dao;
    }

    public void insert(item item){
        Log.v("main","1");
        new insertCategory<>(dao).execute(item);
    }
    public void update(item item){
        new updateCategory<>(dao).execute(item);

    };
    public void delete(item item){
        new deleteCategory<>(dao).execute(item);
    }

    protected static class insertCategory <T extends item>  extends AsyncTask<T,Void,Void> {
        public final DAO dao;

        public insertCategory(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(T... ts) {
            Log.v("main","2");

            dao.insert(ts[0]);
            return null;
        }
    }

    private static class updateCategory<T extends item> extends AsyncTask<T,Void,Void>{
        public final DAO dao;

        public updateCategory(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(T... ts) {
            dao.update(ts[0]);
            return null;
        }
    }
    private static class deleteCategory<T extends item> extends AsyncTask<T,Void,Void>{
        public final DAO dao;

        public deleteCategory(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(T... ts) {
            dao.delete(ts[0]);
            return null;
        }
    }
}
