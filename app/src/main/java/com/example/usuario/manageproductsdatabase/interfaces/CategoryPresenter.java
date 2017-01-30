package com.example.usuario.manageproductsdatabase.interfaces;

import android.content.Context;
import android.database.Cursor;

public interface CategoryPresenter {

    //void getAllCategories(CursorAdapter adapter);

    interface View {
        void setCursorCategory(Cursor cursor);
        Context getContext();
    }
    void getAllCategories();
}

