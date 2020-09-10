package com.wellmax8.tobuy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.wellmax8.tobuy.data.toBuyContract;
import com.wellmax8.tobuy.data.toBuyContract.things_entry;
public class cursor_adapter_things extends CursorAdapter  {

    public cursor_adapter_things(Context context, Cursor c) {
        super(context, c,0);
    }
    int bought;
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_thing,parent,false);
    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {

        TextView nameView= view.findViewById(R.id.item_name_things);
        TextView priceView= view.findViewById(R.id.item_price_thing);
        TextView buyFromView= view.findViewById(R.id.item_buy_from_thing);
        TextView descView= view.findViewById(R.id.item_desc_thing);
        final CheckBox checkBoughtView= view.findViewById(R.id.checkBought_thing);

        String name = cursor.getString(cursor.getColumnIndex(things_entry.COLUMN_NAME));
        String price= cursor.getString(cursor.getColumnIndex(things_entry.COLUMN_EXPECTED_PRICE));
        String buyFrom= cursor.getString(cursor.getColumnIndex(things_entry.COLUMN_WHERE_TO_BUY));
        String desc= cursor.getString(cursor.getColumnIndex(things_entry.COLUMN_DESCRIPTION));
        bought=Integer.valueOf(cursor.getString(cursor.getColumnIndex(things_entry.COLUMN_BOUGHT_OR_NOT)));
        int color=cursor.getInt(cursor.getColumnIndex(toBuyContract.things_entry.COLUMN_BACKGROUND_COLOR));

/*
        //Log.v("main","cu fir"+bought);
        checkBoughtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String []selectionArgs= new String[]{String.valueOf(cursor.getString(cursor.getColumnIndex(things_entry.COLUMN_ID_THING)))};

                ContentValues values= new ContentValues();
                values.put(toBuyContract.things_entry.COLUMN_ID_CATEGORY,cursor.getString(cursor.getColumnIndex(things_entry.COLUMN_ID_CATEGORY)));
                values.put(toBuyContract.things_entry.COLUMN_NAME,cursor.getString(cursor.getColumnIndex(things_entry.COLUMN_NAME)));
                //Log.v("main","cu sec"+bought);

                if (bought==things_entry.NOT_BOUGHT)
                {
                    //Log.v("main","if0");
                    bought=things_entry.BOUGHT;
                    values.put(toBuyContract.things_entry.COLUMN_BOUGHT_OR_NOT, things_entry.BOUGHT);
                }

                else if(bought==things_entry.BOUGHT)
                {
                    bought=things_entry.NOT_BOUGHT;
                    values.put(toBuyContract.things_entry.COLUMN_BOUGHT_OR_NOT, things_entry.NOT_BOUGHT);
                    //Log.v("main","if1");
                }



                //Log.v("main","cu thir"+bought);
                Log.v("main","id"+cursor.getString(cursor.getColumnIndex(things_entry.COLUMN_ID_THING)));
                Cursor c = ((SimpleCursorAdapter)cursor).getCursor();
                c.moveToPosition(position);

                String categoryID= String.valueOf(cursor.getString(cursor.getColumnIndex(things_entry.COLUMN_ID_CATEGORY)));
                String thingID= String.valueOf((Integer) view.getTag());

                int id = context.getContentResolver().update(
                        Uri.parse(toBuyContract.things_entry.CONTENT_URI + "/" + "categoryID" + "/"
                                + categoryID + "/" + thingID), values, toBuyContract.things_entry.COLUMN_ID_THING + "=?", selectionArgs);


            }
        });

*/
        if(name.equals("")){
            nameView.setVisibility(View.GONE);
        }else{
            nameView.setVisibility(View.VISIBLE);
            nameView.setText(context.getString(R.string.name)+" "+name);
        }

        if(buyFrom.equals("")){
            buyFromView.setVisibility(View.GONE);
        }
        else{
            buyFromView.setVisibility(View.VISIBLE);
            buyFromView.setText(context.getString(R.string.buy_from)+" "+buyFrom);
        }

        if(price.equals("")){
            priceView.setVisibility(View.GONE);
        }else{
            priceView.setVisibility(View.VISIBLE);
            priceView.setText(context.getString(R.string.price)+" "+price);

        }
        if(desc.equals("")){
            descView.setVisibility(View.GONE);
        }else{
            descView.setVisibility(View.VISIBLE);
            descView.setText(context.getString(R.string.description)+" "+desc);

        }



        if (bought==things_entry.BOUGHT){
            checkBoughtView.setChecked(true);
        }else{
            checkBoughtView.setChecked(false);
        }



        LinearLayout textContainer= view.findViewById(R.id.item_layout_thing);
        GradientDrawable gradientDrawable=(GradientDrawable) textContainer.getBackground();

        switch (color){

            case toBuyContract.categories_entry.green:
            {
                gradientDrawable.setColor(ContextCompat.getColor(context, R.color.green));
                break;

            }
            case toBuyContract.categories_entry.blue:
            {
                gradientDrawable.setColor(ContextCompat.getColor(context, R.color.blue));
                break;
            }


            case toBuyContract.categories_entry.purple:
            {
                gradientDrawable.setColor(ContextCompat.getColor(context, R.color.purple));
                break;

            }
            case toBuyContract.categories_entry.yellow:
            {
                gradientDrawable.setColor(ContextCompat.getColor(context, R.color.yellow));
                break;
            }
            case toBuyContract.categories_entry.red:
            {
                gradientDrawable.setColor(ContextCompat.getColor(context, R.color.red));
                break;

            }
            default:

        }




    }



}
