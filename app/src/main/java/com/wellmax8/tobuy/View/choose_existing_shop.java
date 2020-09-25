package com.wellmax8.tobuy.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wellmax8.tobuy.Adapters.adapter_choose_existing_shops;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_choose_existing_shops;

import java.util.List;

public class choose_existing_shop extends AppCompatActivity {

    private VM_choose_existing_shops VM;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_existing_shop);
        setTitle("Choose shop");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.existing_shops_recyclerView);
        setupRecyclerView(recyclerView);
        VM=new ViewModelProvider(this).get(VM_choose_existing_shops.class);
        VM.setContext(this);
        VM.getShopList().observe(this,shops -> {
            Log.v("main","shopsSize"+shops.size());
            showRecyclerView(shops,recyclerView);
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void showRecyclerView(List<shop> shops,RecyclerView recyclerView) {
        adapter_choose_existing_shops adapter_existing_shops=new adapter_choose_existing_shops(this);
        recyclerView.setAdapter(adapter_existing_shops);
        adapter_existing_shops.submitList(shops);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}