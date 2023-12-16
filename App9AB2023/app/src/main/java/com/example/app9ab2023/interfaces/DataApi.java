package com.example.app9ab2023.interfaces;


import com.example.app9ab2023.models.Login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataApi {
    @GET("api/security/login/¨{email}")
    public Call<Login> find(@Path("email") String email);
}
