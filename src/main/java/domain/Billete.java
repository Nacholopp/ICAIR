package domain;

import java.io.Serializable;

public class Billete implements Serializable {
    private static final long serialVersionUID=1L;
    private Integer idusuario;
    private Integer idvuelo;
    private String numerotarjeta;
    private String fechacaducidad;
    private Integer cvc;

    public Billete(Integer idusuario, Integer idvuelo, String numerotarjeta, String fechacaducidad, Integer cvc){
        this.idusuario=idusuario;
        this.idvuelo=idvuelo;
        this.numerotarjeta=numerotarjeta;
        this.fechacaducidad=fechacaducidad;
        this.cvc=cvc;
    }

    public Integer getIdusuario() {
        return idusuario;
    }
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }
    public Integer getIdvuelo() {
        return idvuelo;
    }
    public void setIdvuelo(Integer idvuelo) {
        this.idvuelo = idvuelo;
    }
    public String getNumerotarjeta() {
        return numerotarjeta;
    }
    public void setNumerotarjeta(String numerotarjeta) {
        this.numerotarjeta = numerotarjeta;
    }
    public String getFechacaducidad() {
        return fechacaducidad;
    }
    public void setFechacaducidad(String fechacaducidad) {
        this.fechacaducidad = fechacaducidad;
    }
    public Integer getCvc() {
        return cvc;
    }
    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    @Override
    public String toString() {
        return "Billete{" +
                "idusuario=" + idusuario +
                ", idvuelo=" + idvuelo +
                ", numerotarjeta='" + numerotarjeta + '\'' +
                ", fechacaducidad='" + fechacaducidad + '\'' +
                ", cvc=" + cvc +
                '}';
    }
}
