package com.adeks.tabapp.retrofit;

import com.adeks.tabapp.model.StudentHrs;
import com.adeks.tabapp.model.StudentIq;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetStudentInfo {

    @GET("api/hours")
    Call<List<StudentHrs>> getAllStudentHours();

    @GET("api/skilliq")
    Call<List<StudentIq>> getAllStudentIq();
}
