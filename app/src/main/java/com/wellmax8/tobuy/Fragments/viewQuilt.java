package com.wellmax8.tobuy.Fragments;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_Frag_view_quilt;

public class viewQuilt extends Fragment {

    private LinearLayout layoutLargeStyle;
    private LinearLayout layoutSmallStyle;
    private ImageView largeStyle;
    private ImageView smallStyle;
    private VM_Frag_view_quilt VM;

    public static viewQuilt newInstance() {
        return new viewQuilt();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        VM = new ViewModelProvider(this).get(VM_Frag_view_quilt.class);

        View view = inflater.inflate(R.layout.view_quilt_fragment, container, false);
        layoutLargeStyle = view.findViewById(R.id.layout_largeStyle);
        layoutSmallStyle = view.findViewById(R.id.layout_smallStyle);
        largeStyle = view.findViewById(R.id.largeStyle);
        smallStyle = view.findViewById(R.id.smallStyle);

        layoutLargeStyle.setOnClickListener(getListener(true));
        largeStyle.setOnClickListener(getListener(true));
        layoutSmallStyle.setOnClickListener(getListener(false));
        smallStyle.setOnClickListener(getListener(false));

        isCategoriesViewQuiltLarge();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    private OnClickListener getListener(boolean large){
        OnClickListener onClickListener=new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (large){
                    VM.setViewQuiltToLarge(getContext());
                    switchToLarge();
                }else{
                    VM.setViewQuiltToSmall(getContext());
                    switchToSmall();
                }
            }
        };
        return onClickListener;
    }

    private void switchToLarge(){
        layoutLargeStyle.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey));
        layoutSmallStyle.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
    }
    private void switchToSmall(){
        layoutLargeStyle.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        layoutSmallStyle.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey));
    }

    private void isCategoriesViewQuiltLarge() {
        if (VM.isCategoriesViewQuiltLarge(getContext())){
            switchToLarge();
        }else{
            switchToSmall();
        }
    }
}