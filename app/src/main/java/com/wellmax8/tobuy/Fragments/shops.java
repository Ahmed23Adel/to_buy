package com.wellmax8.tobuy.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wellmax8.tobuy.Adapters.shops.adapter_shops;
import com.wellmax8.tobuy.Main_Activity;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.shops.VM_shops;

public class shops extends Fragment {

    private VM_shops VM;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= LayoutInflater.from(container.getContext()).inflate(R.layout.shops, container, false);

        VM=new ViewModelProvider(this).get(VM_shops.class);
        VM.setContext(getContext());
        recyclerView=root.findViewById(R.id.shops_recyclerView);
        Toolbar toolbar=root.findViewById(R.id.toolbar_shops);
        AppCompatActivity activity=(AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ImageView menu=root.findViewById(R.id.drawer_shops);
        menu.setOnClickListener(v -> {
            ((Main_Activity)activity).openDrawer();
        });

        showRecyclerView();
        return root;
    }

    private void showRecyclerView() {
        instantiateRecyclerView();
        VM.getShopList().observe((LifecycleOwner) getContext(), shops -> {
            adapter_shops adapterShops=new adapter_shops(getContext());
            recyclerView.setAdapter(adapterShops);
            adapterShops.submitList(shops);
        });
    }

    private void instantiateRecyclerView() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }
}
