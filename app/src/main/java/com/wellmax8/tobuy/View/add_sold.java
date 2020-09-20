package com.wellmax8.tobuy.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

import com.google.android.material.snackbar.Snackbar;
import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.DTO.contactBuilder;
import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.DTO.shopBuilder;
import com.wellmax8.tobuy.DTO.shop_contact;
import com.wellmax8.tobuy.DTO.sold;
import com.wellmax8.tobuy.DTO.soldBuilder;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_solds;
import com.wellmax8.tobuy.constants;

import java.util.ArrayList;
import java.util.List;

public class add_sold extends AppCompatActivity {

    private EditText nameView;
    private EditText descriptionView;
    private EditText extraView;
    private EditText priceView;
    private TextView notNow;
    private TextView chooseExistingShop;
    private CheckBox isBought;

    private EditText nameShop;
    private EditText addressShop;
    private EditText FBlinkShop;
    private EditText notesShop;
    private Button addContact;
    private TextView nameShopMustAdd;

    private VM_solds VM;
    private category currentCategory;

    private static ArrayList<contact> contacts;
    private int sizeOfContacts;
    private int[] idsContactsSaved;
    private int id_shop = -1;

    private boolean isNameShopMustInsert = false;

    private LinearLayout wholeLayout;

    private final int LAUNCH_ADD_CONTACT_ACTIVITY = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sold);
        VM = new ViewModelProvider(this).get(VM_solds.class);
        VM.setContext(this);
        currentCategory = solds.getCurrentCategory();
        contacts = new ArrayList<>();
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
        setWatcherToAllShopFileds();
        VM.testGetAllSolds(currentCategory.getId()).observe(this, solds -> Log.v("main", "" + solds.size()));
    }

    private void instantiateViews() {
        nameView = findViewById(R.id.add_sold_name);
        descriptionView = findViewById(R.id.add_sold_desc);
        extraView = findViewById(R.id.add_sold_extra);
        priceView = findViewById(R.id.add_sold_price);
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

    private void save() {
        if (isNameInserted()) {
            if (isNameShopMustInsert) {
                nameShopMusBetInserted();
            } else {
                saveSold();
            }
        } else {
            nameNotInserted();
        }
    }


    private boolean isNameInserted() {
        return isFieldInserted(nameView);
    }

    private void nameNotInserted() {
        showDialog("Item");
    }

    protected void showDialog(String fieldName) {
        Snackbar.make(wholeLayout, "You can't insert a new " + fieldName + " without it a name", Snackbar.LENGTH_LONG).show();
    }

    private void nameShopMusBetInserted() {
        if (isNameShopInserted()) {
            nameShopInserted();
        }else{
            nameShopNotInserted();
        }
    }

    private boolean isNameShopInserted() {
        return isFieldInserted(nameShop);
    }

    private void nameShopInserted() {
        if (isThereContacts()){
            Log.v("main","iscon"+isThereContacts());
            saveAllContacts();
            saveShopInstance();
            VM.getLastAddedContactsByLimit(sizeOfContacts).observe(this,contacts1 -> {
                idsContactsSaved = getIdsFromContacts(contacts);
                VM.getLastAddedShop().observe(this,shops -> {
                    id_shop=shops.get(0).getId();
                    save_shop_contact_instance(id_shop, idsContactsSaved);
                    saveSold();
                });
            });

        }else{
            Log.v("main","iscon"+isThereContacts());
            saveShopInstance();
            saveSold();
        }

    }

    private void nameShopNotInserted(){
        showDialog("Shop");
    }

    private boolean isFieldInserted(EditText editText) {
        if (!editText.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    private void saveAllContacts() {

        sizeOfContacts = contacts.size();
        VM.insertContacts(contacts);
    }

    private void saveShopInstance() {
        VM.insertShop(getShopInstance());
    }

    private shop getShopInstance() {
        return new shopBuilder()
                .setName(nameShop.getText().toString())
                .setAddress(addressShop.getText().toString())
                .setFacebookLink(FBlinkShop.getText().toString())
                .setNotes(notesShop.getText().toString())
                .build();

    }

    private boolean isThereContacts() {
        if (contacts.size() > 0) {
            return true;
        }
        return false;
    }

    private int[] getIdsFromContacts(List<contact> contacts) {
        int[] ids = new int[contacts.size()];
        for (int i = 0; i < contacts.size(); i++) {
            ids[i] = contacts.get(i).getId();
        }
        return ids;
    }

    private void save_shop_contact_instance(int id_shop, int[] idsContactsSaved) {
        if (idsContactsSaved.length > 0) {
            for (int id_contact : idsContactsSaved) {
                shop_contact shop_contact = new shop_contact(id_shop, id_contact);
                VM.insertShopContact(shop_contact);
            }
        }
    }

    private void saveSold() {
        Log.v("main", "saveSold_add_sold");
        VM.insertSold(getSoldInstance());
        onBackPressed();
    }


    private sold getSoldInstance() {
        String currentTime = VM.getCurrentTime();
        sold sold = new soldBuilder()
                .setId_category(currentCategory.getId())
                .setId_shop(getIdShop())
                .setName(nameView.getText().toString())
                .setDescription(descriptionView.getText().toString())
                .setExtra(extraView.getText().toString())
                .setPrice(getPrice())
                .setCreated_at(currentTime)
                .setLast_edit(currentTime)
                .setIsBought(chooseExistingShop.isSelected())
                .setTimeBuying(getTimeBuying())
                .build();

        return sold;
    }

    private int getIdShop() {
        if (isNameShopMustInsert) {
            return id_shop;
        } else {
            return sold.ID_SHOP_NOT_SPECIFIED;
        }
    }

    private double getPrice() {
        if (priceView.getText().toString().isEmpty()) {
            return 0;
        } else {
            return Double.parseDouble(priceView.getText().toString());
        }
    }

    //TODO:: check later if user choesed not now and did choose time
    public String getTimeBuying() {
        if (isBought.isChecked()) {
            return sold.TIME_BUY_NOT_SPECIFIED;
        } else {
            return VM.getCurrentTime();
        }
    }

    private void setWatcherToAllShopFileds() {
        nameShop.addTextChangedListener(getListenerFroShop());
        addressShop.addTextChangedListener(getListenerFroShop());
        FBlinkShop.addTextChangedListener(getListenerFroShop());
        notesShop.addTextChangedListener(getListenerFroShop());
    }

    private TextWatcher getListenerFroShop() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isAnyFieldShopInserted()) {
                    nameShopMustAdd.setVisibility(View.VISIBLE);
                    isNameShopMustInsert = true;
                } else {
                    nameShopMustAdd.setVisibility(View.GONE);
                    isNameShopMustInsert = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    private boolean isAnyFieldShopInserted() {
        if (isFieldInserted(nameShop) || isFieldInserted(addressShop) || isFieldInserted(FBlinkShop) || isFieldInserted(notesShop)) {
            return true;
        } else {
            return false;
        }
    }

    public static void addInsertedContacts(contact contact) {
        contacts.add(contact);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==LAUNCH_ADD_CONTACT_ACTIVITY&&resultCode==RESULT_OK){
            contact contact=new contactBuilder()
                    .setName(data.getStringExtra(constants.returnIntent.NAME))
                    .setPhoneNumber(data.getStringExtra(constants.returnIntent.PHONE_NUMBER))
                    .setPositionOfNameInCorporation(data.getStringExtra(constants.returnIntent.POSITION))
                    .setNotes(data.getStringExtra(constants.returnIntent.NOTES))
                    .build();
            addInsertedContacts(contact);

        }
    }
}