package corte2.hotel;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import corte2.hotel.data.HotelDBHelper;
import corte2.hotel.data.ReservationSpa;
import corte2.hotel.data.ReservationSpaContract;
import corte2.hotel.data.Spa;
import corte2.hotel.data.SpaContract;

public class SpaActivity extends AppCompatActivity {

      private Button buttonFechaInicio;
      private Button buttonFechaFin;
      private Button buttonConfirmar;
      private RadioButton RadioMasajes;
      private RadioButton RadioPiscina;
      private int precio;
      private RadioButton RadioSauna;
      private RadioButton RadioTurco;
      private int mYear, mMonth, mDay;

    private HotelDBHelper dbHelper;


    @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_spa);

          dbHelper = new HotelDBHelper(this);
          buttonFechaInicio = findViewById(R.id.buttonFechaInicio);
          buttonFechaFin = findViewById(R.id.buttonFechaFin);
          buttonConfirmar = findViewById(R.id.buttonConfirmar);
          RadioMasajes= findViewById(R.id.radioButtonMasajes);
          RadioPiscina=findViewById(R.id.radioButtonPiscina);
          RadioSauna=findViewById(R.id.radioButtonSauna);
          RadioTurco=findViewById(R.id.radioButtonTurco);


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

                // Verificar la selección de los radiobutton
                String tipoServicio = "";
                if (RadioMasajes.isChecked()) {
                    tipoServicio = "massage room";
                    precio = 200000;
                } else if (RadioPiscina.isChecked()) {
                    tipoServicio = "Swimming Pool";
                    precio = 100000;
                } else if (RadioSauna.isChecked()) {
                    tipoServicio = "Sauna";
                    precio = 300000;
                } else if (RadioTurco.isChecked()) {
                    tipoServicio = "Turco";
                    precio = 400000;
                }

                // Verificar si las fechas están vacías
                if (fechaInicio.isEmpty() || fechaFin.isEmpty() || tipoServicio.isEmpty()) {
                    Toast.makeText(SpaActivity.this, "Por favor, seleccione las fechas y el servicio.", Toast.LENGTH_SHORT).show();
                } else {
                    // Convertir las cadenas de texto a objetos Date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    try {
                        Date fechaInicioDate = dateFormat.parse(fechaInicio);
                        Date fechaFinDate = dateFormat.parse(fechaFin);

                        // Comparar las fechas
                        if (fechaInicioDate.before(fechaFinDate)) {


                            /// Abrir el ReciboReservaActivity
                            Intent recibir = getIntent();
                            String Usuario = recibir.getStringExtra("usuario");

                            // Guardar los datos en la base de datos
                            guardarReserva(fechaInicioDate, fechaFinDate, tipoServicio, Usuario, precio);

                            Intent intent = new Intent(SpaActivity.this, ReciboReservaActivity.class);
                            intent.putExtra("fechaInicio", fechaInicio);
                            intent.putExtra("fechaFin", fechaFin);
                            intent.putExtra("usuario", Usuario);
                            startActivity(intent);
                        } else {
                            // La fecha de inicio es mayor o igual a la fecha de fin
                            Toast.makeText(SpaActivity.this, "La fecha de inicio debe ser menor que la fecha de fin.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(SpaActivity.this, "Error al comparar las fechas.", Toast.LENGTH_SHORT).show();
                    }
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

    private void guardarReserva(Date fechaInicio, Date fechaFin, String tipoServicio, String user, int cost) {
        // Obtener instancia de la base de datos
      SQLiteDatabase db = dbHelper.getWritableDatabase();
      Cursor cursor = db.rawQuery("SELECT MAX(num_reserve) FROM ReservationSpa", null);
      long nextId = 0;
      if (cursor.moveToFirst()) {
        nextId = cursor.getLong(0) + 1;
      }
      cursor.close();


      ReservationSpa reservado = new ReservationSpa(nextId, fechaInicio,fechaFin, user, cost);
      dbHelper.saveReservationSpa(reservado);

        // Crear un ContentValues para insertar los datos
        /*ContentValues values = new ContentValues();
        values.put(ReservationSpaContract.ReservationSpaEntry.start_date_reservation, fechaInicio);
        values.put(ReservationSpaContract.ReservationSpaEntry.end_date_reservation, fechaFin);
        values.put(SpaContract.SpaEntry.col_name_zone, tipoServicio);

        // Insertar los datos en la tabla de reservas
        long newRowId = db.insert("ReservationSpaEntry", null, values);

        if (newRowId != -1) {
            // La inserción fue exitosa
            Toast.makeText(SpaActivity.this, "Reserva guardada correctamente", Toast.LENGTH_SHORT).show();
        } else {
            // Ocurrió un error al insertar los datos
            Toast.makeText(SpaActivity.this, "Error al guardar la reserva", Toast.LENGTH_SHORT).show();
        }*/

        // Cerrar la conexión a la base de datos
        db.close();
    }


}
