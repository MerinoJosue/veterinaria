/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.InputStream;

/**
 *
 * @author USER
 */
public class Mascota {

    int IDMASCOTA;
    int IDUSUARIO;
    String Nombre;
    String Raza;
    String Especie;
    String Sexo;
    InputStream foto;

    public Mascota() {
    }

    public Mascota(int IDMASCOTA, int IDUSUARIO, String Nombre, String Raza, String Especie, String Sexo, InputStream foto) {
        this.IDMASCOTA = IDMASCOTA;
        this.IDUSUARIO = IDUSUARIO;
        this.Nombre = Nombre;
        this.Raza = Raza;
        this.Especie = Especie;
        this.Sexo = Sexo;
        this.foto = foto;
    }

    public int getIDMASCOTA() {
        return IDMASCOTA;
    }

    public void setIDMASCOTA(int IDMASCOTA) {
        this.IDMASCOTA = IDMASCOTA;
    }

    public int getIDUSUARIO() {
        return IDUSUARIO;
    }

    public void setIDUSUARIO(int IDUSUARIO) {
        this.IDUSUARIO = IDUSUARIO;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String Raza) {
        this.Raza = Raza;
    }

    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String Especie) {
        this.Especie = Especie;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    
    
    

}
