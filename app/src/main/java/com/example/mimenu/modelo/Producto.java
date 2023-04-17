package com.example.mimenu.modelo;

public class Producto {

    private String descripcion;
    private double precio;
    private String urlFoto;

    public Producto(String descripcion, double precio, String urlFoto) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.urlFoto = urlFoto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
