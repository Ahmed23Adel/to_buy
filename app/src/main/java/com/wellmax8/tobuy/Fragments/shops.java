package com.wellmax8.tobuy.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wellmax8.tobuy.R;

public class shops extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= LayoutInflater.from(container.getContext()).inflate(R.layout.shops, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
