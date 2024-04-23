package corte2.hotel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SpaActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton ZonasHumedas;
    Button Fecha;
    Button hora;
    EditText textFecha;
    EditText texthora;
    Button FReserva;
    private int dia, mes, año, horad, minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spa);

        ZonasHumedas = findViewById(R.id.imageButton);
        Fecha = findViewById(R.id.buttonFecha);
        hora = findViewById(R.id.buttonHora);
        textFecha = findViewById(R.id.ReservarFecha);
        texthora = findViewById(R.id.ReservarHora);
        FReserva = findViewById(R.id.ReservaFinal);
        Fecha.setOnClickListener(this);
        hora.setOnClickListener(this);

        ZonasHumedas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu();
            }
        });
    }

    public void popupMenu() {
        PopupMenu pop = new PopupMenu(this, ZonasHumedas);
        pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        pop.show();
    }

    @Override
    public void onClick(View v) {
        if (v == Fecha) {
            final Calendar calendar = Calendar.getInstance();
            dia = calendar.get(Calendar.DAY_OF_MONTH);
            mes = calendar.get(Calendar.MONTH);
            año = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    textFecha.setText(String.format("%02d/%02d/%d", dayOfMonth, month + 1, year)); // Mes se indexa desde 0
                }
            }, año, mes, dia);
            datePickerDialog.show();
        }
        if (v == hora) {
            final Calendar calendar = Calendar.getInstance();
            horad = calendar.get(Calendar.HOUR_OF_DAY);
            minuto = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    texthora.setText(String.format("%02d:%02d", hourOfDay, minute));
                }
            }, horad, minuto, false);
            timePickerDialog.show();
        }

    }

    public void GuardarReserva(View v) {
        // Aquí deberías agregar el código para guardar la reserva en la base de datos
        // usando el objeto DatabaseHelper y realizando una inserción en la tabla reservaciones_spa
        // Por ejemplo:
        // DatabaseHelper dbHelper = new DatabaseHelper(this);
        // SQLiteDatabase db = dbHelper.getWritableDatabase();
        // ContentValues values = new ContentValues();
        // values.put("fecha", textFecha.getText().toString());
        // values.put("hora", texthora.getText().toString());
        // db.insert("reservaciones_spa", null, values);
        // db.close();

        // Después de guardar la reserva, puedes iniciar otra actividad o hacer cualquier otra cosa
        Intent linear = new Intent(this, MainActivity.class);
        startActivity(linear);
    }
}
