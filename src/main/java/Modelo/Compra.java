package Modelo;

public class Compra {

    private int id;
    private int IDUSUARIO;
    private String fecha;
    private Double monto;
    private int idPago;
    private String estado;

    public Compra() {
    }

    public Compra(int id, int IDUSUARIO, String fecha, Double monto, int idPago, String estado) {
        this.id = id;
        this.IDUSUARIO = IDUSUARIO;
        this.fecha = fecha;
        this.monto = monto;
        this.idPago = idPago;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIDUSUARIO() {
        return IDUSUARIO;
    }

    public void setIDUSUARIO(int IDUSUARIO) {
        this.IDUSUARIO = IDUSUARIO;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    
    
}
