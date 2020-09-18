package com.wellmax8.tobuy.managers;

import android.view.View;
import android.widget.EditText;

public class categoryForUndoManager {

    private EditText [] views;
    private String [] textOfViews;

    public categoryForUndoManager(EditText... views) {
        this.views = views;
        textOfViews=new String[views.length];
    }


    public void reset(){
        saveInstance();
        setAllFieldsToNull();
    }

    public void undo(){
        for (int i=0;i<views.length;i++){
            views[i].setText(textOfViews[i]);
        }
    }


    private void saveInstance(){
       for (int i=0;i<views.length;i++){
           textOfViews[i]=views[i].getText().toString().trim();
       }
    }

    private void setAllFieldsToNull(){
        for (EditText e:views){
            e.setText("");
        }
    }

}
