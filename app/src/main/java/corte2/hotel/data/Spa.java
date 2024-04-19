package corte2.hotel.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

public class Spa {
    private int cod_zone;
    private String name_zone;
    private String description;
    private int capacity;
    private String type;

    public Spa(int cod_zone, String name_zone, String description, int capacity, String type) {
        this.cod_zone = cod_zone;
        this.name_zone = name_zone;
        this.description = description;
        this.capacity = capacity;
        this.type = type;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(SpaContract.SpaEntry.col_cod_zone, cod_zone);
        values.put(SpaContract.SpaEntry.col_name_zone, name_zone);
        values.put(SpaContract.SpaEntry.col_description, description);
        values.put(SpaContract.SpaEntry.col_capacity, capacity);
        values.put(SpaContract.SpaEntry.col_type, type);
        return values;
    }

    @SuppressLint("Range")
    public Spa(Cursor cursor){
        this.cod_zone = cursor.getInt( cursor.getColumnIndex( SpaContract.SpaEntry.col_cod_zone ) );
        this.name_zone = cursor.getString( cursor.getColumnIndex( SpaContract.SpaEntry.col_name_zone ) );
        this.description = cursor.getString( cursor.getColumnIndex( SpaContract.SpaEntry.col_description ) );
        this.capacity = cursor.getInt( cursor.getColumnIndex( SpaContract.SpaEntry.col_capacity) );
        this.type = cursor.getString( cursor.getColumnIndex( SpaContract.SpaEntry.col_type ) );
    }

    public int getCod_zone() {
        return cod_zone;
    }

    public String getName_zone() {
        return name_zone;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }
}
