package com.example.app9ab2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    TextView nusu;
    String nombresito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        nusu = (TextView) findViewById(R.id.usuario_sesion);
        Bundle cualquiernombre = this.getIntent().getExtras();
        nombresito = cualquiernombre.getString("nombre_usuario");
        nusu.setText(nombresito);

    }


    public void iraAgregarMascota(View v){
        Intent i = new Intent(this, MascotaActivity.class);
        startActivity(i);
    }

    public void iraEliminarMascota(View v){
        Intent i = new Intent(this, EliminarActivity.class);
        startActivity(i);
    }

    public void iraLogin(View v){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void irAchatbot(View v){
        Intent i = new Intent(this, ChatBot.class);
        startActivity(i);
    }



    @Override
    public void onBackPressed() {

    }
}