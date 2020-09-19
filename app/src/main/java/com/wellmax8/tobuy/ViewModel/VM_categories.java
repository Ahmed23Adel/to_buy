package com.wellmax8.tobuy.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.ROOM.category.repo_category;

import java.util.List;

public class VM_categories extends ViewModel {

    private Context context;
    private repo_category repo;

    public VM_categories() {

    }
    public void setContext(Context context) {
        this.context = context;
        repo=new repo_category(context);
    }

    public LiveData<List<category>> getCategoriesOrderedCreatedAtDESC(){
        return repo.getCategoriesOrderedCreatedAtDESC();
    }

    public boolean isStyleLarge(Context context){
        VM_Frag_view_quilt VM=new VM_Frag_view_quilt();
        return VM.isCategoriesViewQuiltLarge(context);
    }



}
