package Modelo;

import Modelo.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiciosDAO {

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Servicios buscar(String Nombre) {
        Servicios sv = new Servicios();
        String sql = "select * from Servicio where Nombre=" + Nombre;
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                sv.setId_Servicio(rs.getInt(1));
                sv.setNombre(rs.getString(2));
                sv.setPrecio(rs.getString(3));
                sv.setDescripcion(rs.getString(4));
            }
        } catch (Exception e) {
        }
        return sv;
    }

    public List listar() {
        String sql = "select * from Servicio";
        List<Servicios> lista = new ArrayList<>();
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Servicios sv = new Servicios();
                sv.setId_Servicio(rs.getInt(1));
                sv.setNombre(rs.getString(2));
                sv.setPrecio(rs.getString(3));
                sv.setDescripcion(rs.getString(4));
                lista.add(sv);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public int agregar(Servicios sv) {
        String sql = "INSERT INTO Servicio (Nombre, Precio, Descripcion) VALUES (?, ?, ?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, sv.getNombre());
            ps.setString(2, sv.getPrecio());
            ps.setString(3, sv.getDescripcion());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public Servicios listarId(int id) {
        Servicios ser = new Servicios();
        String sql = "select * from Servicio where Id_Servicio=" + id;
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ser.setNombre(rs.getString(2));
                ser.setPrecio(rs.getString(3));
                ser.setDescripcion(rs.getString(4));
            }
        } catch (Exception e) {
        }
        return ser;
    }

    public int actualizar(Servicios sr) {
        int resultado = 0;
        String sql = "update Servicio set  Nombre=?, Precio=?, Descripcion=? where Id_Servicio=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, sr.getNombre());
            ps.setString(2, sr.getPrecio());
            ps.setString(3, sr.getDescripcion());
            ps.setInt(4, sr.getId_Servicio());
            resultado = ps.executeUpdate();

        } catch (Exception e) {
        }
        return resultado;
    }

    public void delete(int id) {
        String sql = "delete from Servicio where Id_Servicio=" + id;
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
