package corte2.hotel;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import corte2.hotel.data.DatabaseHelper;

public class MainActivity2 extends AppCompatActivity {
    private EditText NombreH;
    private EditText ApellidoH;
    private EditText IdentificacionH;
    private EditText TelefonoH;
    private EditText EmailH;
    private EditText UsuarioH;
    private EditText PasswordH;
    private Button GuardarH;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbHelper = new DatabaseHelper(this);

        NombreH = findViewById(R.id.IngresoNombreHuesped);
        ApellidoH = findViewById(R.id.IngresoApellidoHuesped);
        IdentificacionH = findViewById(R.id.IngresoNumIdentidadHuesped);
        TelefonoH = findViewById(R.id.IngresoTelHuesped);
        EmailH = findViewById(R.id.IngresoEmailHuesped);
        UsuarioH = findViewById(R.id.CreacionUsuarioHuesped);
        PasswordH = findViewById(R.id.CreacionPasswordHuesped);
        GuardarH = findViewById(R.id.buttonGuardarRegistro);

        GuardarH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarRegistro();
            }
        });
    }

    private void guardarRegistro() {
        String nombre = NombreH.getText().toString();
        String apellido = ApellidoH.getText().toString();
        String identificacion = IdentificacionH.getText().toString();
        String telefono = TelefonoH.getText().toString();
        String email = EmailH.getText().toString();
        String usuario = UsuarioH.getText().toString();
        String password = PasswordH.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("identificacion", identificacion);
        values.put("telefono", telefono);
        values.put("email", email);
        values.put("usuario", usuario);
        values.put("password", password);

        long newRowId = db.insert("huespedes", null, values);

        if (newRowId != -1) {
            Toast.makeText(MainActivity2.this, "Registro guardado con éxito", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity2.this, "Error al guardar el registro", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
