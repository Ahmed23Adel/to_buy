package com.wellmax8.tobuy.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import androidx.annotation.Nullable;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.category_builder;

public class toBuyDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME="toBuy.db";
    private static final int DATABASE_VERSION=1;


    public toBuyDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_CATEGORY="CREATE TABLE "+ toBuyContract.categories_entry.TABLE_NAME
                +"( "+toBuyContract.categories_entry.COLUMN_ID_CATEGORY+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +toBuyContract.categories_entry.COLUMN_NAME+" TEXT NOT NULL,"
                +toBuyContract.categories_entry.COLUMN_CREATED_AT+" INTEGER NOT NULL,"
                +toBuyContract.categories_entry.COLUMN_LAST_EDIT+" INTEGER NOT NULL,"
                +toBuyContract.categories_entry.COLUMN_RELATED_TO+" TEXT,"
                +toBuyContract.categories_entry.COLUMN_DESCRIPTION+" TEXT,"
                +toBuyContract.categories_entry.COLUMN_BACKGROUND_COLOR+" TEXT,"
                +toBuyContract.categories_entry.COLUMN_EXTRA+" TEXT"
                +");";

        String SQL_CREATE_THINGS="CREATE TABLE "+ toBuyContract.things_entry.TABLE_NAME
                +"( "+toBuyContract.things_entry.COLUMN_ID_THING+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +toBuyContract.things_entry.COLUMN_ID_CATEGORY+" INTEGER NOT NULL,"
                +toBuyContract.things_entry.COLUMN_ID_SELLER+" INTEGER,"
                +toBuyContract.things_entry.COLUMN_NAME+" TEXT NOT NULL,"
                +toBuyContract.things_entry.COLUMN_DESCRIPTION+" TEXT,"
                +toBuyContract.things_entry.COLUMN_EXPECTED_PRICE+" REAL,"
                +toBuyContract.things_entry.COLUMN_WHERE_TO_BUY+" TEXT,"
                +toBuyContract.things_entry.COLUMN_EXTRA_INFO+" TEXT,"
                +toBuyContract.things_entry.COLUMN_BACKGROUND_COLOR+" TEXT,"
                +toBuyContract.things_entry.COLUMN_BOUGHT_OR_NOT+" INTEGER"
                +");";


        String SQL_CREATE_SELLER="CREATE TABLE "+ toBuyContract.seller_entry.TABLE_NAME
                +"( "+toBuyContract.seller_entry.COLUMN_ID_SELLER+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +toBuyContract.seller_entry.COLUMN_SELLER_NAME+" TEXT NOT NULL"
                +");";

        String SQL_CREATE_SELLER_INFO="CREATE TABLE "+ toBuyContract.seller_info_entry.TABLE_NAME
                +"( "+toBuyContract.seller_info_entry.COLUMN_ID_SELLER+" INTEGER NOT NULL,"
                +toBuyContract.seller_info_entry.COLUMN_SELLER_NUMBER+" TEXT ,"
                +toBuyContract.seller_info_entry.COLUMN_SELLER_ADDRESS+" TEXT ,"
                +toBuyContract.seller_info_entry.COLUMN_SELLER_INFO_TYPE+" TEXT NOT NULL"
                +");";


        db.execSQL(SQL_CREATE_CATEGORY);
        db.execSQL(SQL_CREATE_THINGS);
        db.execSQL(SQL_CREATE_SELLER);
        db.execSQL(SQL_CREATE_SELLER_INFO);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}