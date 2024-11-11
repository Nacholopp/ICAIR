package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import configuration.PropertiesISW;
import domain.User;
import domain.Avion;
import java.util.List;
import message.Message;


public class Client {

    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // Esto es para conectarse al mismo puerto que tiene abierto el servidor y que se sepa desde que IP viene la petición
    public Client() {
        this.host = PropertiesISW.getInstance().getProperty("host");
        this.port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));
    }

    public boolean sendMessage_User(String context, User usuario) { // Esta función es para Registrar y Logear usuarios, debería de haber otra que devuelva la lista de los vuelos

        Message mensajeEnvio = new Message();
        Message mensajeVuelta = new Message();
        mensajeEnvio.setContext(context);
        mensajeEnvio.setUser(usuario);

        this.sent(mensajeEnvio, mensajeVuelta);
        //Ahora en mensajeVuelta ya tenemos el contexto de vuelta del socket server que utilizamos

        switch(mensajeVuelta.getContext()){
            // Para registrar el contexto a enviar tiene que ser /regUser
            case "/UsuarioRegistrado":
                return true;
            case "/UsuarioNoRegistrado":
                return false;

            case "/UsuarioCorrecto":
                return true;
            case "/UsuarioIncorrecto":
                return false;

            default:
                return false;
        }
    }

    public boolean sendMessage_Avion(String context, Avion avion) { // Esta función es para Registrar y Logear usuarios, debería de haber otra que devuelva la lista de los vuelos

        Message mensajeEnvio = new Message();
        Message mensajeVuelta = new Message();
        mensajeEnvio.setContext(context);
        mensajeEnvio.setAvion(avion);

        this.sent(mensajeEnvio, mensajeVuelta);
        //Ahora en mensajeVuelta ya tenemos el contexto de vuelta del socket server que utilizamos

        switch(mensajeVuelta.getContext()){
            // Para registrar el contexto a enviar tiene que ser /regUser
            case "/AvionEncontrado":
                return true;
            case "/AvionNoEncontrado":
                return false;

            default:
                return false;
        }
    }

    public boolean sendMessage_AvionID(String context, Avion avion) { // Esta función es para Registrar y Logear usuarios, debería de haber otra que devuelva la lista de los vuelos

        Message mensajeEnvio = new Message();
        Message mensajeVuelta = new Message();
        mensajeEnvio.setContext(context);
        mensajeEnvio.setAvion(avion);

        this.sent(mensajeEnvio, mensajeVuelta);
        //Ahora en mensajeVuelta ya tenemos el contexto de vuelta del socket server que utilizamos

        switch(mensajeVuelta.getContext()){
            // Para registrar el contexto a enviar tiene que ser /regUser
            case "/AvionEncontradoID":
                return true;
            case "/AvionNoEncontradoID":
                return false;

            default:
                return false;
        }
    }

    public List<Avion> buscarVuelos(String context, Avion avion) {
        List<Avion> listaAviones = null;

        try {
            Message mensajeEnvio = new Message(context, avion);
            Message mensajeVuelta = new Message();

            this.sent(mensajeEnvio, mensajeVuelta);

            if (mensajeVuelta.getContext().equals("/ListaAviones")) {
                listaAviones = mensajeVuelta.getListaAviones();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaAviones;
    }
    public List<Avion> buscarVuelosID(String context, Avion avion) {
        List<Avion> listaAvionesID = null;

        try {
            Message mensajeEnvio = new Message(context, avion);
            Message mensajeVuelta = new Message();

            this.sent(mensajeEnvio, mensajeVuelta);

            if (mensajeVuelta.getContext().equals("/ListaAvionesID")) {
                listaAvionesID = mensajeVuelta.getListaAvionesID();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaAvionesID;
    }

    public User buscarUsuarioIniciado(String context, User usuario) {
        User usuarioIniciado = null;

        try {
            Message mensajeEnvio = new Message(context, usuario);
            Message mensajeVuelta = new Message();

            this.sent(mensajeEnvio, mensajeVuelta);

            if (mensajeVuelta.getContext().equals("/UsuarioLogeado")) {
                usuarioIniciado = mensajeVuelta.getUser();
                return usuarioIniciado;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Utilizamos esta función para obtener el contexto de vuelta del socket server
    public void sent(Message messageOut, Message messageIn) {

        //messageOut es el que sale de aqui y messageIn es el recibido del socket server

        try{
            Socket echosocket = null;
            OutputStream out = null;
            InputStream in = null;

            try {
                //Creamos un nuevo socket para conectarnos al servidor de host y puerto especificado antes
                echosocket = new Socket(host,port);
                // Obtenemos lo que entra y sale del socket server con estas funciones
                in = echosocket.getInputStream();
                out = echosocket.getOutputStream();

                // Con esto enviamos el objeto al servidor
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
                // Envia el mensaje al servidor messageOut es el context y user que queremos enviar al servidor para que llegue a escribirse en la base de datos
                objectOutputStream.writeObject(messageOut);

                //Con esto leemos lo que nos devuelve el socket server
                ObjectInputStream objectInputStream = new ObjectInputStream(in);
                Message msg = (Message) objectInputStream.readObject();
                messageIn.setContext(msg.getContext());
                if (msg.getUser() != null) {
                    messageIn.setUser(msg.getUser());
                }
                if (msg.getAvion() != null) {
                    messageIn.setAvion(msg.getAvion());
                }
                if (msg.getListaAviones() != null && !msg.getListaAviones().isEmpty()) {
                    messageIn.setListaAviones(msg.getListaAviones());  // Asigna la lista de aviones
                }
                if (msg.getListaAvionesID() != null && !msg.getListaAvionesID().isEmpty()) {
                    messageIn.setListaAvionesID(msg.getListaAvionesID());  // Asigna la lista de aviones
                }
                // Entonces ahora nuestro mensaje de vuelta (messageIN (recibido)) tiene un contexto sobre el que trabajamos en sendMessage()
            }catch (UnknownHostException e) {
                System.err.println("Unknown host: " + host);
                System.exit(1);

            }catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }
            // Con esto cerramos la peticion al socket
            out.close();
            in.close();
            echosocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

















