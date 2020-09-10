package com.wellmax8.tobuy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.wellmax8.tobuy.data.toBuyContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class stat_things extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int THINGS_LOADER = 0;
    private static String nameC;
    private static int categoryID;
    private static double totalPrice=0;
    private static double spentPrice=0;
    private static double leftPrice=0;




    TextView mTotal_stat;
    TextView mSpent_stat;
    TextView mleft_stat;
    TextView mBought_stat;
    TextView mNot_bought_stat;
    TextView mCommon_where_to_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_things);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            categoryID = Integer.parseInt(extras.getString("categoryID"));
            nameC =extras.getString("nameC");

            //The key argument here must match that used in the other activity
        }

        setTitle(nameC);


        mTotal_stat= findViewById(R.id.total_stat);
        mSpent_stat= findViewById(R.id.spent_stat);
        mleft_stat= findViewById(R.id.left_stat);
        mBought_stat= findViewById(R.id.bought_stat);
        mNot_bought_stat= findViewById(R.id.not_bought_stat);
        mCommon_where_to_buy= findViewById(R.id.common_where_to_buy);

        getLoaderManager().initLoader(THINGS_LOADER, null, this);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String [] projection ={
                toBuyContract.things_entry.COLUMN_ID_THING,
                toBuyContract.things_entry.COLUMN_NAME,
                toBuyContract.things_entry.COLUMN_EXPECTED_PRICE,
                toBuyContract.things_entry.COLUMN_WHERE_TO_BUY,
                toBuyContract.things_entry.COLUMN_BOUGHT_OR_NOT,

        };

        String [] selectionArga= new String[]{String.valueOf(categoryID)};

        //Log.v("main","thing 1 ");
        return new CursorLoader(this,
                Uri.parse(toBuyContract.things_entry.CONTENT_URI+"/"+"categoryID"+"/"+String.valueOf(categoryID)),
                projection,
                null,
                selectionArga,
                null) ;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        parse_data(data);
        data.close();
    }



    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    private void parse_data(Cursor data) {

        StringBuilder boughtBuilder= new StringBuilder();
        StringBuilder notBoughtBuilder= new StringBuilder();
        int counBought=1;
        int counNotBought=1;

        StringBuilder whereToBuyBuilder= new StringBuilder();
        Map<String,ArrayList<String>> hashWhereToBuy= new HashMap<String, ArrayList<String>>();
        while (data.moveToNext()){
            boolean bought = Integer.valueOf(data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_BOUGHT_OR_NOT))) == toBuyContract.things_entry.BOUGHT;

            if(!data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_EXPECTED_PRICE)).equals("")) {
                double price = Double.parseDouble(data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_EXPECTED_PRICE)));
                totalPrice += price;
                if (bought) {
                    spentPrice += price;
                } else {
                    leftPrice += price;
                }


            }

            if (bought){
                boughtBuilder.append(String.valueOf(counBought)+"-");
                boughtBuilder.append(data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_NAME)).trim());
                if(!data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_EXPECTED_PRICE)).equals("")) {
                    boughtBuilder.append("     price: "+Double.parseDouble(data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_EXPECTED_PRICE))));;
                }
                boughtBuilder.append("\n");
                counBought++;
            }else if(!bought){
                notBoughtBuilder.append(String.valueOf(counNotBought)+"-");
                notBoughtBuilder.append(data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_NAME)).trim());
                if(!data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_EXPECTED_PRICE)).equals("")) {
                    notBoughtBuilder.append("     price: "+Double.parseDouble(data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_EXPECTED_PRICE))));;
                }
                notBoughtBuilder.append("\n");
                counNotBought++;
            }

            if (!data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_WHERE_TO_BUY)).equals("")){
                String whereToBuy=data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_WHERE_TO_BUY));
                String name=data.getString(data.getColumnIndex(toBuyContract.things_entry.COLUMN_NAME)).trim();
                if (!hashWhereToBuy.containsKey(whereToBuy)){
                    ArrayList <String>arrayList=new ArrayList<String>();
                    arrayList.add(name);
                    hashWhereToBuy.put(whereToBuy,arrayList);

                }
                else{
                    ArrayList test=hashWhereToBuy.get(whereToBuy);
                    test.add(name);
                    hashWhereToBuy.put(whereToBuy,test);

                }
            }

        }

        ////////// study this part later
        ////////// study this part later
        ////////// study this part later
        ////////// study this part later

        int countSuper=1;
        Set<String> keys= hashWhereToBuy.keySet();

        for (String i: keys){
            ArrayList test1= hashWhereToBuy.get(i);
            whereToBuyBuilder.append(String.valueOf(countSuper)+"-");
            whereToBuyBuilder.append(i);
            whereToBuyBuilder.append("\n");
            int countSub=1;
            for (int j=0;j<test1.size();j++){
                whereToBuyBuilder.append("     ");
                whereToBuyBuilder.append(String.valueOf(countSuper)+"."+String.valueOf(j+1) +"-");
                whereToBuyBuilder.append(String.valueOf(test1.get(j)));
                whereToBuyBuilder.append("\n");
            }
            countSuper++;
        }

        String whereToBuySentence=whereToBuyBuilder.toString();
        mCommon_where_to_buy.setText(whereToBuySentence);

        data.close();
        String boughtSentence=boughtBuilder.toString();
        String NotBoughtSentence=notBoughtBuilder.toString();

        mTotal_stat.setText(String.valueOf(totalPrice));
        mSpent_stat.setText(String.valueOf(spentPrice));
        mleft_stat.setText(String.valueOf(leftPrice));
        mBought_stat.setText(boughtSentence);
        mNot_bought_stat.setText(NotBoughtSentence);

        //Log.v("main","stat"+String.valueOf(categoryID));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        totalPrice=0;
        spentPrice=0;
        leftPrice=0;
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        totalPrice=0;
        spentPrice=0;
        leftPrice=0;
    }

}

