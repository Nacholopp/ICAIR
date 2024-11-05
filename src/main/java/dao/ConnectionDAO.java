package dao;

import java.sql.*;

import configuration.PropertiesISW;

public class ConnectionDAO {
    private static ConnectionDAO connectionDAO;
    private Connection con;

    // Privado para evitar que se creen instancias fuera de la clase
    // Obtiene la url de conexion, usuario, password
    // Utiliza el divermanager para establecer la conexion con la base de datos

    private ConnectionDAO() {
        String url = PropertiesISW.getInstance().getProperty("ddbb.connection");
        String user = PropertiesISW.getInstance().getProperty("ddbb.user");
        String password = PropertiesISW.getInstance().getProperty("ddbb.password");
        try {
            con = DriverManager.getConnection(url, user, password);
        }catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

    }

    // Este metodo es para crear una sola instancia de ConnectionDAO y que no se pueda crear más de una

    public static ConnectionDAO getInstance() {
        if (connectionDAO == null) {
            connectionDAO=new ConnectionDAO();
        }
        return connectionDAO;
    }

    // Este metodo te devuelve la conexion de la base de datos para poder utilizarla desde la clase de customerDao

    public Connection getConnection() {
        return con;
    }

}