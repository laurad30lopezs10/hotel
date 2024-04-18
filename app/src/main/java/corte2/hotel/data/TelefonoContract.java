package corte2.hotel.data;

import android.provider.BaseColumns;

public class TelefonoContract {
    public static abstract class TelefonoEntry implements BaseColumns {
        public static final String TABLE_NAME = "telefono";
        public static final String col_telefono = "telef";
    }
}
