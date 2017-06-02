package com.ponthaitay.architecturecomponentsandroid2017.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {StudentEntity.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {

    abstract StudentDao studentDao();
}
