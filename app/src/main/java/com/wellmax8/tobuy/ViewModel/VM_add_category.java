package com.wellmax8.tobuy.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.ROOM.category.repo_category;

import java.util.List;

public class VM_add_category extends ViewModel {
    private repo_category repo_;
    private Context context;
    public VM_add_category() {
    }

    public void setContext(Context context) {
        repo_ = new repo_category(context);
        this.context = context;
    }

    public LiveData<List<category>> getCategoriesOrderedCreatedAtDESC() {
        return repo_.getCategoriesOrderedCreatedAtDESC();
    }

    public void insert(category category){
        repo_.insertCategory(category);
    }
}
