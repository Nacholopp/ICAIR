package dao;

import domain.Billete;

import java.sql.*;

public class BilleteDAO {
    public boolean save(Billete billete) {

        String insertQuery = "INSERT INTO public.billetes (idusuario, idvuelo, numeroTarjeta, fechacaducidad, cvc) VALUES (?,?,?,?,?);";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            // El correo no existe, proceder con la inserción
            insertStmt.setInt(1, billete.getIdusuario());
            insertStmt.setInt(2, billete.getIdvuelo());
            insertStmt.setString(3, billete.getNumerotarjeta());
            insertStmt.setString(4, billete.getFechacaducidad());
            insertStmt.setInt(5, billete.getCvc());

            // Ejecutar la inserción
            int filasAfectadas = insertStmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Billete comprado exitosamente.");
                return true;
            } else {
                System.out.println("No se pudo comprar el billete.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
