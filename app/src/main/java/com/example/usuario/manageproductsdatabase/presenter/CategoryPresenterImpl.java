package com.example.usuario.manageproductsdatabase.presenter;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;


import com.example.usuario.manageproductsdatabase.cursor.CategoryCursorLoader;
import com.example.usuario.manageproductsdatabase.interfaces.CategoryPresenter;

public class CategoryPresenterImpl implements CategoryPresenter, LoaderManager.LoaderCallbacks<Cursor> {

    private CategoryPresenter.View view;
    private final static int CATEGORY = 1;
    private Context context;

    public CategoryPresenterImpl(CategoryPresenter.View view) {
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void getAllCategories() {
        //Cursor cursor = DatabaseManager.getInstance().getAllCategories();
        //adapter.swapCursor(cursor);
        //ó
        //view.getAdapterView().getAdapter.swapCursor(cursor);
        //view.setCategoryCursor(cursor);

        //initLoader llama a Loader
        ((Activity)context).getLoaderManager().initLoader(CATEGORY, null, this); //<-Inicializamos un cargador en el presenter
    }
    @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            Loader<Cursor> loader = null;
            switch (id){
                case CATEGORY:
                    //Si usaramos contentProvider pasariamos la uri
                    loader = new CategoryCursorLoader(context);  //<-Podemos hacerlo directamente del fragment
                    break;
            }
            return loader;
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        //recordar la interfaz donde
        view.setCursorCategory(cursor);
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursorCategory(null);
    }

    //Método que recarga el cursor con todo junto (por Lourdes) <------Mover a clase ListPresenter
    public void restartLoader(int loader) {
        ((Activity) view.getContext()).getLoaderManager().restartLoader(loader, null, this);
    }


}
