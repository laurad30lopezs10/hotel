package corte2.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
private EditText usuario =(EditText) findViewById(R.id.UsuarioHuesped);
private EditText password = (EditText) findViewById(R.id.PasswordHuesped);
private Button Ingresar = (Button) findViewById(R.id.buttonIngresar);
private Button irRegistrar = (Button) findViewById(R.id.buttonRegistrar);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Ingreso(View v) {
        Intent linear=new Intent(this, MainActivity2.class);
        startActivity(linear);
    }
    public void irregistro(View v){
        Intent linear=new Intent(this, MainActivity3.class);
        startActivity(linear);
    }
}