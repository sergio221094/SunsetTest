package com.example.sunsettest.Controllers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String url = "http://www.encicla.gov.co";
    public static final String port = ":80";

    public static final String Base_url = url+port;
    public static Retrofit retrofit = null;

    public static Retrofit getApiclient(){
        if( retrofit == null ){
            retrofit = new Retrofit.Builder().baseUrl(Base_url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}