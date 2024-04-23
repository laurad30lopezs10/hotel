package corte2.hotel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import corte2.hotel.data.DatabaseHelper;
import corte2.hotel.data.Huesped;

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

        // Después de guardar los datos en la base de datos
        if (newRowId != -1) {
            // Mostrar el diálogo emergente con el mensaje de confirmación y los datos de usuario
            showPopup("Datos guardados correctamente", usuario, password);
            // Obtener los datos del usuario recién insertado
            Huesped huesped = dbHelper.getHuesped(newRowId);
            if (huesped != null) {
                // Mostrar el diálogo emergente con los datos del usuario
                showPopup("Datos del usuario:", huesped.getUsuario(), huesped.getPassword());
            } else {
                Toast.makeText(MainActivity2.this, "Error al obtener los datos del usuario", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity2.this, "Error al guardar el registro", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    private void showPopup(String message, String usuario, String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Inflar el diseño del diálogo emergente
        View view = getLayoutInflater().inflate(R.layout.popup_layout, null);
        builder.setView(view);

        // Configurar el mensaje del diálogo emergente
        TextView textViewMessage = view.findViewById(R.id.textViewPopupMessage);
        textViewMessage.setText(message);

        // Mostrar el usuario y la contraseña en los TextViews correspondientes
        EditText textViewUsuario = view.findViewById(R.id.textViewUsuario);
        textViewUsuario.setText("Usuario: " + usuario);

        EditText textViewPassword = view.findViewById(R.id.textViewPassword);
        textViewPassword.setText("Contraseña: " + password);

        // Crear y mostrar el diálogo emergente
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
