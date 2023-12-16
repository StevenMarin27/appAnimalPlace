package com.example.app9ab2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class EliminarActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    List<String> tipoAnimal = new ArrayList<String>();
    Spinner combobox01;
    EditText idCodigoMas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        idCodigoMas = (EditText) findViewById(R.id.idCodigoMas);
    }

    public void Eliminar_mascota(View v){
        String URL = Globales.mipagLocal + "eliminar_mascota.php";
        requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("correcto")){
                    Toast.makeText(EliminarActivity.this, "Mascota eliminada con éxito", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EliminarActivity.this, "Hubo un inconveniente, intentelo más tarde", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EliminarActivity.this, "Hubo un inconveniente, intentelo más tarde", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("idCodigoMas", idCodigoMas.getText().toString());
                return parametros;
            }
        };
        requestQueue.add(stringRequest);
    }
}