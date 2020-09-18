package com.wellmax8.tobuy.managers;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wellmax8.tobuy.Fragments.viewQuilt;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class viewQuiltManager {


    private FrameLayout frameLayoutToShowIn;
    private ImageView backgroundImageView;
    private ImageView buttonToPress;
    private FragmentActivity fragmentActivity;

    private View [] viewsToHide;
    private View [] viewsToVisible;

    private viewQuilt viewQuilt;

    public viewQuiltManager(FrameLayout frameLayoutToShowIn, ImageView backgroundImageView, ImageView buttonToPress, FragmentActivity fragmentActivity, View[] viewsToHide, View[] viewsToVisible) {
        this.frameLayoutToShowIn = frameLayoutToShowIn;
        this.backgroundImageView = backgroundImageView;
        this.buttonToPress = buttonToPress;
        this.fragmentActivity = fragmentActivity;
        this.viewsToHide = viewsToHide;
        this.viewsToVisible = viewsToVisible;
        viewQuilt = com.wellmax8.tobuy.Fragments.viewQuilt.newInstance();
        instantiateButton();
    }



    private void instantiateButton() {
        buttonToPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRearrangeFragment();
            }
        });
    }

    private void showRearrangeFragment() {
        switchToFragment();
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(frameLayoutToShowIn.getId(), viewQuilt)
                .commit();

    }

    private void switchToFragment(){
        backgroundImageView.setVisibility(View.VISIBLE);
        frameLayoutToShowIn.setVisibility(View.VISIBLE);
        hideViewGiven();
        visibleViewGiven();

    }

    private void hideViewGiven(){

        for(View v: viewsToHide){
            v.setVisibility(View.GONE);
        }
    }
    private void visibleViewGiven(){
        for(View v: viewsToVisible){
            v.setVisibility(View.VISIBLE);
        }
    }

}
