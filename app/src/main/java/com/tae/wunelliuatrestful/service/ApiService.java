package com.tae.wunelliuatrestful.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tae.wunelliuatrestful.api.RestfulAdapter;
import com.tae.wunelliuatrestful.model.Constants;
import com.tae.wunelliuatrestful.model.Coordinate;
import com.tae.wunelliuatrestful.model.UserLocation;

import java.security.Timestamp;
import java.util.Date;

/**
 * Created by Eduardo on 04/05/2016.
 */
public class ApiService extends IntentService {


    private static final String TAG = ApiService.class.getSimpleName();

    public ApiService() {
        super(TAG);
    }

    public static Intent makeIntent(Context context, String action) {
        return new Intent(context, ApiService.class).setAction(action);
    }

    public static Intent makeIntent(Context context, String action, Location location) {
        return new Intent(context, ApiService.class).setAction(action).putExtra(Constants.EXTRA_LOCATION, location);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        RestfulAdapter restfulAdapter = new RestfulAdapter(getApplicationContext());
        if (intent.getAction().equals(Constants.ACTION_DOWNLOAD_SUCCESS)) {
            restfulAdapter.getRoutes();
        } else {
            UserLocation userLocation = createUserLocation(intent);
            restfulAdapter.postLocation(userLocation);
        }
    }

    @NonNull
    private UserLocation createUserLocation(Intent intent) {
        Location location = intent.getParcelableExtra(Constants.EXTRA_LOCATION);
        Long ts = location.getTime();
        Date date = new Date(ts);
        Coordinate coordinate = new Coordinate(location.getLatitude(), location.getLongitude());
        return new UserLocation(
                coordinate,
                location.getSpeed(),
                0,
                (int)location.getAccuracy(),
                date.toString());
    }
}
