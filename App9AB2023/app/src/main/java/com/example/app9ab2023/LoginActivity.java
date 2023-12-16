package com.example.app9ab2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app9ab2023.Service.ApiCall;
import com.example.app9ab2023.Service.DataApi;
import com.example.app9ab2023.Service.ReponseModels.LoginResponse;
import com.example.app9ab2023.Util.Loaders;
import com.example.app9ab2023.models.CredencialesUsuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText usuario, clave;
    TextView permiso;
    private Button button;
    private Loaders loaders = new Loaders();
    private DataApi dataApi = ApiCall.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loaders.inicializaProgress(LoginActivity.this);
        usuario = (EditText) findViewById(R.id.et_usuario);
        clave = (EditText) findViewById(R.id.et_pass);
        permiso = (TextView) findViewById(R.id.registrate);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IraMenu(v);
            }
        });
    }

    public void IraMain(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void IraMenu(View v){

        String us = usuario.getText().toString();
        String pa = clave.getText().toString();

        // Verificar si el nombre de usuario o la contraseña están vacíos
        if (us.isEmpty() || pa.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese usuario y contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        loaders.muestraProgress();
        loaders.mensaje("Iniciando...");

        CredencialesUsuario credencialesUsuario = new CredencialesUsuario();
        credencialesUsuario.email = us;
        credencialesUsuario.password = pa;

        Call<LoginResponse> call = dataApi.login(credencialesUsuario);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loaders.cierraProgress();
                if(response.isSuccessful()){

                    if(response.body() != null){
                        SharedPreferences sesiones = getSharedPreferences("variable_sesion", MODE_PRIVATE);
                        SharedPreferences.Editor ed = sesiones.edit();
                        ed.putString("usuario", us);
                        ed.putString("rol", "1");
                        ed.putString("Cliente", "Steven");
                        ed.putString("token",response.body().token);
                        ed.apply();

                        // Redirigir a MenuActivity con el nombre de usuario como extra
                        Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                        i.putExtra("nombre_usuario", us);
                        startActivity(i);
                    }


                }else{

                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loaders.cierraProgress();
                t.printStackTrace();
                Toast.makeText(LoginActivity.this, "Error al iniciar Sesión "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void IraRecuperar(View v){
        String url = "http://www.google.com";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

    public void IraFb(View v){
        String url = "https://www.facebook.com/veterinariaanimalplace";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
        public void IraIg(View v){
        String url = "https://www.instagram.com/veterinariaanimalplace/";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

    }
}