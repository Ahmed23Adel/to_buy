package com.wellmax8.tobuy.ViewModel;

import android.content.Context;
import com.wellmax8.tobuy.ROOM.Sold_Large_Style.repo_sold_large_style;

import com.wellmax8.tobuy.DTO.sold_large_style ;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class VM_solds extends ViewModel {

    private Context context;
    private repo_sold_large_style repoSoldLargeStyle;

    public VM_solds() {
    }

   public void setContext(Context context){
        this.context=context;
        repoSoldLargeStyle=new repo_sold_large_style(context);
    }

    public LiveData<List<sold_large_style>> getAllSoldsLargeStyle(){
        return repoSoldLargeStyle.getAll();
    }
}
