package com.ovi.demotask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.ovi.demotask.model.Response;
import com.ovi.demotask.retrofit.AppConfig;
import com.ovi.demotask.retrofit.DemoService;

import retrofit2.Call;
import retrofit2.Callback;

public class ListActivity extends AppCompatActivity {
    DemoService demoService;
    RecyclerView categoryRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        demoService= AppConfig.getRetrofit(this).create(DemoService.class);
        categoryRv=findViewById(R.id.recyclerView);
        categoryRv.setLayoutManager(new LinearLayoutManager(this));
        getCategory();
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this,MainActivity.class));
            }
        });
    }

    private void getCategory() {
        demoService.get_categories().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                try {
                    categoryRv.setAdapter(new CategoryAdapter(ListActivity.this,response.body().getCategories()));
                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}