package message;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import domain.User;
import domain.Avion;
import domain.Billete;

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String context;
    private User user;
    private Avion avion;
    private Billete billete;
    private List<Avion> listaAviones;
    private List<Avion> listaAvionesID;
    private List<Billete> listaBilletes;

    // Constructor
    public Message(String context, User user) {
            this.context = context;
            this.user = user;
    }
    public Message(String context, Avion avion){
        this.context=context;
        this.avion=avion;
    }
    public Message(String context, Billete billete){
        this.context=context;
        this.billete=billete;
    }
    public List<Avion> getListaAviones() {
        return listaAviones;
    }
    public void setListaAviones(List<Avion> listaAviones) {
        this.listaAviones = listaAviones;
    }
    public List<Avion> getListaAvionesID() {
        return listaAvionesID;
    }
    public void setListaAvionesID(List<Avion> listaAvionesID) {
        this.listaAvionesID = listaAvionesID;
    }
    public Message(String context) {
        this.context = context;
    }
    public Message() {
        context = new String();
    }
    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Avion getAvion(){
        return avion;
    }
    public void setAvion(Avion avion){
        this.avion=avion;
    }
    public Billete getBillete(){
        return billete;
    }
    public void setBillete(Billete billete){
        this.billete=billete;
    }
    public List<Billete> getListaBilletes() {
        return listaBilletes;
    }
    public void setListaBilletes(List<Billete> listaBilletes) {
        this.listaBilletes = listaBilletes;
    }

}

