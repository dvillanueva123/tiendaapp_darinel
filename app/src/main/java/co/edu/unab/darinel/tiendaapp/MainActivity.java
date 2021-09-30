package co.edu.unab.darinel.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private TextView tvTitulo;
private EditText etCorreo;
private EditText etPassword;
private Button btIniciar;
private Button btRegistrar;
private SharedPreferences preferencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitulo= findViewById(R.id.tv_titulo_inicio);
        etCorreo= findViewById(R.id.et_correo_inicio);
        btIniciar = findViewById(R.id.bt_iniciar_inicio);
        etPassword= findViewById(R.id.et_password_inicio);
        btRegistrar=findViewById(R.id.bt_main_registrar);
        preferencias= getSharedPreferences(getString(R.string.txt_nombre_preferencias),MODE_PRIVATE);

        boolean logueado= preferencias.getBoolean(getString(R.string.txt_preferencia_login),false);

        if(logueado){
            Intent miIntencion = new Intent(MainActivity.this,ListadoActivity.class);



            startActivity(miIntencion);


            finish();
        }
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent miIntencion = new Intent(MainActivity.this,RegistroActividad.class);
                startActivity(miIntencion);
                finish();
            }
        });

        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usu="juan@correo.com";
                String pass="juan123";
                String correo= etCorreo.getText().toString();
                String password= etPassword.getText().toString();
                if (correo.equals(usu)&& password.equals(pass)){
                    Toast.makeText(MainActivity.this,"Bienvenido"+correo,Toast.LENGTH_LONG).show();


                    SharedPreferences.Editor editable= preferencias.edit();
                    editable.putBoolean(getString(R.string.txt_preferencia_login), true);
                    editable.putString("email",correo);

                    editable.apply();

                    Intent miIntencion = new Intent(MainActivity.this,ListadoActivity.class);



                    startActivity(miIntencion);


                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Datos errados", Toast.LENGTH_LONG).show();
                }
            }
        });

        Log.d("Ciclovida", "Ejecutando Oncreate...");
    }
    @Override
        protected void onStart() {

            super.onStart();
        Log.d("Ciclovida", "Ejecutando OnStart...");
        }
    @Override
    protected void onResume() {

        super.onResume();
        Log.d("Ciclovida", "Ejecutando Resume...");
    }
    @Override
    protected void onPause() {

        super.onPause();
        Log.d("Ciclovida", "Ejecutando OnPause...");
    }
    @Override
    protected void onRestart() {

        super.onRestart();
        Log.d("Ciclovida", "Ejecutando OnRestart...");
    }
    @Override
    protected void onStop() {

        super.onStop();
        Log.d("Ciclovida", "Ejecutando OnStop...");
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.d("Ciclovida", "Ejecutando OnDestroy...");
    }

    }
