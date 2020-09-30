package com.wellmax8.tobuy.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wellmax8.tobuy.Adapters.adapter_contacts_large_style;
import com.wellmax8.tobuy.Adapters.adapter_solds_at_shop;
import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.DTO.shop_contact;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_shop_details;
import com.wellmax8.tobuy.constants;

public class view_shop_details extends AppCompatActivity {

    private int shop_id=-1;
    private VM_shop_details VM;

    private TextView mainTextView;
    private LinearLayout layout_facebook;
    private TextView facebookLink;
    private ImageView shrinkContacts;
    private ImageView shrinkSolds;
    private RecyclerView contactsRecyclerView;
    private RecyclerView soldsRecyclerView;

    private shop currentShop;

    private boolean isSoldRecShown=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shop_details);
        shop_id=Integer.parseInt(getIntent().getStringExtra(constants.returnIntent.ID_SHOP));
        VM=new ViewModelProvider(this).get(VM_shop_details.class);
        VM.setContext(this);
        instantiateViews();

        setupMainTextView(shop_id);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showSoldsRecyclerView();
        showContactRecyclerView();

        shrinkSolds.setOnClickListener(v -> {
            if (!isSoldRecShown){
                soldsRecyclerView.setVisibility(View.GONE);
                isSoldRecShown=true;
                shrinkSolds.setImageResource(R.drawable.arrow_down);
            }else{
                soldsRecyclerView.setVisibility(View.VISIBLE);
                isSoldRecShown=false;
                shrinkSolds.setImageResource(R.drawable.arrow_up);

            }
        });


    }

    private void showSoldsRecyclerView() {
        adapter_solds_at_shop adapter=new adapter_solds_at_shop(this);
        setupRecyclerView(soldsRecyclerView);
        soldsRecyclerView.setAdapter(adapter);
        VM.getSoldsAtIdShop(shop_id).observe(this, adapter::submitList);
    }

    private void showContactRecyclerView() {
        adapter_contacts_large_style adapter=new adapter_contacts_large_style();
        setupRecyclerView(contactsRecyclerView);
        contactsRecyclerView.setAdapter(adapter);
        VM.getContactsAtShopId(shop_id).observe(this, adapter::submitList);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void instantiateViews(){
        mainTextView=findViewById(R.id.main_textView);
        layout_facebook=findViewById(R.id.layout_facebookLink);
        facebookLink=findViewById(R.id.shop_detail_facebookLink);
        shrinkContacts=findViewById(R.id.shrink_contacts);
        shrinkSolds=findViewById(R.id.shrink_solds);
        contactsRecyclerView=findViewById(R.id.viewShopDetails_contacts);
        soldsRecyclerView=findViewById(R.id.viewShopDetails_solds);
    }

    private void setupMainTextView(int shop_id) {
        VM.getShopAtId(shop_id).observe(this,shops -> {
            currentShop=shops.get(0);
            String textForMainTextView=getMainTextFromShop(currentShop);
            mainTextView.setText(HtmlCompat.fromHtml(textForMainTextView,HtmlCompat.FROM_HTML_MODE_LEGACY));
            checkForFacebook(currentShop);
        });
    }

    private void checkForFacebook(shop  currentShop) {
        if (!getFBLinkShop(currentShop).isEmpty()){
            facebookLink.setText(getFBLinkShop(currentShop));
        }else{
            layout_facebook.setVisibility(View.GONE);
        }
    }

    private String getMainTextFromShop(shop currentShop) {
        return getNameShop(currentShop)+ getAddressShop(currentShop)+getNotesShop(currentShop)+getCreatedAtShop(currentShop);
    }

    private String getNameShop(shop currentShop){
        return getTextLargeAndSmall("Name: ",currentShop.getName_shop());
    }

    private String getAddressShop(shop currentShop){
        if (!currentShop.getAddress().isEmpty()){
            return getTextLargeAndSmall("Address: ",currentShop.getAddress());
        }else {
            return "";
        }

    }

    private String getFBLinkShop(shop currentShop){
        if (!currentShop.getFacebookLink().isEmpty()){
            return currentShop.getFacebookLink();
        }else {
            return "";
        }

    }

    private String getNotesShop(shop currentShop){
        if (!currentShop.getNotes().isEmpty()){
            return getTextLargeAndSmall("Notes: ",currentShop.getNotes());
        }else {
            return "";
        }

    }

    private String getCreatedAtShop(shop currentShop){
        return "<b> " + "Created at: " + " " + "</b> " + "<small> " + currentShop.getCreated_at_shop() + " </small> " + "<br/>";
    }
    private String getTextLargeAndSmall(String large, String small) {
        return "<b> " + large + " " + "</b> " + "<small> " + small + " </small> " + "<br/>" + "<br/>";
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}