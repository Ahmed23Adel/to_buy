package com.wellmax8.tobuy.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.wellmax8.tobuy.Adapters.adapter_solds_large_style;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wellmax8.tobuy.Adapters.adapter_categories_largeStyle;
import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.sold_large_style;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_solds;

import java.util.ArrayList;

public class solds extends AppCompatActivity {

    private int id_category;
    private int position;
    private ArrayList<category> categories;
    private static category currentCategory;

    private Toolbar toolbar;
    private ImageView viewQuilt;
    private FloatingActionButton addSold;
    private RecyclerView recyclerView;

    private VM_solds VM;



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
        setActionOnButtons();

        VM= new ViewModelProvider(this).get(VM_solds.class);
        VM.setContext(this);
        VM.getAllSoldsLargeStyle().observe(this,sold_large_styles -> {
            for (sold_large_style sls: sold_large_styles){
                Log.v("main","1sls"+sls.getSold().getName_sold());
                Log.v("main","1sls"+sls.getShop().getName_shop());
            }

        });
        determineWhichStyleANDShowRecyclerView();

    }

    private void instantiateViews() {
        viewQuilt=findViewById(R.id.solds_viewQuilt);
        addSold=findViewById(R.id.add_sold);
        toolbar=findViewById(R.id.solds_toolbar);
        recyclerView=findViewById(R.id.solds_recyclerView);
    }

    private void setActionOnButtons(){
        addSold.setOnClickListener(getActionToAddSold());
    }

    private View.OnClickListener getActionToAddSold(){
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(solds.this,add_sold.class);
                startActivity(intent);
            }
        };
        return onClickListener;
    }

    public static category getCurrentCategory() {
        return currentCategory;
    }


    private void determineWhichStyleANDShowRecyclerView() {
        showRecyclerViewLargeStyle();
    }

    private void showRecyclerViewLargeStyle(){
        recyclerView.setAdapter(null);
        instantiateRecyclerViewForLargeStyle();
        adapter_solds_large_style  adapter=new adapter_solds_large_style (this,currentCategory);
        recyclerView.setAdapter(adapter);
        showRecyclerView(adapter);
    }


    public void instantiateRecyclerViewForLargeStyle(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    private void showRecyclerView(final ListAdapter adapter){
        VM.getAtIdCategoryLargeStyleOrderLastEditDesc(currentCategory.getId()).observe(this,solds -> {
            adapter.submitList(solds);
        });
    }
}