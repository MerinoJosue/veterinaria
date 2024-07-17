package Modelo;

import java.io.InputStream;


public class Productos {
    int Id_Producto;
    String nombres;    
    String descripcion;
    double precio;
     int stock;
    InputStream foto;
  

    public Productos() {
    }

    public Productos(int Id_Producto, String nombres, String descripcion, double precio, int stock, InputStream foto) {
        this.Id_Producto = Id_Producto;
        this.nombres = nombres;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.foto = foto;
    }

    public int getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(int Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    
    
    
}
