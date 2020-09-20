package com.wellmax8.tobuy.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.wellmax8.tobuy.R;

public class add_sold extends AppCompatActivity {

    private TextView nameView;
    private TextView descriptionView;
    private TextView extraView;
    private TextView priceView;
    private TextView notNow;
    private TextView chooseExistingShop;
    private CheckBox isBought;

    private TextView nameShop;
    private TextView addressShop;
    private TextView FBlinkShop;
    private TextView notesShop;
    private Button addContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sold);
        instantiateViews();
        setUnderline(notNow);
        setUnderline(chooseExistingShop);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(add_sold.this, add_contact.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void instantiateViews(){
        nameView=findViewById(R.id.add_sold_name);
        descriptionView=findViewById(R.id.add_sold_desc);
        extraView=findViewById(R.id.add_sold_extra);
        priceView=findViewById(R.id.add_sold_price);
        notNow=findViewById(R.id.add_sold_notNow);
        isBought=findViewById(R.id.add_sold_isBought);
        isBought=findViewById(R.id.add_sold_isBought);
        addContact=findViewById(R.id.add_sold_add_contact);
        chooseExistingShop=findViewById(R.id.add_sold_chooseExistingShops);
        nameShop=findViewById(R.id.add_shop_name);
        addressShop=findViewById(R.id.add_shop_address);
        FBlinkShop=findViewById(R.id.add_shop_facebookLink);
        notesShop=findViewById(R.id.add_shop_notes);
    }

    private void setUnderline(TextView textView){
        textView.setPaintFlags(textView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
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
}