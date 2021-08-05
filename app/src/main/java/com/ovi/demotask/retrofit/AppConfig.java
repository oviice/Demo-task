package com.ovi.demotask.retrofit;

import android.content.Context;

import com.ovi.demotask.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppConfig {
    public static Retrofit getRetrofit(Context context) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(600, TimeUnit.SECONDS)
                .writeTimeout(600, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS)
                .build();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);

        //builder.client(httpClient.build());
        Retrofit retrofit = builder.build();
        return retrofit;
    }
}
