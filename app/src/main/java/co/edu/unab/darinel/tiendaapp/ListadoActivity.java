package co.edu.unab.darinel.tiendaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {
private ArrayList<Producto> ListadoProductos;
private RecyclerView rvProductos;
private Button btFormulario;
private static final int CODIGO_AGREGAR_PRODUCTO=100;
    private static final int CODIGO_DETALLE_PRODUCTO=100;
private  ProductoAdapter miAdaptador;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mi_cerrar_sesion:

                SharedPreferences preferencias = getSharedPreferences(getString(R.string.txt_nombre_preferencias),MODE_PRIVATE);
                SharedPreferences.Editor editable= preferencias.edit();
                editable.clear();
                editable.apply();

                Intent i = new Intent(ListadoActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        String email = getSharedPreferences(getString(R.string.txt_nombre_preferencias),MODE_PRIVATE).getString("email","");

        setTitle(email);

        rvProductos = findViewById(R.id.rv_producto_listado);
        btFormulario= findViewById(R.id.button);
        btFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent miIntencion = new Intent(ListadoActivity.this,FormularioActivity.class);
                startActivityForResult(miIntencion,CODIGO_AGREGAR_PRODUCTO);
            }
        });






        ListadoProductos = new ArrayList<>();
        cargarProductosFake();

         miAdaptador= new ProductoAdapter(ListadoProductos);
        rvProductos.setAdapter(miAdaptador);

        rvProductos.setLayoutManager(new LinearLayoutManager(ListadoActivity.this));
        rvProductos.setHasFixedSize(true);


        miAdaptador.setOnItemClickListener(new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto) {
                Intent miIntencion= new Intent(ListadoActivity.this,DetalleActivity.class);
                miIntencion.putExtra("producto",miProducto);
                startActivityForResult(miIntencion,CODIGO_DETALLE_PRODUCTO);
            }
        });

      //  lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //    @Override
          //  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //    Producto miProducto= ListadoProductos.get(i);
              //  Toast.makeText(ListadoActivity.this, "Hice click"+miProducto, Toast.LENGTH_SHORT).show();
                //Intent miIntencion = new Intent(ListadoActivity.this,DetalleActivity.class);

               // miIntencion.putExtra("producto",miProducto);
                //startActivity(miIntencion);

           // }
        //});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== CODIGO_AGREGAR_PRODUCTO&& resultCode==RESULT_OK){

            if (data != null) {
                Producto miProducto=(Producto) data.getSerializableExtra("producto");
                Producto ultimo= ListadoProductos.get(ListadoProductos.size()-1);
                int id = ultimo.getId()+1;
                miProducto.setId(id);
                ListadoProductos.add(miProducto);

                miAdaptador.setListado(ListadoProductos);

                Toast.makeText(ListadoActivity.this, miProducto.toString(), Toast.LENGTH_SHORT).show();
            }


        }
        if (requestCode==CODIGO_DETALLE_PRODUCTO&& resultCode==RESULT_OK){
            if (data != null){
                Producto miProducto= (Producto) data.getSerializableExtra("producto");
                Boolean editar= data.getBooleanExtra("editar",false);
                for(Producto elemento: ListadoProductos){
                    Log.d("ELIMINAR","LISTA"+elemento.getId()+"eliminar:"+miProducto.getId());
                    if (elemento.getId()==miProducto.getId()){
                        int posicion= ListadoProductos.indexOf(elemento);

                        if (editar){
                            ListadoProductos.set(posicion,miProducto);
                        }else{
                            ListadoProductos.remove(elemento);
                        }

                        break;
                    }
                }


                miAdaptador.setListado(ListadoProductos);
            }
        }
    }

    private void cargarProductosFake(){
        Producto pr1= new Producto("Memoria USB",70000,"https://www.alkosto.com/medias/619659097318-001-310Wx310H?context=bWFzdGVyfGltYWdlc3wxMjYxOXxpbWFnZS9qcGVnfGltYWdlcy9oMGUvaDUyLzg4Mjg1NjcwOTMyNzguanBnfGM2YzU0YmQ4ZGIzMTFhOGNiMWEyOTJhOTBkMDE0MmFhODViYTExODJjZTI2ZTBmMjBjMWI3NWY1YzIxNzgyNzE");
        pr1.setId(1);
        ListadoProductos.add(pr1);
        Producto pr2= new Producto("Disco Duro",120000,"https://lasus.com.co/5901-large_default/disco-duro-ssd-25-240gb-sata-kingston-sa400s37240g-500-mbs.jpg");
        pr2.setDescripcion("Disco duro SSD 500GB");
        pr2.setId(2);
        ListadoProductos.add(pr2);
        Producto pr3= new Producto("Mouse",50000,"https://tecnoplaza.com.co/sitio/wp-content/uploads/2020/06/Logitech-G203-LIGHTSYNC-mejora-el-modelo-Prodigy-con-nuevo-sensor_2-600x623.jpg");
        pr3.setId(3);
        ListadoProductos.add(pr3);
        Producto pr4= new Producto("Teclado",80000,"https://http2.mlstatic.com/D_NQ_NP_783315-MLA45324995542_032021-O.jpg");
        pr4.setId(4);
        ListadoProductos.add(pr4);
        Producto pr5= new Producto("Pantalla",500000,"https://queoferton.uy/wp-content/uploads/2021/05/06-Monitor-Gamer-Curvo-Msi-Optix-27-1080p-min-600x600.png");
        pr5.setId(5);
        ListadoProductos.add(pr5);

    }
}