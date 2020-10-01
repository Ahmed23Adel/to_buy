package com.wellmax8.tobuy.ViewModel.details;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.ROOM.category.repo_category;

public class VM_category_details extends ViewModel {

    private Context context;
    private repo_category repo;

    public VM_category_details() {

    }
    public void setContext(Context context) {
        this.context = context;
        repo=new repo_category(context);
    }

    public void delete(category category){
        repo.delete(category);
    }
}
