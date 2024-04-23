package corte2.hotel;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import corte2.hotel.data.HotelDBHelper;
import corte2.hotel.data.HotelDBHelper.*;
import corte2.hotel.data.Huesped;
import corte2.hotel.data.HuespedContract;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usuario;
    private EditText password;
    private Button Ingresar;
    private Button irRegistrar;
    private HotelDBHelper db;

    private static final String ARG_PARAM1 = "param1";
    private static  final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usuario = findViewById(R.id.UsuarioHuesped);
        password = findViewById(R.id.PasswordHuesped);
        Ingresar = findViewById(R.id.buttonIngresar);
        irRegistrar = findViewById(R.id.buttonRegistrar);
        db = new HotelDBHelper(this);

        Ingresar.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent!=null){
            mParam1 = intent.getStringExtra("ARG_PRAM1");
            mParam2 = intent.getStringExtra("ARG_PARAM2");
        }
    }



    public void Ingreso(View v) {
        Intent linear = new Intent(this, MainActivity3.class);
        startActivity(linear);
    }

    public void irregistro(View v) {
        Intent linear = new Intent(this, MainActivity2.class);
        startActivity(linear);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonIngresar:

                if (usuario.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(this,"LLENE LOS CAMPOS",Toast.LENGTH_LONG).show();
                }else{
                    Cursor cursor = db.getHuespedByUser(usuario.getText().toString(), password.getText().toString());

                    if(cursor != null &&cursor.moveToNext()){
                        Huesped hdp = new Huesped(cursor);
                        irregistro(v);

                    }else{
                        Toast.makeText(this,"Credenciales Invalidas",Toast.LENGTH_LONG).show();
                    }
                }


                break;
            case R.id.buttonRegistrar:

                Ingreso(v);

                break;
        }
    }

  /*public Cursor getHuespedByUser(String usuario, String password) {
    SQLiteDatabase db = HotelDBHelper.getReadableDatabase();
    String query = "SELECT * FROM HUESPED WHERE USUARIO =? AND PASSWORD =?";
    Cursor cursor = db.rawQuery(query, new String[]{usuario, password});
    return cursor;
  }*/

}