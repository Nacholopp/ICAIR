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
    private Integer cvc; // Información sensible
    private Boolean premium;
    private Integer puntos;

    //Este es el constructor del usuario solo registrado
    public User(Integer id, String nombre, String apellido1, String apellido2, String fechanacimiento, String nacionalidad, String correoelectronico,String numerotarjeta,String fechacaducidadtarjeta, Integer cvc, Boolean premium, Integer puntos, String contrasena) {
        this.id=id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechanacimiento = fechanacimiento;
        this.nacionalidad = nacionalidad;
        this.correoelectronico = correoelectronico;
        this.numerotarjeta=numerotarjeta;
        this.fechacaducidadtarjeta=fechacaducidadtarjeta;
        this.cvc=cvc;
        this.premium=premium;
        this.puntos=puntos;
        this.contrasena = contrasena;
    }
    public User(String nombre, String apellido1, String apellido2, String fechanacimiento, String nacionalidad, String correoelectronico, String contrasena) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechanacimiento = fechanacimiento;
        this.nacionalidad = nacionalidad;
        this.correoelectronico = correoelectronico;
        this.contrasena = contrasena;
    }

    public User(String correoelectronico, String contrasena) {
        this.correoelectronico = correoelectronico;
        this.contrasena = contrasena;
    }

    public User(String correoelectronico, String contrasena,String numerotarjeta,String fechacaducidadtarjeta,Integer cvc) {
        this.correoelectronico = correoelectronico;
        this.contrasena = contrasena;
        this.numerotarjeta=numerotarjeta;
        this.fechacaducidadtarjeta=fechacaducidadtarjeta;
        this.cvc=cvc;
    }

    public User(String correoelectronico, String contrasena, Boolean premium) {
        this.correoelectronico = correoelectronico;
        this.contrasena = contrasena;
        this.premium=premium;
    }

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
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

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", fechanacimiento='" + fechanacimiento + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", correoelectronico='" + correoelectronico + '\'' +
                ", numerotarjeta='" + numerotarjeta + '\'' +
                ", fechacaducidadtarjeta='" + fechacaducidadtarjeta + '\'' +
                ", cvc=" + cvc +
                ", premium=" + premium +
                ", puntos=" + puntos +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }

}