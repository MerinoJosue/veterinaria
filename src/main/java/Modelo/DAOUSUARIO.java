/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.cargo;
import Modelo.conexion;
import Modelo.usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Stephano Sanchez
 */
public class DAOUSUARIO extends conexion {

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public usuario identificar(usuario user) throws Exception {
        usuario usu = null;
        conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT U.IDUSUARIO, C.NOMBRECARGO FROM USUARIO U "
                + "INNER JOIN CARGO C ON U.IDCARGO = C.IDCARGO "
                + "WHERE U.ESTADO = 1 AND U.NOMBREUSUARIO = '" + user.getNombreUsuario() + "' "
                + "AND U.CLAVE = '" + user.getClave() + "'";
        con = new conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next() == true) {
                usu = new usuario();
                usu.setIDUSUARIO(rs.getInt("IDUSUARIO"));
                usu.setNombreUsuario(user.getNombreUsuario());
                usu.setCargo(new cargo());
                usu.getCargo().setNombreCargo(rs.getString("NOMBRECARGO"));
                usu.setEstado(true);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
            if (st != null && st.isClosed() == false) {
                st.close();

            }
            st = null;
            if (cn != null & cn.isClosed() == false) {
                cn.close();

            }
            cn = null;
        }
        return usu;
    }

    public int agregar(usuario us) {
        String sql = "INSERT INTO usuario (NOMBREUSUARIO, CLAVE, ESTADO, IDCARGO) VALUES (?, ?, ?, ?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getNombreUsuario() );
            ps.setString(2, us.getClave());
            ps.setBoolean(3, us.isEstado());
            ps.setBoolean(3, us.isEstado());
            ps.setInt(4, 2);
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

}
