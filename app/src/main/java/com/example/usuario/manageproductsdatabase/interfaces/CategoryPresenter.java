package com.example.usuario.manageproductsdatabase.interfaces;

import android.content.Context;
import android.database.Cursor;

public interface CategoryPresenter {

    //void getAllCategory(CursorAdapter adapter);

    void getAllCategory();
    interface View {
        void setCursorCategory(Cursor cursor);
        Context getContext();
    }
}

