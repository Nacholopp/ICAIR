package domain;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String fechanacimiento; // Puede ser mejor usar Date en lugar de String
    private String nacionalidad;
    private String correoelectronico;
    private String contrasena;
    private String numerotarjeta; // Información sensible
    private String fechacaducidadtarjeta; // Puede ser mejor usar Date
    private String cvc; // Información sensible
    private Boolean premium;
    private Integer puntos;

    // Este constructor no le necesitamos por nunca vamos a necesitar todos estos atributos desde el momento inicial
    // Constructor de un usuario registrado completo, no lo usamos como tal pero tendremos que tener un usuario al que puedas hacer un setPuntos por ejemplo
//    public User(String nombre, String apellido1, String apellido2, String fechanacimiento, String nacionalidad, String correoelectronico, String contrasena, String numerotarjeta, String fechacaducidadtarjeta, String cvc, Boolean premium, Integer puntos) {
//
//        this.nombre = nombre;
//        this.apellido1 = apellido1;
//        this.apellido2 = apellido2;
//        this.fechanacimiento = fechanacimiento;
//        this.nacionalidad = nacionalidad;
//        this.correoelectronico = correoelectronico;
//        this.contrasena = contrasena;
//        this.numerotarjeta = numerotarjeta;
//        this.fechacaducidadtarjeta = fechacaducidadtarjeta;
//        this.cvc = cvc;
//        this.premium = premium;
//        this.puntos = puntos;
//
//    }

    //Este es el constructor del usuario solo registrado
    public User(String nombre, String apellido1, String apellido2, String fechanacimiento, String nacionalidad, String correoelectronico, String contrasena) {

        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechanacimiento = fechanacimiento;
        this.nacionalidad = nacionalidad;
        this.correoelectronico = correoelectronico;
        this.contrasena = contrasena;


    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contraseña) {
        this.contrasena = contraseña;
    }

    public String getNumerotarjeta() {
        return numerotarjeta;
    }

    public void setNumerotarjeta(String numerotarjeta) {
        this.numerotarjeta = numerotarjeta;
    }

    public String getFechacaducidadtarjeta() {
        return fechacaducidadtarjeta;
    }

    public void setFechacaducidadtarjeta(String fechacaducidadtarjeta) {
        this.fechacaducidadtarjeta = fechacaducidadtarjeta;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
}