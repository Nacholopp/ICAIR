package message;


import java.io.Serial;
import java.io.Serializable;


import domain.User;



public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String context; // El contexto de la operación (por ejemplo, "/registerUser")
    private User user; // Información del usuario

    // Constructor
    public Message(String context, User user) {
            this.context = context;
            this.user = user;
    }

    public Message(String context) {
        this.context = context;
    }

    public Message() {
        context = new String();
    }

    // Getters y Setters
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
}

