package com.wellmax8.tobuy;

import androidx.annotation.NonNull;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wellmax8.tobuy.data.toBuyContract;
public class add_thing extends AppCompatActivity  implements  LoaderManager.LoaderCallbacks<Cursor> {


    private final static int PATH_EDIT=0;
    private final static int PATH_ADD=1;
    private static final int THING_LOADER = 0;
    private  static int CURRENT_PATH=-1;
    private static final int MAIN_LOADER =0 ;
    private static int categoryID;
    private static int thingID;
    private static int checked= toBuyContract.things_entry.NOT_BOUGHT;

    boolean touched=false;


    EditText mAdd_thing_name;
    EditText mAdd_thing_price;
    EditText mAdd_thing_where_to_buy;
    EditText mAdd_thing_extra;
    EditText mAdd_thing_desc;
    CheckBox mCheckBought_thing;


    //Declerations of my image fields
    ImageView mAdd_thing_color_red;
    ImageView mAdd_thing_color_yellow;
    ImageView mAdd_thing_color_blue;
    ImageView mAdd_thing_color_purple;
    ImageView mAdd_thing_color_green;


    private int mColor_choice= toBuyContract.categories_entry.red;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thing);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            categoryID = Integer.parseInt(extras.getString("categoryID"));
            thingID = Integer.parseInt(extras.getString("thingID"));
            //Log.v("main","id"+String.valueOf(thingID));

            //The key argument here must match that used in the other activity
        }




        if(thingID==-1){
            CURRENT_PATH=PATH_ADD;
        }else{
            CURRENT_PATH=PATH_EDIT;
            select_current_category();
        }


        mAdd_thing_name= findViewById(R.id.add_thing_name);
        mAdd_thing_price= findViewById(R.id.add_thing_price);
        mAdd_thing_where_to_buy= findViewById(R.id.add_thing_where_to_buy);
        mAdd_thing_extra= findViewById(R.id.add_thing_extra);
        mAdd_thing_desc= findViewById(R.id.add_thing_desc);
        mCheckBought_thing= findViewById(R.id.checkBought_thing);

        //setting all image view
        mAdd_thing_color_red=findViewById(R.id.add_thing_red);
        mAdd_thing_color_yellow=findViewById(R.id.add_thing_yellow);
        mAdd_thing_color_blue=findViewById(R.id.add_thing_blue);
        mAdd_thing_color_purple=findViewById(R.id.add_thing_purple);
        mAdd_thing_color_green=findViewById(R.id.add_thing_green);

        
        setting_onClick_listeners();
        setting_onTouch_listeners();

        
    }


    private void setting_onClick_listeners(){
        mAdd_thing_color_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColor_choice= toBuyContract.categories_entry.red;
                Toast.makeText(add_thing.this, R.string.red_choice, Toast.LENGTH_SHORT).show();
            }
        });
        mAdd_thing_color_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColor_choice= toBuyContract.categories_entry.yellow;
                Toast.makeText(add_thing.this, R.string.yellow_choice, Toast.LENGTH_SHORT).show();

            }
        });
        mAdd_thing_color_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColor_choice= toBuyContract.categories_entry.blue;
                Toast.makeText(add_thing.this, R.string.blue_choice, Toast.LENGTH_SHORT).show();

            }
        });
        mAdd_thing_color_purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColor_choice= toBuyContract.categories_entry.purple;
                Toast.makeText(add_thing.this, R.string.purple_choice, Toast.LENGTH_SHORT).show();

            }
        });
        mAdd_thing_color_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColor_choice= toBuyContract.categories_entry.green;
                Toast.makeText(add_thing.this, R.string.green_choice, Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void setting_onTouch_listeners() {

        View.OnTouchListener onTouchListener= new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                touched=true;
                return false;
            }
        };



        mAdd_thing_name.setOnTouchListener(onTouchListener);
        mAdd_thing_price.setOnTouchListener(onTouchListener);
        mAdd_thing_where_to_buy.setOnTouchListener(onTouchListener);
        mAdd_thing_extra.setOnTouchListener(onTouchListener);
        mAdd_thing_desc.setOnTouchListener(onTouchListener);
        mAdd_thing_color_red.setOnTouchListener(onTouchListener);
        mAdd_thing_color_yellow.setOnTouchListener(onTouchListener);
        mAdd_thing_color_blue.setOnTouchListener(onTouchListener);
        mAdd_thing_color_purple.setOnTouchListener(onTouchListener);
        mAdd_thing_color_green.setOnTouchListener(onTouchListener);
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
                        insert_new_thing();
                    }
                }
                else if(CURRENT_PATH==PATH_EDIT){
                    update_thingy();
                    //Log.v("main","edit");
                }
                break;



            case R.id.delete:
                delet_current_thing();

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
        if (TextUtils.isEmpty(mAdd_thing_name.getText().toString())){
            Toast.makeText(this, "You can't insert new category without name please fill it", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return false;
        }
    }

    private boolean one_is_filled(){


        if (!TextUtils.isEmpty(mAdd_thing_name.getText().toString())){
            return true;
        }
        else if(!TextUtils.isEmpty(mAdd_thing_price.getText().toString())){
            return true;
        }
        else if(!TextUtils.isEmpty(mAdd_thing_where_to_buy.getText().toString())){
            return true;
        }
        else if (!TextUtils.isEmpty(mAdd_thing_extra.getText().toString())){
            return true;
        }
        else if (!TextUtils.isEmpty(mAdd_thing_desc.getText().toString())){
            return true;
        }
        else{
            return false;
        }
    }


    private ContentValues extract_values(){



        String name=mAdd_thing_name.getText().toString().trim();
        String desc=mAdd_thing_desc.getText().toString().trim();
        String price=mAdd_thing_price.getText().toString().trim();
        String whiere_to_buy=mAdd_thing_where_to_buy.getText().toString().trim();
        String extra=mAdd_thing_extra.getText().toString().trim();
        if (mCheckBought_thing.isChecked()){
            checked=toBuyContract.things_entry.BOUGHT;
        }else{
            checked=toBuyContract.things_entry.NOT_BOUGHT;

        }

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

       ContentValues values=new ContentValues();
        values.put(toBuyContract.things_entry.COLUMN_ID_CATEGORY,String.valueOf(categoryID));
        values.put(toBuyContract.things_entry.COLUMN_NAME,name);
        values.put(toBuyContract.things_entry.COLUMN_DESCRIPTION,desc);
        values.put(toBuyContract.things_entry.COLUMN_EXPECTED_PRICE,price);
        values.put(toBuyContract.things_entry.COLUMN_WHERE_TO_BUY,whiere_to_buy);
        values.put(toBuyContract.things_entry.COLUMN_EXTRA_INFO,extra);
        values.put(toBuyContract.things_entry.COLUMN_BACKGROUND_COLOR,String.valueOf(mColor_choice));
        values.put(toBuyContract.things_entry.COLUMN_BOUGHT_OR_NOT,checked);
        return values;
    }



    private void delet_current_thing() {




        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage(R.string.want_to_delet);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Log.v("main","finsih");
                String selection = toBuyContract.things_entry.COLUMN_ID_THING+"=?";
                String [] selectinArgs= new String[]{String.valueOf(thingID)};
                int id = getContentResolver().delete(Uri.parse(toBuyContract.things_entry.CONTENT_URI+"/"+"categoryID"+"/"+categoryID+"/"+thingID),selection,selectinArgs);
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


    private void insert_new_thing(){
        try {
            Uri newUri =  getContentResolver().insert(toBuyContract.things_entry.CONTENT_URI,extract_values());
            if (newUri == null) {
                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, getString(R.string.insert_Category_failed),
                        Toast.LENGTH_SHORT).show();
                //Log.v("main","error");
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this,getString(R.string.insert_thing_successful),
                        Toast.LENGTH_SHORT).show();
                //Log.v("main",String.valueOf(newUri));
                finish();

            }
        }catch (IllegalArgumentException e){

            //Log.v("main","er"+e.getMessage());
            if (e.getMessage().equals(toBuyContract.SAME_NAME_RELATED_TO_EXIST)){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            //Log.v("main",e.getMessage()) ;
        }

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

    private void update_thingy() {
        if(!isEmpty()) {
            String[] selectionArgs = new String[]{String.valueOf(thingID)};
            int id = getContentResolver().update(Uri.parse(toBuyContract.things_entry.CONTENT_URI + "/" + "categoryID" + "/" + categoryID + "/" + thingID), extract_values(), toBuyContract.things_entry.COLUMN_ID_THING + "=?", selectionArgs);
            finish();
            //Log.v("main",String.valueOf(id));
        }
    }
    private void select_current_category() {

        getLoaderManager().initLoader(THING_LOADER, null, this);


    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String [] projection={
                toBuyContract.things_entry.COLUMN_ID_THING,
                toBuyContract.things_entry.COLUMN_ID_CATEGORY,
                toBuyContract.things_entry.COLUMN_NAME,
                toBuyContract.things_entry.COLUMN_DESCRIPTION,
                toBuyContract.things_entry.COLUMN_EXPECTED_PRICE,
                toBuyContract.things_entry.COLUMN_WHERE_TO_BUY,
                toBuyContract.things_entry.COLUMN_EXTRA_INFO,
                toBuyContract.things_entry.COLUMN_BACKGROUND_COLOR,
                toBuyContract.things_entry.COLUMN_BOUGHT_OR_NOT,

        };

        return new CursorLoader(
                this,
                Uri.parse(toBuyContract.things_entry.CONTENT_URI+"/"+"categoryID"+"/"+String.valueOf(categoryID)+"/"+thingID),
                projection,
                toBuyContract.things_entry.COLUMN_ID_THING+" =?",
                new String[]{String.valueOf(thingID)},
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        while (data.moveToNext()){
            String nameC= data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_NAME));
            String descC= data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_DESCRIPTION));
            String priceC= data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_EXPECTED_PRICE));
            String whereToBuyC= data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_WHERE_TO_BUY));
            String extraC= data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_EXTRA_INFO));
            String colorC= data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_BACKGROUND_COLOR));
            checked=Integer.valueOf(data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_BOUGHT_OR_NOT)));




            mAdd_thing_name.setText(nameC);
            mAdd_thing_price.setText(priceC);
            mAdd_thing_where_to_buy.setText(whereToBuyC);
            mAdd_thing_extra.setText(extraC);
            mAdd_thing_desc.setText(descC);
            mColor_choice=Integer.parseInt(colorC);
            if (checked==toBuyContract.things_entry.BOUGHT){
                mCheckBought_thing.setChecked(true);
            }else if (checked==toBuyContract.things_entry.NOT_BOUGHT){
                mCheckBought_thing.setChecked(false);
            }

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdd_thing_name.setText("");
        mAdd_thing_price.setText("");
        mAdd_thing_where_to_buy.setText("");
        mAdd_thing_extra.setText("");
        mAdd_thing_desc.setText("");
    }
}