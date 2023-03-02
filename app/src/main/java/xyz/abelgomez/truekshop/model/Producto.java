package xyz.abelgomez.truekshop.model;

import java.io.Serializable;

public class Producto implements Serializable {

    private byte[] imagenBytes;
    private String nombre;
    private  String descripcion;
    private int id;
    private double precio;

    public Producto() {
    }


//    public Producto(int imagen, String nombre, String descripcion, int id) {
//        this.imagen = imagen;
//        this.nombre = nombre;
//        this.descripcion = descripcion;
//        this.id = id;
//    }

//    public Producto(int imagen, String nombre, String descripcion, int id, double precio) {
//        this.imagen = imagen;
//        this.nombre = nombre;
//        this.descripcion = descripcion;
//        this.id = id;
//        this.precio = precio;
//    }


    public Producto(byte[] imagenBytes, String nombre, String descripcion, int id, double precio) {
        this.imagenBytes = imagenBytes;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id = id;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

//    public int getImagen() {
//        return imagen;
//    }
//
//    public void setImagen(int imagen) {
//        this.imagen = imagen;
//    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImagenBytes() {
        return imagenBytes;
    }

    public void setImagenBytes(byte[] imagenBytes) {
        this.imagenBytes = imagenBytes;
    }
}
