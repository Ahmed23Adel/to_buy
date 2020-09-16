package com.wellmax8.tobuy.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_categories;
import com.wellmax8.tobuy.cursor_adapter_categories;

public class categories extends AppCompatActivity {
    private ImageView imageView_add_category;
    private FloatingActionButton add_category;
    private RecyclerView recyclerView;
    private TextView textView_add_category;
    private VM_categories VM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        instantiateViews();
        setActionOnButtons();
        VM=new ViewModelProvider(this).get(VM_categories.class);
        VM.setContext(this);
        VM.getCategoriesOrderedCreatedAtDESC().observe(this,categories -> {
            if (!categories.isEmpty()){
                categoriesNotEmpty();
            }
        });

    }

    private void instantiateViews(){
        imageView_add_category = findViewById(R.id.main_imageView_add);
        add_category = findViewById(R.id.main_fab);
        recyclerView = findViewById(R.id.main_listView);
        textView_add_category = findViewById(R.id.main_textView_add);
    }

    private void setActionOnButtons(){
        imageView_add_category.setOnClickListener(getActionToAddCategory());
        textView_add_category.setOnClickListener(getActionToAddCategory());
        add_category.setOnClickListener(getActionToAddCategory());

    }

    private OnClickListener getActionToAddCategory(){
        OnClickListener onClickListener=new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(categories.this,add_category.class);
                startActivity(intent);
            }
        };
        return onClickListener;
    }

    private void categoriesNotEmpty(){
        imageView_add_category.setVisibility(View.GONE);
        textView_add_category.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

    }


}
