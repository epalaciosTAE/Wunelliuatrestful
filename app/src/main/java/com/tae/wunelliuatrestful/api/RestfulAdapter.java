package com.tae.wunelliuatrestful.api;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;
import com.tae.wunelliuatrestful.model.Constants;
import com.tae.wunelliuatrestful.model.Route;
import com.tae.wunelliuatrestful.model.UserLocation;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eduardo on 04/05/2016.
 */
public class RestfulAdapter {

    public static final String BASE_URL = "https://www.wunelliuat.com/testingtalent/";
    private static final String TAG = RestfulAdapter.class.getSimpleName();

    private RestfulService restfulService;
    private Context context;

    public RestfulAdapter(Context context) {
        this.context = context;
        OkHttpClient client = getClient();
        restfulService = getService(client);

    }

    public void getRoutes() {
        Call<List<Route>> call = restfulService.getJourneys();
        call.enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {
                Log.i(TAG, "onResponse: ");
                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(new Intent(Constants.ACTION_DOWNLOAD_SUCCESS)
                                .putParcelableArrayListExtra(Constants.EXTRA_ROUTES, (ArrayList<? extends Parcelable>) response.body()));
            }

            @Override
            public void onFailure(Call<List<Route>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    public void postLocation(UserLocation userLocation) {
        Call<UserLocation> call = restfulService.postUserLocation(userLocation);
        call.enqueue(new Callback<UserLocation>() {
            @Override
            public void onResponse(Call<UserLocation> call, Response<UserLocation> response) {
                Log.i(TAG, "onResponse: " + response.message());

            }

            @Override
            public void onFailure(Call<UserLocation> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private RestfulService getService(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build()
                .create(RestfulService.class);
    }

    @NonNull
    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor())
                    .build();
    }
}
