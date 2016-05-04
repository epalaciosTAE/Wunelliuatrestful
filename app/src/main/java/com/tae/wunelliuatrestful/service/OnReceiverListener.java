package com.tae.wunelliuatrestful.service;

import android.location.Location;

import com.tae.wunelliuatrestful.model.Route;

import java.util.List;

/**
 * Created by Eduardo on 04/05/2016.
 */
public interface OnReceiverListener {

    void update(List<Route> routes);
    void postLocation(Location location);
}
