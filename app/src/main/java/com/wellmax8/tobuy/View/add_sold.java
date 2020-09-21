package com.wellmax8.tobuy.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wellmax8.tobuy.DTO.contact_saved_or_addedTo_shopContact;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wellmax8.tobuy.Adapters.adapter_contacts;
import com.google.android.material.snackbar.Snackbar;
import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.DTO.contactBuilder;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_solds;
import com.wellmax8.tobuy.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class add_sold extends AppCompatActivity {

    private EditText nameSold;
    private EditText descriptionSold;
    private EditText extraSold;
    private EditText priceSold;
    private TextView notNow;
    private TextView chooseExistingShop;
    private CheckBox isBought;

    private EditText nameShop;
    private EditText addressShop;
    private EditText FBlinkShop;
    private EditText notesShop;
    private Button addContact;
    private TextView nameShopMustAdd;

    private RecyclerView recyclerViewContacts;

    private VM_solds VM;
    private category currentCategory;

    private static ArrayList<contact> contactsToBeSaved;
    private static ArrayList<contact> contactsAlreadySaved;
    private int id_shop = -1;


    private LinearLayout wholeLayout;

    private int lastIdContactSaved;
    private int numsIdsContactsWillBeSaved=0;

    private final int LAUNCH_ADD_CONTACT_ACTIVITY = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sold);
        VM = new ViewModelProvider(this).get(VM_solds.class);
        VM.setContext(this);
        currentCategory = solds.getCurrentCategory();
        instantiateViews();
        setUnderline(notNow);
        setUnderline(chooseExistingShop);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(add_sold.this, add_contact.class);
                startActivityForResult(i, LAUNCH_ADD_CONTACT_ACTIVITY);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        contactsToBeSaved = new ArrayList<>();
        contactsAlreadySaved = new ArrayList<>();

        nameShop.addTextChangedListener(getWatcerForShop());
        addressShop.addTextChangedListener(getWatcerForShop());
        FBlinkShop.addTextChangedListener(getWatcerForShop());
        notesShop.addTextChangedListener(getWatcerForShop());

       contact contac=new contact("test","test","test","test");
        contact contac2=new contact("4905834","AHEMD","AJKDFH","2");
       contact contac3=new contact("4905834","AHEMD","AJKDFH","3");
       contact contac4=new contact("4905834","AHEMD","AJKDFH","4");

        ArrayList<contact> c=new ArrayList<>();
        c.add(contac);
        c.add(contac2);
        c.add(contac3);
       c.add(contac4);
       Long[] tests=VM.insertAll(contac,contac2,contac3,contac4);
       for(Long i: tests){
           Log.v("main","test"+i);
       }


       //contact contac=new contact("test","test","test","test");
       // contac.setId(17);

        //contact contac2=new contact("4905834","AHEMD","AJKDFH","1");
        //contac.setId(15);
       // VM.testDeleteContact(contac);
       // VM.testDeleteContact(contac2);
      /*  VM.testGetAllIdsForContact().observe(this, new Observer<List<contact>>() {
            @Override
            public void onChanged(List<contact> contactss) {
                ArrayList<contact> contactss1=(ArrayList<contact>)contactss;
                int [] ids=new int[contactss1.size()];
                for (int i=0;i<contactss1.size();i++){
                    Log.v("main","test"+contactss1.get(i).getId());
                }
            }
        });*/

    }

    private void instantiateViews() {
        nameSold = findViewById(R.id.add_sold_name);
        descriptionSold = findViewById(R.id.add_sold_desc);
        extraSold = findViewById(R.id.add_sold_extra);
        priceSold = findViewById(R.id.add_sold_price);
        notNow = findViewById(R.id.add_sold_notNow);
        isBought = findViewById(R.id.add_sold_isBought);
        isBought = findViewById(R.id.add_sold_isBought);
        addContact = findViewById(R.id.add_sold_add_contact);
        chooseExistingShop = findViewById(R.id.add_sold_chooseExistingShops);
        nameShop = findViewById(R.id.add_shop_name);
        addressShop = findViewById(R.id.add_shop_address);
        FBlinkShop = findViewById(R.id.add_shop_facebookLink);
        notesShop = findViewById(R.id.add_shop_notes);
        nameShopMustAdd = findViewById(R.id.shopMustAdd);
        wholeLayout = findViewById(R.id.wholeLayout);
        recyclerViewContacts=findViewById(R.id.contacts_recyclerView);
    }

    private void setUnderline(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
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
                break;
            }
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //TODO :write else for isNameShopMustBeInserted to save sold
    //TODO save shop-->getIDShop-->save sold
    private void save() {
        if (isNameSoldInserted()) {
            if (isNameShopMustBeInserted()) {
                if(isNameShopInserted()){
                    if(VM.isNameShopDuplicated(getNameShop())){
                        if (isThereContacts()){
                            lastIdContactSaved=VM.getLastIdForContacts();
                            saveContacts();
                        }else{
                            //TODO save shop-->getIDShop-->save sold
                        }
                    }else{
                        nameShopIsDuplicated();
                    }
                }else{
                    nameShopNotInserted();
                }
            } else {
                //TODO :write else for isNameShopMustBeInserted to save sold
            }
        } else {
            showSnackMessage("Item", "name");
        }
    }

    private void showSnackMessage(String soldOrShop, String fieled) {
        Snackbar.make(wholeLayout, "You can't insert new " + soldOrShop + " without a " + fieled, Snackbar.LENGTH_LONG).show();
    }
    private void showSnackMessage(String message) {
        Snackbar.make(wholeLayout, message, Snackbar.LENGTH_LONG).show();
    }

    private boolean isNameSoldInserted() {
        return isFieldInserted(nameSold);
    }

    private boolean isFieldInserted(EditText editText) {
        if (!editText.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    private TextWatcher getWatcerForShop() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isNameShopMustBeInserted()) {
                    nameShopMustAdd.setVisibility(View.VISIBLE);
                } else {
                    nameShopMustAdd.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    private boolean isNameShopMustBeInserted() {
        return isAnyFieldShopInserted() || contactsToBeSaved.size() > 0;
    }

    private boolean isAnyFieldShopInserted() {
        if (isFieldInserted(nameShop) || isFieldInserted(addressShop) || isFieldInserted(FBlinkShop) || isFieldInserted(notesShop)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNameShopInserted(){
        return isFieldInserted(nameShop);
    }
    private void nameShopNotInserted(){
        showSnackMessage("shop","name");
    }

    private String getNameShop(){
        return getStringOutOfEditText(nameShop);
    }

    private String getStringOutOfEditText(EditText editText){
        return editText.getText().toString();
    }

    private void nameShopIsDuplicated(){
        showSnackMessage("there is a shop with the same name; please change it to something else, or choose the already saved shop");
    }
    private boolean isThereContacts(){
        return contactsToBeSaved.size()>0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==LAUNCH_ADD_CONTACT_ACTIVITY && resultCode==RESULT_OK){
           extractDataFromContactIntent(data);
        }
    }

    private void extractDataFromContactIntent(@Nullable Intent data){
        int state=Integer.parseInt(data.getStringExtra(constants.returnIntent.STATE));
        contact_saved_or_addedTo_shopContact tempContact=new contact_saved_or_addedTo_shopContact(VM);
        if (tempContact.isNew(state)){
            numsIdsContactsWillBeSaved++;
            addContactFromContactIntent(data);
        }else{
            addAlreadySavedContact(data);
        }

        updateContactRecyclerView();
    }

    private void addContactFromContactIntent(@Nullable Intent data){
        contact contact = new contactBuilder()
                .setName(data.getStringExtra(constants.returnIntent.NAME))
                .setPhoneNumber(data.getStringExtra(constants.returnIntent.PHONE_NUMBER))
                .setPositionOfNameInCorporation(data.getStringExtra(constants.returnIntent.POSITION))
                .setNotes(data.getStringExtra(constants.returnIntent.NOTES))
                .build();
        contactsToBeSaved.add(contact);
    }

    private void addAlreadySavedContact(@Nullable Intent data){
        contact contact = new contactBuilder()
                .setId(data.getStringExtra(constants.returnIntent.ID))
                .setName(data.getStringExtra(constants.returnIntent.NAME))
                .setPhoneNumber(data.getStringExtra(constants.returnIntent.PHONE_NUMBER))
                .setPositionOfNameInCorporation(data.getStringExtra(constants.returnIntent.POSITION))
                .setNotes(data.getStringExtra(constants.returnIntent.NOTES))
                .build();
        contactsAlreadySaved.add(contact);
    }

    private void updateContactRecyclerView() {
        recyclerViewContacts.setAdapter(null);
        ArrayList<contact> tempContact = new ArrayList<>();
        for (contact c: contactsToBeSaved){
            tempContact.add(c);
        }
        for (contact c: contactsAlreadySaved){
            tempContact.add(c);
        }
        adapter_contacts adapterContacts=new adapter_contacts();
        adapterContacts.submitList(tempContact);
        LinearLayoutManager linearLayout=new LinearLayoutManager(this);
        recyclerViewContacts.setLayoutManager(linearLayout);
        recyclerViewContacts.setHasFixedSize(true);
        recyclerViewContacts.setAdapter(adapterContacts);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                linearLayout.getOrientation());
        recyclerViewContacts.addItemDecoration(dividerItemDecoration);
    }

    private void saveContacts(){
        VM.insertContacts(contactsToBeSaved);
    }
}

