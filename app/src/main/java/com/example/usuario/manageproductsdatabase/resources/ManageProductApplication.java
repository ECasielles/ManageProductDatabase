package com.example.usuario.manageproductsdatabase.resources;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.manageproductsdatabase.database.DatabaseHelper;


public class ManageProductApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Falta recuperar toda la BD
        DatabaseHelper.getInstance(this).open();
    }
}
