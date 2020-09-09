package com.adeks.tabapp.retrofit;

import com.adeks.tabapp.AsyncResponse;
import com.adeks.tabapp.ui.main.SubmitAsyncResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSubmit {

    public void submitPoject(String firstName, String lastName, String email, String githubLink, SubmitAsyncResponse callback) {
        SubmitProject submitProject = RetrofitCLientClass.getRetrofitGoogleInstance()
                .create(SubmitProject.class);
        Call<Void> call = submitProject.merchantRest(firstName, lastName, email, githubLink);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                callback.onProcessCompleted();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onProcessFailed();
            }
        });

    }
}
