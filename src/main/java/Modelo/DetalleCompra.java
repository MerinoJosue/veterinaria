
package Modelo;

public class DetalleCompra {
    int id;
    int idcompra;
    int Id_Producto;
    int cantidad;
    double precioCompra;
    Productos producto;

    public DetalleCompra() {
    }

    public DetalleCompra(int id, int idcompra, int Id_Producto, int cantidad, double precioCompra, Productos producto) {
        this.id = id;
        this.idcompra = idcompra;
        this.Id_Producto = Id_Producto;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(int Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    
}
