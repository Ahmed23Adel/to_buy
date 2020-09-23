package com.wellmax8.tobuy.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.DTO.shopBuilder;
import com.wellmax8.tobuy.DTO.shop_contact;
import com.wellmax8.tobuy.DTO.sold;
import com.wellmax8.tobuy.DTO.soldBuilder;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_add_sold;
import com.wellmax8.tobuy.constants;

import java.util.ArrayList;

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

    private LinearLayout wholeLayout;

    private VM_add_sold VM;
    private category currentCategory;

    private final int LAUNCH_ADD_CONTACT = 101;
    private ArrayList<contact> contacts;
    private boolean isNameShopMustBeInsertedForContacts = false;
    private boolean isNameShopMustBeInsertedForAnyField = false;

    private boolean isShopAdded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sold);
        VM = new ViewModelProvider(this).get(VM_add_sold.class);
        VM.setContext(this);
        currentCategory = solds.getCurrentCategory();
        instantiateViews();
        setUnderline(notNow);
        setUnderline(chooseExistingShop);

        addContact.setOnClickListener(v -> {
            Intent intent = new Intent(this, add_contact.class);
            startActivityForResult(intent, LAUNCH_ADD_CONTACT);
        });
        contacts = new ArrayList<>();
        setupRecyclerView();

        nameShop.addTextChangedListener(getWatcherForShop());
        addressShop.addTextChangedListener(getWatcherForShop());
        FBlinkShop.addTextChangedListener(getWatcherForShop());
        notesShop.addTextChangedListener(getWatcherForShop());


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
        recyclerViewContacts = findViewById(R.id.contacts_recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_ADD_CONTACT && resultCode == RESULT_OK) {
            addContactInstanceFromIntent(data);
        }
    }

    private void addContactInstanceFromIntent(@Nullable Intent data) {
        String id = data.getStringExtra(constants.returnIntent.ID);
        Log.v("main","id_contact"+id);
        String name = data.getStringExtra(constants.returnIntent.NAME);
        String phoneNumber = data.getStringExtra(constants.returnIntent.PHONE_NUMBER);
        String position = data.getStringExtra(constants.returnIntent.POSITION);
        String notes = data.getStringExtra(constants.returnIntent.NOTES);
        contact contact = new contactBuilder()
                .setId(id)
                .setName(name)
                .setPhoneNumber(phoneNumber)
                .setPositionOfNameInCorporation(position)
                .setNotes(notes)
                .build();
        contacts.add(contact);
        updateContactRecyclerView();

    }

    private void updateContactRecyclerView() {
        changeIsShopNameMustBeInsertedForContacts();
        recyclerViewContacts.setAdapter(null);
        adapter_contacts adapterContacts = new adapter_contacts();
        adapterContacts.submitList(contacts);
        recyclerViewContacts.setAdapter(adapterContacts);
        adapterContacts.setOnLongItemClicked(position -> {
            contacts.remove(position);
            updateContactRecyclerView();
        });


    }

    private void changeIsShopNameMustBeInsertedForContacts() {
        if (contacts.size() > 0) {
            isNameShopMustBeInsertedForContacts = true;
            switchToNameShopMustBeInserted();
        } else {
            isNameShopMustBeInsertedForContacts = false;
            switchToNameShopMustNotBeInserted();

        }
    }


    private void switchToNameShopMustBeInserted() {
        nameShopMustAdd.setVisibility(View.VISIBLE);
    }

    private void switchToNameShopMustNotBeInserted() {
        nameShopMustAdd.setVisibility(View.GONE);
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerViewContacts.setLayoutManager(linearLayout);
        recyclerViewContacts.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                linearLayout.getOrientation());
        recyclerViewContacts.addItemDecoration(dividerItemDecoration);
    }

    private TextWatcher getWatcherForShop() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isAnyFieldShopInserted() || isNameShopMustBeInsertedForContacts) {
                    switchToNameShopMustBeInserted();
                } else {
                    switchToNameShopMustNotBeInserted();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        };
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
        if (isNameSoldInserted()) {
            if (isNameShopMustBeInserted()) {
                nameShopMustBeInserted();
            } else {
                save_sold((long) sold.ID_SHOP_NOT_SPECIFIED);
            }

        } else {
            showSnackBar("item", "name");
        }
    }

    private void nameShopMustBeInserted() {
        if (isNameShopInserted()) {
            checkDuplicationFroNameShop();
        } else {
            showSnackBar("shop", "name");
        }
    }

    private void checkDuplicationFroNameShop() {
        ArrayList<shop> oldShopWithSameName = (ArrayList<shop>) VM.isNameShopDuplicated(getTextOutOfEditText(nameShop));
        if (oldShopWithSameName.size() > 0) {
            showSnackBarForEditShopName();
        } else {
            save_contact_shop_instace();
        }
    }

    private void save_contact_shop_instace() {
        Long id_shop=VM.insertShop(getShopInstance());
        save_shop_contact_instance(id_shop,contacts);
        save_sold(id_shop);
    }


    private void save_shop_contact_instance(Long id_shop, ArrayList<contact> contacts) {
        if (contacts.size()>0){
            for(int i=0;i<contacts.size();i++){
                shop_contact shop_contact=new shop_contact(id_shop.intValue(),contacts.get(i).getId());
                VM.insertShop_contacts(shop_contact);
            }
        }
    }

    private void save_sold(Long id_shop) {
        VM.insertSold(getSoldInstance(id_shop));
        onBackPressed();

    }

    private sold getSoldInstance(Long id_shop){
        String currentTime=VM.getCurrentTime();
        return new soldBuilder()
                .setId_category(currentCategory.getId())
                .setId_shop(id_shop.intValue())
                .setName(getTextOutOfEditText(nameSold))
                .setDescription(getTextOutOfEditText(descriptionSold))
                .setExtra(getTextOutOfEditText(extraSold))
                .setPrice(getTextOutOfEditText(priceSold).isEmpty()?0.0:Double.parseDouble(getTextOutOfEditText(priceSold)))
                .setLast_edit(currentTime)
                .setCreated_at(currentTime)
                .setIsBought(isBought.isChecked())
                .setTimeBuying(isBought.isChecked()?currentTime: com.wellmax8.tobuy.DTO.sold .TIME_BUY_NOT_SPECIFIED)
                .build();
    }
    private shop getShopInstance() {
        return new shopBuilder()
                .setName(getTextOutOfEditText(nameShop))
                .setAddress(getTextOutOfEditText(addressShop))
                .setFacebookLink(getTextOutOfEditText(FBlinkShop))
                .setNotes(getTextOutOfEditText(notesShop))
                .build();
    }

    private boolean isNameSoldInserted() {
        return isFieldInserted(nameSold);
    }

    private boolean isNameShopInserted() {
        return isFieldInserted(nameShop);
    }

    private void showSnackBar(String kindOfItem, String field) {
        Snackbar.make(wholeLayout, "You can't inert new " + kindOfItem + " without filling " + field, Snackbar.LENGTH_LONG).show();
    }

    private void showSnackBarForEditShopName() {
        Snackbar.make(wholeLayout, "There is a shop with the exact name please change it to something else or choose from exiting shops", Snackbar.LENGTH_LONG).show();
    }

    private boolean isFieldInserted(EditText editText) {
        if (!editText.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isAnyFieldShopInserted() {
        if (isFieldInserted(nameShop) || isFieldInserted(addressShop) || isFieldInserted(FBlinkShop) || isFieldInserted(notesShop)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNameShopMustBeInserted() {
        return isNameShopMustBeInsertedForContacts || isAnyFieldShopInserted();
    }

    private String getTextOutOfEditText(EditText editText) {
        return editText.getText().toString();
    }
}

