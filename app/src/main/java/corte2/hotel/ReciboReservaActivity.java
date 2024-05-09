package corte2.hotel;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import corte2.hotel.data.HotelDBHelper;
import corte2.hotel.data.ReservationSpaContract;


public class ReciboReservaActivity extends AppCompatActivity {

    private TextView fechaInicioTextView;

    private TextView fechaFinTextView;

    private TextView costoTextView;

    private Button cerrarButton;

    private HotelDBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_reserva);

        db = new HotelDBHelper(this);
        fechaInicioTextView = findViewById(R.id.fechaInicioTextView);
        fechaFinTextView = findViewById(R.id.fechaFinTextView);
        costoTextView = findViewById(R.id.costoTextView);
        cerrarButton = findViewById(R.id.cerrarButton);

        // Extract data from ReservationSpa table
        Cursor cursor = db.getAllReservations();

        if (cursor.moveToFirst()) {
            long startDateMillis = cursor.getLong(cursor.getColumnIndexOrThrow(ReservationSpaContract.ReservationSpaEntry.COLUMN_START_DATE));
            long endDateMillis = cursor.getLong(cursor.getColumnIndexOrThrow(ReservationSpaContract.ReservationSpaEntry.COLUMN_END_DATE));
            int price = cursor.getInt(cursor.getColumnIndexOrThrow(ReservationSpaContract.ReservationSpaEntry.COLUMN_PRICE));

            // Formatear las fechas
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String startDate = dateFormat.format(new Date(startDateMillis));
            String endDate = dateFormat.format(new Date(endDateMillis));

            // Calcular la duración de la reserva en días
            long durationInMillis = endDateMillis - startDateMillis;
            // convertir milisegundos a días
            int durationInDays = (int) (durationInMillis / (1000 * 60 * 60 * 24));

            // Calcular el costo total de la reserva
            int totalPrice = durationInDays * price;

            // Mostrar los datos en los TextViews
            fechaInicioTextView.setText(startDate);
            fechaFinTextView.setText(endDate);
            costoTextView.setText(String.valueOf(totalPrice));
        }

        cursor.close();
        db.close();

        // Agregar un listener al botón "cerrar"
        cerrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lanzar la actividad MainActivity
                Intent intent = new Intent(ReciboReservaActivity.this, MainActivity.class);
                startActivity(intent);
                // Finalizar la actividad actual
                finish();
            }
        });
    }

}
