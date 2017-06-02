package com.ponthaitay.architecturecomponentsandroid2017.room;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ponthaitay.architecturecomponentsandroid2017.BaseLifecycleActivity;
import com.ponthaitay.architecturecomponentsandroid2017.R;

import java.util.List;

public class MainRoomActivity extends BaseLifecycleActivity {

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_room);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class,
                "database-app").build();

        final StudentEntity entity = new StudentEntity();
        entity.setId(3);
        entity.setFirstName("Mr.Jedsada Tiwongvorakul");
        entity.setLastName("POND");

        final StudentViewModel studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        studentViewModel.insertStudent(db.studentDao(), entity);

        studentViewModel.getStudentById(db.studentDao(), 3)
                .observe(this, new Observer<List<StudentEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<StudentEntity> studentEntities) {
                        if (!studentEntities.isEmpty()) {
                            Log.e("POND", String.valueOf(studentEntities.get(0).getFirstName()));
                        } else {
                            Log.e("POND", "ERROR");
                        }
                    }
                });

    }
}
