package co.edu.unab.darinel.tiendaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetalleActivity extends AppCompatActivity {
    private TextView tvTitulo;
    private ImageView ivProducto;
    private TextView tvPrecio;
    private TextView tvDescripcion;
    private Button btEditar;
    private Button btEliminar;
    private static final int CODIGO_EDITAR_PRODUCTO=100;
    private Producto miProducto;
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mi_cerrar_sesion:

                SharedPreferences preferencias = getSharedPreferences(getString(R.string.txt_nombre_preferencias),MODE_PRIVATE);
                SharedPreferences.Editor editable= preferencias.edit();
                editable.clear();
                editable.apply();

                Intent i = new Intent(DetalleActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        tvTitulo = findViewById(R.id.tv_titulo_detalle);
        ivProducto= findViewById(R.id.iv_producto_detalle);
        tvPrecio= findViewById(R.id.tv_precio_detalle);
        tvDescripcion= findViewById(R.id.tv_descripcion_detalle);
        btEditar= findViewById(R.id.bt_editar_producto);
        btEliminar=findViewById(R.id.bt_eliminar_producto);


        miProducto =(Producto) getIntent().getSerializableExtra("producto");
        cargarDatosProducto();


        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iData= new Intent();
                iData.putExtra("producto",miProducto);
                setResult(RESULT_OK,iData);
                finish();
            }
        });
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(DetalleActivity.this,FormularioActivity.class);
                i.putExtra("producto",miProducto);

                startActivityForResult(i,CODIGO_EDITAR_PRODUCTO);

            }
        });



    }

    private void cargarDatosProducto() {
        tvTitulo.setText(getString(R.string.txt_titulo_detalle, miProducto.getNombre()));
        tvPrecio.setText(String.valueOf(miProducto.getPrecio()));
        tvDescripcion.setText(miProducto.getDescripcion());
        Glide.with(DetalleActivity.this).load(miProducto.getUrlImage()).into(ivProducto);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODIGO_EDITAR_PRODUCTO && resultCode == RESULT_OK) {

            if (data != null) {

                 miProducto = (Producto) data.getSerializableExtra("producto");
                  cargarDatosProducto();
            }


        }
    }

    @Override
    public void onBackPressed() {
        Intent iData= new Intent();
        iData.putExtra("producto",miProducto);
        iData.putExtra("editar",true);
        setResult(RESULT_OK,iData);
        super.onBackPressed();


    }
}