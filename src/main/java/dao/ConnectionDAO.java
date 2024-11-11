package dao;

import java.sql.*;

import configuration.PropertiesISW;

public class ConnectionDAO {
    private static ConnectionDAO connectionDAO;

    // Constructor privado para Singleton
    private ConnectionDAO() {}

    // Método para obtener la instancia única
    public static ConnectionDAO getInstance() {
        if (connectionDAO == null) {
            connectionDAO = new ConnectionDAO();
        }
        return connectionDAO;
    }

    // Método para obtener una nueva conexión cada vez
    public Connection getConnection() {
        String url = PropertiesISW.getInstance().getProperty("ddbb.connection");
        String user = PropertiesISW.getInstance().getProperty("ddbb.user");
        String password = PropertiesISW.getInstance().getProperty("ddbb.password");

        try {
            // Devuelve una nueva conexión en cada llamada
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.err.println("Error al obtener la conexión: " + ex.getMessage());
            return null;
        }
    }

}