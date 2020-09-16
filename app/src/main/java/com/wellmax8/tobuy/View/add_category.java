package com.wellmax8.tobuy.View;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.wellmax8.tobuy.R;

public class add_category extends AppCompatActivity {

    private EditText category_name;
    private EditText category_relatedTo;
    private EditText category_desc;
    private EditText category_extra;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        instantiateViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_category,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:{
                break;
            }
            case android.R.id.home:{
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void instantiateViews(){
        category_name=findViewById(R.id.add_category_name);
        category_relatedTo=findViewById(R.id.add_category_related_to);
        category_desc=findViewById(R.id.add_category_desc);
        category_extra=findViewById(R.id.add_category_extra);
    }



}
