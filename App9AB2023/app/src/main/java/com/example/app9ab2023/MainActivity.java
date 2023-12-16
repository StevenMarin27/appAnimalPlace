package com.example.app9ab2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        },1000);





    }

    public void IraLogin(View v){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);

    }

    public void IraMenu(){

    }

}