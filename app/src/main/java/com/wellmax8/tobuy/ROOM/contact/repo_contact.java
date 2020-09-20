package com.wellmax8.tobuy.ROOM.contact;

import android.content.Context;
import android.os.AsyncTask;

import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.ROOM.to_buy_db;

public class repo_contact {

    private to_buy_db db;
    private DAO_contact dao;

    public repo_contact(Context context) {
        db=to_buy_db.getInstance(context);
        dao=db.getDaoContact();
    }

    public  void insert(contact contact){
        new insertContact(dao).execute(contact);
    }
    public void update(contact contact){
        new updateContact(dao).execute(contact);
    }
    public void delete(contact contact){
        new updateContact(dao).execute(contact);
    }

    private static class insertContact extends AsyncTask<contact,Void,Void> {
        public final DAO_contact dao;

        public insertContact(DAO_contact dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(contact... contacts) {
            dao.insert(contacts[0]);
            return null;
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
}
