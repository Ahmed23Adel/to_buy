package com.wellmax8.tobuy.managers;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

public class viewQuiltManagerBuilder {

    private FrameLayout frameLayoutToShowIn;
    private ImageView backgroundImageView;
    private ImageView imgToPress;
    private FragmentActivity fragmentActivity;

    private View[] viewsToHide;
    private View [] viewsToVisible;

    public viewQuiltManagerBuilder setFrameLayoutToShowIn(FrameLayout frameLayoutToShowIn) {
        this.frameLayoutToShowIn = frameLayoutToShowIn;
        return this;
    }

    public viewQuiltManagerBuilder setBackgroundImageView(ImageView backgroundImageView) {
        this.backgroundImageView = backgroundImageView;
        return this;

    }

    public viewQuiltManagerBuilder setImgToPress(ImageView imgToPress) {
        this.imgToPress = imgToPress;
        return this;

    }

    public viewQuiltManagerBuilder setFragmentActivity(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
        return this;

    }

    public viewQuiltManagerBuilder setViewsToHide(View... viewsToHide) {
        this.viewsToHide = viewsToHide;
        return this;

    }

    public viewQuiltManagerBuilder setViewsToVisible(View... viewsToVisible) {
        this.viewsToVisible = viewsToVisible;
        return this;

    }

    public viewQuiltManager build(){
        viewQuiltManager viewQuiltManager=new viewQuiltManager(frameLayoutToShowIn,backgroundImageView, imgToPress,fragmentActivity,viewsToHide,viewsToVisible);
        return viewQuiltManager;
    }
}
