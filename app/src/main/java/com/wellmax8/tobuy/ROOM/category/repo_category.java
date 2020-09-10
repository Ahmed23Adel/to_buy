package com.wellmax8.tobuy.ROOM.category;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.ROOM.category.DAO_category;
import com.wellmax8.tobuy.ROOM.to_buy_db;

import java.util.List;

public class repo_category {
    private com.wellmax8.tobuy.ROOM.to_buy_db to_buy_db;
    private DAO_category dao_category;

    public repo_category(Context context) {
        to_buy_db= to_buy_db.getInstance(context);
        dao_category=to_buy_db.getDaoCategory();
    }

    public void insertCategory(category category){
        new insertCategory(dao_category).execute(category);
    }
    public void updateCategory(category category){
        new updateCategory(dao_category).execute(category);
    }
    public void deleteCategory(category category){
        new deleteCategory(dao_category).execute(category);
    }

    public LiveData<List<category>> getCategoriesOrderedCreatedAtDESC(){
        return dao_category.getCategoriesOrderedCreatedAtDESC();
    }


    private static class insertCategory extends AsyncTask<category,Void,Void>{
        public final DAO_category dao;

        public insertCategory(DAO_category dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(category... categories) {
            dao.insertCategory(categories[0]);
            return null;
        }
    }

    private static class updateCategory extends AsyncTask<category,Void,Void>{
        public final DAO_category dao;

        public updateCategory(DAO_category dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(category... categories) {
            dao.updateCategory(categories[0]);
            return null;
        }
    }

    private static class deleteCategory extends AsyncTask<category,Void,Void>{
        public final DAO_category dao;

        public deleteCategory(DAO_category dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(category... categories) {
            dao.deleteCategory(categories[0]);
            return null;
        }
    }

}
