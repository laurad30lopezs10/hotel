package corte2.hotel.data;

import android.provider.BaseColumns;

public class SpaContract {
    public static abstract class SpaEntry implements BaseColumns {
        public static final String TABLE_NAME = "spa";
        public static final String col_cod_zone = "zone";
        public static final String col_name_zone = "name_zone";
        public static final String col_description = "description_zone";
        public static final String col_capacity = "capacity_zone";
        public static final String col_type = "type_zone";
    }
}
