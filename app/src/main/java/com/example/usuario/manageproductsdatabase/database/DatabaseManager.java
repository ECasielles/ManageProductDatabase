package com.example.usuario.manageproductsdatabase.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.usuario.manageproductsdatabase.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

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
        Product product;
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();

        //Como es muy probable que tenga que realizar
        Cursor cursor = sqLiteDatabase.query(
                ManageProductContract.ProductEntry.TABLE_NAME,
                ManageProductContract.ProductEntry.ALL_COLUMNS,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            //Recordar que no es asíncrono así que habrá que darle un hilo propio
            do {
                product = new Product();
                product.setmId(cursor.getInt(0));
                product.setmName(cursor.getString(1));
                product.setmDescription(cursor.getString(2));
                product.setmBrand(cursor.getString(3));
                product.setmDosage(cursor.getString(4));
                product.setmPrice(cursor.getDouble(5));
                product.setmStock(cursor.getInt(6));
                product.setmImage(cursor.getInt(7));
                product.setmCategory(cursor.getInt(8));
                products.add(product);
            } while (cursor.moveToNext());
        }
        DatabaseHelper.getInstance().closeDatabase();


        return products;
    }
    public void deleteProduct (Product product) {

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
    public int update (String table, ContentValues values, String whereClause, String[] whereArgs){

    }
    public void updateProduct (Product product) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_NAME, product.getmName());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DESCRIPTION, product.getmDescription());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_BRAND, product.getmBrand());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DOSAGE, product.getmDosage());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_PRICE, product.getmPrice());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_STOCK, product.getmStock());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IMAGE, product.getmId());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IDCATEGORY, 1);
        String where = BaseColumns._ID + "=7";
        String[] whereArgs = new String[] { String.valueOf(product.getmId()) };
        sqLiteDatabase.update(ManageProductContract.ProductEntry.TABLE_NAME, contentValues, where, whereArgs);
        DatabaseHelper.getInstance().closeDatabase();
    }

    //getAllCategories
    public Cursor getAllCategories() {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(ManageProductContract.CategoryEntry.TABLE_NAME, ManageProductContract.CategoryEntry.GET_ALL_COLUMMS, null, null, null, null, null);

    }


}






