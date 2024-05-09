package corte2.hotel;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import corte2.hotel.data.HotelDBHelper;
import corte2.hotel.data.Huesped;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Declaración de variables
    private EditText usuario;
    private EditText password;
    private Button Ingresar;
    private Button irRegistrar;
    private HotelDBHelper db;

    // Parámetros para la actividad
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Vinculación de variables con elementos de diseño
        usuario = findViewById(R.id.UsuarioHuesped);
        password = findViewById(R.id.PasswordHuesped);
        Ingresar = findViewById(R.id.buttonIngresar);
        irRegistrar = findViewById(R.id.buttonRegistrar);
        db = new HotelDBHelper(this); // Inicializar el ayudante de la base de datos

        // Establecer oyentes de clic para los botones
        Ingresar.setOnClickListener(this);
        irRegistrar.setOnClickListener(this);

        // Recuperar los parámetros si existen
        Intent intent = getIntent();
        if (intent != null) {
            mParam1 = intent.getStringExtra(ARG_PARAM1);
            mParam2 = intent.getStringExtra(ARG_PARAM2);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonIngresar:
                // Lógica para el botón de Ingresar
                login();
                break;
            case R.id.buttonRegistrar:
                // Lógica para el botón de Registrar
                irRegistro();
                break;
        }
    }

    // Método para procesar el inicio de sesión
    private void login() {
        if (usuario.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            // Verificar si los campos están vacíos
            Toast.makeText(this,"LLENE LOS CAMPOS",Toast.LENGTH_LONG).show();
        } else {
            // Verificar las credenciales en la base de datos
            Cursor cursor = db.getHuespedByUser(usuario.getText().toString(), password.getText().toString());
            if (cursor != null && cursor.moveToNext()) {
                // Si las credenciales son válidas, iniciar la actividad SpaActivity
                Huesped hdp = new Huesped(cursor);
                Intent linear = new Intent(this, SpaActivity.class);
                linear.putExtra("usuario", usuario.getText().toString());
                startActivity(linear);
            } else {
                // Si las credenciales son inválidas, mostrar un mensaje de error
                Toast.makeText(this,"Credenciales Invalidas",Toast.LENGTH_LONG).show();
            }
        }
    }

    // Método para iniciar la actividad RegisterActivity
    private void irRegistro() {
        Intent linear = new Intent(this, RegisterActivity.class);
        startActivity(linear);
    }

}
