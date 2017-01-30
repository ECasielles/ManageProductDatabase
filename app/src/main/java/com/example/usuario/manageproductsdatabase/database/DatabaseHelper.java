package com.example.usuario.manageproductsdatabase.database;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.usuario.manageproductsdatabase.resources.ManageProductApplication;

import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ManageProduct.db";
    //Lo ponemos volátil para garantizar que no haya una lectura a null
    //en caso de acceso concurrente entre hilos (uno en getInstance y otro en
    //acceso directo a la propiedad).
    private volatile static DatabaseHelper databaseHelper;
    private AtomicInteger mOpenCounter;
    private SQLiteDatabase mDatabase;

    //Usaremos el segundo constructor de los recomendados en la práctica, porque tiene el manejador
    //de errores de la BD (clase que permite manejar los mensajes de SQLite)
    private DatabaseHelper() {
        super(ManageProductApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        mOpenCounter = new AtomicInteger();
    }

    /*
    Esta parte corresponde al uso de mapeo y no se usa con los cursores

    //Recordar que queremos un singleton para tener un objeto único
    //Además, no queremos que dos hilos abran la BD a la vez, por lo que
    //restringimos el acceso al getInstance.
    public synchronized static DatabaseHelper getInstance() {
        //El contexto lo vamos a necesitar siempre, para inicializar cosas
        if (databaseHelper == null)
            //Usamos getApplicationContext porque puede ser distinto al de la actividad
            //Recordar que nunca usamos el getBaseContext
            //Si ponemos el contexto de una actividad, esa actividad jamás se eliminaría
            //y lo que queremos es que lo que no se elimine nunca sea ESTA clase,
            //la conexión a la BD.
            databaseHelper = new DatabaseHelper(ManageProductApplication.getContext());
        return databaseHelper;
    }
    public synchronized SQLiteDatabase openDatabase() {
        //Abre la base de datos para el acceso de los hilos asíncronos en concurrencia
        //No confundir con onOpen, que se llama automáticamente
        if (mOpenCounter.incrementAndGet() == 1)
            //Garantizamos que el primer hilo siempre inicalice la variable
            mDatabase = getWritableDatabase();
        return mDatabase;
    }
    public synchronized void closeDatabase() {
        //Garantiza que sólo se cierre la base de datos si el hilo que la cierra
        //es el último hilo en consultar.
        if (mOpenCounter.decrementAndGet() == 0)
            mDatabase.close();
        //En otros SGBD tenemos un objeto conexión (Oracle, etc.)
    }

    */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Crearemos una sentencia SQL para crear toooodas las tablas
        //El objeto SQLiteDatabase es el que contiene todos los manejadores
        //Para crear las tablas llamamos a execSQL
        //IMPORTANTE: CUIDADO CON EL ORDEN, GUARDAR LAS REFERENCIAS

        sqLiteDatabase.beginTransaction();

        try {
            sqLiteDatabase.execSQL(ManageProductContract.CategoryEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.CategoryEntry.SQL_INSERT_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.InvoiceStatusEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.InvoiceStatusEntry.SQL_INSERT_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.ProductEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.ProductEntry.SQL_INSERT_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.PharmacyEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.PharmacyEntry.SQL_INSERT_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.InvoiceEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.InvoiceEntry.SQL_INSERT_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.InvoiceLineEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.InvoiceLineEntry.SQL_INSERT_ENTRIES);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("manageproductdatabase", "Error al crear la base de datos: " + e.getMessage());
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //Eliminamos en orden inverso
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.execSQL(ManageProductContract.CategoryEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.InvoiceStatusEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.ProductEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.PharmacyEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.InvoiceEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(ManageProductContract.InvoiceLineEntry.SQL_DELETE_ENTRIES);
            onCreate(sqLiteDatabase);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("manageproductdatabase", "Error al actualizar la base de datos: " + e.getMessage());
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }
    //No debe hacerse. Lo mejor sería hacer un alter table.
    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //super.onDowngrade(sqLiteDatabase, oldVersion, newVersion); <- no es necesario llamarlo
        //Normalmente degradamos a la versión anterior
        onUpgrade(sqLiteDatabase, newVersion, oldVersion);
    }
    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase) {
        super.onOpen(sqLiteDatabase);
        //Si no es de lectura realizamos la comprobación de la compilación
        if(!sqLiteDatabase.isReadOnly())
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                sqLiteDatabase.setForeignKeyConstraintsEnabled(true);
            else
                sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
    }
    //Encapsulamos el método para abrirla BD en modo escritura
    public SQLiteDatabase open() {
        return getWritableDatabase();
    }
}
