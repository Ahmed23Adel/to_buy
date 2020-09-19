package com.wellmax8.tobuy.View;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.Nullable;
import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.category_builder;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.colors.color;
import com.wellmax8.tobuy.ViewModel.VM_update_categoty;


public class update_category extends category_parent {
    private category currentCategory;


    private VM_update_categoty VM;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentCategory=view_category_details.getCurrentCategory();

        VM=new ViewModelProvider(this).get(VM_update_categoty.class);
        VM.setContext(this);
        showSavedData();
        setTitle(getString(R.string.update_category));


    }

    private void showSavedData() {
        category_name.setText(currentCategory.getName());
        category_relatedTo.setText(currentCategory.getRelated_to());
        category_desc.setText(currentCategory.getDescription());
        category_extra.setText(currentCategory.getExtra());
        clickChosenColor();
    }

    private void clickChosenColor() {
        if (currentCategory.getChosenColor()==color.RED){
            onColorPressed(color.RED);
        }else if (currentCategory.getChosenColor()==color.BLUE){
            onColorPressed(color.BLUE);
        }else if (currentCategory.getChosenColor()==color.GREEN){
            onColorPressed(color.GREEN);
        }else if (currentCategory.getChosenColor()==color.PURPLE){
            onColorPressed(color.PURPLE);
        }else if (currentCategory.getChosenColor()==color.YELLOW){
            onColorPressed(color.YELLOW);
        }
    }



    @Override
    protected void save() {
        update();
    }

    private void update() {
        if (isNameInserted()) {
            VM.getWhereNameAndRelated(getName(),getRelatedTo()).observe(this,categories -> {
                if (categories.isEmpty()){
                    category category= getCategoryInstance();
                    updateCategory(category);
                    onBackPressed();
                }
            });
        }
        else {
            nameNotInserted();
        }
    }


    @Override
    protected category getCategoryInstance(){
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
        category.setId(currentCategory.getId());
        return category;
    }

    private void updateCategory(category category) {
        VM.update(category);
    }
}
