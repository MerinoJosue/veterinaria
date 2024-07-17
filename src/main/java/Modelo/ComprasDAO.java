package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComprasDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public int IdCompra() {
        int idc = 0;
        String sql = "select max(idCompras) from Compras";
        try (Connection con = cn.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                idc = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idc;
    }

    public int guardarCompra(Compra co) {
        String sql = "insert into Compras(IDUSUARIO, idPago, FechaCompras, Monto, Estado) values (?, ?, ?, ?, ?)";
        try (Connection con = cn.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, co.getIDUSUARIO());
            ps.setInt(2, co.getIdPago());
            ps.setString(3, co.getFecha());
            ps.setDouble(4, co.getMonto());
            ps.setString(5, co.getEstado());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int guardarDetalleCompra(DetalleCompra detalleCompra) {
        String sql = "INSERT INTO detalle_compras (Id_Producto, idCompras, Cantidad, PrecioCompra) VALUES (?, ?, ?, ?)";
        try {
            con = cn.getConnection();

            ps = con.prepareStatement(sql);
            ps.setInt(1, detalleCompra.getId_Producto());
            ps.setInt(2, detalleCompra.getIdcompra());
            ps.setInt(3, detalleCompra.getCantidad());
            ps.setDouble(4, detalleCompra.getPrecioCompra());

            int resultado = ps.executeUpdate();
            return resultado;
        } catch (Exception e) {
        } 
        return 0;
    }

// Método para verificar si un producto existe por su ID
    private boolean existeProducto(int idProducto) throws Exception {
        String sql = "SELECT COUNT(*) FROM producto WHERE Id_Producto = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Devuelve true si el producto existe, false si no
            }
            return false;
        } finally {
        }
    }

    public List misCompras(int id) {
        List lista = new ArrayList();
        String sql = "select * from compras where IDUSUARIO=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Compra com = new Compra();
                com.setId(rs.getInt(1));
                com.setIDUSUARIO(rs.getInt(2));
                com.setIdPago(rs.getInt(3));
                com.setFecha(rs.getString(4));
                com.setMonto(rs.getDouble(5));
                com.setEstado(rs.getString(6));
                lista.add(com);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public List Detalle(int id) {
        List lista = new ArrayList();
        String sql = "SELECT * FROM detalle_compras WHERE idCompras = " + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleCompra dcom = new DetalleCompra();
                dcom.setId(rs.getInt(1));
                dcom.setProducto(new Productos());
                dcom.getProducto().setFoto(rs.getBinaryStream(2));
                dcom.getProducto().setNombres(rs.getString(3));
                dcom.setIdcompra(rs.getInt(4));
                dcom.setCantidad(rs.getInt(5));
                dcom.setPrecioCompra(rs.getDouble(6));
                lista.add(dcom);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public int Pagar(double monto) {
        String sql = "INSERT INTO pago (Monto) VALUES (?)";
        int resultado = 0;
        try (Connection con = cn.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, monto);
            resultado = ps.executeUpdate();

            // Verificar el número de filas afectadas
            if (resultado > 0) {
                System.out.println("Inserción exitosa. Filas afectadas: " + resultado);
            } else {
                System.out.println("No se insertó ninguna fila.");
            }

        } catch (Exception e) {
            System.out.println("Error al realizar el pago: " + e.getMessage());
        }
        return resultado;
    }

    public int IdPago() {
        int idPago = 0;
        String sql = "SELECT idPago FROM pago ORDER BY idPago DESC LIMIT 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                idPago = rs.getInt("idPago");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return idPago;
    }

}
