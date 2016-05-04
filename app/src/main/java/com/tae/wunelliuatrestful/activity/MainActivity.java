package com.tae.wunelliuatrestful.activity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.tae.wunelliuatrestful.adapter.ItemClickListener;
import com.tae.wunelliuatrestful.adapter.ListAdapter;
import com.tae.wunelliuatrestful.model.Constants;
import com.tae.wunelliuatrestful.R;
import com.tae.wunelliuatrestful.service.LocationService;
import com.tae.wunelliuatrestful.service.OnReceiverListener;
import com.tae.wunelliuatrestful.receiver.RestfulReceiver;
import com.tae.wunelliuatrestful.model.Route;
import com.tae.wunelliuatrestful.service.ApiService;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnReceiverListener, ItemClickListener {

    private RestfulReceiver restfulReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initReceiver();
        locationPermission();
    }

    private void initReceiver() {
        restfulReceiver = new RestfulReceiver();
        restfulReceiver.setListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_DOWNLOAD_SUCCESS);
                intentFilter.addAction(Constants.ACTION_LOCATION_PROVIDED);

        LocalBroadcastManager.getInstance(MainActivity.this)
                .registerReceiver(restfulReceiver, intentFilter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(restfulReceiver);
    }

    public void getRoutes(View v) {
        startService(ApiService.makeIntent(this, Constants.ACTION_DOWNLOAD_SUCCESS));
    }

    public void postLocation(View view) {
        startService(LocationService.makeIntent(this));
    }

    @Override
    public void update(List<Route> routes) {
        Toast.makeText(this, "Routes available", Toast.LENGTH_SHORT).show();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listViewRoutes);
        ListAdapter adapter = new ListAdapter(routes, this);
        if (recyclerView != null) {
            adapter.setListener(this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void postLocation(Location location) {
        startService(ApiService.makeIntent(this, Constants.ACTION_POST_LOCATION, location));
    }

    @Override
    public void clickOnListItem(Route route) {
        startActivity(new Intent(this, RouteActivity.class)
                .putExtra(Constants.EXTRA_ROUTE, route));
    }

    private void locationPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        Constants.PERMISION_REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
}
