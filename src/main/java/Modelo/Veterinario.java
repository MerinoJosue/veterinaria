/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author USER
 */
public class Veterinario {
    int Id_Veterinario;
    String Dni;
    String Nom;
    String Tel;
    String Estado;

    public Veterinario() {
    }
    
    

    public Veterinario(int Id_Veterinario, String Dni, String Nom, String Tel, String Estado) {
        this.Id_Veterinario = Id_Veterinario;
        this.Dni = Dni;
        this.Nom = Nom;
        this.Tel = Tel;
        this.Estado = Estado;
    }

    public int getId_Veterinario() {
        return Id_Veterinario;
    }

    public void setId_Veterinario(int Id_Veterinario) {
        this.Id_Veterinario = Id_Veterinario;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
  
    
}
