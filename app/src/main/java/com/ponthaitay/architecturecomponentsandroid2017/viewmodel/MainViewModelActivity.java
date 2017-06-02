package com.ponthaitay.architecturecomponentsandroid2017.viewmodel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.ponthaitay.architecturecomponentsandroid2017.BaseLifecycleActivity;
import com.ponthaitay.architecturecomponentsandroid2017.R;

import java.util.List;

public class MainViewModelActivity extends BaseLifecycleActivity {

    private View container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_model);
        container = findViewById(R.id.container);
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (!hasDeniedPermission(report)) {
                            observableLocationViewModel();
                        } else {
                            Snackbar.make(container, report.toString(), Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }

                    private boolean hasDeniedPermission(MultiplePermissionsReport report) {
                        List<PermissionDeniedResponse> denyPermission = report.getDeniedPermissionResponses();
                        return denyPermission != null && !denyPermission.isEmpty();
                    }
                }).check();
    }

    private void observableLocationViewModel() {
        ViewModelProviders.of(this)
                .get(MyLocationViewModel.class)
                .getLocation(this)
                .observe(this, new Observer<Location>() {
                    @Override
                    public void onChanged(@Nullable Location location) {
                        if (location != null) {
                            @SuppressLint("DefaultLocale")
                            String result = String.format("%f / %f", location.getLatitude(), location.getLongitude());
                            ((TextView) findViewById(R.id.tv_result)).setText(result);
                        }
                    }
                });
    }
}