package com.tae.wunelliuatrestful.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;

import com.tae.wunelliuatrestful.model.Constants;
import com.tae.wunelliuatrestful.service.OnReceiverListener;
import com.tae.wunelliuatrestful.model.Route;

import java.util.List;

/**
 * Created by Eduardo on 04/05/2016.
 */
public class RestfulReceiver extends BroadcastReceiver{

    private OnReceiverListener listener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.ACTION_DOWNLOAD_SUCCESS)) {
            List<Route> routes = intent.getParcelableArrayListExtra(Constants.EXTRA_ROUTES);
            listener.update(routes);
        } else {
            listener.postLocation((Location) intent.getParcelableExtra(Constants.ACTION_LOCATION_PROVIDED));
        }
    }

    public void setListener(OnReceiverListener listener) {
        this.listener = listener;
    }
}
