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

    public void insert(category category){
        new insertCategory(dao_category).execute(category);
    }
    public void update(category category){
        new updateCategory(dao_category).execute(category);
    }
    public void delete(category category){
        new deleteCategory(dao_category).execute(category);
    }

    public LiveData<List<category>> getCategoriesOrderedCreatedAtDESC(){
        return dao_category.getCategoriesOrderedCreatedAtDESC();
    }
    public LiveData<List<category>>getCategoriesByNameAndRelatedTo(String name,String relatedTo){
        return dao_category.getCategoriesByNameAndRelatedTo(name,relatedTo);
    }


    private static class insertCategory extends AsyncTask<category,Void,Void>{
        public final DAO_category dao;

        public insertCategory(DAO_category dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(category... categories) {
            dao.insert(categories[0]);
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
            dao.update(categories[0]);
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
            dao.delete(categories[0]);
            return null;
        }
    }

}