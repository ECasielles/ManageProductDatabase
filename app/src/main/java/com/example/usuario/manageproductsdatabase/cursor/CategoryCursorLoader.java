package com.example.usuario.manageproductsdatabase.cursor;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import com.example.usuario.manageproductsdatabase.database.DatabaseManager;


public class CategoryCursorLoader extends CursorLoader{
    public CategoryCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance().getAllCategories(); //<- Recuerda que no cierra la base de datos
    }
}
