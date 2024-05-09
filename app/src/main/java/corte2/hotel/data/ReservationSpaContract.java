package corte2.hotel.data;

import android.provider.BaseColumns;

public class ReservationSpaContract {
    public static abstract class ReservationSpaEntry implements BaseColumns {
        public static final String TABLE_NAME = "reservations_spa";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_START_DATE = "start_date";
        public static final String COLUMN_END_DATE = "end_date";
        public static final String COLUMN_SERVICE_TYPE = "service_type";
        public static final String COLUMN_PRICE = "price";

    }
}
