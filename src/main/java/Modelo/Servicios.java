
package Modelo;

public class Servicios {
    
    int Id_Servicio;
    String nombre;
    String precio;
    String descripcion;

    public Servicios() {
    }

    public Servicios(int Id_Servicio, String nombre, String precio, String descripcion) {
        this.Id_Servicio = Id_Servicio;
        this.nombre = nombre;
        this.precio= precio;
        this.descripcion = descripcion;
    }

    public int getId_Servicio() {
        return Id_Servicio;
    }

    public void setId_Servicio(int Id_Servicio) {
        this.Id_Servicio = Id_Servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
