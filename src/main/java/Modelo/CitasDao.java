/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.conexion;
import Modelo.conexion;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
        
/**
 *
 * @author USER
 */
public class CitasDao {
    
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public List<Citas> Listar() {
        String sql = "SELECT C.Id_Cita, M.nombre AS Nom_Masc, V.Nom AS Nom_Vet, S.nombre AS Nom_Serv, C.Fecha, C.Hora FROM citas C "
                + "INNER JOIN mascota M ON C.Id_Mascota = M.IDUSUARIO "
                + "INNER JOIN veterinario V ON C.Id_Veterinario = V.Id_Veterinario "
                + "INNER JOIN servicio S ON C.Id_Servicio = S.Id_Servicio "
                + "ORDER BY C.Id_Cita";
        List<Citas> lista = new ArrayList<>();
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Citas cita = new Citas();
                
                cita.setId_cita(rs.getInt("Id_Cita"));
                cita.setMascota(rs.getString("Nom_Masc"));
                cita.setVeterinario(rs.getString("Nom_Vet"));
                cita.setServicio(rs.getString("Nom_Serv"));
                cita.setFecha(rs.getString("Fecha"));
                cita.setHora(rs.getString("Hora"));
                lista.add(cita);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
    
    public int agregar(Citas c) {
        String sql = "INSERT INTO citas(Id_Mascota, Id_Veterinario, Id_Servicio, Fecha, Hora) VALUES (?,?,?,?,?)";        
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, parseInt(c.getMascota()));
            ps.setInt(2, parseInt(c.getVeterinario()));
            ps.setInt(3, parseInt(c.getServicio()));
            ps.setString(4, c.getFecha());
            ps.setString(5, c.getHora());
            r = ps.executeUpdate();
            System.out.println("Registro agregado: " + r);
        } catch (Exception e) {
            System.out.println("Error al agregar cita: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }
    
    public Citas buscarPorId(int Id_Cita) {
        Citas cita = null;
        String sql = "SELECT * FROM citas WHERE Id_Cita = ?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, Id_Cita);
            rs = ps.executeQuery();
            if (rs.next()) {
                cita = new Citas();
                cita.setId_cita(rs.getInt("Id_Cita"));
                cita.setMascota(rs.getString("Id_Mascota"));
                cita.setVeterinario(rs.getString("Id_Veterinario"));
                cita.setServicio(rs.getString("Id_Servicio"));
                cita.setFecha(rs.getString("Fecha"));
                cita.setHora(rs.getString("Hora"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cita;
    }
    
    public int actualizar(Citas c) {
        String sql = "UPDATE citas SET Id_Mascota=?, Id_Veterinario=?, Id_Servicio=?, Fecha=?, Hora=? WHERE Id_Cita = ?";        
        
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, parseInt(c.getMascota()));
            ps.setInt(2, parseInt(c.getVeterinario()));
            ps.setInt(3, parseInt(c.getServicio()));
            ps.setString(4, c.getFecha());
            ps.setString(5, c.getHora());
            ps.setInt(6, c.getId_cita());
            r = ps.executeUpdate();
            System.out.println("Registro actualizado: " + r);
        } catch (Exception e) {
            System.out.println("Error al actualizar cita: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }
    
    public int eliminar(int Id_Cita) {
        String sql = "DELETE FROM citas WHERE Id_Cita = ?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, Id_Cita);
            r = ps.executeUpdate();
            System.out.println("Registro eliminado: " + r);
        } catch (Exception e) {
            System.out.println("Error al eliminar cita: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }
    
}
