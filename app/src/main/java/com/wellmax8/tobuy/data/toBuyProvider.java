package com.wellmax8.tobuy.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.data.toBuyContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class toBuyProvider extends ContentProvider {




    //paths
    private final static int PATH_CATEGORIES=100;
    private final static int PATH_CATEGORIES_ID=101;
    private final static int PATH_THING=200;
    private final static int PATH_THING_1=200;
    private final static int PATH_THING_ID=201;
    private final static int PATH_SELLER=300;
    private final static int PATH_SELLER_ID=301;
    private final static UriMatcher sUriMatcher= new UriMatcher(UriMatcher.NO_MATCH);
    private toBuyDbHelper toBuyDbHelper;



    static{

        sUriMatcher.addURI(toBuyContract.CONTENT_AUTHORITY,toBuyContract.CATEGORIES_PATH,PATH_CATEGORIES);
        sUriMatcher.addURI(toBuyContract.CONTENT_AUTHORITY,toBuyContract.CATEGORIES_PATH+"/#",PATH_CATEGORIES_ID);
        sUriMatcher.addURI(toBuyContract.CONTENT_AUTHORITY,toBuyContract.THINGS_PATH,PATH_THING_1);
        sUriMatcher.addURI(toBuyContract.CONTENT_AUTHORITY,toBuyContract.THINGS_PATH+"/"+"categoryID"+"/"+"#",PATH_THING);
        sUriMatcher.addURI(toBuyContract.CONTENT_AUTHORITY,toBuyContract.THINGS_PATH+"/"+"categoryID"+"/"+"#"+"/"+"#",PATH_THING_ID);
        sUriMatcher.addURI(toBuyContract.CONTENT_AUTHORITY,toBuyContract.SELLER_PATH,PATH_SELLER);
        sUriMatcher.addURI(toBuyContract.CONTENT_AUTHORITY,toBuyContract.SELLER_PATH+"/#",PATH_SELLER_ID);

    }



    @Override
    public boolean onCreate() {
        toBuyDbHelper= new toBuyDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int match = sUriMatcher.match(uri);
        switch (match) {

            case PATH_CATEGORIES: {
                SQLiteDatabase db = toBuyDbHelper.getReadableDatabase();
                Cursor cursor = db.query(toBuyContract.categories_entry.TABLE_NAME, projection, null, null, null, null, null);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }

            case PATH_CATEGORIES_ID: {
                SQLiteDatabase db = toBuyDbHelper.getReadableDatabase();
                selection = toBuyContract.categories_entry.COLUMN_ID_CATEGORY + " =?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                Cursor cursor = db.query(toBuyContract.categories_entry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                return cursor;
            }


            case PATH_THING: {
                SQLiteDatabase db = toBuyDbHelper.getReadableDatabase();
                selection = toBuyContract.things_entry.COLUMN_ID_CATEGORY + " =?";

                Cursor cursor = db.query(toBuyContract.things_entry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case PATH_THING_ID:
            {
                SQLiteDatabase db = toBuyDbHelper.getReadableDatabase();
                selection = toBuyContract.things_entry.COLUMN_ID_THING + " =?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                Cursor cursor = db.query(toBuyContract.things_entry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                return cursor;
            }



            default:
                // Log.v("main","no path founded");
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match= sUriMatcher.match(uri);
        switch (match){
            case PATH_CATEGORIES:
                return insert_category(uri,values);
            case PATH_CATEGORIES_ID:
                throw new IllegalArgumentException("in Insert you cannot add category with specific id it will automatically be created");

            case PATH_THING_1:
            {
                return insert_thing(uri,values);
            }

        }



        return null;
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db=toBuyDbHelper.getWritableDatabase();

        int match=sUriMatcher.match(uri);
        switch (match){

            case PATH_CATEGORIES_ID:{
                return update_category(uri,values,selection,selectionArgs);
            }

            case PATH_THING_ID:
            {
                return updatae_thing(uri,values,selection,selectionArgs);
            }
        }

        return 0;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db=toBuyDbHelper.getWritableDatabase();

        int match=sUriMatcher.match(uri);
        switch (match) {

            case PATH_CATEGORIES_ID:
            {
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                int id = db.delete(toBuyContract.categories_entry.TABLE_NAME, toBuyContract.categories_entry.COLUMN_ID_CATEGORY + "=?", selectionArgs);
                getContext().getContentResolver().notifyChange(toBuyContract.categories_entry.CONTENT_URI, null);
                delete_things_category_id(selectionArgs);
                return id;
            }

            case PATH_THING_ID:
            {
                return delete_thing_id(uri,selection,selectionArgs);
            }
        }

        return 0;
    }




    private Uri insert_thing(Uri uri, ContentValues values) {
        SQLiteDatabase db= toBuyDbHelper.getWritableDatabase();

        if (values.getAsInteger(toBuyContract.things_entry.COLUMN_ID_THING)!=null){
            throw new IllegalArgumentException("no need to add ID. it's added automatically");
        }

        if(values.getAsString(toBuyContract.things_entry.COLUMN_ID_CATEGORY)==null){
            throw new IllegalArgumentException("You cannot add new item without the ID of category");
        }
        if(values.getAsString(toBuyContract.things_entry.COLUMN_NAME)==null){
            throw new IllegalArgumentException("You cannot add new item without a name for it");
        }

        if (values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.red)) ||
                values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.yellow))||
                values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.blue))||
                values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.purple))||
                values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.green))
        ){}else{
            throw new IllegalArgumentException("Your are restricted in background color in 5 option" +
                    " 1-"+"#BF6262" + "stands for 1 \n"
                    +"2-"+"#E7BF74"+ "stands for 2 \n"
                    +"3-"+"#4E84BF"+ "stands for 3 \n"
                    +"4-"+"#808EDF"+ "stands for 4 \n"
                    +"5-"+"#6CB1AC"+ "stands for 5 \n"
            );
        }

        Long id = db.insert(toBuyContract.things_entry.TABLE_NAME,null,values);
        if (id !=-1){
            Long tsLong = System.currentTimeMillis()/1000;
            String ts = tsLong.toString();
            ContentValues valuesCategories=new ContentValues();
            valuesCategories.put(toBuyContract.categories_entry.COLUMN_LAST_EDIT,ts);
            String [] selectionArgs= new String[]{String.valueOf(values.getAsInteger(toBuyContract.things_entry.COLUMN_ID_CATEGORY))};
            db.update(toBuyContract.categories_entry.TABLE_NAME,valuesCategories,toBuyContract.categories_entry.COLUMN_ID_CATEGORY+"=?",selectionArgs);
            getContext().getContentResolver().notifyChange(toBuyContract.categories_entry.CONTENT_URI, null);
        }
        getContext().getContentResolver().notifyChange(Uri.parse(toBuyContract.things_entry.CONTENT_URI+"/"+"categoryID"+"/"+values.getAsInteger(toBuyContract.things_entry.COLUMN_ID_CATEGORY)),null);
        return ContentUris.withAppendedId(uri,id);

    }



    public Uri insert_category(Uri uri,ContentValues values){
        SQLiteDatabase db= toBuyDbHelper.getWritableDatabase();

        if (values.getAsInteger(toBuyContract.categories_entry.COLUMN_ID_CATEGORY)!=null){
            throw new IllegalArgumentException("no need to add ID. it's added automatically");
        }
        if(values.getAsString(toBuyContract.categories_entry.COLUMN_NAME)==null){
            throw new IllegalArgumentException("You cannot add new Category with empty name field");
        }
        if (values.getAsInteger(toBuyContract.categories_entry.COLUMN_CREATED_AT)==null){
            throw new IllegalArgumentException("You cannot add new Category with empty CREATED_AT field");
        }
        if (values.getAsInteger(toBuyContract.categories_entry.COLUMN_LAST_EDIT)==null){
            throw new IllegalArgumentException("You cannot add new Category with empty LAST_EDIT field");
        }
        if (values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.red)) ||
                values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.yellow))||
                values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.blue))||
                values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.purple))||
                values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.green))
        ){}else{
            throw new IllegalArgumentException("Your are restricted in background color in 5 option" +
                    " 1-"+"#BF6262" + "stands for 1 \n"
                    +"2-"+"#E7BF74"+ "stands for 2 \n"
                    +"3-"+"#4E84BF"+ "stands for 3 \n"
                    +"4-"+"#808EDF"+ "stands for 4 \n"
                    +"5-"+"#6CB1AC"+ "stands for 5 \n"
            );
        }

        String name=values.getAsString(toBuyContract.categories_entry.COLUMN_NAME);
        String related_to=values.getAsString(toBuyContract.categories_entry.COLUMN_RELATED_TO);
        if (name_relatedTo_exist(name,related_to)){
            throw new IllegalArgumentException(toBuyContract.SAME_NAME_RELATED_TO_EXIST);
        }

        Long id = db.insert(toBuyContract.categories_entry.TABLE_NAME,null,values);
        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri,id);
    }





    private int delete_thing_id(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs){
        SQLiteDatabase db=toBuyDbHelper.getWritableDatabase();

        selectionArgs= new String[]{String.valueOf(ContentUris.parseId(uri))};
        int id = db.delete(toBuyContract.things_entry.TABLE_NAME,selection,selectionArgs);
        String Suri=String.valueOf(uri);
        String [] arrayUri=Suri.split("/",-1);
        long categoryID= Long.parseLong(arrayUri[5]);
        if (id !=-1){
            Long tsLong = System.currentTimeMillis()/1000;
            String ts = tsLong.toString();
            ContentValues valuesCategories=new ContentValues();
            valuesCategories.put(toBuyContract.categories_entry.COLUMN_LAST_EDIT,ts);
            String [] selectionArgsCategories= new String[]{String.valueOf(categoryID)};
            db.update(toBuyContract.categories_entry.TABLE_NAME,valuesCategories,toBuyContract.categories_entry.COLUMN_ID_CATEGORY+"=?",selectionArgsCategories);
            getContext().getContentResolver().notifyChange(toBuyContract.categories_entry.CONTENT_URI, null);
        }

        getContext().getContentResolver().notifyChange(Uri.parse(toBuyContract.things_entry.CONTENT_URI+"/"+"categoryID"+"/"+categoryID),null);
        return id;
    }


    private int update_category(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs){
        SQLiteDatabase db=toBuyDbHelper.getWritableDatabase();

        if (values.getAsInteger(toBuyContract.categories_entry.COLUMN_ID_CATEGORY)!=null){
            throw new IllegalArgumentException("no need to add ID. it's added automatically");
        }
        if(values.getAsString(toBuyContract.categories_entry.COLUMN_NAME)==null){
            throw new IllegalArgumentException("You cannot add new Category with empty name field");
        }
        if (values.getAsInteger(toBuyContract.categories_entry.COLUMN_CREATED_AT)==null){
            throw new IllegalArgumentException("You cannot add new Category with empty CREATED_AT field");
        }
        if (values.getAsInteger(toBuyContract.categories_entry.COLUMN_LAST_EDIT)==null){
            throw new IllegalArgumentException("You cannot add new Category with empty LAST_EDIT field");
        }
        if (values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.red)) ||
                values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.yellow))||
                values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.blue))||
                values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.purple))||
                values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.green))
        ){}else{
            throw new IllegalArgumentException("Your are restricted in background color in 5 option" +
                    " 1-"+"#BF6262" + "stands for 1 \n"
                    +"2-"+"#E7BF74"+ "stands for 2 \n"
                    +"3-"+"#4E84BF"+ "stands for 3 \n"
                    +"4-"+"#808EDF"+ "stands for 4 \n"
                    +"5-"+"#6CB1AC"+ "stands for 5 \n"
            );
        }

        selectionArgs= new String[]{String.valueOf(ContentUris.parseId(uri))};
        int id= db.update(toBuyContract.categories_entry.TABLE_NAME,values,toBuyContract.categories_entry.COLUMN_ID_CATEGORY+"=?",selectionArgs);
        getContext().getContentResolver().notifyChange(toBuyContract.categories_entry.CONTENT_URI, null);
        return  id;
    }


    private int updatae_thing(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs){
        SQLiteDatabase db=toBuyDbHelper.getWritableDatabase();

        if (values.getAsInteger(toBuyContract.things_entry.COLUMN_ID_THING)!=null){
            throw new IllegalArgumentException("no need to add ID. it's added automatically");
        }

        if(values.getAsString(toBuyContract.things_entry.COLUMN_ID_CATEGORY)==null){
            throw new IllegalArgumentException("You cannot add new item without the ID of category");
        }
        if(values.getAsString(toBuyContract.things_entry.COLUMN_NAME)==null){
            throw new IllegalArgumentException("You cannot add new item without a name for it");
        }

        if (values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR)!=null) {
            if (values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.red)) ||
                    values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.yellow)) ||
                    values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.blue)) ||
                    values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.purple)) ||
                    values.getAsString(toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR).equals(String.valueOf(toBuyContract.categories_entry.green))
            ) {
            } else {
                throw new IllegalArgumentException("Your are restricted in background color in 5 option" +
                        " 1-" + "#BF6262" + "stands for 1 \n"
                        + "2-" + "#E7BF74" + "stands for 2 \n"
                        + "3-" + "#4E84BF" + "stands for 3 \n"
                        + "4-" + "#808EDF" + "stands for 4 \n"
                        + "5-" + "#6CB1AC" + "stands for 5 \n"
                );
            }
        }
        selectionArgs= new String[]{String.valueOf(ContentUris.parseId(uri))};
        int id = db.update(toBuyContract.things_entry.TABLE_NAME,values,toBuyContract.things_entry.COLUMN_ID_THING+" =?",selectionArgs);
        String Suri=String.valueOf(uri);
        String [] arrayUri=Suri.split("/",-1);
        long categoryID= Long.parseLong(arrayUri[5]);
        if (id !=-1){
            Long tsLong = System.currentTimeMillis()/1000;
            String ts = tsLong.toString();
            ContentValues valuesCategories=new ContentValues();
            valuesCategories.put(toBuyContract.categories_entry.COLUMN_LAST_EDIT,ts);
            String [] selectionArgsCategories= new String[]{String.valueOf(categoryID)};
            db.update(toBuyContract.categories_entry.TABLE_NAME,valuesCategories,toBuyContract.categories_entry.COLUMN_ID_CATEGORY+"=?",selectionArgsCategories);
            getContext().getContentResolver().notifyChange(toBuyContract.categories_entry.CONTENT_URI, null);
        }
        getContext().getContentResolver().notifyChange(Uri.parse(toBuyContract.things_entry.CONTENT_URI+"/"+"categoryID"+"/"+categoryID),null);
        //Log.v("main","main1");
        //Log.v("main",values.getAsString(toBuyContract.things_entry.COLUMN_BOUGHT_OR_NOT));
        return id;
    }

    private boolean name_relatedTo_exist(String name,String related_to){
        String []projection={toBuyContract.categories_entry.COLUMN_ID_CATEGORY};
        String selection=toBuyContract.categories_entry.COLUMN_NAME+" =? AND "+toBuyContract.categories_entry.COLUMN_RELATED_TO+" =?";
        String [] selectionArgs=new String[]{name,related_to};
        SQLiteDatabase db=toBuyDbHelper.getReadableDatabase();
        Cursor c= db.query(toBuyContract.categories_entry.TABLE_NAME,projection,selection,selectionArgs,null,null,null,null);
        if(c.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    private void delete_things_category_id(String[] selectionArgs) {
        SQLiteDatabase db=toBuyDbHelper.getWritableDatabase();
        String selection= toBuyContract.things_entry.COLUMN_ID_CATEGORY+" =?";
        int id = db.delete(toBuyContract.things_entry.TABLE_NAME,selection,selectionArgs);

    }
}