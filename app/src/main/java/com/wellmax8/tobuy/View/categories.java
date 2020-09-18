package com.wellmax8.tobuy.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wellmax8.tobuy.Fragments.viewQuilt;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_categories;
import com.wellmax8.tobuy.Adapters.adapter_categories_largeStyle;

public class categories extends AppCompatActivity {
    private ImageView imageView_add_category;
    private FloatingActionButton add_category;
    private RecyclerView recyclerView;
    private TextView textView_add_category;
    private FrameLayout frameLayoutForImageView;
    private ImageView imageViewForClicking;
    private VM_categories VM;
    private viewQuilt viewQuilt;
    private ImageView viewQuiltView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        instantiateViews();
        setActionOnButtons();
        viewQuilt=viewQuilt.newInstance();
        VM=new ViewModelProvider(this).get(VM_categories.class);
        VM.setContext(this);
        VM.getCategoriesOrderedCreatedAtDESC().observe(this,categories -> {
            if (!categories.isEmpty()){
                categoriesNotEmpty();
            }
        });
        setViewQuiltSetup();
        showRecyclerView();

    }

    private void instantiateViews(){
        imageView_add_category = findViewById(R.id.main_imageView_add);
        add_category = findViewById(R.id.main_fab);
        textView_add_category = findViewById(R.id.main_textView_add);
        recyclerView=findViewById(R.id.category_recyclerView);
        imageViewForClicking=findViewById(R.id.img_background_forClicking);
        frameLayoutForImageView=findViewById(R.id.layout_forRearrange);
        viewQuiltView=findViewById(R.id.viewQuilt);
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

    private void showRecyclerView(){
        instantiateRecyclerView();
        adapter_categories_largeStyle adapter=new adapter_categories_largeStyle(this);
        recyclerView.setAdapter(adapter);
        VM.getCategoriesOrderedCreatedAtDESC().observe(this,categories -> {
            adapter.submitList(categories);
        });
    }
    private void instantiateRecyclerView(){
        instantiateRecyclerViewForLargeStyle();
    }

    public void instantiateRecyclerViewForLargeStyle(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    private void setViewQuiltSetup(){
        viewQuiltView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showRearrangeFragment();
            }
        });
    }

    private void showRearrangeFragment() {
        switchViewsToFragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(frameLayoutForImageView.getId(),viewQuilt)
                .commit();
    }
    private void switchViewsToFragment(){
        frameLayoutForImageView.setVisibility(View.VISIBLE);
        imageViewForClicking.setVisibility(View.VISIBLE);
        add_category.setVisibility(View.GONE);
    }





}
