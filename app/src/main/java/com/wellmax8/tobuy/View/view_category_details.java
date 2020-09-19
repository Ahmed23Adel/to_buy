package com.wellmax8.tobuy.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.wellmax8.tobuy.DTO.category;
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
        position=getIntent().getIntExtra(getString(R.string.pos),0);
        categories= com.wellmax8.tobuy.View.categories.getCategoriesForDetails();
        currentCategory=categories.get(position);
        instantiateViews();
        categoryManager categoryManager =new categoryManager(currentCategory,textView,this);
        categoryManager.showText();

    }

    private void instantiateViews(){
        textView=findViewById(R.id.textView);
    }
}