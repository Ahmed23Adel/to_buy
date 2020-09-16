package com.wellmax8.tobuy.DTO;

import android.widget.EditText;

public class categoryForUndoManager {
    private String name;
    private String relatedTo;
    private String desc;
    private String extra;

    private EditText nameField;
    private EditText relatedToField;
    private EditText descField;
    private EditText extraField;

    public categoryForUndoManager(EditText nameField, EditText relatedToField, EditText descField, EditText extraField) {
        this.nameField = nameField;
        this.relatedToField = relatedToField;
        this.descField = descField;
        this.extraField = extraField;
    }

    public void reset(){
        saveInstance();
        setAllFieldsToNull();
    }

    public void undo(){
        nameField.setText(name);
        relatedToField.setText(relatedTo);
        descField.setText(desc);
        extraField.setText(extra);
    }


    private void saveInstance(){
        name=nameField.getText().toString();
        relatedTo=relatedToField.getText().toString();
        desc=descField.getText().toString();
        extra=extraField.getText().toString();
    }

    private void setAllFieldsToNull(){
        nameField.setText("");
        relatedToField.setText("");
        descField.setText("");
        extraField.setText("");
    }

}
