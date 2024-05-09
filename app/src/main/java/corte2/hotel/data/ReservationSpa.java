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
    private Date start_date;
    private Date end_date;

    private int Spa;

    public ReservationSpa(int num_reservation, Date start_date, Date end_date, String usuario, int Spa) {
        this.num_reservation = num_reservation;
        this.start_date = start_date;
        this.end_date = end_date;
        this.usuario = usuario;
        this.Spa = Spa;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(ReservationSpaContract.ReservationSpaEntry.col_num_reservation, num_reservation );
        values.put(HuespedContract.HuespedEntry.col_usuario, usuario);
        values.put(ReservationSpaEntry.start_date_reservation, String.valueOf(start_date));
        values.put(ReservationSpaEntry.end_date_reservation, String.valueOf(end_date));
        values.put(SpaContract.SpaEntry.col_cod_zone, Spa);
        return values;
    }

    @SuppressLint("Range")
    public ReservationSpa(Cursor cursor){
        this.num_reservation = cursor.getInt( cursor.getColumnIndex( ReservationSpaEntry.col_num_reservation));
        this.usuario = cursor.getString( cursor.getColumnIndex( HuespedEntry.col_usuario ));
        long fechaStartLong = cursor.getLong(cursor.getColumnIndex(ReservationSpaEntry.start_date_reservation));
        this.start_date = new Date(fechaStartLong);
        long fechaEndLong = cursor.getLong(cursor.getColumnIndex(ReservationSpaEntry.end_date_reservation));
        this.end_date = new Date(fechaEndLong);
        this.Spa = cursor.getInt( cursor.getColumnIndex( SpaContract.SpaEntry.col_cod_zone));
    }

    public int getNum_reservation() {
        return num_reservation;
    }

    public Date getStart_date() {
        return start_date;
    }
    public Date getEnd_date() {
        return end_date;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getSpa() {
        return Spa;
    }
}
