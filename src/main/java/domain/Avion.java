package domain;

import java.io.Serializable;

public class Avion implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String origen;
    private String destino;
    private String fecha;
    private String hora_salida; // Puede ser mejor usar Date en lugar de String
    private String duracion;
    private Integer asientos_tot;
    private Integer asientos_disp;

    public Avion(Integer id, String origen, String destino, String fecha, String hora_salida, String duracion, Integer asientos_tot, Integer asientos_disp) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora_salida = hora_salida;
        this.duracion = duracion;
        this.asientos_tot = asientos_tot;
        this.asientos_disp=asientos_disp;
    }
    public Avion(String origen, String destino, String fecha) {
        this.origen = origen;
        this.destino = destino;
        this.fecha=fecha;
    }
    public Avion(Integer id){
        this.id=id;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrigen() {
        return origen;
    }
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHora_salida() {
        return hora_salida;
    }
    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }
    public String getDuracion() {
        return duracion;
    }
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    public Integer getAsientos_tot() {
        return asientos_tot;
    }
    public void setAsientos_tot(Integer asientos_tot) {
        this.asientos_tot = asientos_tot;
    }
    public Integer getAsientos_disp() {
        return asientos_disp;
    }
    public void setAsientos_disp(Integer asientos_disp) {
        this.asientos_disp = asientos_disp;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "id=" + id +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora_salida='" + hora_salida + '\'' +
                ", duracion='" + duracion + '\'' +
                ", asientos_tot=" + asientos_tot +
                ", asientos_disp=" + asientos_disp +
                '}';
    }
}
