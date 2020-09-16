package com.wellmax8.tobuy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.wellmax8.tobuy.data.toBuyContract.categories_entry;
import com.wellmax8.tobuy.data.toBuyContract;
import java.net.ConnectException;
import java.util.ArrayList;

public class add_category extends AppCompatActivity implements  LoaderManager.LoaderCallbacks<Cursor> {

    private final static int PATH_EDIT=0;
    private final static int PATH_ADD=1;
    private  static int CURRENT_PATH=-1;
    private static final int MAIN_LOADER =0 ;
    private static long categoryID;

    boolean touched=false;

    Uri currentUri=null;

    //Declerations of my editText fields
    EditText mAdd_category_name;
    EditText mAdd_category_related_to;
    EditText mAdd_category_desc;
    EditText mAdd_category_extra;



    //Declerations of my image fields
    ImageView mAdd_category_color_red;
    ImageView mAdd_category_color_yellow;
    ImageView mAdd_category_color_blue;
    ImageView mAdd_category_color_purple;
    ImageView mAdd_category_color_green;


    private int mColor_choice=categories_entry.red;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        Intent intent= getIntent();
        currentUri=intent.getData();
        if(currentUri==null){

            CURRENT_PATH=PATH_ADD;
        }else{
            CURRENT_PATH=PATH_EDIT;
            categoryID= ContentUris.parseId(currentUri);
            select_current_category();

            //Log.v("main","there is a uri");
        }

        //setting all editTextView
        mAdd_category_name= findViewById(R.id.add_category_name);
        mAdd_category_related_to= findViewById(R.id.add_category_related_to);
        mAdd_category_desc= findViewById(R.id.add_category_desc);
        mAdd_category_extra= findViewById(R.id.add_category_extra);

