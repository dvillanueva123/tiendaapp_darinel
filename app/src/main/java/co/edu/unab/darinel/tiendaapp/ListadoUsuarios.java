package co.edu.unab.darinel.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ListadoUsuarios extends AppCompatActivity {
    private ArrayList<Usuario> ListadoUsuario;
    private RecyclerView rvUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rvUsuarios.findViewById(R.id.rv_usuario);
        setContentView(R.layout.activity_listado_usuarios);

        super.onCreate(savedInstanceState);

    }
}