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
import domain.Billete;
import java.util.List;
import message.Message;

public class Client {

    private String host;
    private int port;
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public Client() {
        this.host = PropertiesISW.getInstance().getProperty("host");
        this.port = Integer.parseInt(PropertiesISW.getInstance().getProperty("port"));
    }
    public boolean sendMessage_User(String context, User usuario) {

        Message mensajeEnvio = new Message();
        Message mensajeVuelta = new Message();
        mensajeEnvio.setContext(context);
        mensajeEnvio.setUser(usuario);

        this.sent(mensajeEnvio, mensajeVuelta);

        switch(mensajeVuelta.getContext()){
            case "/UsuarioRegistrado":
                return true;
            case "/UsuarioNoRegistrado":
                return false;

            case "/UsuarioCorrecto":
                return true;
            case "/UsuarioIncorrecto":
                return false;

            case "/DatosBancariosRegistrados":
                return true;
            case "/DatosBancariosNoRegistrados":
                return false;

            case "/PremiumCorrecto":
                return true;
            case "/PremiumNoCorrecto":
                return false;

            default:
                return false;
        }
    }
    public boolean sendMessage_Avion(String context, Avion avion) {

        Message mensajeEnvio = new Message();
        Message mensajeVuelta = new Message();
        mensajeEnvio.setContext(context);
        mensajeEnvio.setAvion(avion);

        this.sent(mensajeEnvio, mensajeVuelta);

        switch(mensajeVuelta.getContext()){
            case "/AvionEncontrado":
                return true;
            case "/AvionNoEncontrado":
                return false;

            case "/AsientoReservado":
                return true;
            case "/AsientoNoReservado":
                return false;


            default:
                return false;
        }
    }
    public boolean sendMessage_Billete(String context, Billete billete) {

        Message mensajeEnvio = new Message();
        Message mensajeVuelta = new Message();
        mensajeEnvio.setContext(context);
        mensajeEnvio.setBillete(billete);

        this.sent(mensajeEnvio, mensajeVuelta);

        switch(mensajeVuelta.getContext()){
            case "/BilleteComprado":
                return true;
            case "/BilleteNoComprado":
                return false;

            default:
                return false;
        }
    }
    public boolean sendMessage_AvionID(String context, Avion avion) {

        Message mensajeEnvio = new Message();
        Message mensajeVuelta = new Message();
        mensajeEnvio.setContext(context);
        mensajeEnvio.setAvion(avion);

        this.sent(mensajeEnvio, mensajeVuelta);

        switch(mensajeVuelta.getContext()){
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

    public Avion buscarVueloComprado(String context, Avion avion) {

        Avion vueloComprado=null;

        try{
            Message mensajeEnvio = new Message(context, avion);
            Message mensajeVuelta = new Message();
            this.sent(mensajeEnvio, mensajeVuelta);

            if(mensajeVuelta.getContext().equals("/VueloComprado")){
                vueloComprado=mensajeVuelta.getAvion();
                return vueloComprado;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Billete> buscarBilletes(String context, Billete billete) {
        List<Billete> listaBilletes = null;

        try {
            Message mensajeEnvio = new Message(context, billete);
            Message mensajeVuelta = new Message();

            this.sent(mensajeEnvio, mensajeVuelta);

            if (mensajeVuelta.getContext().equals("/ListaBilletes")) {
                listaBilletes = mensajeVuelta.getListaBilletes();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaBilletes;
    }

    public void sent(Message messageOut, Message messageIn) {

        try{
            Socket echosocket = null;
            OutputStream out = null;
            InputStream in = null;

            try {
                echosocket = new Socket(host,port);
                in = echosocket.getInputStream();
                out = echosocket.getOutputStream();

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
                objectOutputStream.writeObject(messageOut);

                ObjectInputStream objectInputStream = new ObjectInputStream(in);
                Message msg = (Message) objectInputStream.readObject();
                messageIn.setContext(msg.getContext());
                if (msg.getUser() != null) {
                    messageIn.setUser(msg.getUser());
                }
                if (msg.getAvion() != null) {
                    messageIn.setAvion(msg.getAvion());
                }
                if (msg.getBillete() != null) {
                    messageIn.setBillete(msg.getBillete());
                }
                if (msg.getListaAviones() != null && !msg.getListaAviones().isEmpty()) {
                    messageIn.setListaAviones(msg.getListaAviones());
                }
                if (msg.getListaAvionesID() != null && !msg.getListaAvionesID().isEmpty()) {
                    messageIn.setListaAvionesID(msg.getListaAvionesID());
                }
                if (msg.getListaBilletes() != null && !msg.getListaBilletes().isEmpty()) {
                    messageIn.setListaBilletes(msg.getListaBilletes());
                }
            }catch (UnknownHostException e) {
                //System.err.println("Unknown host: " + host);
                System.exit(1);

            }catch (IOException e) {
                //System.err.println("Unable to get streams from server");
                System.exit(1);
            }
            out.close();
            in.close();
            echosocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

















