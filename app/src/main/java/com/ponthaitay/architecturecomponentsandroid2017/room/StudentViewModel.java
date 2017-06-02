package com.ponthaitay.architecturecomponentsandroid2017.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

class StudentViewModel extends ViewModel {

    private final MutableLiveData<List<StudentEntity>> mutableLiveData = new MutableLiveData<>();
    private Observable<Void> observable;

    void insertStudent(final StudentDao dao, final StudentEntity entity) {
        observable = new Observable<Void>() {
            @Override
            protected void subscribeActual(Observer<? super Void> observer) {
                dao.insertStudent(entity);
            }
        };
        subscribeObservable();
    }

    void updateStudent(final StudentDao dao, final StudentEntity entity) {
        observable = new Observable<Void>() {
            @Override
            protected void subscribeActual(Observer<? super Void> observer) {
                dao.updateStudent(entity);
            }
        };
        subscribeObservable();
    }

    void deleteStudent(final StudentDao dao, final StudentEntity entity) {
        observable = new Observable<Void>() {
            @Override
            protected void subscribeActual(Observer<? super Void> observer) {
                dao.deleteStudent(entity);
            }
        };
        subscribeObservable();
    }

    private void subscribeObservable() {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    LiveData<List<StudentEntity>> getStudentById(StudentDao dao, int id) {
        dao.queryStudent(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<StudentEntity>>() {
                    @Override
                    public void accept(List<StudentEntity> studentEntities) throws Exception {
                        mutableLiveData.setValue(studentEntities);
                    }
                });
        return mutableLiveData;
    }
}