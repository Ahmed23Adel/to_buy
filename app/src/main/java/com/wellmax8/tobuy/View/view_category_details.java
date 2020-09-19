package com.wellmax8.tobuy.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.Exceptions.colorNotSpecifiedException;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.managers.categoryManager;

import java.util.ArrayList;

public class view_category_details extends AppCompatActivity {

    private int position;
    private ArrayList<category> categories;
    private category currentCategory;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        position=getIntent().getIntExtra(getString(R.string.pos),0);
        categories= com.wellmax8.tobuy.View.categories.getCategoriesForDetails();
        currentCategory=categories.get(position);
        instantiateViews();
        categoryManager categoryManager =new categoryManager(currentCategory,textView,this);
        categoryManager.showText();

        setupActionBar();

    }

    private void instantiateViews(){
        textView=findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_category_details,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:{
                break;
            }
            case android.R.id.home:{
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupActionBar(){
        ActionBar actionBar=getSupportActionBar();
        ColorDrawable colorDrawable;
        try {
            colorDrawable=new ColorDrawable(Color.parseColor(currentCategory.getColor().getColoHEX()));
            actionBar.setBackgroundDrawable(colorDrawable);
        } catch (colorNotSpecifiedException e) {
            e.printStackTrace();
        }
    }
}