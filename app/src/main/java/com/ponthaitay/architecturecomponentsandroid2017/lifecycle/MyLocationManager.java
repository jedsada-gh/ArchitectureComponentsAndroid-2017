package com.ponthaitay.architecturecomponentsandroid2017.lifecycle;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;

class MyLocationManager implements LifecycleObserver, LocationListener {

    private static final String TAG = MyLocationManager.class.getName();
    private boolean enabled = false;
    private Lifecycle lifecycle;
    private MyLocationListener listener;
    private LocationManager locationManager;

    interface MyLocationListener {
        void onLocationChange(Location location);

        void onProviderDisabled(String msg);

        void onProviderEnabled(String provider);
    }

    MyLocationManager(Context context, Lifecycle lifecycle, MyLocationListener listener) {
        this.listener = listener;
        this.lifecycle = lifecycle;
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        lifecycle.addObserver(this);
    }

    void locationEnable() {
        if (!enabled) {
            enabled = true;
            start();
        }
    }

    @SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        if (enabled) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, this, Looper.getMainLooper());
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10f, this, Looper.getMainLooper());
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        locationManager.removeUpdates(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void cleanup() {
        lifecycle.removeObserver(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        listener.onLocationChange(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        listener.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        listener.onProviderDisabled(provider);
    }
}
