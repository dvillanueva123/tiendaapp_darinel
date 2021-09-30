package co.edu.unab.darinel.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class RegistroActividad extends AppCompatActivity {
private EditText etNombre;
private EditText etUrlimagen;
private  EditText etCorreo;
private  EditText etContraseña;
private Button btRegistrar;
public Adapter miAdaptador;
public RecyclerView rvUsuarios;
    public ArrayList<Usuario> ListadoUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        etNombre.findViewById(R.id.et_usuario_nombre);
        etUrlimagen.findViewById(R.id.et_usuario_imagen);
        etCorreo.findViewById(R.id.et_usuario_correo);
        etContraseña.findViewById(R.id.et_usuario_contraseña);

Cargarusuario();

btRegistrar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent miIntencion = new Intent(RegistroActividad.this,ListadoUsuarios.class);
        startActivity(miIntencion);
        finish();
    }
});

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_actividad);
    }
    private void Cargarusuario(){

        Usuario nuevo= new Usuario(etNombre,etCorreo,etUrlimagen);
        ListadoUsuario.add(nuevo);
    }
}