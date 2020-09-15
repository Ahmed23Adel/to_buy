package com.wellmax8.tobuy.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.ROOM.category.repo_category;

import java.util.List;

public class VM_add_category extends ViewModel {
    private repo_category repo;
    private Context context;

    public VM_add_category(Context context) {
        repo = new repo_category(context);
        this.context = context;
    }

    public void setContext(Context context) {

    }

    public LiveData<List<category>> getCategoriesOrderedCreatedAtDESC() {
        return repo.getCategoriesOrderedCreatedAtDESC();
    }

    public void insert(category category) {
        repo.insert(category);
    }

    public void update(category category) {
        repo.update(category);
    }

    public void delete(category category) {
        repo.delete(category);
    }
}
