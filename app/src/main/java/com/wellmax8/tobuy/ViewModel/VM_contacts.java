package com.wellmax8.tobuy.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.ROOM.contact.repo_contact;

import java.util.List;

public class VM_contacts extends ViewModel {
    private Context context;
    private repo_contact repoContact;

    public VM_contacts() {
    }

    public void setContext(Context context) {
        this.context = context;
        repoContact=new repo_contact(context);
    }

    public LiveData<List<contact>> getAllContacts(){
        return repoContact.getAll();
    }
}
