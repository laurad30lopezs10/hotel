package corte2.hotel.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;
import corte2.hotel.data.ReservationSpaContract.ReservationSpaEntry;
import corte2.hotel.data.HuespedContract.HuespedEntry;
import corte2.hotel.data.SpaContract.SpaEntry;
public class ReservationSpa {
    private int num_reservation;
    private String usuario;
    private Date date_reservation;

    private int Spa;

    public ReservationSpa(int num_reservation, Date date_reservation, String usuario, int Spa) {
        this.num_reservation = num_reservation;
        this.date_reservation = date_reservation;
        this.usuario = usuario;
        this.Spa = Spa;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(ReservationSpaContract.ReservationSpaEntry.col_num_reservation, num_reservation );
        values.put(HuespedContract.HuespedEntry.col_usuario, usuario);
        values.put(ReservationSpaContract.ReservationSpaEntry.col_date_reservation, String.valueOf(date_reservation));
        values.put(SpaContract.SpaEntry.col_cod_zone, Spa);
        return values;
    }

    @SuppressLint("Range")
    public ReservationSpa(Cursor cursor){
        this.num_reservation = cursor.getInt( cursor.getColumnIndex( ReservationSpaEntry.col_num_reservation));
        this.usuario = cursor.getString( cursor.getColumnIndex( HuespedEntry.col_usuario ));
        long fechaLong = cursor.getLong(cursor.getColumnIndex(ReservationSpaEntry.col_date_reservation));
        this.date_reservation = new Date(fechaLong);
        this.Spa = cursor.getInt( cursor.getColumnIndex( SpaContract.SpaEntry.col_cod_zone));
    }

    public int getNum_reservation() {
        return num_reservation;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getSpa() {
        return Spa;
    }
}
