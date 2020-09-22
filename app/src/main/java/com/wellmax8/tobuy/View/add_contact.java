package com.wellmax8.tobuy.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;

import com.google.android.material.snackbar.Snackbar;
import com.wellmax8.tobuy.DTO.contactBuilder;
import com.wellmax8.tobuy.managers.categoryForUndoManager;
import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_add_contact;
import com.wellmax8.tobuy.constants;

import java.util.ArrayList;

public class add_contact extends AppCompatActivity {

    private LinearLayout wholeLayout;
    private AppCompatMultiAutoCompleteTextView addName;
    private AppCompatMultiAutoCompleteTextView addPhoneNumber;
    private EditText addPosition;
    private EditText addNotes;

    private VM_add_contact VM;
    private ArrayList<contact> suggestedContacts;
    private String[] names;
    private String[] phoneNumbers;
    private boolean chosenFromSuggestions = false;

    private  categoryForUndoManager undoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        setTitle(getString(R.string.add_contact));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        VM = new ViewModelProvider(this).get(VM_add_contact.class);
        VM.setContext(this);
        instantiateViews();

        AddContactsToMultiAutoCompleteTextView();
        VM.getAll().observe(this,contacts -> Log.v("main","size"+contacts.size()));

        undoManager =new categoryForUndoManager(addName,addPhoneNumber,addPosition,addNotes);
    }

    private void instantiateViews() {
        wholeLayout = findViewById(R.id.wholeLayout);
        addName = findViewById(R.id.add_contact_name);
        addPhoneNumber = findViewById(R.id.add_contact_phoneNumber);
        addPosition = findViewById(R.id.add_contact_position);
        addNotes = findViewById(R.id.add_contact_notes);
    }

    private void AddContactsToMultiAutoCompleteTextView() {
        VM.getAllContacts().observe(this, contactsByObservable -> {
            suggestedContacts = (ArrayList<contact>) contactsByObservable;
            names = new String[contactsByObservable.size()];
            phoneNumbers = new String[contactsByObservable.size()];

            for (int i = 0; i < contactsByObservable.size(); i++) {
                names[i] = contactsByObservable.get(i).getName();
                phoneNumbers[i] = contactsByObservable.get(i).getPhoneNumber();
            }
            showSuggestions(addName, names,true);
            showSuggestions(addPhoneNumber, phoneNumbers,false);
        });
    }

    private void showSuggestions(AppCompatMultiAutoCompleteTextView textView, final String[] values,boolean isName) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        textView.setAdapter(arrayAdapter);
        textView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        textView.setOnItemClickListener((parent, view, position, id) -> {
            if (isName){
                itemOnSuggestionsClicked(getPositionAtNameFromSuggestions((String)parent.getItemAtPosition(position),position));
            }else{

            }
            itemOnSuggestionsClicked(getPositionAtPhoneNumberFromSuggestions((String) parent.getItemAtPosition(position), position));

        });
    }

    private int getPositionAtNameFromSuggestions(String name,int position){
        Log.v("main","atnamne"+name+position);
        for(int i=0;i<suggestedContacts.size();i++){
            if (suggestedContacts.get(i).getName().equals(name)){
                Log.v("main","1atnamne"+i);
                if (position>0){
                    Log.v("main","1atnamne"+1);
                    position--;
                }else {
                    Log.v("main","1atnamne"+2);

                    return i;
                }
            }
        }
        return 0;
    }
    private int getPositionAtPhoneNumberFromSuggestions(String phoneNumber,int position){
        for(int i=0;i<suggestedContacts.size();i++){
            if (suggestedContacts.get(i).getPhoneNumber().equals(phoneNumber)){
                if (position>0){
                    position--;
                    continue;
                }
                return i;
            }
        }
        return 0;
    }

    private int isItemDone=1;
    private void itemOnSuggestionsClicked(int position) {
        if (isItemDone%2==1) {
            Log.v("main", "posHere");
            Log.v("main", "pos" + position);
            chosenFromSuggestions = true;
            contact contact = suggestedContacts.get(position);
            setTextTo(addName, contact.getName());
            setTextTo(addPhoneNumber, contact.getPhoneNumber());
            setTextTo(addPosition, contact.getPositionOfNameInCorporation());
            setTextTo(addNotes, contact.getNotes());
            isItemDone++;
        }else{
            isItemDone++;
        }
    }

    private void setTextTo(EditText text, String value) {
        text.setText(value);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_sold, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_sold_save: {
                save();
                break;
            }
            case R.id.add_sold_reset: {
                undoManager.reset();
                showDialogForReset();
                break;
            }
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void showDialogForReset() {
        Snackbar.make(wholeLayout, "Undo?", Snackbar.LENGTH_LONG).setAction("Undo", v -> {
            undoChanges();
        }).show();
    }

    private void undoChanges() {
        undoManager.undo();
    }

    private void save() {
        if (isNameAndPhoneNumberInserted()) {
            checkForDuplication();
        } else {
            nameAndPhoneNumberNotInserted();
        }
    }


    private boolean isNameAndPhoneNumberInserted() {
        if (isFieldInserted(addName) && isFieldInserted(addPhoneNumber)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isFieldInserted(EditText editText) {
        return !editText.getText().toString().isEmpty();
    }

    private void nameAndPhoneNumberNotInserted() {
        showSnackBar();
    }

    private void showSnackBar() {
        Snackbar.make(wholeLayout, "Name or phone number is not inserted, please insert it and try again", Snackbar.LENGTH_LONG).show();
    }

    private boolean isChecked=false;
    private void checkForDuplication() {
        ArrayList<contact> oldPhoneNumbers= (ArrayList<contact>) VM.getAtPhoneNumber(getPhoneNumberInserted());
        if (oldPhoneNumbers.size() > 0) {
            Log.v("main", "2");
            phoneDuplicated(oldPhoneNumbers.get(0));
        } else {
            Log.v("main", "1");
            saveContact();
        }
    }

    private String getNameInserted() {
        return getTextOutOfEditText(addName);
    }

    private String getPhoneNumberInserted() {
        return getTextOutOfEditText(addPhoneNumber);
    }

    private void phoneDuplicated(contact contact) {
        if (chosenFromSuggestions) {
            Log.v("main", "3");
            if (isDataChangedAfterChoosingSuggestion(contact)) {
                Log.v("main", "4");
                showDialogToUpdate();
            } else {
                Log.v("main", "5");
                end(contact);
            }
        } else {
            Log.v("main", "6");
            showDialogToUpdate();
        }
    }

    private boolean isDataChangedAfterChoosingSuggestion(contact contact) {
        if (!getNameInserted().equals(contact.getName()) || !getPhoneNumberInserted().equals(contact.getPhoneNumber()) ||
                !getTextOutOfEditText(addPosition).equals(contact.getPositionOfNameInCorporation())
                || !getTextOutOfEditText(addNotes).equals(contact.getNotes())
        ) {
            return true;
        }
        return false;

    }

    private void showDialogToUpdate() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setTitle("There is a contact withe the same phone number, Do you want to update it?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    updateContact();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    if (dialog != null) dialog.dismiss();
                });
        alert.show();
    }

    private void updateContact() {
        Log.v("main", "7");
        contact oldContact=getContactAtPhoneNumber(getPhoneNumberInserted());
        oldContact.setName(getNameInserted());
        oldContact.setPhoneNumber(getPhoneNumberInserted());
        oldContact.setPositionOfNameInCorporation(getTextOutOfEditText(addPosition));
        oldContact.setNotes(getTextOutOfEditText(addNotes));
        VM.updateContact(oldContact);
        end(oldContact);
    }

    private void saveContact() {
        Log.v("main", "8");
        contact contact=new contactBuilder()
                .setName(getNameInserted())
                .setPhoneNumber(getPhoneNumberInserted())
                .setPositionOfNameInCorporation(getTextOutOfEditText(addPosition))
                .setNotes(getTextOutOfEditText(addNotes))
                .build();
        Long newId= VM.insertContact(contact);
        contact.setId(newId.intValue());
        end(contact);
    }

    private contact getContactAtPhoneNumber(String phoneNumber) {
        for (contact c: suggestedContacts){
            if (phoneNumber.equals(c.getPhoneNumber())){
                return c;
            }
        }
        return new contact("","","","");
    }


    private void end(contact contact) {
        Log.v("main", "10");
        Intent returnIntent = new Intent();
        returnIntent.putExtra(constants.returnIntent.ID, String.valueOf(contact.getId()));
        returnIntent.putExtra(constants.returnIntent.NAME, contact.getName());
        returnIntent.putExtra(constants.returnIntent.PHONE_NUMBER, contact.getPhoneNumber());
        returnIntent.putExtra(constants.returnIntent.POSITION,contact.getPositionOfNameInCorporation());
        returnIntent.putExtra(constants.returnIntent.NOTES, contact.getNotes());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    private String getTextOutOfEditText(EditText editText) {
        return editText.getText().toString();
    }
}