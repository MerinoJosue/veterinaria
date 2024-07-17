
package Modelo;

import java.io.InputStream;

public class Carrito {
    int item;
    int Id_Producto;
    String nombres;
    InputStream Imagen;
    String descripcion;
    double precioCompra;
    int cantidad;
    double subTotal;

    public Carrito() {
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
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

    public InputStream getImagen() {
        return Imagen;
    }

    public void setImagen(InputStream Imagen) {
        this.Imagen = Imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Carrito(int item, int Id_Producto, String nombres, InputStream Imagen, String descripcion, double precioCompra, int cantidad, double subTotal) {
        this.item = item;
        this.Id_Producto = Id_Producto;
        this.nombres = nombres;
        this.Imagen = Imagen;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    
}
