package com.example.usuario.manageproductsdatabase.cursor;


import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import com.example.usuario.manageproductsdatabase.database.DatabaseManager;

public class PharmacyCursorLoader extends CursorLoader {

    public PharmacyCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance().getAllPharmacies();
    }
}
