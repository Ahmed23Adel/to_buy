package com.wellmax8.tobuy.ViewModel;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.R;

public class VM_Frag_view_quilt extends ViewModel {
    // TODO: Implement the ViewModel

    public VM_Frag_view_quilt(Context context) {

    }

    public void setViewQuiltToLarge(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.keyCategories_largeStyle)
                ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putBoolean(context.getString(R.string.keyCategories_largeStyle),true);
        editor.putBoolean(context.getString(R.string.keyCategories_smallStyle),false);
        editor.commit();
    }

    public void setViewQuiltToSmall(Context context){
        SharedPreferences sharedPref=context.getSharedPreferences(context.getString(R.string.keyCategories_smallStyle)
                ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putBoolean(context.getString(R.string.keyCategories_largeStyle),false);
        editor.putBoolean(context.getString(R.string.keyCategories_smallStyle),true);
    }

}