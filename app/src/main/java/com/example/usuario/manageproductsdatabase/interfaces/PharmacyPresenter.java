package com.example.usuario.manageproductsdatabase.interfaces;


import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

public interface PharmacyPresenter {

    void getAllPharmacies();

    Loader<Cursor> onCreateLoader(int id, Bundle args);

    void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor);

    void onLoaderReset(Loader<Cursor> cursorLoader);

    interface View {
        Context getContext();
        void setCursorPharmacy(Cursor cursor);
    }

}
