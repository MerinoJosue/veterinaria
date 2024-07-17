package Modelo;

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

public class ProductosDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    // Method to search products by name
    public List<Productos> buscar(String nombre) {
        List<Productos> list = new ArrayList<>();
        String sql = "select * from productos where Nombres like ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos p = new Productos();
                p.setId_Producto(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    // Method to get a product by its ID
    public Productos listarId(int id) {
        Productos p = new Productos();
        String sql = "select * from productos where Id_Producto=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setId_Producto(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setDescripcion(rs.getString(3));
                p.setPrecio(rs.getDouble(4));
                p.setStock(rs.getInt(5));
                p.setFoto(rs.getBinaryStream(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return p;
    }

    // Method to list all products
    public List listar() {
        String sql = "select * from productos";
        List<Productos> lista = new ArrayList<>();
      
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos p = new Productos();
                p.setId_Producto(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setDescripcion(rs.getString(3));
                p.setPrecio(rs.getDouble(4));
                p.setStock(rs.getInt(5));
                p.setFoto(rs.getBinaryStream(6));

                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    // Method to list an image
    public void listarImg(int id, HttpServletResponse response) {
        String sql = "select * from productos where Id_Producto="+id;
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

    public int agregar(Productos p) {
        Conexion cn = new Conexion();
        String sql = "INSERT INTO productos(Nombre, Descripcion, Precio, Stock, Foto) VALUES (?, ?, ?, ?, ?)";
        int result = 0;
        try (Connection con = cn.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombres());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setBlob(5, p.getFoto());

            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    // Method to update a product
    public int actualizar(Productos p) {
        int resultado = 0;
        String sql = "update productos set Nombres=?,Descripcion=?,Precio=?,Stock=?,Foto=? where Id_Producto=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setBlob(5, p.getFoto());
            ps.setInt(6, p.getId_Producto());
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Method to delete a product
    public void delete(int id) {
        String sql = "delete from productos where Id_Producto=?";
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

    // Method to close database resources
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
