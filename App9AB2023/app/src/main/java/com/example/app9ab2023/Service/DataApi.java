package com.example.app9ab2023.Service;


import com.example.app9ab2023.Service.ReponseModels.LoginResponse;
import com.example.app9ab2023.models.CredencialesUsuario;
import com.example.app9ab2023.models.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DataApi {
    @POST("api/Security/login")
    Call<LoginResponse> login(@Body CredencialesUsuario credencialesUsuario);
}
