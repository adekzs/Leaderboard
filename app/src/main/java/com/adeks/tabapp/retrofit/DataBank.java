package com.adeks.tabapp.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adeks.tabapp.AsyncResponse;
import com.adeks.tabapp.model.StudentHrs;
import com.adeks.tabapp.model.StudentIq;
import com.adeks.tabapp.retrofit.GetStudentInfo;
import com.adeks.tabapp.retrofit.RetrofitCLientClass;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataBank {

    private static final String TAG = "DataBank";
    private GetStudentInfo mGetStudentInfo;
    private Context mContext;


    public DataBank() {
    }

    public DataBank(Context context, AsyncResponse asyncResponse) {
        mContext = context;
        getDetails(asyncResponse);
    }

    private void getDetails(AsyncResponse callback) {
        mGetStudentInfo = RetrofitCLientClass.getRetrofitInstanceO().create(GetStudentInfo.class);
        Call<List<StudentHrs>> call = mGetStudentInfo.getAllStudentHours();
        call.enqueue(new Callback<List<StudentHrs>>() {
            @Override
            public void onResponse(Call<List<StudentHrs>> call, Response<List<StudentHrs>> response) {
                Log.d(TAG, "onResponse: " + response.body());
                callback.processFinished(response.body());

            }

            @Override
            public void onFailure(Call<List<StudentHrs>> call, Throwable t) {
                Toast.makeText(mContext, "Error loading Student Scores", Toast.LENGTH_SHORT).show();
            }
        });
        Call<List<StudentIq>> call2 = mGetStudentInfo.getAllStudentIq();
        call2.enqueue(new Callback<List<StudentIq>>() {
            @Override
            public void onResponse(Call<List<StudentIq>> call, Response<List<StudentIq>> response) {
                Log.d(TAG, "onResponse: " + response.body());
                callback.processFinished(response.body(), true);
            }

            @Override
            public void onFailure(Call<List<StudentIq>> call, Throwable t) {
                Toast.makeText(mContext, "Error loading student scores", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
