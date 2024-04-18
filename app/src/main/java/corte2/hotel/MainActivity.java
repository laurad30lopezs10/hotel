package corte2.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText usuario;
    private EditText password;
    private Button Ingresar;
    private Button irRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usuario = findViewById(R.id.UsuarioHuesped);
        password = findViewById(R.id.PasswordHuesped);
        Ingresar = findViewById(R.id.buttonIngresar);
        irRegistrar = findViewById(R.id.buttonRegistrar);
    }

    public void Ingreso(View v) {
        Intent linear = new Intent(this, MainActivity2.class);
        startActivity(linear);
    }

    public void irregistro(View v) {
        Intent linear = new Intent(this, MainActivity3.class);
        startActivity(linear);
    }
}
