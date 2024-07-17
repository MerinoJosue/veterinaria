/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Stephano Sanchez
 */
public class usuario {
    private int IDUSUARIO;
    private String nombreUsuario;
    private String clave;
    private boolean estado;
    private cargo cargo;

    public usuario() {
    }

    public usuario(int IDUSUARIO, String nombreUsuario, String clave, boolean estado, cargo cargo) {
        this.IDUSUARIO = IDUSUARIO;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.estado = estado;
        this.cargo = cargo;
    }

    public int getIDUSUARIO() {
        return IDUSUARIO;
    }

    public void setIDUSUARIO(int IDUSUARIO) {
        this.IDUSUARIO = IDUSUARIO;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public cargo getCargo() {
        return cargo;
    }

    public void setCargo(cargo cargo) {
        this.cargo = cargo;
    }

    
    
}
