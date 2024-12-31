package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Avion;
import domain.User;

public class CustomerDAO {


    // De aqui hay que cambiar a un usuario que se registre y otro que añada datos bancarios y otro que sea premium

    //Register va a tener como entrada un User y la sentencia será user.getNombre()...
    public boolean register( User user ) {
        String checkQuery = "SELECT 1 FROM usuarios WHERE correoelectronico = ?";

        String insertQuery =  "INSERT INTO public.usuarios (nombre, apellido1, apellido2, fechanacimiento, nacionalidad, correoelectronico, contrasena, numerotarjeta, fechacaducidadtarjeta, cvc, premium, puntos) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            checkStmt.setString(1, user.getCorreoelectronico());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return false;
            }

            insertStmt.setString(1, user.getNombre());
            insertStmt.setString(2, user.getApellido1());
            insertStmt.setString(3, user.getApellido2());
            insertStmt.setString(4, user.getFechanacimiento());
            insertStmt.setString(5, user.getNacionalidad());
            insertStmt.setString(6, user.getCorreoelectronico());
            insertStmt.setString(7, user.getContrasena());
            insertStmt.setNull(8, Types.VARCHAR);
            insertStmt.setNull(9, Types.VARCHAR);
            insertStmt.setNull(10, Types.INTEGER);
            insertStmt.setBoolean(11, false);
            insertStmt.setNull(12, Types.INTEGER);

            int filasAfectadas = insertStmt.executeUpdate();

            if (filasAfectadas > 0) {
                //System.out.println("Usuario registrado exitosamente.");
                return true;
            }
            else {
                //System.out.println("No se pudo registrar el usuario.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean logear(User user) {
        String checkQuery = "SELECT 1 FROM usuarios WHERE correoelectronico = ? AND contrasena = ?";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(checkQuery)) {

            pstmt.setString(1, user.getCorreoelectronico());
            pstmt.setString(2, user.getContrasena());

            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User buscarUsuarioLogeado(User usuario) {
        String query = "SELECT idusuario, nombre, apellido1, apellido2, fechanacimiento, nacionalidad, correoelectronico, numerotarjeta, fechacaducidadtarjeta, cvc, premium, puntos, contrasena FROM usuarios WHERE correoelectronico = ? AND contrasena =?";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, usuario.getCorreoelectronico());
            pstmt.setString(2, usuario.getContrasena());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int idusuario = rs.getInt("idusuario");
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                String fechanacimiento = rs.getString("fechanacimiento");
                String nacionalidad = rs.getString("nacionalidad");
                String correoelectronico = rs.getString("correoelectronico");
                String numerotarjeta = rs.getString("numerotarjeta");
                String fechacaducidadtarjeta = rs.getString("fechacaducidadtarjeta");
                int cvc = rs.getInt("cvc");
                Boolean premium = rs.getBoolean("premium");
                int puntos = rs.getInt("puntos");
                String contrasena = rs.getString("contrasena");

                User usuarioResult = new User(idusuario, nombre, apellido1, apellido2, fechanacimiento, nacionalidad, correoelectronico, numerotarjeta,fechacaducidadtarjeta, cvc, premium, puntos, contrasena);
                return usuarioResult;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateDatosBancarios(User user) {
        String query = "UPDATE public.usuarios SET numerotarjeta = ?, fechacaducidadtarjeta = ?, cvc = ? WHERE correoelectronico = ? AND contrasena = ?";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {


            pstmt.setString(1, user.getNumerotarjeta());
            pstmt.setString(2, user.getFechacaducidadtarjeta());
            pstmt.setInt(3, user.getCvc());
            pstmt.setString(4, user.getCorreoelectronico());
            pstmt.setString(5, user.getContrasena());

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setUserPremium(User user) {
        String query = "UPDATE public.usuarios SET premium = ? WHERE correoelectronico = ? AND contrasena = ?";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {


            pstmt.setBoolean(1, user.getPremium());
            pstmt.setString(2, user.getCorreoelectronico());
            pstmt.setString(3, user.getContrasena());
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(User user) {
        String deleteQuery = "DELETE FROM usuarios WHERE correoelectronico = ? AND contrasena = ?";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {

            deleteStmt.setString(1, user.getCorreoelectronico());
            deleteStmt.setString(2, user.getContrasena());

            // Ejecutamos la sentencia DELETE
            int rowsAffected = deleteStmt.executeUpdate();

            // Devolvemos true si se eliminó al menos una fila
            return rowsAffected > 0;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