        //setting all image view
        mAdd_category_color_red=findViewById(R.id.add_category_red);
        mAdd_category_color_yellow=findViewById(R.id.add_category_yellow);
        mAdd_category_color_blue=findViewById(R.id.add_category_blue);
        mAdd_category_color_purple=findViewById(R.id.add_category_purple);
        mAdd_category_color_green=findViewById(R.id.add_category_green);
        setting_onClick_listeners();
        setting_onTouch_listeners();


    }

    private void setting_onTouch_listeners() {

        View.OnTouchListener onTouchListener= new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                touched=true;
                return false;
            }
        };

        mAdd_category_name.setOnTouchListener(onTouchListener);
        mAdd_category_related_to.setOnTouchListener(onTouchListener);
        mAdd_category_desc.setOnTouchListener(onTouchListener);
        mAdd_category_extra.setOnTouchListener(onTouchListener);
        mAdd_category_color_red.setOnTouchListener(onTouchListener);
        mAdd_category_color_yellow.setOnTouchListener(onTouchListener);
        mAdd_category_color_blue.setOnTouchListener(onTouchListener);
        mAdd_category_color_purple.setOnTouchListener(onTouchListener);
        mAdd_category_color_green.setOnTouchListener(onTouchListener);
    }


    private void setting_onClick_listeners(){
        mAdd_category_color_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColor_choice=categories_entry.red;
                Toast.makeText(add_category.this, R.string.red_choice, Toast.LENGTH_SHORT).show();
            }
        });
        mAdd_category_color_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColor_choice=categories_entry.yellow;
                Toast.makeText(add_category.this, R.string.yellow_choice, Toast.LENGTH_SHORT).show();

            }
        });
        mAdd_category_color_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColor_choice=categories_entry.blue;
                Toast.makeText(add_category.this, R.string.blue_choice, Toast.LENGTH_SHORT).show();

            }
        });
        mAdd_category_color_purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColor_choice=categories_entry.purple;
                Toast.makeText(add_category.this, R.string.purple_choice, Toast.LENGTH_SHORT).show();

            }
        });
        mAdd_category_color_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColor_choice=categories_entry.green;
                Toast.makeText(add_category.this, R.string.green_choice, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.save:
                if (CURRENT_PATH==PATH_ADD) {
                    if (!isEmpty()) {
                        insert_new_category();
                    }
                }else if(CURRENT_PATH==PATH_EDIT){
                    update_category();
                    //Log.v("main","edit");
                }
                break;

            case R.id.delete:
                delet_current_category();
                break;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (CURRENT_PATH==PATH_ADD){
            MenuItem menuItem= menu.findItem(R.id.delete);
            menuItem.setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    private boolean isEmpty(){
        if (TextUtils.isEmpty(mAdd_category_name.getText().toString())){
            Toast.makeText(this, "You can't insert new category without name please fill it", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return false;
        }
    }

    private boolean one_is_filled(){
        if (!TextUtils.isEmpty(mAdd_category_name.getText().toString())){
            return true;
        }
        else if(!TextUtils.isEmpty(mAdd_category_related_to.getText().toString())){
            return true;
        }
        else if(!TextUtils.isEmpty(mAdd_category_desc.getText().toString())){
            return true;
        }
        else if (!TextUtils.isEmpty(mAdd_category_extra.getText().toString())){
            return true;
        }
        else{
            return false;
        }
    }



    private void insert_new_category(){
        try {
            Uri newUri =  getContentResolver().insert(categories_entry.CONTENT_URI,extract_values());
            if (newUri == null) {
                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, getString(R.string.insert_Category_failed),
                        Toast.LENGTH_SHORT).show();
                //Log.v("main","error");
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this,extract_values().getAsString(categories_entry.COLUMN_NAME)+ " " +getString(R.string.insert_Category_successful),
                        Toast.LENGTH_SHORT).show();
                //Log.v("main",String.valueOf(newUri));
                finish();

            }
        }catch (IllegalArgumentException e){

               if (e.getMessage().equals(toBuyContract.SAME_NAME_RELATED_TO_EXIST)){
                   Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
               }
                //Log.v("main",e.getMessage()) ;
        }

    }


    private ContentValues extract_values(){
         String name=mAdd_category_name.getText().toString().trim();
         String related_to=mAdd_category_related_to.getText().toString().trim();
         String desc=mAdd_category_desc.getText().toString().trim();
         String extra=mAdd_category_extra.getText().toString().trim();

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

         ContentValues values=new ContentValues();
         values.put(categories_entry.COLUMN_NAME,name);
         values.put(categories_entry.COLUMN_CREATED_AT,ts);
         values.put(categories_entry.COLUMN_LAST_EDIT,ts);
         values.put(categories_entry.COLUMN_RELATED_TO,related_to);
         values.put(categories_entry.COLUMN_DESCRIPTION,desc);
         values.put(categories_entry.COLUMN_EXTRA,extra);
         values.put(categories_entry.COLUMN_BACKGROUND_COLOR,String.valueOf(mColor_choice));
         return values;
    }


    private void update_category() {

        if(!isEmpty()) {
            String[] selectionArgs = new String[]{String.valueOf(categoryID)};
            int id = getContentResolver().update(Uri.parse(categories_entry.CONTENT_URI + "/" + String.valueOf(categoryID)), extract_values(), categories_entry.COLUMN_ID_CATEGORY + "=?", selectionArgs);
            finish();
            //Log.v("main",String.valueOf(id));
        }
    }


    private void select_current_category() {

        getLoaderManager().initLoader(MAIN_LOADER, null, this);


    }

    private void delet_current_category() {




            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage(R.string.want_to_delet);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Log.v("main","finsih");
                    String selection = categories_entry.COLUMN_ID_CATEGORY+"=?";
                    String [] selectinArgs= new String[]{String.valueOf(categoryID)};
                    int id = getContentResolver().delete(Uri.parse(categories_entry.CONTENT_URI+"/"+categoryID),selection,selectinArgs);
                    finish();
                }
            });
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Log.v("main","keep");
                    if (dialog!=null){
                        dialog.dismiss();
                    }
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();




    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String [] projection={
            categories_entry.COLUMN_ID_CATEGORY,
            categories_entry.COLUMN_NAME,
            categories_entry.COLUMN_RELATED_TO,
            categories_entry.COLUMN_DESCRIPTION,
            categories_entry.COLUMN_EXTRA,
            categories_entry.COLUMN_BACKGROUND_COLOR,
        };

        return new CursorLoader(
                this,
                Uri.parse(categories_entry.CONTENT_URI+"/"+String.valueOf(categoryID)),
                projection,
                categories_entry.COLUMN_ID_CATEGORY+"=?",
                new String[]{String.valueOf(categoryID)},
                null
                );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        while (data.moveToNext()){
            String nameC= data.getString(data.getColumnIndex(categories_entry.COLUMN_NAME));
            String relatedToC= data.getString(data.getColumnIndex(categories_entry.COLUMN_RELATED_TO));
            String descC= data.getString(data.getColumnIndex(categories_entry.COLUMN_DESCRIPTION));
            String extraC= data.getString(data.getColumnIndex(categories_entry.COLUMN_EXTRA));
            String colorC= data.getString(data.getColumnIndex(categories_entry.COLUMN_BACKGROUND_COLOR));

            mAdd_category_name.setText(nameC);
            mAdd_category_related_to.setText(relatedToC);
            mAdd_category_desc.setText(descC);
            mAdd_category_extra.setText(extraC);
            mColor_choice=Integer.parseInt(colorC);
            setTitle(getString(R.string.edit)+" "+nameC);

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdd_category_name.setText("");
        mAdd_category_related_to.setText("");
        mAdd_category_desc.setText("");
        mAdd_category_extra.setText("");
        //mColor_choice=Integer.parseInt("");
    }

    @Override
    public void onBackPressed() {
        if ((CURRENT_PATH==PATH_EDIT&&!touched)||(CURRENT_PATH==PATH_ADD&&!one_is_filled())) {
            super.onBackPressed();
        }

        //Log.v("main",String.valueOf(touched));
        if ((CURRENT_PATH==PATH_EDIT&&touched) ||(CURRENT_PATH==PATH_ADD)&&one_is_filled() ){

            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage(R.string.unsaved_changes_dialog_msg);
            builder.setPositiveButton(R.string.discard, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Log.v("main","finsih");
                    finish();
                }
            });
            builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   // Log.v("main","keep");
                        if (dialog!=null){
                            dialog.dismiss();
                        }
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    }



}
