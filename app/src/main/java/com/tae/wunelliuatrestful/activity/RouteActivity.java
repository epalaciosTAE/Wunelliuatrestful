package com.tae.wunelliuatrestful.activity;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tae.wunelliuatrestful.R;
import com.tae.wunelliuatrestful.model.Constants;
import com.tae.wunelliuatrestful.model.Route;

/**
 * Created by Eduardo on 04/05/2016.
 */
public class RouteActivity  extends AppCompatActivity implements OnMapReadyCallback{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Route route = getIntent().getParcelableExtra(Constants.EXTRA_ROUTE);
            googleMap.setMyLocationEnabled(true);

            LatLng locationStart = new LatLng(route.getStartCoordinate().getLat(), route.getStartCoordinate().getLng());
            LatLng locationEnd = new LatLng(route.getEndCoordinate().getLat(), route.getStartCoordinate().getLng());

            googleMap.addMarker(new MarkerOptions().position(locationStart));
            googleMap.addMarker(new MarkerOptions().position(locationEnd));


            CameraUpdateFactory.newCameraPosition(new CameraPosition(
                    locationStart,
                    15,
                    90,
                    90
            ));
        } else {
            // Show rationale and request permission.
        }
    }
}
