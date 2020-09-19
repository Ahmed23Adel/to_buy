package com.wellmax8.tobuy.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.ROOM.category.repo_category;

import java.util.List;

public class VM_update_categoty extends ViewModel {
    private repo_category repo;
    private Context context;

    public VM_update_categoty() {

    }

    public void setContext(Context context) {
        repo = new repo_category(context);
        this.context = context;
    }
    public void update(category category){
        repo.update(category);
    }
    public String getCurrentTime(){
        Long timeInSecond=System.currentTimeMillis();
        String timeInSecondeText=timeInSecond.toString();
        return timeInSecondeText;
    }

    public LiveData<List<category>> getWhereNameAndRelated(String name, String relatedTo){
        return repo.getCategoriesByNameAndRelatedTo(name,relatedTo);
    }
}
