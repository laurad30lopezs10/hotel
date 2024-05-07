package corte2.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReciboReservaActivity extends AppCompatActivity {

    private TextView fechaInicioTextView;
    private TextView fechaFinTextView;
    private TextView costoTextView;
    private Button cerrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_reserva);

        fechaInicioTextView = findViewById(R.id.fechaInicioTextView);
        fechaFinTextView = findViewById(R.id.fechaFinTextView);
        costoTextView = findViewById(R.id.costoTextView);
        cerrarButton = findViewById(R.id.cerrarButton);

        Intent intent = getIntent();
        if (intent != null) {
            String fechaInicio = intent.getStringExtra("fechaInicio");
            String fechaFin = intent.getStringExtra("fechaFin");
            int costoTotal = intent.getIntExtra("costoTotal", 0);

            fechaInicioTextView.setText("Fecha de inicio: " + fechaInicio);
            fechaFinTextView.setText("Fecha de finalización: " + fechaFin);
            costoTextView.setText("Costo total: $" + costoTotal);
        }

        cerrarButton.setOnClickListener(view -> finish());
    }
}
