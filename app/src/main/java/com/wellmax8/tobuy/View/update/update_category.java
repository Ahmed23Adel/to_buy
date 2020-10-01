package com.wellmax8.tobuy.View.update;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.Builders.category_builder;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.View.category_parent;
import com.wellmax8.tobuy.View.viewDetails.view_category_details;
import com.wellmax8.tobuy.colors.color;
import com.wellmax8.tobuy.ViewModel.update.VM_update_categoty;


public class update_category extends category_parent {
    private category currentCategory;
    private FloatingActionButton deleteFab;


    private VM_update_categoty VM;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentCategory= view_category_details.getCurrentCategory();

        VM=new ViewModelProvider(this).get(VM_update_categoty.class);
        VM.setContext(this);
        showSavedData();
        setTitle(getString(R.string.update_category));


    }

    @Override
    protected void instantiateViews() {
        super.instantiateViews();
        deleteFab =findViewById(R.id.delete);
        deleteFab.setVisibility(View.VISIBLE);
        deleteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToSureDeleting();
            }
        });
    }

    private void delete() {
        VM.delete(currentCategory);
        onBackPressed();
    }
    private void showDialogToSureDeleting() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(getString(R.string.sure_delete))
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        alertDialog.show();
    }

    private void showSavedData() {
        category_name.setText(currentCategory.getName_category());
        category_relatedTo.setText(currentCategory.getRelated_to());
        category_desc.setText(currentCategory.getDescription_category());
        category_extra.setText(currentCategory.getExtra_category());
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
                }else if (categories.size()==1&&categories.get(0).getId()==currentCategory.getId()){
                    category category= getCategoryInstance();
                    updateCategory(category);
                    onBackPressed();
                }
            }
            );
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
                .setCreated_at(currentCategory.getCreated_at_category())
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
