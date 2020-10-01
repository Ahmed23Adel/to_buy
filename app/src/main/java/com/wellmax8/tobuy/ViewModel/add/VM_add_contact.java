package com.wellmax8.tobuy.ViewModel.add;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.ROOM.contact.repo_contact;
import com.wellmax8.tobuy.ROOM.to_buy_db;

import java.util.List;

public class VM_add_contact extends ViewModel {
    private repo_contact repo_contact;
    private Context context;

    public VM_add_contact() {
    }

    public void setContext(Context context) {
        this.context = context;
        repo_contact=new repo_contact(context);
    }

    public LiveData<List<contact>> getAllContacts(){
        return repo_contact.getAll();
    }

    public List<contact> getAtPhoneNumber(String phoneNumber){
        return repo_contact.getAtPhoneNumber(phoneNumber);
    }
    public LiveData<List<contact>> getAll(){
        return repo_contact.getAll();
    }

    public Long insertContact(contact contact){
        return repo_contact.insert(contact);
    }

    public void updateContact(contact contact){
        repo_contact.update(contact);
    }

}
