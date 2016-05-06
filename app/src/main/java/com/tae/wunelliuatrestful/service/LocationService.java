package com.tae.wunelliuatrestful.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.tae.wunelliuatrestful.model.Constants;

/**
 * Created by Eduardo on 04/05/2016.
 */
public class LocationService extends Service implements LocationListener {

    private static final String TAG = LocationService.class.getSimpleName();

    public static Intent makeIntent(Context context) {
        return new Intent(context, LocationService.class);
    }

    @Override
    public void onLocationChanged(Location location) {
        LocalBroadcastManager.getInstance(getApplicationContext())
                .sendBroadcast(new Intent(Constants.ACTION_LOCATION_PROVIDED)
                        .putExtra(Constants.EXTRA_LOCATION, location));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getApplicationContext(), "Location disable", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initLocationManager();
        return START_NOT_STICKY;
    }

    private void initLocationManager() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
