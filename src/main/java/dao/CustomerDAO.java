package dao;

import java.sql.*;


import domain.User;

public class CustomerDAO {


    // De aqui hay que cambiar a un usuario que se registre y otro que añada datos bancarios y otro que sea premium

    //Register va a tener como entrada un User y la sentencia será user.getNombre()...
    public boolean register( User user ) {


        String InsertQuery =  "INSERT INTO public.usuarios ( nombre, apellido1, apellido2, fechanacimiento, nacionalidad, correoelectronico, contrasena, numerotarjeta, fechacaducidadtarjeta, cvc, premium, puntos) VALUES ( ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?);";

        try (Connection conn = ConnectionDAO.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(InsertQuery)) {


            pstmt.setString(1, user.getNombre());
            pstmt.setString(2, user.getApellido1());
            pstmt.setString(3, user.getApellido2());
            pstmt.setString(4, user.getFechanacimiento());
            pstmt.setString(5, user.getNacionalidad());
            pstmt.setString(6, user.getCorreoelectronico());
            pstmt.setString(7, user.getContrasena());

            //Cuando un usuario se registra de primeras no tiene estos parámetros hasta que no los configure o se configuren

            if(user.getNumerotarjeta() != null){
                pstmt.setString(8, user.getNumerotarjeta());
            }else{
                pstmt.setNull(8, Types.VARCHAR);
            }
            if(user.getFechacaducidadtarjeta() != null){
                pstmt.setString(9, user.getFechacaducidadtarjeta());
            }else{
                pstmt.setNull(9, Types.VARCHAR);
            }

            if(user.getCvc() != null){
                pstmt.setString(10, user.getCvc());
            }else{
                pstmt.setNull(10, Types.INTEGER);
            }

            if (user.getPremium() != null) {
                pstmt.setBoolean(11, user.getPremium());
            } else {
                pstmt.setBoolean(11, false);
            }
            if (user.getPuntos()!= null){
                pstmt.setInt(12, user.getPuntos());
            }else{
                // Si el usuario que se registra no es premium o no tiene puntos se pone un null
                pstmt.setObject(12,user.getPuntos(), Types.INTEGER);
            }


            //UNA VEZ FUNCIONE IMPLEMENTAR QUE NO REGISTRE UN USUARIO YA REGISTRADO POR EL CORREO

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){ //Es un while para que recorra todos los guardados
                String correoalmacenado = rs.getString("correoelectronico").trim();
                if(correoalmacenado.equals(user.getCorreoelectronico())) {
                    return false;
                }
            }

            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean login(String correo, String contraseña) {

        String consultaSQL = "SELECT contraseña FROM usuarios WHERE correo = ?" ;

        try (Connection conexion = ConnectionDAO.getInstance().getConnection();
             PreparedStatement stmt = conexion.prepareStatement(consultaSQL)) {


            stmt.setString(1, correo.trim());


            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String contraseñaAlmacenada = rs.getString("contraseña").trim();

                if (contraseñaAlmacenada.equals(contraseña.trim())) {
                    return true;
                } else {
                    System.out.println("La contraseña es incorrecta.");
                    return false;
                }
            } else {
                System.out.println("El correo no está registrado.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
