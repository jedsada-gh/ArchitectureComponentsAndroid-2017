package com.ponthaitay.architecturecomponentsandroid2017;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.v7.app.AppCompatActivity;

public class BaseLifecycleActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    public BaseLifecycleActivity() {
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }
}
