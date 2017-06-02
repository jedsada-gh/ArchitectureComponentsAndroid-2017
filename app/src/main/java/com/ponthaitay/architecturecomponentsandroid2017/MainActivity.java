package com.ponthaitay.architecturecomponentsandroid2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ponthaitay.architecturecomponentsandroid2017.lifecycle.MainLifecycleActivity;
import com.ponthaitay.architecturecomponentsandroid2017.livedata.MainLiveDataActivity;
import com.ponthaitay.architecturecomponentsandroid2017.room.MainRoomActivity;
import com.ponthaitay.architecturecomponentsandroid2017.viewmodel.MainViewModelActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Class aClass = null;
        switch (id) {
            case R.id.btn_lifecycle_activity:
                aClass = MainLifecycleActivity.class;
                break;
            case R.id.btn_live_data_activity:
                aClass = MainLiveDataActivity.class;
                break;
            case R.id.btn_view_model_activity:
                aClass = MainViewModelActivity.class;
                break;
            case R.id.btn_room_activity:
                aClass = MainRoomActivity.class;
                break;
            default:
                break;
        }
        startActivity(new Intent(this, aClass));
    }
}
