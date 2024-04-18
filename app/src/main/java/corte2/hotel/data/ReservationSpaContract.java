package corte2.hotel.data;

import android.provider.BaseColumns;

public class ReservationSpaContract {
    public static abstract class ReservationSpaEntry implements BaseColumns {
        public static final String TABLE_NAME = "ReservationSpa";
        public static final String col_num_reservation = "num_reserve";
        public static final String col_usuario = "user";

        public static final String col_date_reservation = "date_reserve";
        public static final String col_zone_spa = "spa";
    }
}
