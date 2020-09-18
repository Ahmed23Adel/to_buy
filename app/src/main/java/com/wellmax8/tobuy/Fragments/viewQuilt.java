package com.wellmax8.tobuy.Fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_Frag_view_quilt;

public class viewQuilt extends Fragment {

    private VM_Frag_view_quilt mViewModel;

    public static viewQuilt newInstance() {
        return new viewQuilt();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_quilt_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(VM_Frag_view_quilt.class);
        // TODO: Use the ViewModel
    }

}