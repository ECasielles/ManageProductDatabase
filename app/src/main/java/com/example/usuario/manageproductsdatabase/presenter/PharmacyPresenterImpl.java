package com.example.usuario.manageproductsdatabase.presenter;


import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.example.usuario.manageproductsdatabase.cursor.CategoryCursorLoader;
import com.example.usuario.manageproductsdatabase.interfaces.PharmacyPresenter;

public class PharmacyPresenterImpl implements PharmacyPresenter, LoaderManager.LoaderCallbacks<Cursor> {

    private PharmacyPresenter.View view;
    private final static int PHARMACY = 1;
    private Context context;

    public PharmacyPresenterImpl(PharmacyPresenter.View view){
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void getAllPharmacies() {
        ((Activity)context).getLoaderManager().initLoader(PHARMACY, null, this);

    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> cursorLoader = null;
        switch (id) {
            case PHARMACY:
                cursorLoader = new CategoryCursorLoader(context);
                break;
        }
        return cursorLoader;
    }
    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        view.setCursorPharmacy(cursor);
    }
    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        view.setCursorPharmacy(null);
    }

}
