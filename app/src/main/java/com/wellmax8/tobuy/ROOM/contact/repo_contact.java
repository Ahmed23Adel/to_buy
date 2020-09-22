package com.wellmax8.tobuy.ROOM.contact;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.ROOM.to_buy_db;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class repo_contact {

    private to_buy_db db;
    private DAO_contact dao;

    public repo_contact(Context context) {
        db=to_buy_db.getInstance(context);
        dao=db.getDaoContact();
    }

    public  Long insert(contact contact){
        try {
            return new insertContact(dao).execute(contact).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return new Long(-1);
    }
    public Long[] insertAll(contact... contacts){
        try {
            return new insertAll(dao).execute(contacts).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return new Long[0];
    }

    public void update(contact contact){
        new updateContact(dao).execute(contact);
    }

    public void delete(contact contact){
        new deleteContact(dao).execute(contact);
    }


    public LiveData<List<contact>> getAll(){
        return dao.getAllOrderedIdDesc();
    }

    public List<contact> getAtPhoneNumber(String phoneNumber){
        try {
            return new getAllAtPhoneNumber(dao).execute(phoneNumber).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static class insertContact extends AsyncTask<contact,Void,Long> {
        public final DAO_contact dao;

        public insertContact(DAO_contact dao) {
            this.dao = dao;
        }

        @Override
        protected Long doInBackground(contact... contacts) {
            return dao.insert(contacts[0]);
        }
    }


    private static class insertAll extends AsyncTask<contact,Long[],Long[]> {
        public final DAO_contact dao;

        public insertAll(DAO_contact dao) {
            this.dao = dao;
        }

        @Override
        protected Long[] doInBackground(contact... contacts) {
            return dao.insertAll(contacts);
        }

    }

    private static class updateContact extends AsyncTask<contact,Void,Void>{
        public final DAO_contact dao;

        public updateContact(DAO_contact dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(contact... contacts) {
            dao.update(contacts[0]);
            return null;
        }
    }

    private static class deleteContact extends AsyncTask<contact,Void,Void>{
        public final DAO_contact dao;

        public deleteContact(DAO_contact dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(contact... contacts) {
            dao.delete(contacts[0]);
            return null;
        }
    }


    private static class getAllAtPhoneNumber extends AsyncTask<String,Void,List<contact>>{
        public final DAO_contact dao;

        public getAllAtPhoneNumber(DAO_contact dao) {
            this.dao = dao;
        }


        @Override
        protected List<contact> doInBackground(String... strings) {
            return dao.getAtPhoneNumber(strings[0]);
        }
    }




}
