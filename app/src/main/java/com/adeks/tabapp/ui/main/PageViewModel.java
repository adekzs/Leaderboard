package com.adeks.tabapp.ui.main;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.adeks.tabapp.AsyncResponse;
import com.adeks.tabapp.model.StudentHrs;
import com.adeks.tabapp.model.StudentIq;
import com.adeks.tabapp.retrofit.DataBank;

import java.util.List;

public class PageViewModel extends AndroidViewModel {
    private static final String TAG = "PageViewModel";

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, input -> "Hello world from section: " + input);
    private final Context mContext;

    public PageViewModel(@NonNull Application application) {
        super(application);
        mContext = application;
    }

    void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

    private MutableLiveData<List<StudentIq>> studentIq = new MutableLiveData<>();
    private MutableLiveData<List<StudentHrs>> studentHr = new MutableLiveData<>();

    void setStudentData() {
        DataBank db = new DataBank(mContext, new AsyncResponse() {
            @Override
            public void processFinished(List<StudentHrs> objectList) {
                studentHr.setValue(objectList);
                Log.d(TAG, "processFinished: " + studentHr.getValue());
            }

            @Override
            public void processFinished(List<StudentIq> objectList, boolean isTrue) {
                studentIq.setValue(objectList);
                Log.d(TAG, "processFinished: " + studentIq.getValue());
            }
        });
    }

    public LiveData<List<StudentIq>> getStudentIqs() {
        return studentIq;
    }

    public LiveData<List<StudentHrs>> getStudentHrs() {
        return studentHr;
    }

}