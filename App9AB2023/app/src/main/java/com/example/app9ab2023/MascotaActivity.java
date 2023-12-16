package com.example.app9ab2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class MascotaActivity extends AppCompatActivity {
    List<String> tipoAnimal = new ArrayList<String>();
    Spinner combobox01;
    EditText fechita, fechita2, mascota, dueno, cedula, edad, direccion, telefono, raza, grupo, estado, fech, fech2;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);

        fechita = (EditText) findViewById(R.id.idFechaNacMas);
        fechita2 = (EditText) findViewById(R.id.idFechaIngMas);
        mascota = (EditText) findViewById(R.id.idCodigoMas);
        dueno = (EditText) findViewById(R.id.idDuenoMas);
        cedula = (EditText) findViewById(R.id.idCedulaMas);
        edad = (EditText) findViewById(R.id.idEdadMas);
        direccion = (EditText) findViewById(R.id.idDireccionMas);
        telefono = (EditText) findViewById(R.id.idTelefonoMas);
        fech = (EditText) findViewById(R.id.idFechaNacMas);
        fech2 = (EditText) findViewById(R.id.idFechaIngMas);


        combobox01 = (Spinner) findViewById(R.id.cmbTipoAnimal);
        tipoAnimal.add("--Seleccione un tipo--");
        tipoAnimal.add("Gato");
        tipoAnimal.add("Perro");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoAnimal );
        adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        combobox01.setAdapter(adapter);
    }
    public void mostrar_fecha(View v){
        showDatePickerDialog();
    }
    public void mostrar_fecha2(View v){
        showDatePickerDialog2();
    }

    public String dosdigitos(int c){
        String mesito = "";
        if (c < 10){
            mesito = "0" + c;
        }else{
            mesito = "" + c;
        }
        return mesito;
    }

    private void showDatePickerDialog() {
        DatePickerFragment fechitax = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                final String fecha = anio + "/" + dosdigitos(mes+1) + "/" + dosdigitos(dia);
                fechita.setText(fecha);
            }
        });
        fechitax.show(this.getSupportFragmentManager(),"Calendario" );
    }

    private void showDatePickerDialog2() {
        DatePickerFragment fechitax = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                final String fecha = anio + "/" + dosdigitos(mes+1) + "/" + dosdigitos(dia);
                fechita2.setText(fecha);
            }
        });
        fechitax.show(this.getSupportFragmentManager(),"Calendario" );
    }

    public static class DatePickerFragment extends DialogFragment {

        private DatePickerDialog.OnDateSetListener listener;

        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar cal = Calendar.getInstance();
            int anio = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH);
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), listener, anio, mes, dia);
        }

        public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

    }

    public void Ingresar_mascota(View v){
        String URL = Globales.mipagLocal + "ingresar_mascota.php";
        requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("correcto")){
                    Toast.makeText(MascotaActivity.this, "Producto ingresado con éxito", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MascotaActivity.this, "Hubo un inconveniente, intentelo más tarde", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MascotaActivity.this, "Hubo un inconveniente, intentelo más tarde", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("idCodigoMas", mascota.getText().toString());
                parametros.put("idDuenoMas", dueno.getText().toString());
                parametros.put("idCedulaMas", cedula.getText().toString());
                parametros.put("idEdadMas", edad.getText().toString());
                parametros.put("idDireccionMas", direccion.getText().toString());
                parametros.put("idTelefonoMas", telefono.getText().toString());
                parametros.put("idRazaMas", raza.getText().toString());
                parametros.put("idGrupoMas", grupo.getText().toString());
                parametros.put("idEstadoMas", estado.getText().toString());
                parametros.put("idFechaNacMas", fech.getText().toString());
                parametros.put("idFechaIngMas", fech2.getText().toString());
                parametros.put("idTipoMas", combobox01.getSelectedItem().toString());
                return parametros;
            }
        };
        requestQueue.add(stringRequest);
    }

}