package com.wellmax8.tobuy.View;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.category_builder;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.ViewModel.VM_add_category;
import com.wellmax8.tobuy.colors.color;
import com.wellmax8.tobuy.colors.colorsManager;
import com.wellmax8.tobuy.managers.categoryForUndoManager;

public class add_category extends category_parent {


    private VM_add_category VM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        VM = new ViewModelProvider(this).get(VM_add_category.class);
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


    @Override
    protected void save() {
        if (isNameInserted()) {
            VM.getWhereNameAndRelated(getName(), getRelatedTo()).observe(this, categories -> {
                if (categories.isEmpty()) {
                    category category = getCategoryInstance();
                    insertCategory(category);
                    onBackPressed();
                }
            });
        } else {
            nameNotInserted();
        }

    }

    @Override
    protected category getCategoryInstance() {
        String name = getName();
        String relatedTo = getRelatedTo();
        String desc = category_desc.getText().toString();
        String extra = category_extra.getText().toString();
        String currentTime = VM.getCurrentTime();
        category category = new category_builder()
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

    private void insertCategory(category category) {
        VM.insert(category);
    }


}
