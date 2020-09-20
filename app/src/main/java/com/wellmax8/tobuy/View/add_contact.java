package com.wellmax8.tobuy.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.DTO.contactBuilder;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.constants;

public class add_contact extends AppCompatActivity {

    private EditText addName;
    private EditText addPhoneNumber;
    private EditText addPosition;
    private EditText addNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        setTitle(getString(R.string.add_contact));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        instantiateViews();
    }

    private void instantiateViews() {
        addName=findViewById(R.id.add_contact_name);
        addPhoneNumber=findViewById(R.id.add_contact_phoneNumber);
        addPosition=findViewById(R.id.add_contact_position);
        addNotes=findViewById(R.id.add_contact_notes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_sold,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_sold_save:{
                save();
                break;
            }
            case R.id.add_sold_reset:{
                break;
            }
            case android.R.id.home:{
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        if(isNameAndPhoneNumberInserted()){
                saveContact();
        }
    }


    private boolean isNameAndPhoneNumberInserted() {
        if(!getTextOutOfEditText(addName).isEmpty()&&!getTextOutOfEditText(addPhoneNumber).isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    private void saveContact() {
        Intent returnIntent=new Intent();
        returnIntent.putExtra(constants.returnIntent.NAME,getTextOutOfEditText(addName));
        returnIntent.putExtra(constants.returnIntent.PHONE_NUMBER,getTextOutOfEditText(addPhoneNumber));
        returnIntent.putExtra(constants.returnIntent.POSITION,getTextOutOfEditText(addPosition));
        returnIntent.putExtra(constants.returnIntent.NOTES,getTextOutOfEditText(addNotes));
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    private contact getContactInstance(){
        return new contactBuilder()
                .setPhoneNumber(getTextOutOfEditText(addPhoneNumber))
                .setName(getTextOutOfEditText(addName))
                .setPositionOfNameInCorporation(getTextOutOfEditText(addPosition))
                .setNotes(getTextOutOfEditText(addNotes))
                .build();

    }


    private String getTextOutOfEditText(EditText editText){
        return editText.getText().toString();
    }
}