package com.wellmax8.tobuy.ViewModel;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.ROOM.category.repo_category;

import java.util.List;

public class VM_add_category extends ViewModel {
    private repo_category repo;
    private Context context;

    public VM_add_category() {

    }

    public void setContext(Context context) {
        repo = new repo_category(context);
        this.context = context;
    }

    public void insert(category category) {
        repo.insert(category);
    }

    public LiveData<List<category>> getWhereNameAndRelated(String name,String relatedTo){
        return repo.getCategoriesByNameAndRelatedTo(name,relatedTo);
    }
    public LiveData<List<category>> getCategoriesOrderedCreatedAtDESC() {
        return repo.getCategoriesOrderedCreatedAtDESC();
    }

    public String getCurrentTime(){
        Long timeInSecond=System.currentTimeMillis()/1000;
        String timeInSecondeText=timeInSecond.toString();
        return timeInSecondeText;
    }



    public void update(category category) {
        repo.update(category);
    }

    public void delete(category category) {
        repo.delete(category);
    }
}
