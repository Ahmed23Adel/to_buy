package com.wellmax8.tobuy.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.R;

import java.util.ArrayList;

public class solds extends AppCompatActivity {

    private int id_category;
    private int position;
    private ArrayList<category> categories;
    private category currentCategory;

    private Toolbar toolbar;
    private ImageView viewQuilt;
    private FloatingActionButton addSold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solds);
        position = getIntent().getIntExtra(getString(R.string.pos), 0);
        id_category = getIntent().getIntExtra(getString(R.string.id), 0);
        categories = com.wellmax8.tobuy.View.categories.getCategoriesForDetails();
        for (category c : categories) {
            if (c.getId() == id_category) {
                currentCategory = c;
                break;
            }
        }

        instantiateViews();
        toolbar.setTitle(currentCategory.getName());

    }

    private void instantiateViews() {
        viewQuilt=findViewById(R.id.solds_viewQuilt);
        addSold=findViewById(R.id.add_sold);
        toolbar=findViewById(R.id.solds_toolbar);
    }
}