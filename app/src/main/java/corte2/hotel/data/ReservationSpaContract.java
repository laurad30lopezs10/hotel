package corte2.hotel.data;

import android.provider.BaseColumns;

public class ReservationSpaContract {
    public static abstract class ReservationSpaEntry implements BaseColumns {
        public static final String TABLE_NAME = "ReservationSpa";
        public static final String col_num_reservation = "num_reserve";
        public static final String col_usuario = "user";

        public static final String start_date_reservation = "start_reserve";
        public static final String end_date_reservation = "end_reserve";
        public static final String col_zone_spa = "spa";
    }
}
