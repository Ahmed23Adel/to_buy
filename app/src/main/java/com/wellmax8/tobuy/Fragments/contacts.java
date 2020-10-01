package com.wellmax8.tobuy.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wellmax8.tobuy.Adapters.contacts.adapter_contacts_large_style;
import com.wellmax8.tobuy.Main_Activity;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.contacts.VM_contacts;

public class contacts extends Fragment {

    private VM_contacts VM;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=LayoutInflater.from(container.getContext()).inflate(R.layout.contacts, container, false);
        VM=new ViewModelProvider(this).get(VM_contacts.class);
        VM.setContext(getContext());
        recyclerView=root.findViewById(R.id.contacts_recyclerView_large_style);
        Toolbar toolbar=root.findViewById(R.id.toolbar_contacts);
        AppCompatActivity activity=(AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ImageView menu=root.findViewById(R.id.drawer_contacts);
        menu.setOnClickListener(v -> {
            ((Main_Activity)activity).openDrawer();
        });

        showRecyclerView();
        return root;
    }

    private void showRecyclerView() {
        instantiateRecyclerView();
        VM.getAllContacts().observe((LifecycleOwner) getContext(), contacts -> {
            adapter_contacts_large_style adapter_contacts_large_style=new adapter_contacts_large_style();
            recyclerView.setAdapter(adapter_contacts_large_style);
            adapter_contacts_large_style.submitList(contacts);
        });
    }

    private void instantiateRecyclerView() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }
}
