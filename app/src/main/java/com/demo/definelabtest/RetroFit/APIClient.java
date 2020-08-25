package com.demo.definelabtest.RetroFit;

import com.demo.definelabtest.Constant.Urls;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit retrofit=null;

    public static Retrofit getInstance(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Urls.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(initOkHttp())
                    .build();
        }

        return retrofit;
    }

    private static OkHttpClient initOkHttp() {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);   // set your desired log level
        httpClient.addInterceptor(interceptor);   // add logging as last interceptor
        httpClient.cache(null);
        httpClient.connectTimeout(5, TimeUnit.SECONDS);
        return httpClient.build();
    }

}
