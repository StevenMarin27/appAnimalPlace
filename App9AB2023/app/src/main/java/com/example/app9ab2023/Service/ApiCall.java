package com.example.app9ab2023.Service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiCall {

    private static DataApi single_instance = null;
    public static String baseurl = "https://ca38-2800-bf0-8283-b56-65ec-8c8a-2d03-b456.ngrok-free.app/";

    public static DataApi getInstance(){
        if (single_instance == null){

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(240, TimeUnit.SECONDS)
                    .writeTimeout(240, TimeUnit.SECONDS).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseurl)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
            single_instance = retrofit.create(DataApi.class);
        }
        return single_instance;
    }

}
