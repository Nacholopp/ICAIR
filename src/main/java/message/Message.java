package message;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import domain.User;
import domain.Avion;

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String context; // El contexto de la operación (por ejemplo, "/registerUser")
    private User user; // Información del usuario
    private Avion avion;
    private List<Avion> listaAviones;
    private List<Avion> listaAvionesID;

    // Constructor
    public Message(String context, User user) {
            this.context = context;
            this.user = user;
    }
    public Message(String context, Avion avion){
        this.context=context;
        this.avion=avion;
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

}

