package dao;

import domain.Avion;
import domain.Billete;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BilleteDAO {
    public boolean save(Billete billete) {

        String insertQuery = "INSERT INTO public.billetes (idusuario, idvuelo, numeroTarjeta, fechacaducidad, cvc, asiento) VALUES (?,?,?,?,?,?);";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            insertStmt.setInt(1, billete.getIdusuario());
            insertStmt.setInt(2, billete.getIdvuelo());
            insertStmt.setString(3, billete.getNumerotarjeta());
            insertStmt.setString(4, billete.getFechacaducidad());
            insertStmt.setInt(5, billete.getCvc());
            insertStmt.setInt(6, billete.getAsiento());

            int filasAfectadas = insertStmt.executeUpdate();

            if (filasAfectadas > 0) {
                //System.out.println("Billete comprado exitosamente.");
                return true;
            } else {
                //System.out.println("No se pudo comprar el billete.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Billete> buscarBilletes(Billete billete) {
        String query = "SELECT idvuelo FROM billetes WHERE idusuario = ?";

        List<Billete> listaBilletes = new ArrayList<>();

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, billete.getIdusuario());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int idvuelo = rs.getInt("idvuelo");


                Billete billeteresult = new Billete(null,idvuelo);
                listaBilletes.add(billeteresult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaBilletes;
    }
}
