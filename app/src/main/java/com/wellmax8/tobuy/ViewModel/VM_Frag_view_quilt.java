package com.wellmax8.tobuy.ViewModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.R;

public class VM_Frag_view_quilt extends ViewModel {
    // TODO: Implement the ViewModel

    public VM_Frag_view_quilt() {

    }

    public boolean isCategoriesViewQuiltLarge(Context context){
        SharedPreferences sharedPreferences= context.getSharedPreferences("com_wellmax8_tobuy",Context.MODE_PRIVATE);
        boolean isLarge=sharedPreferences.getBoolean(context.getString(R.string.keyCategories_largeStyle),true);
        return isLarge;
    }

    public void setViewQuiltToLarge(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("com_wellmax8_tobuy"
                ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putBoolean(context.getString(R.string.keyCategories_largeStyle),true);
        editor.putBoolean(context.getString(R.string.keyCategories_smallStyle),false);
        editor.apply();
    }

    public void setViewQuiltToSmall(Context context){
        SharedPreferences sharedPref=context.getSharedPreferences("com_wellmax8_tobuy"
                ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putBoolean(context.getString(R.string.keyCategories_largeStyle),false);
        editor.putBoolean(context.getString(R.string.keyCategories_smallStyle),true);
        editor.apply();
    }

}