package corte2.hotel.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hotel.db";
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla para almacenar los datos de los huéspedes
        String createTableHuespedesQuery = "CREATE TABLE huespedes (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "apellido TEXT," +
                "identificacion TEXT," +
                "telefono TEXT," +
                "email TEXT," +
                "usuario TEXT," +
                "password TEXT)";
        db.execSQL(createTableHuespedesQuery);

        // Crear la tabla para almacenar las reservaciones del spa
        String createTableReservacionesSpaQuery = "CREATE TABLE reservaciones_spa (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_huesped INTEGER," +  // Clave foránea que hace referencia al ID del huésped
                "fecha TEXT," +
                "hora TEXT," +
                "servicio TEXT," +
                "FOREIGN KEY(id_huesped) REFERENCES huespedes(_id))";
        db.execSQL(createTableReservacionesSpaQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de actualización de la base de datos, se pueden manejar cambios en la estructura aquí
    }

    public Huesped getHuesped(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Huesped huesped = null;
        Cursor cursor = db.query(
                "huespedes",
                null,
                "_id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            huesped = new Huesped(cursor);
            cursor.close();
        }
        return huesped;
    }
}
