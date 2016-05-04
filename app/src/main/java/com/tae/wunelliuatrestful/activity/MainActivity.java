package com.tae.wunelliuatrestful.activity;

import android.content.IntentFilter;
import android.location.Location;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tae.wunelliuatrestful.adapter.ListAdapter;
import com.tae.wunelliuatrestful.model.Constants;
import com.tae.wunelliuatrestful.R;
import com.tae.wunelliuatrestful.service.LocationService;
import com.tae.wunelliuatrestful.service.OnReceiverListener;
import com.tae.wunelliuatrestful.receiver.RestfulReceiver;
import com.tae.wunelliuatrestful.model.Route;
import com.tae.wunelliuatrestful.service.ApiService;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnReceiverListener {

    private RestfulReceiver restfulReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listViewRoutes);
        ListAdapter adapter = new ListAdapter(routes, this);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void postLocation(Location location) {
        startService(ApiService.makeIntent(this, Constants.ACTION_POST_LOCATION, location));
    }
}
