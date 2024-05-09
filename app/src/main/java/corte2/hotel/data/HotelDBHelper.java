package corte2.hotel.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import corte2.hotel.data.HuespedContract.HuespedEntry;
import corte2.hotel.data.TelefonoContract.TelefonoEntry;
import corte2.hotel.data.ReservationSpaContract.ReservationSpaEntry;

public class
HotelDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hotel";
    private static final int DATABASE_VERSION = 1;
    public HotelDBHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ HuespedEntry.TABLE_NAME + " (" +
                HuespedEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HuespedEntry.col_usuario+" TEXT NOT NULL, " +
                HuespedEntry.col_password+" TEXT NOT NULL, " +
                HuespedEntry.col_nombre+" TEXT NOT NULL, " +
                HuespedEntry.col_email+" TEXT NOT NULL, " +
                "UNIQUE ("+HuespedEntry.col_usuario+"), UNIQUE("+HuespedEntry.col_email+"))");

        sqLiteDatabase.execSQL( "CREATE TABLE " + TelefonoEntry.TABLE_NAME + " (" +
                HuespedEntry.col_usuario+" TEXT NOT NULL, " +
                TelefonoEntry.col_telefono+" NUMERIC(12,0) NOT NULL," +
                "PRIMARY KEY ("+HuespedEntry.col_usuario+","+TelefonoEntry.col_telefono+")," +
                "UNIQUE("+TelefonoEntry.col_telefono+"),"+
                "FOREIGN KEY ("+HuespedEntry.col_usuario+") REFERENCES "+ HuespedEntry.TABLE_NAME +"("+ HuespedEntry.col_usuario+") ON DELETE CASCADE)");

        sqLiteDatabase.execSQL("CREATE TABLE " + ReservationSpaEntry.TABLE_NAME + " (" +
                ReservationSpaEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                ReservationSpaEntry.COLUMN_USER_ID + " INTEGER, " +
                ReservationSpaEntry.COLUMN_START_DATE + " DATE, " +
                ReservationSpaEntry.COLUMN_END_DATE + " DATE, " +
                ReservationSpaEntry.COLUMN_SERVICE_TYPE + " TEXT, " +
                ReservationSpaEntry.COLUMN_PRICE + " INTEGER)");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen( db );
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    public long saveHuesped(Huesped huesped, Telefono telefono) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(
                HuespedEntry.TABLE_NAME,
                null,
                huesped.toContentValues());
        return saveTelefono( telefono );
    }

    public long saveTelefono(Telefono telefono){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                TelefonoEntry.TABLE_NAME,
                null,
                telefono.toContentValues());
    }

    public void saveReservationSpa(ReservationSpa reservation) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ReservationSpaEntry.COLUMN_START_DATE, reservation.getStartDate().getTime());

        values.put(ReservationSpaEntry.COLUMN_PRICE, reservation.getPrice());

        values.put(ReservationSpaEntry.COLUMN_SERVICE_TYPE, reservation.getServiceType());

        values.put(ReservationSpaEntry.COLUMN_END_DATE, reservation.getEndDate().getTime());

        db.insert(ReservationSpaEntry.TABLE_NAME, null, values);

    }
    public Cursor getHuespedByUser(String user, String password) {
        Cursor c = getReadableDatabase().query(
                HuespedEntry.TABLE_NAME,
                null,
                HuespedEntry.col_usuario + " LIKE ? AND "+HuespedEntry.col_password + " LIKE ?",
                new String[]{user,password},
                null,
                null,
                null);
        return c;
    }


    public Cursor getAllReservations() {

        SQLiteDatabase db = getReadableDatabase();

        return db.query(

                ReservationSpaEntry.TABLE_NAME,

                null,

                null,

                null,

                null,

                null,

                null);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HuespedEntry.TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TelefonoEntry.TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReservationSpaEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);

    }

}
