package com.wellmax8.tobuy;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.wellmax8.tobuy.data.toBuyContract.categories_entry;
import com.wellmax8.tobuy.data.toBuyContract;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class cursor_adapter_categories extends CursorAdapter {


    public cursor_adapter_categories(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        //view.generateViewId();
        //view.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(categories_entry.COLUMN_ID_CATEGORY))));

        TextView nameView=view.findViewById(R.id.item_name);
        TextView relatedToView=view.findViewById(R.id.item_relate_to);
        TextView createAtView=view.findViewById(R.id.item_created_at);
        TextView lastEditView=view.findViewById(R.id.item_lastEdit);

        String  name=cursor.getString(cursor.getColumnIndex(categories_entry.COLUMN_NAME));
        String  relatedTo=cursor.getString(cursor.getColumnIndex(categories_entry.COLUMN_RELATED_TO));
        String  createdAt=cursor.getString(cursor.getColumnIndex(categories_entry.COLUMN_CREATED_AT));
        String  lastEdit=cursor.getString(cursor.getColumnIndex(categories_entry.COLUMN_LAST_EDIT));
        int color=cursor.getInt(cursor.getColumnIndex(categories_entry.COLUMN_BACKGROUND_COLOR));


        if (relatedTo.equals("")){
            relatedToView.setVisibility(View.GONE);
        }
        LinearLayout textContainer= view.findViewById(R.id.item_layout);
        GradientDrawable gradientDrawable=(GradientDrawable) textContainer.getBackground();

        switch (color){

            case categories_entry.green:
            {
                gradientDrawable.setColor(ContextCompat.getColor(context, R.color.green));
                break;

            }
            case categories_entry.blue:
            {
                gradientDrawable.setColor(ContextCompat.getColor(context, R.color.blue));
                break;
            }


            case categories_entry.purple:
            {
                gradientDrawable.setColor(ContextCompat.getColor(context, R.color.purple));
                break;

            }
            case categories_entry.yellow:
            {
                gradientDrawable.setColor(ContextCompat.getColor(context, R.color.yellow));
                break;
            }
            case categories_entry.red:
            {
                gradientDrawable.setColor(ContextCompat.getColor(context, R.color.red));
                break;

            }
            default:

        }

        nameView.setText(context.getString(R.string.name)+" "+name);
        relatedToView.setText(context.getString(R.string.related_to)+" "+relatedTo);


        Date dateCreateAt=new Date(Long.parseLong(createdAt)*1000);
        SimpleDateFormat dateFormat=new SimpleDateFormat("MMMM, dd");
        String dateCreateAtStr=dateFormat.format(dateCreateAt);
        createAtView.setText(context.getString(R.string.created_at)+" "+dateCreateAtStr);




        Date dateLastEdit = new Date(Long.parseLong(lastEdit)*1000);
        SimpleDateFormat dateFormat1=new SimpleDateFormat("MMMM, dd HH:mm a");
        String dateLastEditStr=dateFormat1.format(dateLastEdit);
        lastEditView.setText(context.getString(R.string.last_edit)+" "+dateLastEditStr);

    }
}
