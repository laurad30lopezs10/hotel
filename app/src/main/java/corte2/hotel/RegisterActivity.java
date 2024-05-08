package corte2.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import corte2.hotel.data.*;
import corte2.hotel.data.Huesped;

public class RegisterActivity extends AppCompatActivity {
  private EditText NombreH;
  private EditText ApellidoH;
  private EditText IdentificacionH;
  private EditText TelefonoH;
  private EditText EmailH;
  private EditText UsuarioH;
  private EditText PasswordH;
  private Button GuardarH;
  private HotelDBHelper dbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    dbHelper = new HotelDBHelper(this);
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
    // Recopilación de datos del usuario
    String nombre = NombreH.getText().toString() + ApellidoH.getText().toString();
    String identificacion = IdentificacionH.getText().toString();
    String telefono = TelefonoH.getText().toString();
    String email = EmailH.getText().toString();
    String usuario = UsuarioH.getText().toString();
    String password = PasswordH.getText().toString();

    if (verificarCamposVacios()) {

      mostrarMensaje("INGRESE TODOS LOS DATOS");

    }else if(TelefonoH.length() < 7){

      mostrarMensaje("INGRESE UN NUMERO DE TELENO VALIDO");
    }else{

      Telefono telefonoUser = new Telefono(usuario, Long.parseLong(telefono));
      Huesped nuevoUser = new Huesped(usuario, password, nombre, email);


      dbHelper.saveHuesped(nuevoUser, telefonoUser);

      Intent linear = new Intent(this, MainActivity.class);
      startActivity(linear);

    }




  }

  private boolean verificarCamposVacios() {
    String[] campos = {NombreH.getText().toString(), IdentificacionH.getText().toString(), TelefonoH.getText().toString(), EmailH.getText().toString(), UsuarioH.getText().toString(), PasswordH.getText().toString()};
    for (String campo : campos) {
      if (campo == null || campo.isEmpty()) {
        return true;
      }
    }
    return false;
  }

  private void mostrarMensaje(String mensaje) {
    new AlertDialog.Builder(this)
      .setTitle("Atención")
      .setMessage(mensaje)
      .setPositiveButton(android.R.string.ok, null)
      .setIcon(android.R.drawable.ic_dialog_alert)
      .show();


  }
}
