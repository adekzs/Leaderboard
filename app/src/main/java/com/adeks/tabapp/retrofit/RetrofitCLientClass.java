package com.adeks.tabapp.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitCLientClass {

    private static Retrofit retrofit;
    private static Retrofit googleRetrofit;
    private static final String BASE_URL = "https://gadsapi.herokuapp.com/";
    private static final String BASE_URL_GOOGLE_FORM = "https://docs.google.com/forms/d/e/";

    static Retrofit getRetrofitInstanceO() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    static Retrofit getRetrofitGoogleInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        if (googleRetrofit == null) {
            googleRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_GOOGLE_FORM)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return googleRetrofit;
    }
}
