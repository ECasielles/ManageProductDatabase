package com.example.usuario.manageproductsdatabase.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.example.usuario.manageproductsdatabase.cursor.CategoryCursorLoader;
import com.example.usuario.manageproductsdatabase.interfaces.CategoryPresenter;
import com.example.usuario.manageproductsdatabase.interfaces.ListPharmaciesPresenter;


public class ListPharmaciesPresenterImpl implements ListPharmaciesPresenter, LoaderManager.LoaderCallbacks<Cursor> {

    private CategoryPresenter.View view;
    private final static int LIST_PHARMACIES = 1;
    private Context context;

    public ListPharmaciesPresenterImpl(CategoryPresenter.View view) {
        this.view = view;
        this.context = view.getContext();
    }

    ListPharmaciesPresenter

    @Override
    public void getPharmacies() {
        ((Activity)context).getLoaderManager().initLoader(LIST_PHARMACIES, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> cursorLoader = null;
        switch (id){
            case LIST_PHARMACIES:
                cursorLoader = new CategoryCursorLoader(context);
                break;
        }
        return cursorLoader;

    }
    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        view.setCursorPharmacyList();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }
}
