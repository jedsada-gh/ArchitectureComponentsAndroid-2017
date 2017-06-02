package com.ponthaitay.architecturecomponentsandroid2017.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;

public class MyLocationViewModel extends ViewModel implements LocationListener {

    private MutableLiveData<Location> mutableLiveData;

    LiveData<Location> getLocation(Context context) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            requestLocation(context);
        }
        return mutableLiveData;
    }

    @SuppressLint("MissingPermission")
    private void requestLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, this, Looper.getMainLooper());
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10f, this, Looper.getMainLooper());
    }

    @Override
    public void onLocationChanged(Location location) {
        mutableLiveData.setValue(location);
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