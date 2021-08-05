package com.ovi.demotask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements CheckInterface{
    RecyclerView horizontalRv;
    CheckInterface checkInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPref.init(this);
        checkInterface=this;
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ListActivity.class));
            }
        });
        horizontalRv=findViewById(R.id.horizontalRv);
        horizontalRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        horizontalRv.setAdapter(new SubCategoryHozAdapter(this,SharedPref.getArrayList("SUBCATEGORY"),MainActivity.this));
        Log.d("sdfsdfsdf", "onCreate: "+SharedPref.getArrayList("SUBCATEGORY"));
    }

    @Override
    public void check() {
        horizontalRv.setAdapter(new SubCategoryHozAdapter(this,SharedPref.getArrayList("SUBCATEGORY"),MainActivity.this));

    }
}