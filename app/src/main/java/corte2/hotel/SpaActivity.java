package corte2.hotel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class SpaActivity extends AppCompatActivity {

    private Button buttonFechaInicio;
    private Button buttonFechaFin;
    private Button buttonConfirmar;

    private int mYear, mMonth, mDay,mMinute,mHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spa);

        buttonFechaInicio = findViewById(R.id.buttonFechaInicio);
        buttonFechaFin = findViewById(R.id.buttonFechaFin);
        buttonConfirmar = findViewById(R.id.buttonConfirmar);

        buttonFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(buttonFechaInicio);
            }
        });

        buttonFechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(buttonFechaFin);
            }
        });

        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener las fechas seleccionadas
                String fechaInicio = buttonFechaInicio.getText().toString();
                String fechaFin = buttonFechaFin.getText().toString();

                // Verificar si las fechas están vacías
                if (fechaInicio.isEmpty() || fechaFin.isEmpty()) {
                    Toast.makeText(SpaActivity.this, "Por favor, seleccione las fechas de inicio y finalización.", Toast.LENGTH_SHORT).show();
                } else {
                    // Guardar los datos y abrir el ReciboReservaActivity
                    Intent intent = new Intent(SpaActivity.this, ReciboReservaActivity.class);
                    intent.putExtra("fechaInicio", fechaInicio);
                    intent.putExtra("fechaFin", fechaFin);
                    startActivity(intent);
                }
            }
        });
    }

    private void showDatePickerDialog(final Button button) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                        button.setText(date);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }



}