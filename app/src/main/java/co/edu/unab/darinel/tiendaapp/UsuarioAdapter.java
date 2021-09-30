package co.edu.unab.darinel.tiendaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UsuarioAdapter extends RecyclerView.Adapter {
    private ArrayList<Usuario> listado;


    public UsuarioAdapter(ArrayList<Usuario> listado) {
        this.listado = listado;
    }
    public class UsuarioViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImagen;
        TextView tvNombreUsuario;
        TextView tvCorreoUsuario;
        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen =itemView.findViewById(R.id.iv_usuario_imagen);
            tvNombreUsuario= itemView.findViewById(R.id.tv_usuario_nombre);
            tvCorreoUsuario=itemView.findViewById(R.id.tv_usuario_correo);


        }
    }

    public void setListado(ArrayList<Usuario> listado) {
        this.listado = listado;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario,parent,false);

        return new UsuarioAdapter.UsuarioViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Usuario miUsuario = listado.get(position);
        UsuarioAdapter.UsuarioViewHolder miHolder= (UsuarioAdapter.UsuarioViewHolder) holder;
        miHolder.tvNombreUsuario.setText(miUsuario.getNombre());
        miHolder.tvCorreoUsuario.setText(miUsuario.getCorreo());
        Glide.with(holder.itemView.getContext()).load(miUsuario.getUrlimagen()).into(((UsuarioViewHolder)holder).ivImagen);



    }

    @Override
    public int getItemCount() {
        return listado.size();
    }
    public interface OnItemClickListener{
        void onItemClick(Usuario miUsuario);
    }
}
