package com.wellmax8.tobuy.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wellmax8.tobuy.Adapters.adapter_categories_smallStyle;
import com.wellmax8.tobuy.Fragments.viewQuilt;
import com.wellmax8.tobuy.Observers.Observer_viewQuilt;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_categories;
import com.wellmax8.tobuy.Adapters.adapter_categories_largeStyle;
import com.wellmax8.tobuy.managers.viewQuiltManager;
import com.wellmax8.tobuy.managers.viewQuiltManagerBuilder;
import com.wellmax8.tobuy.DTO.category;

import java.util.ArrayList;

public class categories extends AppCompatActivity implements Observer_viewQuilt {
    private ImageView imageView_add_category;
    private FloatingActionButton add_category;
    private RecyclerView recyclerView;
    private TextView textView_add_category;
    private FrameLayout frameLayoutToShowViewQuiltIn;
    private ImageView background_imageView;
    private VM_categories VM;
    private viewQuilt viewQuilt;
    private ImageView viewQuiltViewButton;
    private viewQuiltManager viewQuiltManager;

    public static ArrayList<category> categoriesForDetails;

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

        determineWhichStyleANDShowRecyclerView();
        showQuiltViews();

    }




    private void instantiateViews(){
        imageView_add_category = findViewById(R.id.main_imageView_add);
        add_category = findViewById(R.id.main_fab);
        textView_add_category = findViewById(R.id.main_textView_add);
        recyclerView=findViewById(R.id.category_recyclerView);
        background_imageView =findViewById(R.id.backgroundImageView);
        frameLayoutToShowViewQuiltIn =findViewById(R.id.layout_forRearrange);
        viewQuiltViewButton =findViewById(R.id.viewQuilt);
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

    private void determineWhichStyleANDShowRecyclerView() {
        if (VM.isStyleLarge(this)){
            showRecyclerViewLargeStyle();
        }else{
            showRecyclerViewSmallStyle();
        }
    }
    private void showRecyclerViewLargeStyle(){
        recyclerView.setAdapter(null);
        instantiateRecyclerViewForLargeStyle();
        adapter_categories_largeStyle adapter=new adapter_categories_largeStyle(this);
        recyclerView.setAdapter(adapter);
        showRecyclerView(adapter);
    }
    private void showRecyclerViewSmallStyle(){
        recyclerView.setAdapter(null);
        instantiateRecyclerViewForSmallStyle();
        adapter_categories_smallStyle adapter=new adapter_categories_smallStyle(this);
        recyclerView.setAdapter(adapter);
        showRecyclerView(adapter);
    }

    private void showRecyclerView(ListAdapter adapter){
        VM.getCategoriesOrderedCreatedAtDESC().observe(this,categories -> {
            categoriesForDetails=(ArrayList<category>) categories;
            adapter.submitList(categories);
        });
    }


    public void instantiateRecyclerViewForLargeStyle(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
    public void instantiateRecyclerViewForSmallStyle(){
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }


    private void showQuiltViews() {
        viewQuiltManager =new viewQuiltManagerBuilder()
                .setBackgroundImageView(background_imageView)
                .setFrameLayoutToShowIn(frameLayoutToShowViewQuiltIn)
                .setImgToPress(viewQuiltViewButton)
                .setFragmentActivity(this)
                .setViewsToHide(add_category)
                .setViewsToVisible()
                .setObserverViewQuilt(this)
                .build();
        viewQuilt.subscribe(this);

    }

    @Override
    public void updateCategories(boolean isLarge) {
        if (isLarge){
            showRecyclerViewLargeStyle();
        }else{
            showRecyclerViewSmallStyle();

        }
    }
}
