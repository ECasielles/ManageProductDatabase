package com.example.usuario.manageproductsdatabase.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ManageProduct.db";
    //Lo ponemos volátil para garantizar que no haya una lectura a null
    //en caso de acceso concurrente entre hilos (uno en getInstance y otro en
    //acceso directo a la propiedad).
    private volatile static DatabaseHelper databaseHelper;

    //Usaremos el segundo constructor de los recomendados en la práctica, porque tiene el manejador
    //de errores de la BD (clase que permite manejar los mensajes de SQLite)
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Recordar que queremos un singleton para tener un objeto único
    //Además, no queremos que dos hilos abran la BD a la vez, por lo que
    //restringimos el acceso al getInstance.
    public synchronized static DatabaseHelper getInstance(Context context) {
        //El contexto lo vamos a necesitar siempre, para inicializar cosas
        if (databaseHelper == null)
            //Usamos getApplicationContext porque puede ser distinto al de la actividad
            //Recordar que nunca usamos el getBaseContext
            //Si ponemos el contexto de una actividad, esa actividad jamás se eliminaría
            //y lo que queremos es que lo que no se elimine nunca sea ESTA clase,
            //la conexión a la BD.
            databaseHelper = new DatabaseHelper(context.getApplicationContext());
        return databaseHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crearemos una sentencia SQL para crear toooodas las tablas
        //El objeto SQLiteDatabase es el que contiene todos los manejadores
        //Para crear las tablas llamamos a execSQL
        //IMPORTANTE: CUIDADO CON EL ORDEN, GUARDAR LAS REFERENCIAS
        db.execSQL(ManageProductContract.CategoryEntry.SQL_CREATE_ENTRIES);
        db.execSQL(ManageProductContract.ProductEntry.SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Eliminamos en orden inverso
        db.execSQL(ManageProductContract.CategoryEntry.SQL_DELETE_ENTRIES);
        db.execSQL(ManageProductContract.ProductEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    //No debe hacerse. Lo mejor sería hacer un alter table.
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //super.onDowngrade(db, oldVersion, newVersion); <- no es necesario llamarlo
        //Normalmente degradamos a la versión anterior
        onUpgrade(db, newVersion, oldVersion);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //Si no es de lectura realizamos la comprobación de la compilación
        if(!db.isReadOnly())
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                db.setForeignKeyConstraintsEnabled(true);
            else
                db.execSQL("PRAGMA foreign_keys = ON");
    }
    //Encapsulamos el método para abrirla BD en modo escritura
    public SQLiteDatabase open() {
        return getWritableDatabase();
    }
}
