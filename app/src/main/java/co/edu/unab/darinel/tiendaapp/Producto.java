package co.edu.unab.darinel.tiendaapp;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private double precio;
    private String descripcion;
    private String urlImage;
    public int id;
    public Producto(){

    }
    @Override
    public String toString(){
        return  this.nombre+"($"+precio+")";
    }

    public Producto(String nombre, double precio, String urlImage) {
        this.nombre = nombre;
        this.precio = precio;
        this.urlImage = urlImage;
        this.descripcion="Sin drecripción";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
