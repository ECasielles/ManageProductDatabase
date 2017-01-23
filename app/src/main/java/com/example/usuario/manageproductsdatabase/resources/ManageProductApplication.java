package com.example.usuario.manageproductsdatabase.resources;

import android.app.Application;
import android.content.Context;

import com.example.usuario.manageproductsdatabase.database.DatabaseHelper;


public class ManageProductApplication extends Application {

    private static ManageProductApplication manageProductApplication;
    Context context;

    private ManageProductApplication() { }

    //Se pone el nombre getContext por compromiso pero no es óptimo
    public static ManageProductApplication getContext() {
        return manageProductApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.getInstance().open();
    }
}
