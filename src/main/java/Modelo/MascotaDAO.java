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

    public List Listar(int userId) {
        String sql = "SELECT * FROM mascota WHERE IDUSUARIO=?";
        List<Mascota> lista = new ArrayList<>();
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);  // Asegúrate de establecer el ID del usuario en la consulta
            rs = ps.executeQuery();
            while (rs.next()) {
                Mascota mascota = new Mascota();
                mascota.setIDMASCOTA(rs.getInt(1));
                mascota.setNombre(rs.getString(3));
                mascota.setRaza(rs.getString(4));
                mascota.setSexo(rs.getString(5));
                mascota.setEspecie(rs.getString(6));
                lista.add(mascota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
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
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public int actualizar(Mascota m) {
        int resultado = 0;
        String sql = "UPDATE mascota SET nombre = ?, raza = ?, sexo = ?, especie = ? WHERE IDMASCOTA = ?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getRaza());
            ps.setString(3, m.getSexo());
            ps.setString(4, m.getEspecie());
            resultado = ps.executeUpdate();
        } catch (Exception e){}
        return resultado;
    }


    public Mascota buscarPorId(int usermascota) {
        Mascota mascota = null;
        String sql = "SELECT * FROM mascota WHERE IDMASCOTA= ?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, usermascota);
            rs = ps.executeQuery();
            if (rs.next()) {
                mascota = new Mascota();
                mascota.setIDUSUARIO(rs.getString(2));
                mascota.setNombre(rs.getString(3));
                mascota.setRaza(rs.getString(4));
                mascota.setSexo(rs.getString(5));
                mascota.setEspecie(rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mascota;
    }

    public int eliminar(int idMascota) {
        String sql = "DELETE FROM mascota WHERE IDMASCOTA = "+idMascota;
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
            System.out.println("Registro eliminado: " + r);
        } catch (Exception e) {
            System.out.println("Error al eliminar mascota: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }
    

}
