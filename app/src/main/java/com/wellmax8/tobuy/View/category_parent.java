package com.wellmax8.tobuy.View;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.category_builder;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.View.view_category_details;
import com.wellmax8.tobuy.ViewModel.VM_update_categoty;
import com.wellmax8.tobuy.colors.color;
import com.wellmax8.tobuy.colors.colorsManager;
import com.wellmax8.tobuy.managers.categoryForUndoManager;

public abstract class category_parent   extends AppCompatActivity {
    
    protected EditText category_name;
    protected EditText category_relatedTo;
    protected EditText category_desc;
    protected EditText category_extra;
    protected RelativeLayout rRed;
    protected RelativeLayout rYellow;
    protected RelativeLayout rBlue;
    protected RelativeLayout rPurple;
    protected RelativeLayout rGreen;
    protected ImageView color_red;
    protected ImageView color_yellow;
    protected ImageView color_blue;
    protected ImageView color_purple;
    protected ImageView color_green;
    protected com.wellmax8.tobuy.colors.colorsManager colorsManager;

    protected com.wellmax8.tobuy.managers.categoryForUndoManager categoryForUndoManager;
    protected LinearLayout wholeLayout;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        instantiateViews();
        colorsManager = new colorsManager(rRed, rYellow, rBlue, rPurple, rGreen);
        categoryForUndoManager = new categoryForUndoManager(category_name, category_relatedTo, category_desc, category_extra);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setOnColorPressed(color_red, rRed, color.RED);
        setOnColorPressed(color_yellow, rYellow, color.YELLOW);
        setOnColorPressed(color_blue, rBlue, color.BLUE);
        setOnColorPressed(color_purple, rPurple, color.PURPLE);
        setOnColorPressed(color_green, rGreen, color.GREEN);

    }
    protected void instantiateViews() {
        category_name = findViewById(R.id.add_category_name);
        category_relatedTo = findViewById(R.id.add_category_related_to);
        category_desc = findViewById(R.id.add_category_desc);
        category_extra = findViewById(R.id.add_category_extra);
        rRed = findViewById(R.id.rRed);
        rYellow = findViewById(R.id.rYellow);
        rBlue = findViewById(R.id.rBlue);
        rPurple = findViewById(R.id.rPurple);
        rGreen = findViewById(R.id.rGreen);
        color_red = findViewById(R.id.add_category_red);
        color_yellow = findViewById(R.id.add_category_yellow);
        color_blue = findViewById(R.id.add_category_blue);
        color_purple = findViewById(R.id.add_category_purple);
        color_green = findViewById(R.id.add_category_green);
        wholeLayout = findViewById(R.id.wholeLayout);
    }

    protected void setOnColorPressed(ImageView imageView, RelativeLayout relativeLayout, int color) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onColorPressed(color);
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onColorPressed(color);
            }
        });
    }

    protected void onColorPressed(int pressedColor) {
        colorsManager.press(pressedColor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_category, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save: {
                save();
                break;
            }
            case R.id.reset: {
                reset();
                break;
            }
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract void save();

    protected void reset() {
        categoryForUndoManager.reset();
        showDialog();
    }

    protected void showDialog() {
        Snackbar.make(wholeLayout, "Undo?", Snackbar.LENGTH_LONG).setAction("Undo", v -> {
            undoChanges();
        }).show();
    }

    protected void undoChanges() {
        categoryForUndoManager.undo();
    }
    
    protected boolean isNameInserted() {
        if (!category_name.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    protected void nameNotInserted(){
        String notInsertedText = getString(R.string.nameField);
        showDialog(notInsertedText, "Change");
    }

    protected void showDialog(String fieldName, String buttonMessage) {
        Snackbar.make(wholeLayout, "please insert a " + fieldName + " for that category", Snackbar.LENGTH_LONG).setAction(buttonMessage, v -> {
            undoChanges();
        }).show();
    }


    protected String getName(){
        return category_name.getText().toString();
    }

    protected String getRelatedTo(){
        return category_relatedTo.getText().toString();
    }

    abstract protected category getCategoryInstance();
}
