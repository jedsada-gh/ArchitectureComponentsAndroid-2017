package com.ponthaitay.architecturecomponentsandroid2017.livedata;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;

public class MyLocationManager extends LiveData<Location> implements LocationListener {

    private final LocationManager locationManager;

    MyLocationManager(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onActive() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, this, Looper.getMainLooper());
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10f, this, Looper.getMainLooper());
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onInactive() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        setValue(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
