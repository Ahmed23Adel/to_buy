package com.wellmax8.tobuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wellmax8.tobuy.data.toBuyContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class things extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private static int categoryID;

    private static final int things_LOADER = 0;
    ImageView things_imageView_add;
    ListView things_listView;
    TextView things_textView_add;

    private static final int THINGS_LOADER = 0;
    private static String nameC;
    //views
    FloatingActionButton fab;


    // TODO
    cursor_adapter_things mCursor_adapter_categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things);


        things_imageView_add = findViewById(R.id.things_imageView_add);
        fab = findViewById(R.id.things_fab);
        things_listView = findViewById(R.id.things_listView);
        things_textView_add = findViewById(R.id.things_textView_add);



        fab = findViewById(R.id.things_fab);
        setting_onClick_listeners();


        categoryID=Integer.parseInt(getIntent().getStringExtra(toBuyContract.categories_entry.COLUMN_ID_CATEGORY));
        nameC=getIntent().getStringExtra("nameC");

        setTitle(nameC);

        mCursor_adapter_categories = new cursor_adapter_things(this, null);
        things_listView.setAdapter(mCursor_adapter_categories);


        setting_onClick_listeners_listView();

        getLoaderManager().initLoader(THINGS_LOADER, null, this);

    }


    private void setting_onClick_listeners() {
        //setting on click listener for floating action button to go to add_catrgory activity to add new one
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(things.this, add_thing.class);
                //Log.v("main","here"+String.valueOf(categoryID));
                i.putExtra("categoryID",String.valueOf(categoryID));
                i.putExtra("thingID","-1");
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.statitics,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        switch (id){
            case R.id.stat:{
                Intent i =  new Intent(this,stat_things.class);
                i.putExtra("nameC",nameC);
                i.putExtra("categoryID",String.valueOf(categoryID));
                startActivity(i);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setting_onClick_listeners_listView() {



        things_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(things.this, add_thing.class);
                i.putExtra("categoryID",String.valueOf(categoryID));
                i.putExtra("thingID",String.valueOf(id));
                startActivity(i);
                return false;
            }
        });



    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String [] projection ={
                toBuyContract.things_entry.COLUMN_ID_THING,
                toBuyContract.things_entry.COLUMN_ID_CATEGORY,
                toBuyContract.things_entry.COLUMN_NAME,
                toBuyContract.things_entry.COLUMN_EXPECTED_PRICE,
                toBuyContract.things_entry.COLUMN_WHERE_TO_BUY,
                toBuyContract.things_entry.COLUMN_BOUGHT_OR_NOT,
                toBuyContract.things_entry.COLUMN_DESCRIPTION,
                toBuyContract.things_entry.COLUMN_BACKGROUND_COLOR,
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
        //Log.v("main","thing 2 ");

        if (data!=null) {
            if (data.getCount() > 0) {
                things_listView.setVisibility(View.VISIBLE);
                things_imageView_add.setVisibility(View.GONE);
                things_textView_add.setVisibility(View.GONE);
                mCursor_adapter_categories.swapCursor(data);
                setting_onClick_listeners_listView();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursor_adapter_categories.swapCursor(null);

    }
}