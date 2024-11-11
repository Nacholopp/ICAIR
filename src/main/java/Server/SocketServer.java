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
import controler.AvionControler;
import message.Message;
import java.util.List;
import domain.Avion;
import domain.User;



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
            AvionControler avionControler;
            switch (mensajeIn.getContext()) {
                case "/regUser":
                    userControler = new UserControler();
                    // Registra al usuario enviado por cliente
                    boolean registroExitoso = userControler.regUser(mensajeIn.getUser());
                    if (registroExitoso) {
                        mensajeOut.setContext("/UsuarioRegistrado");
                    }else{
                        mensajeOut.setContext("/UsuarioNoRegistrado");
                    }
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/logUser":
                    userControler = new UserControler();
                    boolean loginExistoso=userControler.logUser(mensajeIn.getUser());
                    if (loginExistoso) {
                        mensajeOut.setContext("/UsuarioCorrecto");
                    }else{
                        mensajeOut.setContext("/UsuarioIncorrecto");
                    }
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/findAvion":
                    avionControler = new AvionControler();
                    boolean busquedaExistoso=avionControler.findAvion(mensajeIn.getAvion());
                    if (busquedaExistoso) {
                        mensajeOut.setContext("/AvionEncontrado");
                    }else{
                        mensajeOut.setContext("/AvionNoEncontrado");
                    }
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/findAvionID":
                    avionControler = new AvionControler();
                    boolean busquedaExistosoID=avionControler.findAvionID(mensajeIn.getAvion());
                    if (busquedaExistosoID) {
                        mensajeOut.setContext("/AvionEncontradoID");
                    }else{
                        mensajeOut.setContext("/AvionNoEncontradoID");
                    }
                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/ListaVuelos":
                    avionControler = new AvionControler();
                    List<Avion> listaAviones = avionControler.buscarVuelos(mensajeIn.getAvion());

                    if (!listaAviones.isEmpty()) {
                        mensajeOut.setContext("/ListaAviones");
                        mensajeOut.setListaAviones(listaAviones);
                    }
                    else {
                        mensajeOut.setContext("/NoHayAviones");
                    }

                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/ListaVuelosID":
                    avionControler = new AvionControler();
                    List<Avion> listaAvionesID = avionControler.buscarVuelosID(mensajeIn.getAvion());
                    if (!listaAvionesID.isEmpty()) {
                        mensajeOut.setContext("/ListaAvionesID");
                        mensajeOut.setListaAvionesID(listaAvionesID);
                    }
                    else {
                        mensajeOut.setContext("/NoHayAvionesID");
                    }

                    objectOutputStream.writeObject(mensajeOut);
                    break;
                case "/getLoggedUser":
                    userControler = new UserControler();
                   User usuarioLogeado = userControler.buscarUsuarioLogeado(mensajeIn.getUser());
                    if (usuarioLogeado != null) {
                        mensajeOut.setContext("/UsuarioLogeado");
                        mensajeOut.setUser(usuarioLogeado);
                    }
                    else {
                        mensajeOut.setContext("/UsuarioNoLogeado");
                    }

                    objectOutputStream.writeObject(mensajeOut);
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

