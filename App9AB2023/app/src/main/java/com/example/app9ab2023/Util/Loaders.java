package com.example.app9ab2023.Util;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Loaders {
    public static SweetAlertDialog sweetAlertDialog;
    public static Context context;


    public void inicializaProgress(Context context){
        this.context = context;
        sweetAlertDialog = new SweetAlertDialog(context);

    }

    public void muestraProgress(){
        sweetAlertDialog = new SweetAlertDialog(context,SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.show();
        sweetAlertDialog.setCancelable(false);
    }

    public  void mensaje(String mensaje){
        sweetAlertDialog.setTitleText(mensaje);
    }

    public void cierraProgress(){
        sweetAlertDialog.dismiss();
    }
}
