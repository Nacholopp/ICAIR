package dao;

import domain.Avion;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AvionDAO {
    public boolean find(Avion avion) {
        String checkQuery = "SELECT 1 FROM aviones WHERE origen = ? AND destino = ? AND fecha = ?";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(checkQuery)) {

            pstmt.setString(1, avion.getOrigen());
            pstmt.setString(2, avion.getDestino());
            pstmt.setString(3, avion.getFecha());

            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findID(Avion avion) {
        String checkQuery = "SELECT 1 FROM aviones WHERE id = ?";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(checkQuery)) {

            pstmt.setInt(1, avion.getId());

            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean reserveSit(Avion avion) {
        String updateQuery = "UPDATE aviones SET asientos_disp = asientos_disp - 1 WHERE id = ? AND asientos_disp > 0";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            pstmt.setInt(1, avion.getId());

            int rowsUpdated = pstmt.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Avion> buscarVuelos(Avion avion) {
        String query = "SELECT id, origen, destino, fecha, hora_salida, duracion, asientos_tot, asientos_disp FROM aviones WHERE origen = ? AND destino = ? AND fecha = ?";

        List<Avion> listaAviones = new ArrayList<>();

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, avion.getOrigen());
            pstmt.setString(2, avion.getDestino());
            pstmt.setString(3, avion.getFecha());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String origen = rs.getString("origen");
                String destino = rs.getString("destino");
                String fecha = rs.getString("fecha");
                String hora_salida = rs.getString("hora_salida");
                String duracion = rs.getString("duracion");
                int asientos_tot = rs.getInt("asientos_tot");
                int asientos_disp = rs.getInt("asientos_disp");

                Avion avionResult = new Avion(id, origen, destino, fecha, hora_salida, duracion, asientos_tot, asientos_disp);
                listaAviones.add(avionResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAviones;
    }

    public List<Avion> buscarVuelosID(Avion avion) {
        String query = "SELECT id, origen, destino, fecha, hora_salida, duracion, asientos_tot, asientos_disp FROM aviones WHERE id = ?";

        List<Avion> listaAvionesID = new ArrayList<>();

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, avion.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String origen = rs.getString("origen");
                String destino = rs.getString("destino");
                String fecha = rs.getString("fecha");
                String hora_salida = rs.getString("hora_salida");
                String duracion = rs.getString("duracion");
                int asientos_tot = rs.getInt("asientos_tot");
                int asientos_disp = rs.getInt("asientos_disp");

                Avion avionResult = new Avion(id, origen, destino, fecha, hora_salida, duracion, asientos_tot, asientos_disp);
                listaAvionesID.add(avionResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAvionesID;
    }
    public Avion buscarVueloComprado(Avion avion) {
        String query = "SELECT id, origen, destino, fecha, hora_salida, duracion, asientos_tot, asientos_disp FROM aviones WHERE id = ?";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, avion.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String origen = rs.getString("origen");
                String destino = rs.getString("destino");
                String fecha = rs.getString("fecha");
                String hora_salida = rs.getString("hora_salida");
                String duracion = rs.getString("duracion");
                int asientos_tot = rs.getInt("asientos_tot");
                int asientos_disp = rs.getInt("asientos_disp");

                Avion avionResult = new Avion(id, origen, destino, fecha, hora_salida, duracion, asientos_tot, asientos_disp);
                return avionResult;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
