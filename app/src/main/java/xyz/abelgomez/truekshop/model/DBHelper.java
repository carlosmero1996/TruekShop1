package xyz.abelgomez.truekshop.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "productos.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_PRODUCTOS = "productos";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_PRECIO = "precio";
    public static final String COLUMN_IMAGEN = "imagen";

    private static final String CREATE_TABLE_PRODUCTOS =
            "CREATE TABLE " + TABLE_PRODUCTOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " TEXT NOT NULL, " +
                    COLUMN_DESCRIPCION + " TEXT NOT NULL, " +
                    COLUMN_PRECIO + " REAL NOT NULL, " +
                    COLUMN_IMAGEN + " BLOB);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUCTOS);

        // Eliminar todos los registros de la tabla Productos
        db.execSQL("DELETE FROM " + TABLE_PRODUCTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
        onCreate(db);
    }

    public void insertProducto(Producto producto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, producto.getNombre());
        values.put(COLUMN_DESCRIPCION, producto.getDescripcion());
        values.put(COLUMN_PRECIO, producto.getPrecio());
        values.put(COLUMN_IMAGEN, producto.getImagenBytes());
        db.insert(TABLE_PRODUCTOS, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<Producto> getProductos() {
        List<Producto> productos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS, null);
        if (cursor.moveToFirst()) {
            do {
                Producto producto = new Producto();
                producto.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                producto.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)));
                producto.setDescripcion(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION)));
                producto.setPrecio(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRECIO)));
                producto.setImagenBytes(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGEN)));
                productos.add(producto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return productos;
    }

    public void clearProductos() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTOS, null, null);
        db.close();
    }
}