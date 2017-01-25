package com.example.usuario.manageproductsdatabase.resources;

import android.app.Application;
import android.content.Context;

import com.example.usuario.manageproductsdatabase.database.DatabaseHelper;


public class ManageProductApplication extends Application {

    private static ManageProductApplication manageProductApplication;

    private ManageProductApplication() { }

    //Se pone el nombre getContext por compromiso pero no es óptimo
    public static Context getContext() {
        return manageProductApplication.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manageProductApplication = this;
        DatabaseHelper.getInstance().open();
    }
}
