package com.example.usuario.manageproductsdatabase.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.manageproductsdatabase.model.Product;

import java.util.ArrayList;
import java.util.List;

//Manejadora de las operaciones de tareas asíncronas
public class DatabaseManager {

    private static DatabaseManager databaseManager;

    private DatabaseManager() { }

    public static DatabaseManager getInstance() {
        if(databaseManager == null)
            databaseManager = new DatabaseManager();
        return databaseManager;
    }

    public List<Product> getAllProducts() {
        //Construiremos un ArrayList desde la BD
        ArrayList<Product> products = new ArrayList<Product>();
        return null;
    }
    public void updateProduct (Product product) {
    }
    public void addProduct (Product product) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        //Cerramos todos los objetos SQLiteDatabase para evitar error de fuga de memoria
        ContentValues contentValues = new ContentValues();
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_NAME, product.getmName());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DESCRIPTION, product.getmDescription());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_BRAND, product.getmBrand());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DOSAGE, product.getmDosage());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_PRICE, product.getmPrice());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_STOCK, product.getmStock());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IMAGE, product.getmId());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IDCATEGORY, 1);
        //contentValues es un diccionario que contiene todos los datos en un objeto
        sqLiteDatabase.insert(ManageProductContract.ProductEntry.TABLE_NAME, null, contentValues);

        DatabaseHelper.getInstance().closeDatabase();

    }
    public void deleteProduct (Product product) {

    }




}






