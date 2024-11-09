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

    public boolean sendMessage(String context, User usuario) { // Esta función es para Registrar y Logear usuarios, debería de haber otra que devuelva la lista de los vuelos

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

            case "/UsuarioLogeado":
                return true;

            default:
                return false;

        }

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
                messageIn.setUser(msg.getUser());

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

















