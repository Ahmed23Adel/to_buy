package com.wellmax8.tobuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.wellmax8.tobuy.ViewModel.VM_add_category;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wellmax8.tobuy.data.toBuyContract.categories_entry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int MAIN_LOADER = 0;
    ImageView main_imageView_add;
    FloatingActionButton fab;
    ListView main_listView;
    TextView main_textView_add;
    cursor_adapter_categories mCursor_adapter_categories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        VM_add_category vm_add_category=new ViewModelProvider(this).get(VM_add_category.class);
        vm_add_category.setContext(this);


      /*  vm_add_category.getCategoriesOrderedCreatedAtDESC().observe(this, new Observer<List<category>>() {
            @Override
            public void onChanged(List<category> categories) {
                if (categories.size()>0){
                    Log.v("main",">0");
                }else{
                    Log.v("main","=0");

                }
                for(category c: categories){
                    Log.v("main",c.getName());
                }
            }
        });*/


        main_imageView_add = findViewById(R.id.main_imageView_add);
        fab = findViewById(R.id.main_fab);
        main_listView = findViewById(R.id.category_recyclerView);
        main_textView_add = findViewById(R.id.main_textView_add);

        mCursor_adapter_categories = new cursor_adapter_categories(this, null);
        main_listView.setAdapter(mCursor_adapter_categories);




        setting_onClick_listeners();
        setting_onClick_listeners_listView();


        main_imageView_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, com.wellmax8.tobuy.View.add_category .class);
                i.setData(null);
                startActivity(i);
            }
        });



        getLoaderManager().initLoader(MAIN_LOADER, null, this);


    }

    private void setting_onClick_listeners_listView() {
        main_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView nameView = view.findViewById(R.id.item_category_name);
                String name= nameView.getText().toString();
                String [] nameC=name.split(":",-1);
                Intent i = new Intent(MainActivity.this, things.class);
                i.putExtra(categories_entry.COLUMN_ID_CATEGORY,String.valueOf(id));
                i.putExtra("nameC",nameC[1]);
                startActivity(i);


                //Log.v("main",String.valueOf(id));
            }
        });

        main_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, add_category.class);
                i.setData(Uri.parse(categories_entry.CONTENT_URI+"/"+String.valueOf(id)));
                startActivity(i);
                return false;
            }
        });
    }


    private void setting_onClick_listeners() {
        //setting on click listener for floating action button to go to add_catrgory activity to add new one
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, add_category.class);
                i.setData(null);
                startActivity(i);
            }
        });
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String [] projection ={
            categories_entry.COLUMN_ID_CATEGORY,
            categories_entry.COLUMN_NAME,
            categories_entry.COLUMN_RELATED_TO,
            categories_entry.COLUMN_CREATED_AT,
            categories_entry.COLUMN_LAST_EDIT,
            categories_entry.COLUMN_BACKGROUND_COLOR,
        };


        return new CursorLoader(this,
                categories_entry.CONTENT_URI,
                projection,
                null,
                null,
                null) ;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.getCount()>0) {
                main_listView.setVisibility(View.VISIBLE);
                main_imageView_add.setVisibility(View.GONE);
                main_textView_add.setVisibility(View.GONE);
                mCursor_adapter_categories.swapCursor(data);
            }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursor_adapter_categories.swapCursor(null);
    }
}
