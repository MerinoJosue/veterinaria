package Modelo;

import Modelo.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

   public List<Mascota> Listar() {
        String sql = "SELECT * FROM mascota";
        List<Mascota> lista = new ArrayList<>();
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mascota mascota = new Mascota();
                
                mascota.setNombre(rs.getString("nombre"));
                mascota.setRaza(rs.getString("raza"));
                mascota.setSexo(rs.getString("sexo"));
                mascota.setEspecie(rs.getString("especie"));
                lista.add(mascota);
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
   
   
    public int agregar(Mascota m, int userId) {
        String sql = "INSERT INTO mascota (IDUSUARIO, nombre, raza, sexo, especie) VALUES (?, ?, ?, ?, ?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getRaza());
            ps.setString(4, m.getSexo());
            ps.setString(5, m.getEspecie());
            r = ps.executeUpdate();
            System.out.println("Registro agregado: " + r);
        } catch (Exception e) {
            System.out.println("Error al agregar mascota: " + e.getMessage());
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
     public int actualizar(Mascota m,int userId) {
        String sql = "UPDATE mascota SET nombre = ?, raza = ?, sexo = ?, especie = ? WHERE IDUSUARIO = ?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getRaza());
            ps.setString(3, m.getSexo());
            ps.setString(4, m.getEspecie());
            ps.setInt(5, userId);
            
            r = ps.executeUpdate();
            System.out.println("Registro actualizado: " + r);
        } catch (Exception e) {
            System.out.println("Error al actualizar mascota: " + e.getMessage());
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
      public Mascota buscarPorId(int userId) {
        Mascota mascota = null;
        String sql = "SELECT * FROM mascota WHERE IDUSUARIO = ?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                mascota = new Mascota();
                mascota.setIDUSUARIO(rs.getString("id"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setRaza(rs.getString("raza"));
                mascota.setSexo(rs.getString("sexo"));
                mascota.setEspecie(rs.getString("especie"));
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
        return mascota;
    }
     public int eliminar(int userId) {
        String sql = "DELETE FROM mascota WHERE IDUSUARIO = ?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            r = ps.executeUpdate();
            System.out.println("Registro eliminado: " + r);
        } catch (Exception e) {
            System.out.println("Error al eliminar mascota: " + e.getMessage());
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
