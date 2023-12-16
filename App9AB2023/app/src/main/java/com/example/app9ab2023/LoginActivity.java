package com.example.app9ab2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app9ab2023.interfaces.DataApi;
import com.example.app9ab2023.models.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText usuario, clave;
    TextView permiso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.et_usuario);
        clave = (EditText) findViewById(R.id.et_pass);
        permiso = (TextView) findViewById(R.id.registrate);
    }

    public void IraMain(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void IraMenu(View v){
        String usu = "steven";
        String cla = "123456";

        // Obtener la entrada del usuario desde los campos EditText
        String us = usuario.getText().toString();
        String pa = clave.getText().toString();

        // Verificar si el nombre de usuario o la contraseña están vacíos
        if (us.isEmpty() || pa.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese usuario y contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        // Verificar si las credenciales ingresadas son válidas
        if (us.equals(usu) && pa.equals(cla)) {
            // Guardar información del usuario en SharedPreferences
            SharedPreferences sesiones = getSharedPreferences("variable_sesion", MODE_PRIVATE);
            SharedPreferences.Editor ed = sesiones.edit();
            ed.putString("usuario", us);
            ed.putString("rol", "1");
            ed.putString("Cliente", "Steven");
            ed.apply();

            // Redirigir a MenuActivity con el nombre de usuario como extra
            Intent i = new Intent(this, MenuActivity.class);
            i.putExtra("nombre_usuario", us);
            startActivity(i);
        } else {
            // Mensaje de error si las credenciales son incorrectas
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
            // Limpiar campos de entrada
            usuario.setText("");
            clave.setText("");
        }
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