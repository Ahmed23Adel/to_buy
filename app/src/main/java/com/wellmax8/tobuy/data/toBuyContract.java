package com.wellmax8.tobuy.data;

import android.net.Uri;
import android.provider.BaseColumns;

//toBuyContract is a contract class the hold all tables info
//final class :means it cannot be extended
public final class toBuyContract {


    public final static String CONTENT_AUTHORITY="com.wellmax8.android.toBuy";
    private final static Uri BASE_URI=Uri.parse("content://"+CONTENT_AUTHORITY);
    public final static String CATEGORIES_PATH="categories";
    public final static String THINGS_PATH="things";
    public final static String SELLER_PATH="seller";


    public final static String SAME_NAME_RELATED_TO_EXIST="a category with the same name the related to already exist";


    //table that holds all categories info
    //final :it cannot be extended
    //static : can be accessed using the class name only not by creating an object of toBuyContract
    public final static class categories_entry implements BaseColumns {
        public final static String TABLE_NAME="categories";

        //columns names
        public final static String COLUMN_ID_CATEGORY=BaseColumns._ID;
        public final static String COLUMN_NAME="name";
        public final static String COLUMN_CREATED_AT="created_at";
        public final static String COLUMN_LAST_EDIT="last_edit";
        public final static String COLUMN_RELATED_TO="related_to";
        public final static String COLUMN_DESCRIPTION="description";
        public final static String COLUMN_EXTRA="extra";
        public final static String COLUMN_BACKGROUND_COLOR="background_color";

        //public final static String image="description";
        public final static Uri CONTENT_URI=Uri.withAppendedPath(BASE_URI,CATEGORIES_PATH);




        public final static int red=0;
        public final static int yellow=1;
        public final static int blue=3;
        public final static int purple=4;
        public final static int green=5;

    }




    //table for things that the use want to buy in each table
    public final static class things_entry implements BaseColumns{
        public final static String TABLE_NAME="things";

        //columns names
        public final static String COLUMN_ID_CATEGORY=BaseColumns._ID+"_category";
        public final static String COLUMN_ID_THING=BaseColumns._ID;
        /*
         * ID_SELLER =
         * 1A5A6
         * each number represents the seller id and it will be split upon the "A"
         * */
        public final static String COLUMN_ID_SELLER=BaseColumns._ID+"_seller";
        public final static String COLUMN_NAME="name";
        public final static String COLUMN_DESCRIPTION="description";
        public final static String COLUMN_EXPECTED_PRICE="expected_price";
        public final static String COLUMN_WHERE_TO_BUY="where_to_buy";
        public final static String COLUMN_EXTRA_INFO="extra_info";
        //this is a boolean column 1 =bought 0=not boug
        public final static String COLUMN_BOUGHT_OR_NOT="bought_or_not";
        public final static String COLUMN_BACKGROUND_COLOR="background_color";
        //ICON
        //public final static String ICON="bout_or not";

        //constants
        public final static int BOUGHT=1;
        public final static int NOT_BOUGHT=0;
        // A represets the thing that will be split upon in seller id
        public final static String COLUMNS_ID_SELLER_BORDER="A";

        public final static Uri CONTENT_URI=Uri.withAppendedPath(BASE_URI,THINGS_PATH);


    }


    /* table for seller info
     * but because that the seller might have several contact number we split it. so for each seller id it would have several rows contains contact number of that seller.
     *it would be conneted to each by id seller for each one
     */
    public final static class seller_entry implements BaseColumns{
        public final static String TABLE_NAME="seller";

        public final static String COLUMN_ID_SELLER=BaseColumns._ID+"_seller";
        public final static String COLUMN_SELLER_NAME="name";

        public final static Uri CONTENT_URI=Uri.withAppendedPath(BASE_URI,SELLER_PATH);

    }




    public final static class seller_info_entry implements BaseColumns{
        public final static String TABLE_NAME="seller_info";

        public final static String COLUMN_ID_SELLER=BaseColumns._ID+"_seller";
        public final static String COLUMN_SELLER_NUMBER="number";
        public final static String COLUMN_SELLER_ADDRESS="address";
        public final static String COLUMN_SELLER_INFO_TYPE="seller_info_type";


        //constants
        public final static String SELLER_NUMBER="NUMBER";
        public final static String SELLER_ADDRESS="ADDRESS";


    }


}