package com.machinetest.ajinkya.other.api;



import com.machinetest.ajinkya.dashboard.home.data.CityResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //http://api.openweathermap.org/data/2.5/weather?q=Pune&appid=094aa776d64c50d5b9e9043edd4ffd00
    @GET("weather?")
    Call<CityResponse> providerRegister(@Query("q") String Q, @Query("appid") String appid);


}