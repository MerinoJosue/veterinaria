package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private Connection conexion = null;
    private String base = "happy";
    private String url = "jdbc:mysql://localhost/" + base;
    private String user = "root";
    private String password = "";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
            return conexion;
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error en la conexión: " + e.getMessage());
        }
    }

    public void closeConnection() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
