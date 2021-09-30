package co.edu.unab.darinel.tiendaapp;

import android.widget.EditText;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String correo;
    private String urlimagen;

    public Usuario(EditText etNombre, EditText etCorreo, EditText etUrlimagen) {

    }

    public Usuario(String nombre, String correo, String urlimagen) {
        this.nombre = nombre;
        this.correo = correo;
        this.urlimagen = urlimagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }
}
