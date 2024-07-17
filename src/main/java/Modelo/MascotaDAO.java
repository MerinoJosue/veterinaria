package Modelo;

import Modelo.conexion;
import config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class MascotaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public List Listar() {
        String sql = "SELECT * FROM mascota";
        List<Mascota> lista = new ArrayList<>();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mascota m = new Mascota();
                m.setIDMASCOTA(rs.getInt(1));
                m.setIDUSUARIO(rs.getInt(2));
                m.setNombre(rs.getString(3));
                m.setRaza(rs.getString(4));
                m.setSexo(rs.getString(5));
                m.setEspecie(rs.getString(6));
                m.setFoto(rs.getBinaryStream(7));
                lista.add(m);
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

    // Method to list an image
    public void listarImg(int id, HttpServletResponse response) {
        String sql = "select * from mascota where IDMASCOTA=" + id;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setContentType("image/*");
        try {
            outputStream = response.getOutputStream();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                inputStream = rs.getBinaryStream("Foto");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    public int agregar(Mascota m) {
        Conexion cn = new Conexion();
        String sql = "INSERT INTO mascota (IDUSUARIO, nombre, raza, sexo, especie, Foto) VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (Connection con = cn.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, m.getIDUSUARIO());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getRaza());
            ps.setString(4, m.getSexo());
            ps.setString(5, m.getEspecie());
            ps.setBlob(6, m.getFoto());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Mascota listarId(int id) {
        Mascota m = new Mascota();
        String sql = "select * from mascota where IDUSUARIO=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                m.setIDUSUARIO(rs.getInt(2));
                m.setNombre(rs.getString(3));
                m.setRaza(rs.getString(4));
                m.setSexo(rs.getString(5));
                m.setEspecie(rs.getString(6));
                m.setFoto(rs.getBinaryStream(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return m;
    }

    public int actualizar(Mascota m, int userId) {
        String sql = "UPDATE mascota SET nombre = ?, raza = ?, sexo = ?, especie = ? WHERE IDUSUARIO = ?";
        try {
            con = cn.getConnection();
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

    public void eliminar(int id) {
        String sql = "DELETE FROM mascota WHERE IDMASCOTA = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
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

}
