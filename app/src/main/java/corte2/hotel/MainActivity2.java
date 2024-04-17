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
    private EditText NombreH;
    private EditText ApellidoH;
    private EditText IdentificacionH;
    private EditText TelefonoH;
    private EditText EmailH;
    private EditText UsuarioH;
    private EditText PasswordH;
private Button GuardarH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NombreH = (EditText) findViewById(R.id.IngresoNombreHuesped);
        ApellidoH = (EditText) findViewById(R.id.IngresoApellidoHuesped);
        IdentificacionH=(EditText) findViewById(R.id.IngresoNumIdentidadHuesped);
        TelefonoH=(EditText) findViewById(R.id.IngresoTelHuesped);
        EmailH=(EditText) findViewById(R.id.IngresoEmailHuesped);
        PasswordH=(EditText) findViewById(R.id.CreacionPasswordHuesped);
        GuardarH=(Button) findViewById(R.id.buttonGuardarRegistro);
    }  public void GuardarRegistro(View v){
        Intent linear=new Intent(this, MainActivity.class);
        startActivity(linear);
    }
}