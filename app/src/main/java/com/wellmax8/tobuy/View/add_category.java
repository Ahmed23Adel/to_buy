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
import com.wellmax8.tobuy.DTO.category;
import com.google.android.material.snackbar.Snackbar;
import com.wellmax8.tobuy.DTO.category_builder;
import com.wellmax8.tobuy.Exceptions.AllFieldsAreInsertedException;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_add_category;
import com.wellmax8.tobuy.colors.color;
import com.wellmax8.tobuy.colors.colorsManager;
import com.wellmax8.tobuy.managers.categoryForUndoManager;

public class add_category extends AppCompatActivity {

    private EditText category_name;
    private EditText category_relatedTo;
    private EditText category_desc;
    private EditText category_extra;
    private RelativeLayout rRed;
    private RelativeLayout rYellow;
    private RelativeLayout rBlue;
    private RelativeLayout rPurple;
    private RelativeLayout rGreen;
    private ImageView color_red;
    private ImageView color_yellow;
    private ImageView color_blue;
    private ImageView color_purple;
    private ImageView color_green;
    private colorsManager colorsManager;

    private categoryForUndoManager categoryForUndoManager;
    private LinearLayout wholeLayout;
    private VM_add_category VM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        VM=new ViewModelProvider(this).get(VM_add_category.class);
        VM.setContext(this);
        instantiateViews();
        colorsManager = new colorsManager(rRed, rYellow, rBlue, rPurple, rGreen);
        categoryForUndoManager = new categoryForUndoManager(category_name, category_relatedTo, category_desc, category_extra);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setOnColorPressed(color_red, rRed, color.RED);
        setOnColorPressed(color_yellow, rYellow, color.YELLOW);
        setOnColorPressed(color_blue, rBlue, color.BLUE);
        setOnColorPressed(color_purple, rPurple, color.PURPLE);
        setOnColorPressed(color_green, rGreen, color.GREEN);
        setTitle(getString(R.string.add_category));
    }


    private void instantiateViews() {
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

    private void setOnColorPressed(ImageView imageView, RelativeLayout relativeLayout, int color) {
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

    private void onColorPressed(int pressedColor) {
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

    private void reset() {
        categoryForUndoManager.reset();
        showDialog();
    }

    private void showDialog() {
        Snackbar.make(wholeLayout, "Undo?", Snackbar.LENGTH_LONG).setAction("Undo", v -> {
            undoChanges();
        }).show();
    }

    private void undoChanges() {
        categoryForUndoManager.undo();
    }

    private void save() {
        if (isNameInserted()) {
            VM.getWhereNameAndRelated(getName(),getRelatedTo()).observe(this,categories -> {
                if (categories.isEmpty()){
                    category category= getCategoryInstance();
                    insertCategory(category);
                    onBackPressed();
                }
            });
        }
        else {
            nameNotInserted();
        }

    }

    private boolean isNameInserted() {
        if (!category_name.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void nameNotInserted(){
        String notInsertedText = getString(R.string.nameField);
        showDialog(notInsertedText, "Change");
    }

    private String whichFieldNameWasntInserted() throws AllFieldsAreInsertedException {
        if (category_name.getText().toString().isEmpty()) {
            return getString(R.string.nameField);
        } else if (category_relatedTo.getText().toString().isEmpty()) {
            return getString(R.string.related_toFiled);
        } else {
            throw new AllFieldsAreInsertedException("All fileds are inserted fully, nothing is missing");

        }
    }

    private void showDialog(String fieldName, String buttonMessage) {
        Snackbar.make(wholeLayout, "please insert a " + fieldName + " for that category", Snackbar.LENGTH_LONG).setAction(buttonMessage, v -> {
            undoChanges();
        }).show();
    }

    private String getName(){
        return category_name.getText().toString();
    }

    private String getRelatedTo(){
        return category_relatedTo.getText().toString();
    }

    private category getCategoryInstance(){
        String name=getName();
        String relatedTo= getRelatedTo();
        String desc=category_desc.getText().toString();
        String extra=category_extra.getText().toString();
        String currentTime=VM.getCurrentTime();
        category category= new category_builder()
                .setName(name)
                .setCreated_at(currentTime)
                .setLast_edit(currentTime)
                .setRelated_to(relatedTo)
                .setDescription(desc)
                .setExtra(extra)
                .setChosenColor(colorsManager.getChosenColor())
                .build();
        return category;
    }
    private void insertCategory(category category){
        VM.insert(category);
    }


}
