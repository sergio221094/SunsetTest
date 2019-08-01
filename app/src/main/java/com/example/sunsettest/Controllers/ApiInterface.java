package com.example.sunsettest.Controllers;

import com.example.sunsettest.Models.DataResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("estado")
    Call<DataResponse> getInfo();

}