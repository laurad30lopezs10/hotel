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

public class MainActivity2 extends AppCompatActivity {
    private EditText NombreH = (EditText) findViewById(R.id.IngresoNombreHuesped);
    private EditText ApellidoH = (EditText) findViewById(R.id.IngresoApellidoHuesped);
    private EditText IdentificacionH=(EditText) findViewById(R.id.IngresoNumIdentidadHuesped);
    private EditText TelefonoH=(EditText) findViewById(R.id.IngresoTelHuesped);
    private EditText EmailH=(EditText) findViewById(R.id.IngresoEmailHuesped);
    private EditText UsuarioH=(EditText) findViewById(R.id.CreacionUsuarioHuesped);
    private EditText PasswordH=(EditText) findViewById(R.id.CreacionPasswordHuesped);
private Button GuardarH=(Button) findViewById(R.id.buttonGuardarRegistro);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }  public void GuardarRegistro(View v){
        Intent linear=new Intent(this, MainActivity.class);
        startActivity(linear);
    }
}