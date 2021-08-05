package com.ovi.demotask.retrofit;

import com.ovi.demotask.model.Response;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DemoService {

    @GET("get_categories")
    Call<Response> get_categories();

}
