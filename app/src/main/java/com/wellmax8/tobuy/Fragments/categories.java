package com.wellmax8.tobuy.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wellmax8.tobuy.Adapters.adapter_categories_largeStyle;
import com.wellmax8.tobuy.Adapters.adapter_categories_smallStyle;
import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.Main_Activity;
import com.wellmax8.tobuy.Observers.Observer_viewQuilt;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.View.add_category;
import com.wellmax8.tobuy.ViewModel.VM_categories;
import com.wellmax8.tobuy.managers.viewQuiltManager;
import com.wellmax8.tobuy.managers.viewQuiltManagerBuilder;

import java.util.ArrayList;

public class categories extends Fragment implements Observer_viewQuilt {
    private ImageView imageView_add_category;
    private FloatingActionButton add_category;
    private RecyclerView recyclerView;
    private TextView textView_add_category;
    private FrameLayout frameLayoutToShowViewQuiltIn;
    private ImageView background_imageView;
    private VM_categories VM;
    private viewQuilt viewQuilt;
    private ImageView viewQuiltViewButton;
    private com.wellmax8.tobuy.managers.viewQuiltManager viewQuiltManager;

    public static ArrayList<category> categoriesForDetails;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=LayoutInflater.from(getContext()).inflate(R.layout.categories, container, false);
        instantiateViews(root);
        setActionOnButtons();
        viewQuilt=viewQuilt.newInstance();
        VM=new ViewModelProvider(this).get(VM_categories.class);
        VM.setContext(getContext());
        VM.getCategoriesOrderedCreatedAtDESC().observe((LifecycleOwner) getContext(), categories -> {
            if (!categories.isEmpty()){
                categoriesNotEmpty();
            }
        });

        determineWhichStyleANDShowRecyclerView();
        showQuiltViews();

        Toolbar toolbar =  root.findViewById(R.id.toolbar_categories);

        //set toolbar appearance

        //for create home button
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        //activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView menu=root.findViewById(R.id.drawer_categories);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main_Activity)getActivity()).openDrawer();

            }
        });

        return root;
    }

    private void setActionOnButtons(){
        imageView_add_category.setOnClickListener(getActionToAddCategory());
        textView_add_category.setOnClickListener(getActionToAddCategory());
        add_category.setOnClickListener(getActionToAddCategory());

    }

    private void instantiateViews(View root){
        imageView_add_category = root.findViewById(R.id.main_imageView_add);
        add_category =root.findViewById(R.id.main_fab);
        textView_add_category = root.findViewById(R.id.main_textView_add);
        recyclerView=root.findViewById(R.id.category_recyclerView);
        background_imageView =root.findViewById(R.id.backgroundImageView);
        frameLayoutToShowViewQuiltIn =root.findViewById(R.id.layout_forRearrange);
        viewQuiltViewButton =root.findViewById(R.id.viewQuilt);
    }

    private View.OnClickListener getActionToAddCategory(){
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), com.wellmax8.tobuy.View.add_category.class);
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
        if (VM.isStyleLarge(getContext())){
            showRecyclerViewLargeStyle();
        }else{
            showRecyclerViewSmallStyle();
        }
    }
    private void showRecyclerViewLargeStyle(){
        recyclerView.setAdapter(null);
        instantiateRecyclerViewForLargeStyle();
        adapter_categories_largeStyle adapter=new adapter_categories_largeStyle(getContext());
        recyclerView.setAdapter(adapter);
        showRecyclerView(adapter);
    }
    private void showRecyclerViewSmallStyle(){
        recyclerView.setAdapter(null);
        instantiateRecyclerViewForSmallStyle();
        adapter_categories_smallStyle adapter=new adapter_categories_smallStyle(getContext());
        recyclerView.setAdapter(adapter);
        showRecyclerView(adapter);
    }

    private void showRecyclerView(final ListAdapter adapter){
        VM.getCategoriesOrderedCreatedAtDESC().observe((LifecycleOwner) getContext(), categories -> {
            categoriesForDetails=(ArrayList<category>) categories;
            adapter.submitList(categories);
        });
    }


    public void instantiateRecyclerViewForLargeStyle(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
    public void instantiateRecyclerViewForSmallStyle(){
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }


    private void showQuiltViews() {
        viewQuiltManager =new viewQuiltManagerBuilder()
                .setBackgroundImageView(background_imageView)
                .setFrameLayoutToShowIn(frameLayoutToShowViewQuiltIn)
                .setImgToPress(viewQuiltViewButton)
                .setFragmentActivity((FragmentActivity) getContext())
                .setViewsToHide(add_category)
                .setViewsToVisible()
                .setObserverViewQuilt(isLarge -> updateCategories(isLarge))
                .build();

    }


    @Override
    public void updateCategories(boolean isLarge) {
        if (isLarge){
            showRecyclerViewLargeStyle();
        }else{
            showRecyclerViewSmallStyle();

        }
    }
    public static ArrayList<category> getCategoriesForDetails() {
        return categoriesForDetails;
    }
}
