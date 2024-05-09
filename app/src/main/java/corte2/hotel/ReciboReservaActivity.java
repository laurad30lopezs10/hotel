package corte2.hotel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import corte2.hotel.data.HotelDBHelper;
import corte2.hotel.data.ReservationSpa;
import corte2.hotel.data.Spa;

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




        Intent intent = getIntent();
        if (intent != null) {
            String user =  intent.getStringExtra("usuario");
            Cursor cursor = db.getReservationsByUser(user);
            if(cursor.moveToFirst()){
              cursor.moveToLast();

              @SuppressLint("Range") String fechaInicio = cursor.getString(cursor.getColumnIndex("start_reserve"));
              @SuppressLint("Range") String fechaFin =  cursor.getString(cursor.getColumnIndex("end_reserve"));
              @SuppressLint("Range") int costoTotal = cursor.getInt(cursor.getColumnIndex("spa"));

              fechaInicioTextView.setText("Fecha de inicio: " + fechaInicio);
              fechaFinTextView.setText("Fecha de finalización: " + fechaFin);
              costoTextView.setText("Costo total: $" + costoTotal);


            }


        }

        cerrarButton.setOnClickListener(view -> finish());
    }
}
