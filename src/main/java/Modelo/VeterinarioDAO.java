/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.conexion;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {
     conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
   public List listar() {
        String sql = "select * from Veterinario";
        List<Veterinario> lista = new ArrayList<>();
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Veterinario ve = new Veterinario();
                ve.setId_Veterinario(rs.getInt(1));
                ve.setDni(rs.getString(2));
                ve.setNom(rs.getString(3));
                ve.setTel(rs.getString(4));
                ve.setEstado(rs.getString(5));
                lista.add(ve);
            }
        } catch (Exception e) {
        }
        return lista;
    }
   
    public int agregar(Veterinario ve) {
        String sql = "INSERT INTO veterinario (Dni,Nom, Tel, Estado) VALUES (?,?, ?, ?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, ve.getDni());
            ps.setString(2, ve.getNom());
            ps.setString(3, ve.getTel());
            ps.setString(4, ve.getEstado());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
    public Veterinario listarId(int id) {
        Veterinario vet = new Veterinario();
        String sql = "select * from veterinario where Id_Veterinario=" + id;
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                vet.setDni(rs.getString(2));
                vet.setNom(rs.getString(3));
                vet.setTel(rs.getString(4));
                vet.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return vet;
    }
       public int actualizar(Veterinario ve) {
        int resultado = 0;
        String sql = "update veterinario set  Dni=?, Nom=?, Tel=?,Estado=? where Id_Veterinario=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, ve.getDni());
            ps.setString(2, ve.getNom());
            ps.setString(3, ve.getTel());
            ps.setString(4, ve.getEstado());
            ps.setInt(5, ve.getId_Veterinario());
            resultado=ps.executeUpdate();

        } catch (Exception e) {
        }
        return resultado;
    }

    public void delete(int id) {
        String sql = "delete from veterinario where Id_Veterinario=" + id;
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
