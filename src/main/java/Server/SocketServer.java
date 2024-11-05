package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import configuration.PropertiesISW;
import controler.UserControler;
import domain.User;
import message.Message;


public class SocketServer extends Thread {

    //Escucha el puerto declarado en properties
    public static int port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));

    protected Socket socket;

    private SocketServer(Socket socket) {
        this.socket = socket;
        //Configure connections
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
        start();
    }

    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            // Creamos lo que entra y lo que sale
            in = socket.getInputStream();
            out = socket.getOutputStream();
            //Lee el mensaje que le entra de cliente
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            Message mensajeIn = (Message) objectInputStream.readObject();

            //Creamos el objeto que devolvemos
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            Message mensajeOut = new Message();

            // Creamos el UserControler para utilizar sus funciones
            UserControler userControler;

            switch (mensajeIn.getContext()) {
                case "/regUser":
                    userControler = new UserControler();
                    // Registra al usuario enviado por cliente
                    userControler.regUser(mensajeIn.getUser());
                    if (userControler.regUser(mensajeIn.getUser())) {
                        mensajeOut.setContext("/UsuarioRegistrado");
                    }
                    objectOutputStream.writeObject(mensajeOut);
                    break;

                default:
                    System.out.println("\nParámetro no encontrado");
                    break;
            }
            try {
                Thread.sleep(1000); // Pausa de 1 segundo entre iteraciones
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            System.out.println("Unable to get streams from client");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("SocketServer Example - Listening on port " + port);
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            while (true) {
                // Espera y acepta conexiones de clientes
                Socket clientSocket = server.accept();
                new SocketServer(clientSocket); // Crea un nuevo hilo para manejar la conexión
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
            ex.printStackTrace();
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}

