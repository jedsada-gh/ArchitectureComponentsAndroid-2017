package com.ponthaitay.architecturecomponentsandroid2017.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface StudentDao {

    @Insert
    void insertStudent(StudentEntity studentEntity);

    @Update
    void updateStudent(StudentEntity studentEntity);

    @Delete
    void deleteStudent(StudentEntity studentEntity);

    @Query("SELECT * FROM student WHERE id = :id")
    Flowable<List<StudentEntity>> queryStudent(int id);
}
