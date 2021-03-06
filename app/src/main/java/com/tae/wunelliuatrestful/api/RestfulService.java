package com.tae.wunelliuatrestful.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tae.wunelliuatrestful.model.Route;
import com.tae.wunelliuatrestful.model.UserLocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Eduardo on 04/05/2016.
 */
public interface RestfulService {

    @GET("journeys")
    Call<List<Route>> getJourneys();


    @POST("user/location")
    Call<Response<UserLocation>> postUserLocation(@Body UserLocation userLocation);
}


