package ui;


import client.Client;
import domain.User;
import domain.Avion;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.table.*;
import java.util.regex.*;

public class PanelPrincipal extends JPanel{
    public static final int tamOrig=16; //ESTO ME AYUDA A TENER UNA FORMA DE ORDENAR TODOS LOS ELEMENTOS DEL JUEGO
    public static final int escala=3;
    public static final int tam=tamOrig*escala;
    public static final int numColumnas=25;
    public static final int numFilas=15;
    public static final int ancho=numColumnas*tam;
    public static final int alto=numFilas*tam;
    private Dimension d1=new Dimension(ancho,alto); //ME DECLARO YA UNA DIMENSION PARA QUE TODOS LOS PANELES TENGAN EL MISMO TAMAÑO

    List<String>allCountries=List.of("","Alemania", "Austria", "Bélgica", "Bulgaria", "Chipre", "Croacia", "Dinamarca", "Eslovaquia", "Eslovenia", "España", "Estonia", "Finlandia", "Francia", "Grecia",
            "Hungría", "Irlanda", "Italia", "Letonia", "Lituania", "Luxemburgo", "Malta", "Países Bajos", "Polonia", "Portugal", "República Checa", "Rumania", "Suecia");
    List<String> allCities = List.of("","Madrid", "Barcelona", "Chicago", "Paris", "Tokyo", "Miami");
    JPanel pnlPantallaInicial;
    JPanel pnlMostrarBilletesInicio;
    JPanel pnlPantallaRegistro;
    JPanel pnlPantallaInicioSesion;
    JPanel pnlOpcionesSesionIniciada;
    JPanel pnlPantallaBusquedaBilletesSinPremium;
    JPanel pnlPantallaBusquedaBilletesConPremium; //FaltaPorImplementar
    JPanel pnlAñadirDatosBancarios;
    JPanel pnlMostrarBilletesSinPremium; //FaltaPorImplementar
    JPanel pnlMostrarBilletesConPremium; //FaltaPorImplementar

    //ELEMENTOS PANTALLA INICIAL
    //region
    private JButton btnRegistrarse_PantallaInicial, btnIniciarSesion_PantallaInicial, btnBuscarDatos_PantallaInicial, btnBuscarID_PantallaInicial;
    private JTextField txtOrigen_PantallaInicial, txtDestino_PantallaInicial, txtFechaIda_PantallaInicial, txtIDIda_PantallaInicial;
    private JLabel lblOrigen_PantallaInicial, lblDestino_PantallaInicial, lblFechaIda_PantallaInicial, lblIDIda_PantallaInicial, lblBusquedaDatos_PantallaInicial, lblBusquedaID_PantallaInicial;
    private JLabel lblNoHayVuelosDisponibles_PantallaBilletesInicio, lblNoHayVuelosDisponiblesID_PantallaBilletesInicio;
    private JComboBox<String> comboBoxCiudadesOrigen_PantallaInicial;
    private JComboBox<String> comboBoxCiudadesDestino_PantallaInicial;
    private DefaultComboBoxModel<String> comboBoxModelOrigen_PantallaInicial;
    private DefaultComboBoxModel<String> comboBoxModelDestino_PantallaInicial;

    //endregion
    //ELEMENTOS PANTALLA DE BILLETES DISPONIBLES PANTALLA INICIAL
    //region
    private JButton btnVolverPantallaInicio_PantallaBilletesInicio;
    private DefaultTableModel modelotablaVuelos_PantallaBilletesInicio;
    private JTable  tablaVuelos_PantallaBilletesInicio;
    private JScrollPane scrollPaneTabla_PantallaBilletesInicio;
    private JPanel pnlSecundario_PantallaBilletesInicio;
    //endregion
    //ELEMENTOS PANTALLA REGISTRO
    //region
    private JButton btnRegistrarse_PantallaRegistro, btnVolverPantallaInicio_PantallaRegistro;
    private JTextField txtNombre_PantallaRegistro, txtApellido1_PantallaRegistro, txtApellido2_PantallaRegistro, txtFechaNacimiento_PantallaRegistro, txtCorreoElectronico_PantallaRegistro, txtContraseña1_PantallaRegistro, txtContraseña2_PantallaRegistro;
    private JLabel lblNombre_PantallaRegistro, lblApellido1_PantallaRegistro, lblApellido2_PantallaRegistro, lblFechaNacimiento_PantallaRegistro, lblNacionalidad_PantallaRegistro, lblCorreoElectronico_PantallaRegistro, lblContraseña1_PantallaRegistro, lblContraseña2_PantallaRegistro;
    private JLabel lblContraseñasDistintas,lblUsuarioExistente, lblUsuarioRegistradoConExito;
    private JComboBox<String> comboBoxNacionalidades_PantallaRegistro;
    private DefaultComboBoxModel<String> comboBoxModel_PantallaRegistro;
    private Date fechaNacimiento_PantallaRegistro;
    //endregion
    //ELEMENTOS PANTALLA DE INICIO DE SESIÓN
    //region
    private JLabel lblCorreElectronico_PantallaInicioSesion, lblContraseña_PantallaInicioSesion;
    private JTextField txtCorreoElectronico_PantallaInicioSesion, txtContraseña_PantallaInicioSesion;
    private JButton btnIniciarSesion_PantallaInicioSesion, btnVolverPantallaInicio_PantallaInicioSesion;
    private JLabel lblLoginFallido_PantallaInicioSesion;
    //endregion
    //ELEMENTOS PANTALLA DE OPCIONES CUANDO SE HA INICIADO SESIÓN
    //region
    JButton btnCerrarSesion_PantallaOpciones, btnConsultarBilletesComprados_PantallaOpciones, btnSuscribirse_PantallaOpciones, btnCancelarSuscripcion_PantallaOpciones, btnAñadirDatosBancarios_PantallaOpciones, btnComprarBilletes_PantallaOpciones;
    JLabel lblNombreUsuarioIniciado_PantallaOpciones;
    //endregion
    //ELEMENTOS PANTALLA BUSCAR BILLETES SIN PREMIUM
    //region
    private JButton btnBuscarDatos_PantallaBBNoPremium, btnBuscarID_PantallaBBNoPremium, btnVolverPantallaOpciones_PantallaBBNoPremium;
    private JTextField txtOrigen_PantallaBBNoPremium, txtDestino_PantallaBBNoPremium, txtFechaIda_PantallaBBNoPremium, txtFechaVuelta_PantallaBBNoPremium, txtIDIda_PantallaBBNoPremium, txtIDVuelta_PantallaBBNoPremium;
    private JLabel lblOrigen_PantallaBBNoPremium, lblDestino_PantallaBBNoPremium, lblFechaIda_PantallaBBNoPremium, lblFechaVuelta_PantallaBBNoPremium, lblIDIda_PantallaBBNoPremium, lblIDVuelta_PantallaBBNoPremium, lblBusquedaDatos_PantallaBBNoPremium, lblBusquedaID_PantallaBBNoPremium,
            lblBuscarDatos_PantallaBBNoPremium, lblBuscarID_PantallaBBNoPremium, lblNombreUsuarioIniciado_PantallaBBNoPremium;
    //endregion
    //ELEMENTOS PANTALLA DE INGRESAR DATOS BANCARIOS
    //region
    private JButton btnVolverOpciones_PantallaDatosBancarios, btnAñadirDatosBancarios_PantallaDatosBancarios;
    private JLabel lblNombreUsuarioIniciado_PantallaDatosBancarios, lblNumeroTarjeta_PantallaDatosBancarios, lblFechaCaducidad_PantallaDatosBancarios, lblCVC_PantallaDatosBancarios;
    private JLabel lblDatosBancariosRegistradosConExito;
    private JTextField txtNumeroTarjeta_PantallaDatosBancarios, txtFechaCaducidad_PantallaDatosBancarios, txtCVC_PantallaDatosBancarios;
    private Date fechaCaducidadtarjeta_PantallaDatosBancarios;
    //endregion

    class LinePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawLine(0, 0, ancho, 0);
        }
    }
    public PanelPrincipal(){
        this.setPreferredSize(d1);

        //PANTALLA INICIAL
        //region
        pnlPantallaInicial=new JPanel();
        pnlPantallaInicial.setBackground(Color.white);
        pnlPantallaInicial.setPreferredSize(d1);
        pnlPantallaInicial.setLayout(null);

        ImageIcon imageIconInicio = null;
        try {
            imageIconInicio = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\Logo3.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblLogoInicio = new JLabel(imageIconInicio);
        lblLogoInicio.setBounds(tamOrig, tamOrig, 13*tamOrig, 4*tamOrig);
        pnlPantallaInicial.add(lblLogoInicio);

        ImageIcon imageIconCalendario = null;
        try {
            imageIconCalendario = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\calendar5.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblCalendar = new JLabel(imageIconCalendario);
        lblCalendar.setBounds(6*tamOrig, 16*tamOrig, 13*tamOrig, 5*tamOrig);
        pnlPantallaInicial.add(lblCalendar);

        ImageIcon imageIconLocation = null;
        try {
            imageIconLocation = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\location3.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblLocation = new JLabel(imageIconLocation);
        lblLocation.setBounds(6*tamOrig, 10*tamOrig, 13*tamOrig, 5*tamOrig);
        pnlPantallaInicial.add(lblLocation);

        ImageIcon imageIconID = null;
        try {
            imageIconID = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\ID3.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblID = new JLabel(imageIconID);
        lblID.setBounds(6*tamOrig, 32*tamOrig, 13*tamOrig, 5*tamOrig);
        pnlPantallaInicial.add(lblID);

        ImageIcon imageIconLogIn = null;
        try {
            imageIconLogIn = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\LogIn3.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblLogIn = new JLabel(imageIconLogIn);
        lblLogIn.setBounds(ancho-350, 10, 13*tamOrig, 5*tamOrig);
        pnlPantallaInicial.add(lblLogIn);

        ImageIcon imageIconRegistrar = null;
        try {
            imageIconRegistrar = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\Registro5.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblRegistro = new JLabel(imageIconRegistrar);
        lblRegistro.setBounds(ancho-750, 10, 13*tamOrig, 5*tamOrig);
        pnlPantallaInicial.add(lblRegistro);

        btnIniciarSesion_PantallaInicial =new JButton("Log in");
        btnIniciarSesion_PantallaInicial.setBackground(Color.WHITE);
        btnIniciarSesion_PantallaInicial.setFont(new Font("Arial",Font.BOLD,35));
        btnIniciarSesion_PantallaInicial.setBounds(ancho-200, tamOrig, 11*tamOrig, 4*tamOrig);
        btnIniciarSesion_PantallaInicial.addActionListener(new eventoBotonesPanelPrincipal());
        pnlPantallaInicial.add(btnIniciarSesion_PantallaInicial);

        btnRegistrarse_PantallaInicial =new JButton("Registrarse");
        btnRegistrarse_PantallaInicial.setBackground(Color.WHITE);
        btnRegistrarse_PantallaInicial.setFont(new Font("Arial",Font.BOLD,35));
        btnRegistrarse_PantallaInicial.setBounds(ancho-600, tamOrig, 17*tamOrig, 4*tamOrig);
        btnRegistrarse_PantallaInicial.addActionListener(new eventoBotonesPanelPrincipal());
        pnlPantallaInicial.add(btnRegistrarse_PantallaInicial);

        lblBusquedaDatos_PantallaInicial =new JLabel("Búsqueda con datos:");
        lblBusquedaDatos_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        lblBusquedaDatos_PantallaInicial.setBounds(tamOrig, 6*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaInicial.add(lblBusquedaDatos_PantallaInicial);

        lblOrigen_PantallaInicial =new JLabel("Origen:");
        lblOrigen_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        lblOrigen_PantallaInicial.setBounds(17*tamOrig, 9*tamOrig, 9*tamOrig, 3*tamOrig);
        pnlPantallaInicial.add(lblOrigen_PantallaInicial);

        /*
        txtOrigen_PantallaInicial =new JTextField();
        txtOrigen_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        txtOrigen_PantallaInicial.setBounds(17*tamOrig, 12*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaInicial.add(txtOrigen_PantallaInicial);
        */

        comboBoxModelOrigen_PantallaInicial = new DefaultComboBoxModel<>();
        comboBoxCiudadesOrigen_PantallaInicial = new JComboBox<>(comboBoxModelOrigen_PantallaInicial);
        comboBoxCiudadesOrigen_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        comboBoxCiudadesOrigen_PantallaInicial.setBounds(17*tamOrig, 12*tamOrig, 20*tamOrig, 3*tamOrig);
        comboBoxCiudadesOrigen_PantallaInicial.setBackground(Color.white);
        comboBoxCiudadesOrigen_PantallaInicial.setMaximumRowCount(5);
        updateComboBoxOrigen_Cities(allCities);
        JScrollPane scrollPaneOrigen_Ciudades = new JScrollPane(comboBoxCiudadesOrigen_PantallaInicial);
        scrollPaneOrigen_Ciudades.setBounds(17*tamOrig, 12*tamOrig, 20*tamOrig, 10*tamOrig);
        pnlPantallaInicial.add(comboBoxCiudadesOrigen_PantallaInicial);
        scrollPaneOrigen_Ciudades.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneOrigen_Ciudades.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        lblDestino_PantallaInicial =new JLabel("Destino:");
        lblDestino_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        lblDestino_PantallaInicial.setBounds(40*tamOrig, 9*tamOrig, 9*tamOrig, 3*tamOrig);
        pnlPantallaInicial.add(lblDestino_PantallaInicial);

        /*
        txtDestino_PantallaInicial =new JTextField();
        txtDestino_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        txtDestino_PantallaInicial.setBounds(40*tamOrig, 12*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaInicial.add(txtDestino_PantallaInicial);
         */

        comboBoxModelDestino_PantallaInicial = new DefaultComboBoxModel<>();
        comboBoxCiudadesDestino_PantallaInicial = new JComboBox<>(comboBoxModelDestino_PantallaInicial);
        comboBoxCiudadesDestino_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        comboBoxCiudadesDestino_PantallaInicial.setBounds(40*tamOrig, 12*tamOrig, 20*tamOrig, 3*tamOrig);
        comboBoxCiudadesDestino_PantallaInicial.setBackground(Color.white);
        comboBoxCiudadesDestino_PantallaInicial.setMaximumRowCount(5);
        updateComboBoxDestino_Cities(allCities);
        JScrollPane scrollPaneDestino_Ciudades = new JScrollPane(comboBoxCiudadesDestino_PantallaInicial);
        scrollPaneDestino_Ciudades.setBounds(40*tamOrig, 12*tamOrig, 20*tamOrig, 10*tamOrig);
        pnlPantallaInicial.add(comboBoxCiudadesDestino_PantallaInicial);
        scrollPaneDestino_Ciudades.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneDestino_Ciudades.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        lblFechaIda_PantallaInicial =new JLabel("Fecha (DD/MM/AAAA):");
        lblFechaIda_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        lblFechaIda_PantallaInicial.setBounds(17*tamOrig, 15*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaInicial.add(lblFechaIda_PantallaInicial);

        txtFechaIda_PantallaInicial =new JTextField();
        txtFechaIda_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        txtFechaIda_PantallaInicial.setBounds(17*tamOrig, 18*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaInicial.add(txtFechaIda_PantallaInicial);

        btnBuscarDatos_PantallaInicial =new JButton("Buscar");
        btnBuscarDatos_PantallaInicial.setBackground(Color.WHITE);
        btnBuscarDatos_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        btnBuscarDatos_PantallaInicial.setBounds(28*tamOrig, 22*tamOrig, 20*tamOrig, 3*tamOrig);
        btnBuscarDatos_PantallaInicial.addActionListener(new eventoBotonesPanelPrincipal());
        pnlPantallaInicial.add(btnBuscarDatos_PantallaInicial);

        lblNoHayVuelosDisponibles_PantallaBilletesInicio =new JLabel("No hay billetes disponibles para los datos que ha seleccionado");
        lblNoHayVuelosDisponibles_PantallaBilletesInicio.setForeground(Color.RED);
        lblNoHayVuelosDisponibles_PantallaBilletesInicio.setFont(new Font("Arial",Font.BOLD,20));
        lblNoHayVuelosDisponibles_PantallaBilletesInicio.setBounds(tamOrig, 25*tamOrig, ancho, 3*tamOrig);
        lblNoHayVuelosDisponibles_PantallaBilletesInicio.setHorizontalAlignment(JLabel.CENTER);
        pnlPantallaInicial.add(lblNoHayVuelosDisponibles_PantallaBilletesInicio);
        lblNoHayVuelosDisponibles_PantallaBilletesInicio.setVisible(false);

        lblNoHayVuelosDisponiblesID_PantallaBilletesInicio =new JLabel("No hay billetes disponibles con ese identificador de vuelo");
        lblNoHayVuelosDisponiblesID_PantallaBilletesInicio.setForeground(Color.RED);
        lblNoHayVuelosDisponiblesID_PantallaBilletesInicio.setFont(new Font("Arial",Font.BOLD,20));
        lblNoHayVuelosDisponiblesID_PantallaBilletesInicio.setBounds(tamOrig, 41*tamOrig, ancho, 3*tamOrig);
        lblNoHayVuelosDisponiblesID_PantallaBilletesInicio.setHorizontalAlignment(JLabel.CENTER);
        pnlPantallaInicial.add(lblNoHayVuelosDisponiblesID_PantallaBilletesInicio);
        lblNoHayVuelosDisponiblesID_PantallaBilletesInicio.setVisible(false);

        lblBusquedaID_PantallaInicial =new JLabel("Búsqueda con ID del vuelo:");
        lblBusquedaID_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        lblBusquedaID_PantallaInicial.setBounds(tamOrig, 28*tamOrig, 25*tamOrig, 3*tamOrig);
        pnlPantallaInicial.add(lblBusquedaID_PantallaInicial);

        lblIDIda_PantallaInicial =new JLabel("ID del vuelo (ICAIRX):");
        lblIDIda_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        lblIDIda_PantallaInicial.setBounds(17*tamOrig, 31*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaInicial.add(lblIDIda_PantallaInicial);

        txtIDIda_PantallaInicial =new JTextField();
        txtIDIda_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        txtIDIda_PantallaInicial.setBounds(17*tamOrig, 34*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaInicial.add(txtIDIda_PantallaInicial);

        btnBuscarID_PantallaInicial =new JButton("Buscar");
        btnBuscarID_PantallaInicial.setBackground(Color.WHITE);
        btnBuscarID_PantallaInicial.setFont(new Font("Arial",Font.BOLD,30));
        btnBuscarID_PantallaInicial.setBounds(28*tamOrig, 38*tamOrig, 20*tamOrig, 3*tamOrig);
        btnBuscarID_PantallaInicial.addActionListener(new eventoBotonesPanelPrincipal());
        pnlPantallaInicial.add(btnBuscarID_PantallaInicial);

        LinePanel linePanel1 = new LinePanel();
        linePanel1.setBounds(tamOrig, 6*tamOrig, ancho, 1);
        pnlPantallaInicial.add(linePanel1);

        LinePanel linePanel2 = new LinePanel();
        linePanel2.setBounds(tamOrig, 28*tamOrig, ancho, 2);
        pnlPantallaInicial.add(linePanel2);
        this.add(pnlPantallaInicial);
        //endregion
        pnlPantallaInicial.setVisible(true);

        //PANTALLA DE BILLETES DISPOBILES DEL BUSCADOR DE LA PÁGINA INICIAL
        //region
        pnlMostrarBilletesInicio=new JPanel();
        pnlMostrarBilletesInicio.setBackground(Color.white);
        pnlMostrarBilletesInicio.setPreferredSize(d1);
        pnlMostrarBilletesInicio.setLayout(null);

        ImageIcon imageIconLogoMostrarBilletesInicio = null;
        try {
            imageIconLogoMostrarBilletesInicio = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\Logo3.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblLogoMostrarBilletesInicio = new JLabel(imageIconLogoMostrarBilletesInicio);
        lblLogoMostrarBilletesInicio.setBounds(tamOrig, tamOrig, 13*tamOrig, 4*tamOrig);
        pnlMostrarBilletesInicio.add(lblLogoMostrarBilletesInicio);

        LinePanel linePanel7 = new LinePanel();
        linePanel7.setBounds(tamOrig, 6*tamOrig, ancho, 1);
        pnlMostrarBilletesInicio.add(linePanel7);

        btnVolverPantallaInicio_PantallaBilletesInicio =new JButton("Volver");
        btnVolverPantallaInicio_PantallaBilletesInicio.setBackground(Color.WHITE);
        btnVolverPantallaInicio_PantallaBilletesInicio.setFont(new Font("Arial",Font.BOLD,30));
        btnVolverPantallaInicio_PantallaBilletesInicio.setBounds(ancho-200, tamOrig, 11*tamOrig, 4*tamOrig);
        btnVolverPantallaInicio_PantallaBilletesInicio.addActionListener(new eventoBotonesPantallaBilletesInicio());
        pnlMostrarBilletesInicio.add(btnVolverPantallaInicio_PantallaBilletesInicio);



        this.add(pnlMostrarBilletesInicio);
        //endregion
        pnlMostrarBilletesInicio.setVisible(false);

        //PANTALLA DE REGISTRO
        //region
        pnlPantallaRegistro=new JPanel();
        pnlPantallaRegistro.setBackground(Color.white);
        pnlPantallaRegistro.setPreferredSize(d1);
        pnlPantallaRegistro.setLayout(null);

        ImageIcon imageIconPantallaRegistro = null;
        try {
            imageIconPantallaRegistro = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\Logo3.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblLogoRegistro = new JLabel(imageIconPantallaRegistro);
        lblLogoRegistro.setBounds(tamOrig, tamOrig, 13*tamOrig, 4*tamOrig);
        pnlPantallaRegistro.add(lblLogoRegistro);

        JLabel lblTituloPantallaRegistro=new JLabel("Pantalla de registro");
        lblTituloPantallaRegistro.setFont(new Font("Arial",Font.BOLD,40));
        lblTituloPantallaRegistro.setBounds(27*tamOrig, tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(lblTituloPantallaRegistro);

        lblNombre_PantallaRegistro =new JLabel("Nombre:");
        lblNombre_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        lblNombre_PantallaRegistro.setBounds(3*tamOrig, 6*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(lblNombre_PantallaRegistro);

        txtNombre_PantallaRegistro =new JTextField();
        txtNombre_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        txtNombre_PantallaRegistro.setBounds(3*tamOrig, 9*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(txtNombre_PantallaRegistro);

        lblApellido1_PantallaRegistro =new JLabel("Apellido 1:");
        lblApellido1_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        lblApellido1_PantallaRegistro.setBounds(3*tamOrig, 12*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(lblApellido1_PantallaRegistro);

        txtApellido1_PantallaRegistro =new JTextField();
        txtApellido1_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        txtApellido1_PantallaRegistro.setBounds(3*tamOrig, 15*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(txtApellido1_PantallaRegistro);

        lblApellido2_PantallaRegistro =new JLabel("Apellido 2:");
        lblApellido2_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        lblApellido2_PantallaRegistro.setBounds(40*tamOrig, 12*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(lblApellido2_PantallaRegistro);

        txtApellido2_PantallaRegistro =new JTextField();
        txtApellido2_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        txtApellido2_PantallaRegistro.setBounds(40*tamOrig, 15*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(txtApellido2_PantallaRegistro);

        lblFechaNacimiento_PantallaRegistro =new JLabel("Fecha de naciemiento (DD/MM/AAAA):");
        lblFechaNacimiento_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        lblFechaNacimiento_PantallaRegistro.setBounds(3*tamOrig, 18*tamOrig, 35*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(lblFechaNacimiento_PantallaRegistro);

        txtFechaNacimiento_PantallaRegistro =new JTextField();
        txtFechaNacimiento_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        txtFechaNacimiento_PantallaRegistro.setBounds(3*tamOrig, 21*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(txtFechaNacimiento_PantallaRegistro);

        lblNacionalidad_PantallaRegistro =new JLabel("Nacionalidad:");
        lblNacionalidad_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        lblNacionalidad_PantallaRegistro.setBounds(40*tamOrig, 18*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(lblNacionalidad_PantallaRegistro);

        //txtNacionalidad=new JTextField();
        //txtNacionalidad.setFont(new Font("Arial",Font.BOLD,30));
        //txtNacionalidad.setBounds(40*tamOrig, 21*tamOrig, 30*tamOrig, 3*tamOrig);
        //pnlPantallaRegistro.add(txtNacionalidad);

        comboBoxModel_PantallaRegistro = new DefaultComboBoxModel<>();
        comboBoxNacionalidades_PantallaRegistro = new JComboBox<>(comboBoxModel_PantallaRegistro);
        comboBoxNacionalidades_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        comboBoxNacionalidades_PantallaRegistro.setBounds(40*tamOrig, 21*tamOrig, 30*tamOrig, 3*tamOrig);
        comboBoxNacionalidades_PantallaRegistro.setBackground(Color.white);
        comboBoxNacionalidades_PantallaRegistro.setMaximumRowCount(5);
        updateComboBox_Countries(allCountries);
        JScrollPane scrollPane_Paises = new JScrollPane(comboBoxNacionalidades_PantallaRegistro);
        scrollPane_Paises.setBounds(40*tamOrig, 21*tamOrig, 30*tamOrig, 10*tamOrig);
        pnlPantallaRegistro.add(comboBoxNacionalidades_PantallaRegistro);
        scrollPane_Paises.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_Paises.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        lblCorreoElectronico_PantallaRegistro =new JLabel("Correo electrónico (X@Y.Z):");
        lblCorreoElectronico_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        lblCorreoElectronico_PantallaRegistro.setBounds(3*tamOrig, 24*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(lblCorreoElectronico_PantallaRegistro);

        txtCorreoElectronico_PantallaRegistro =new JTextField();
        txtCorreoElectronico_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        txtCorreoElectronico_PantallaRegistro.setBounds(3*tamOrig, 27*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(txtCorreoElectronico_PantallaRegistro);

        lblContraseña1_PantallaRegistro =new JLabel("Contraseña:");
        lblContraseña1_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        lblContraseña1_PantallaRegistro.setBounds(3*tamOrig, 30*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(lblContraseña1_PantallaRegistro);

        txtContraseña1_PantallaRegistro =new JTextField();
        txtContraseña1_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        txtContraseña1_PantallaRegistro.setBounds(3*tamOrig, 33*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(txtContraseña1_PantallaRegistro);

        lblContraseña2_PantallaRegistro =new JLabel("Repetir Contraseña:");
        lblContraseña2_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        lblContraseña2_PantallaRegistro.setBounds(40*tamOrig, 30*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(lblContraseña2_PantallaRegistro);

        txtContraseña2_PantallaRegistro =new JTextField();
        txtContraseña2_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        txtContraseña2_PantallaRegistro.setBounds(40*tamOrig, 33*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaRegistro.add(txtContraseña2_PantallaRegistro);

        btnRegistrarse_PantallaRegistro =new JButton("Registrarse");
        btnRegistrarse_PantallaRegistro.setBackground(Color.WHITE);
        btnRegistrarse_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        btnRegistrarse_PantallaRegistro.setBounds(23*tamOrig, 37*tamOrig, 30*tamOrig, 4*tamOrig);
        btnRegistrarse_PantallaRegistro.addActionListener(new eventoBotonesPanelRegistro());
        pnlPantallaRegistro.add(btnRegistrarse_PantallaRegistro);

        btnVolverPantallaInicio_PantallaRegistro =new JButton("Volver");
        btnVolverPantallaInicio_PantallaRegistro.setBackground(Color.WHITE);
        btnVolverPantallaInicio_PantallaRegistro.setFont(new Font("Arial",Font.BOLD,30));
        btnVolverPantallaInicio_PantallaRegistro.setBounds(ancho-200, tamOrig, 11*tamOrig, 4*tamOrig);
        btnVolverPantallaInicio_PantallaRegistro.addActionListener(new eventoBotonesPanelRegistro());
        pnlPantallaRegistro.add(btnVolverPantallaInicio_PantallaRegistro);

        LinePanel linePanelRegistro1 = new LinePanel();
        linePanelRegistro1.setBounds(tamOrig, 6*tamOrig, ancho, 1); // Adjust the position and size as needed
        pnlPantallaRegistro.add(linePanelRegistro1);

        lblContraseñasDistintas=new JLabel("Las contraseñas tienen que ser iguales");
        lblContraseñasDistintas.setForeground(Color.RED);
        lblContraseñasDistintas.setFont(new Font("Arial",Font.BOLD,20));
        lblContraseñasDistintas.setBounds(tamOrig, 41*tamOrig, ancho, 3*tamOrig);
        lblContraseñasDistintas.setHorizontalAlignment(JLabel.CENTER);
        pnlPantallaRegistro.add(lblContraseñasDistintas);
        lblContraseñasDistintas.setVisible(false);

        lblUsuarioExistente=new JLabel("El usuario ya está registrado");
        lblUsuarioExistente.setForeground(Color.RED);
        lblUsuarioExistente.setFont(new Font("Arial",Font.BOLD,20));
        lblUsuarioExistente.setBounds(tamOrig, 41*tamOrig, ancho, 3*tamOrig);
        lblUsuarioExistente.setHorizontalAlignment(JLabel.CENTER);
        pnlPantallaRegistro.add(lblUsuarioExistente);
        lblUsuarioExistente.setVisible(false);

        lblUsuarioRegistradoConExito=new JLabel("Usuario registrado con éxito");
        lblUsuarioRegistradoConExito.setForeground(Color.RED);
        lblUsuarioRegistradoConExito.setFont(new Font("Arial",Font.BOLD,20));
        lblUsuarioRegistradoConExito.setBounds(tamOrig, 41*tamOrig, ancho, 3*tamOrig);
        lblUsuarioRegistradoConExito.setHorizontalAlignment(JLabel.CENTER);
        pnlPantallaRegistro.add(lblUsuarioRegistradoConExito);
        lblUsuarioRegistradoConExito.setVisible(false);

        this.add(pnlPantallaRegistro);
        //endregion
        pnlPantallaRegistro.setVisible(false);

        //PANTALLA DE INICIO DE SESION
        //region
        pnlPantallaInicioSesion=new JPanel();
        pnlPantallaInicioSesion.setBackground(Color.white);
        pnlPantallaInicioSesion.setPreferredSize(d1);
        pnlPantallaInicioSesion.setLayout(null);

        ImageIcon imageIconPantallaInicioSesion = null;
        try {
            imageIconPantallaInicioSesion = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\Logo3.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblLogoInicioSesion = new JLabel(imageIconPantallaInicioSesion);
        lblLogoInicioSesion.setBounds(tamOrig, tamOrig, 13*tamOrig, 4*tamOrig);
        pnlPantallaInicioSesion.add(lblLogoInicioSesion);

        JLabel lblTituloPantallaInicioSesion=new JLabel("Pantalla de inicio de sesión");
        lblTituloPantallaInicioSesion.setFont(new Font("Arial",Font.BOLD,40));
        lblTituloPantallaInicioSesion.setBounds(27*tamOrig, tamOrig, 40*tamOrig, 3*tamOrig);
        pnlPantallaInicioSesion.add(lblTituloPantallaInicioSesion);

        lblCorreElectronico_PantallaInicioSesion =new JLabel("Correo electrónico:");
        lblCorreElectronico_PantallaInicioSesion.setFont(new Font("Arial",Font.BOLD,30));
        lblCorreElectronico_PantallaInicioSesion.setBounds(3*tamOrig, 6*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaInicioSesion.add(lblCorreElectronico_PantallaInicioSesion);

        txtCorreoElectronico_PantallaInicioSesion =new JTextField("pedro@marcos.plaza");
        txtCorreoElectronico_PantallaInicioSesion.setFont(new Font("Arial",Font.BOLD,30));
        txtCorreoElectronico_PantallaInicioSesion.setBounds(3*tamOrig, 9*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaInicioSesion.add(txtCorreoElectronico_PantallaInicioSesion);

        lblContraseña_PantallaInicioSesion =new JLabel("Contraseña:");
        lblContraseña_PantallaInicioSesion.setFont(new Font("Arial",Font.BOLD,30));
        lblContraseña_PantallaInicioSesion.setBounds(3*tamOrig, 12*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaInicioSesion.add(lblContraseña_PantallaInicioSesion);

        txtContraseña_PantallaInicioSesion =new JTextField("1234");
        txtContraseña_PantallaInicioSesion.setFont(new Font("Arial",Font.BOLD,30));
        txtContraseña_PantallaInicioSesion.setBounds(3*tamOrig, 15*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlPantallaInicioSesion.add(txtContraseña_PantallaInicioSesion);

        btnIniciarSesion_PantallaInicioSesion =new JButton("Iniciar sesión");
        btnIniciarSesion_PantallaInicioSesion.setBackground(Color.WHITE);
        btnIniciarSesion_PantallaInicioSesion.setFont(new Font("Arial",Font.BOLD,30));
        btnIniciarSesion_PantallaInicioSesion.setBounds(3*tamOrig, 20*tamOrig, 30*tamOrig, 4*tamOrig);
        btnIniciarSesion_PantallaInicioSesion.addActionListener(new eventoBotonesPanelInicioSesion());
        pnlPantallaInicioSesion.add(btnIniciarSesion_PantallaInicioSesion);

        btnVolverPantallaInicio_PantallaInicioSesion =new JButton("Volver");
        btnVolverPantallaInicio_PantallaInicioSesion.setBackground(Color.WHITE);
        btnVolverPantallaInicio_PantallaInicioSesion.setFont(new Font("Arial",Font.BOLD,30));
        btnVolverPantallaInicio_PantallaInicioSesion.setBounds(ancho-200, tamOrig, 11*tamOrig, 4*tamOrig);
        btnVolverPantallaInicio_PantallaInicioSesion.addActionListener(new eventoBotonesPanelInicioSesion());
        pnlPantallaInicioSesion.add(btnVolverPantallaInicio_PantallaInicioSesion);

        lblLoginFallido_PantallaInicioSesion=new JLabel("Correo o contraseña erroneos");
        lblLoginFallido_PantallaInicioSesion.setForeground(Color.RED);
        lblLoginFallido_PantallaInicioSesion.setFont(new Font("Arial",Font.BOLD,20));
        lblLoginFallido_PantallaInicioSesion.setBounds(tamOrig, 41*tamOrig, ancho, 3*tamOrig);
        lblLoginFallido_PantallaInicioSesion.setHorizontalAlignment(JLabel.CENTER);
        pnlPantallaInicioSesion.add(lblLoginFallido_PantallaInicioSesion);
        lblLoginFallido_PantallaInicioSesion.setVisible(false);

        this.add(pnlPantallaInicioSesion);
        //endregion
        pnlPantallaInicioSesion.setVisible(false);

        //PANTALLA OPCINES CON SESION INICIADA
        //region
        pnlOpcionesSesionIniciada=new JPanel();
        pnlOpcionesSesionIniciada.setBackground(Color.white);
        pnlOpcionesSesionIniciada.setPreferredSize(d1);
        pnlOpcionesSesionIniciada.setLayout(null);

        ImageIcon imageIconOpcionesSesionInciada = null;
        try {
            imageIconOpcionesSesionInciada = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\Logo3.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblLogoOpcionesSesionInciada = new JLabel(imageIconOpcionesSesionInciada);
        lblLogoOpcionesSesionInciada.setBounds(tamOrig, tamOrig, 13*tamOrig, 4*tamOrig);
        pnlOpcionesSesionIniciada.add(lblLogoOpcionesSesionInciada);

        LinePanel linePanel5 = new LinePanel();
        linePanel5.setBounds(tamOrig, 6*tamOrig, ancho, 1);
        pnlOpcionesSesionIniciada.add(linePanel5);

        btnCerrarSesion_PantallaOpciones =new JButton("Cerrar sesión");
        btnCerrarSesion_PantallaOpciones.setBackground(Color.WHITE);
        btnCerrarSesion_PantallaOpciones.setFont(new Font("Arial",Font.BOLD,30));
        btnCerrarSesion_PantallaOpciones.setBounds(ancho-250, tamOrig, 15*tamOrig, 4*tamOrig);
        btnCerrarSesion_PantallaOpciones.addActionListener(new eventoBotonesPanelOpcionesSesionIniciada());
        pnlOpcionesSesionIniciada.add(btnCerrarSesion_PantallaOpciones);

        btnComprarBilletes_PantallaOpciones =new JButton("Comprar billetes");
        btnComprarBilletes_PantallaOpciones.setBackground(Color.WHITE);
        btnComprarBilletes_PantallaOpciones.setFont(new Font("Arial",Font.BOLD,30));
        btnComprarBilletes_PantallaOpciones.setBounds(25*tamOrig, 7*tamOrig, 25*tamOrig, 5*tamOrig);
        btnComprarBilletes_PantallaOpciones.addActionListener(new eventoBotonesPanelOpcionesSesionIniciada());
        pnlOpcionesSesionIniciada.add(btnComprarBilletes_PantallaOpciones);

        btnConsultarBilletesComprados_PantallaOpciones =new JButton("Consultar mis billetes");
        btnConsultarBilletesComprados_PantallaOpciones.setBackground(Color.WHITE);
        btnConsultarBilletesComprados_PantallaOpciones.setFont(new Font("Arial",Font.BOLD,30));
        btnConsultarBilletesComprados_PantallaOpciones.setBounds(25*tamOrig, 13*tamOrig, 25*tamOrig, 5*tamOrig);
        btnConsultarBilletesComprados_PantallaOpciones.addActionListener(new eventoBotonesPanelOpcionesSesionIniciada());
        pnlOpcionesSesionIniciada.add(btnConsultarBilletesComprados_PantallaOpciones);

        btnAñadirDatosBancarios_PantallaOpciones =new JButton("Añadir datos bancarios");
        btnAñadirDatosBancarios_PantallaOpciones.setBackground(Color.WHITE);
        btnAñadirDatosBancarios_PantallaOpciones.setFont(new Font("Arial",Font.BOLD,30));
        btnAñadirDatosBancarios_PantallaOpciones.setBounds(25*tamOrig, 19*tamOrig, 25*tamOrig, 5*tamOrig);
        btnAñadirDatosBancarios_PantallaOpciones.addActionListener(new eventoBotonesPanelOpcionesSesionIniciada());
        pnlOpcionesSesionIniciada.add(btnAñadirDatosBancarios_PantallaOpciones);



        this.add(pnlOpcionesSesionIniciada);
        //endregion
        pnlOpcionesSesionIniciada.setVisible(false);

        //PANTALLA BUSQUEDA DE BILLETES SIN PREMIUM
        //region
        pnlPantallaBusquedaBilletesSinPremium =new JPanel();
        pnlPantallaBusquedaBilletesSinPremium.setBackground(Color.white);
        pnlPantallaBusquedaBilletesSinPremium.setPreferredSize(d1);
        pnlPantallaBusquedaBilletesSinPremium.setLayout(null);

        ImageIcon imageIconSesionIniciada = null;
        try {
            imageIconSesionIniciada = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\Logo3.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblLogoSesionIniciada = new JLabel(imageIconSesionIniciada);
        lblLogoSesionIniciada.setBounds(tamOrig, tamOrig, 13*tamOrig, 4*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblLogoSesionIniciada);

        ImageIcon imageIconCalendarioSesionIniciada = null;
        try {
            imageIconCalendarioSesionIniciada = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\calendar5.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblCalendarSesionIniciada = new JLabel(imageIconCalendarioSesionIniciada);
        lblCalendarSesionIniciada.setBounds(6*tamOrig, 16*tamOrig, 13*tamOrig, 5*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblCalendarSesionIniciada);

        ImageIcon imageIconLocationSesionIniciada = null;
        try {
            imageIconLocationSesionIniciada = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\location3.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblLocationSesionIniciada = new JLabel(imageIconLocationSesionIniciada);
        lblLocationSesionIniciada.setBounds(6*tamOrig, 10*tamOrig, 13*tamOrig, 5*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblLocationSesionIniciada);

        ImageIcon imageIconIDSesionIniciada = null;
        try {
            imageIconIDSesionIniciada = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\ID3.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblIDSesionIniciada = new JLabel(imageIconIDSesionIniciada);
        lblIDSesionIniciada.setBounds(6*tamOrig, 32*tamOrig, 13*tamOrig, 5*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblIDSesionIniciada);

        lblNombreUsuarioIniciado_PantallaBBNoPremium =new JLabel("Nombre usuario");
        lblNombreUsuarioIniciado_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        lblNombreUsuarioIniciado_PantallaBBNoPremium.setBounds(ancho-950, tamOrig, 25*tamOrig, 4*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblNombreUsuarioIniciado_PantallaBBNoPremium);

        btnVolverPantallaOpciones_PantallaBBNoPremium =new JButton("Volver");
        btnVolverPantallaOpciones_PantallaBBNoPremium.setBackground(Color.WHITE);
        btnVolverPantallaOpciones_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        btnVolverPantallaOpciones_PantallaBBNoPremium.setBounds(ancho-250, tamOrig, 15*tamOrig, 4*tamOrig);
        btnVolverPantallaOpciones_PantallaBBNoPremium.addActionListener(new eventoBotonesPanelSesionIniciada());
        pnlPantallaBusquedaBilletesSinPremium.add(btnVolverPantallaOpciones_PantallaBBNoPremium);

        lblBusquedaDatos_PantallaBBNoPremium =new JLabel("Búsqueda con datos:");
        lblBusquedaDatos_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        lblBusquedaDatos_PantallaBBNoPremium.setBounds(tamOrig, 6*tamOrig, 500, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblBusquedaDatos_PantallaBBNoPremium);

        lblOrigen_PantallaBBNoPremium =new JLabel("Origen:");
        lblOrigen_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        lblOrigen_PantallaBBNoPremium.setBounds(17*tamOrig, 9*tamOrig, 9*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblOrigen_PantallaBBNoPremium);

        txtOrigen_PantallaBBNoPremium =new JTextField();
        txtOrigen_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        txtOrigen_PantallaBBNoPremium.setBounds(17*tamOrig, 12*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(txtOrigen_PantallaBBNoPremium);

        lblDestino_PantallaBBNoPremium =new JLabel("Destino:");
        lblDestino_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        lblDestino_PantallaBBNoPremium.setBounds(40*tamOrig, 9*tamOrig, 9*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblDestino_PantallaBBNoPremium);

        txtDestino_PantallaBBNoPremium =new JTextField();
        txtDestino_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        txtDestino_PantallaBBNoPremium.setBounds(40*tamOrig, 12*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(txtDestino_PantallaBBNoPremium);

        lblFechaIda_PantallaBBNoPremium =new JLabel("Ida:");
        lblFechaIda_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        lblFechaIda_PantallaBBNoPremium.setBounds(17*tamOrig, 15*tamOrig, 9*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblFechaIda_PantallaBBNoPremium);

        txtFechaIda_PantallaBBNoPremium =new JTextField();
        txtFechaIda_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        txtFechaIda_PantallaBBNoPremium.setBounds(17*tamOrig, 18*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(txtFechaIda_PantallaBBNoPremium);

        lblFechaVuelta_PantallaBBNoPremium =new JLabel("Vuelta:");
        lblFechaVuelta_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        lblFechaVuelta_PantallaBBNoPremium.setBounds(40*tamOrig, 15*tamOrig, 9*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblFechaVuelta_PantallaBBNoPremium);

        txtFechaVuelta_PantallaBBNoPremium =new JTextField();
        txtFechaVuelta_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        txtFechaVuelta_PantallaBBNoPremium.setBounds(40*tamOrig, 18*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(txtFechaVuelta_PantallaBBNoPremium);

        btnBuscarDatos_PantallaBBNoPremium =new JButton("Buscar");
        btnBuscarDatos_PantallaBBNoPremium.setBackground(Color.WHITE);
        btnBuscarDatos_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        btnBuscarDatos_PantallaBBNoPremium.setBounds(28*tamOrig, 22*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(btnBuscarDatos_PantallaBBNoPremium);

        lblBuscarDatos_PantallaBBNoPremium =new JLabel("Hay x vuelos disponiles para la ida y x para la vuelta. Inicie sesión para comprar.");
        lblBuscarDatos_PantallaBBNoPremium.setForeground(Color.RED);
        lblBuscarDatos_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,20));
        lblBuscarDatos_PantallaBBNoPremium.setBounds(tamOrig, 25*tamOrig, ancho, 3*tamOrig);
        lblBuscarDatos_PantallaBBNoPremium.setHorizontalAlignment(JLabel.CENTER);
        pnlPantallaBusquedaBilletesSinPremium.add(lblBuscarDatos_PantallaBBNoPremium);
        lblBuscarDatos_PantallaBBNoPremium.setVisible(false);

        lblBusquedaID_PantallaBBNoPremium =new JLabel("Búsqueda con ID del vuelo:");
        lblBusquedaID_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        lblBusquedaID_PantallaBBNoPremium.setBounds(tamOrig, 28*tamOrig, 25*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblBusquedaID_PantallaBBNoPremium);

        lblIDIda_PantallaBBNoPremium =new JLabel("ID de Ida:");
        lblIDIda_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        lblIDIda_PantallaBBNoPremium.setBounds(17*tamOrig, 31*tamOrig, 9*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblIDIda_PantallaBBNoPremium);

        txtIDIda_PantallaBBNoPremium =new JTextField();
        txtIDIda_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        txtIDIda_PantallaBBNoPremium.setBounds(17*tamOrig, 34*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(txtIDIda_PantallaBBNoPremium);

        lblIDVuelta_PantallaBBNoPremium =new JLabel("ID de Vuelta:");
        lblIDVuelta_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        lblIDVuelta_PantallaBBNoPremium.setBounds(40*tamOrig, 31*tamOrig, 13*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(lblIDVuelta_PantallaBBNoPremium);

        txtIDVuelta_PantallaBBNoPremium =new JTextField();
        txtIDVuelta_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        txtIDVuelta_PantallaBBNoPremium.setBounds(40*tamOrig, 34*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(txtIDVuelta_PantallaBBNoPremium);

        btnBuscarID_PantallaBBNoPremium =new JButton("Buscar");
        btnBuscarID_PantallaBBNoPremium.setBackground(Color.WHITE);
        btnBuscarID_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,30));
        btnBuscarID_PantallaBBNoPremium.setBounds(28*tamOrig, 38*tamOrig, 20*tamOrig, 3*tamOrig);
        pnlPantallaBusquedaBilletesSinPremium.add(btnBuscarID_PantallaBBNoPremium);


        lblBuscarID_PantallaBBNoPremium =new JLabel("Hay x vuelos disponiles para la ida y x para la vuelta. Inicie sesión para comprar.");
        lblBuscarID_PantallaBBNoPremium.setForeground(Color.RED);
        lblBuscarID_PantallaBBNoPremium.setFont(new Font("Arial",Font.BOLD,20));
        lblBuscarID_PantallaBBNoPremium.setBounds(tamOrig, 41*tamOrig, ancho, 3*tamOrig);
        lblBuscarID_PantallaBBNoPremium.setHorizontalAlignment(JLabel.CENTER);
        pnlPantallaBusquedaBilletesSinPremium.add(lblBuscarID_PantallaBBNoPremium);
        lblBuscarID_PantallaBBNoPremium.setVisible(false);

        LinePanel linePanel3 = new LinePanel();
        linePanel3.setBounds(tamOrig, 6*tamOrig, ancho, 1);
        pnlPantallaBusquedaBilletesSinPremium.add(linePanel3);

        LinePanel linePanel4 = new LinePanel();
        linePanel4.setBounds(tamOrig, 28*tamOrig, ancho, 2);
        pnlPantallaBusquedaBilletesSinPremium.add(linePanel4);

        this.add(pnlPantallaBusquedaBilletesSinPremium);
        //endregion
        pnlPantallaBusquedaBilletesSinPremium.setVisible(false);

        //PANTALLA PARA INTRODUCIR DATOS BANCARIOS
        //region
        pnlAñadirDatosBancarios=new JPanel();
        pnlAñadirDatosBancarios.setBackground(Color.white);
        pnlAñadirDatosBancarios.setPreferredSize(d1);
        pnlAñadirDatosBancarios.setLayout(null);

        ImageIcon imageIconAñadirDatosBancarios = null;
        try {
            imageIconAñadirDatosBancarios = new ImageIcon(ImageIO.read(new File("C:\\ICAIR\\Imagenes\\Logo3.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel lblLogoAñadirDatosBancarios = new JLabel(imageIconAñadirDatosBancarios);
        lblLogoAñadirDatosBancarios.setBounds(tamOrig, tamOrig, 13*tamOrig, 4*tamOrig);
        pnlAñadirDatosBancarios.add(lblLogoAñadirDatosBancarios);

        LinePanel linePanel6 = new LinePanel();
        linePanel6.setBounds(tamOrig, 6*tamOrig, ancho, 1);
        pnlAñadirDatosBancarios.add(linePanel6);

        lblNumeroTarjeta_PantallaDatosBancarios =new JLabel("Numero de Tarjeta (XXXX-XXXX-XXXX-XXXX):");
        lblNumeroTarjeta_PantallaDatosBancarios.setFont(new Font("Arial",Font.BOLD,30));
        lblNumeroTarjeta_PantallaDatosBancarios.setBounds(3*tamOrig, 6*tamOrig, 45*tamOrig, 3*tamOrig);
        pnlAñadirDatosBancarios.add(lblNumeroTarjeta_PantallaDatosBancarios);

        txtNumeroTarjeta_PantallaDatosBancarios =new JTextField();
        txtNumeroTarjeta_PantallaDatosBancarios.setFont(new Font("Arial",Font.BOLD,30));
        txtNumeroTarjeta_PantallaDatosBancarios.setBounds(3*tamOrig, 9*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlAñadirDatosBancarios.add(txtNumeroTarjeta_PantallaDatosBancarios);

        lblFechaCaducidad_PantallaDatosBancarios =new JLabel("Fecha Caducidad (MM/AAAA):");
        lblFechaCaducidad_PantallaDatosBancarios.setFont(new Font("Arial",Font.BOLD,30));
        lblFechaCaducidad_PantallaDatosBancarios.setBounds(3*tamOrig, 12*tamOrig, 45*tamOrig, 3*tamOrig);
        pnlAñadirDatosBancarios.add(lblFechaCaducidad_PantallaDatosBancarios);

        txtFechaCaducidad_PantallaDatosBancarios =new JTextField();
        txtFechaCaducidad_PantallaDatosBancarios.setFont(new Font("Arial",Font.BOLD,30));
        txtFechaCaducidad_PantallaDatosBancarios.setBounds(3*tamOrig, 15*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlAñadirDatosBancarios.add(txtFechaCaducidad_PantallaDatosBancarios);

        lblCVC_PantallaDatosBancarios =new JLabel("CVC (XXX):");
        lblCVC_PantallaDatosBancarios.setFont(new Font("Arial",Font.BOLD,30));
        lblCVC_PantallaDatosBancarios.setBounds(3*tamOrig, 18*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlAñadirDatosBancarios.add(lblCVC_PantallaDatosBancarios);

        txtCVC_PantallaDatosBancarios =new JTextField();
        txtCVC_PantallaDatosBancarios.setFont(new Font("Arial",Font.BOLD,30));
        txtCVC_PantallaDatosBancarios.setBounds(3*tamOrig, 21*tamOrig, 30*tamOrig, 3*tamOrig);
        pnlAñadirDatosBancarios.add(txtCVC_PantallaDatosBancarios);

        btnAñadirDatosBancarios_PantallaDatosBancarios =new JButton("Añadir tarjeta");
        btnAñadirDatosBancarios_PantallaDatosBancarios.setBackground(Color.WHITE);
        btnAñadirDatosBancarios_PantallaDatosBancarios.setFont(new Font("Arial",Font.BOLD,30));
        btnAñadirDatosBancarios_PantallaDatosBancarios.setBounds(3*tamOrig, 26*tamOrig, 30*tamOrig, 4*tamOrig);
        btnAñadirDatosBancarios_PantallaDatosBancarios.addActionListener(new eventoBotonesPanelAñadirDatosBancarios());
        pnlAñadirDatosBancarios.add(btnAñadirDatosBancarios_PantallaDatosBancarios);

        btnVolverOpciones_PantallaDatosBancarios =new JButton("Volver");
        btnVolverOpciones_PantallaDatosBancarios.setBackground(Color.WHITE);
        btnVolverOpciones_PantallaDatosBancarios.setFont(new Font("Arial",Font.BOLD,30));
        btnVolverOpciones_PantallaDatosBancarios.setBounds(ancho-200, tamOrig, 11*tamOrig, 4*tamOrig);
        btnVolverOpciones_PantallaDatosBancarios.addActionListener(new eventoBotonesPanelAñadirDatosBancarios());
        pnlAñadirDatosBancarios.add(btnVolverOpciones_PantallaDatosBancarios);

        lblDatosBancariosRegistradosConExito=new JLabel("Datos Bancarios Registrados con éxito");
        lblDatosBancariosRegistradosConExito.setForeground(Color.RED);
        lblDatosBancariosRegistradosConExito.setFont(new Font("Arial",Font.BOLD,30));
        lblDatosBancariosRegistradosConExito.setBounds(3*tamOrig, 31*tamOrig, 50*tamOrig, 3*tamOrig);
        pnlAñadirDatosBancarios.add(lblDatosBancariosRegistradosConExito);
        lblDatosBancariosRegistradosConExito.setVisible(false);

        this.add(pnlAñadirDatosBancarios);
        //endregion
        pnlAñadirDatosBancarios.setVisible(false);


    }

    private class eventoBotonesPanelPrincipal implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String origen= comboBoxCiudadesOrigen_PantallaInicial.getSelectedItem().toString().trim();
            String destino= comboBoxCiudadesDestino_PantallaInicial.getSelectedItem().toString().trim();
            String fecha=txtFechaIda_PantallaInicial.getText().trim();
            String id=txtIDIda_PantallaInicial.getText().trim();
            if(e.getSource()== btnIniciarSesion_PantallaInicial) {
                comboBoxCiudadesOrigen_PantallaInicial.setSelectedItem("");
                comboBoxCiudadesDestino_PantallaInicial.setSelectedItem("");
                txtFechaIda_PantallaInicial.setText("");
                txtIDIda_PantallaInicial.setText("");
                lblNoHayVuelosDisponibles_PantallaBilletesInicio.setVisible(false);
                lblNoHayVuelosDisponiblesID_PantallaBilletesInicio.setVisible(false);
                pnlPantallaInicial.setVisible(false);
                pnlPantallaInicioSesion.setVisible(true);
            }
            if(e.getSource()== btnRegistrarse_PantallaInicial) {
                comboBoxCiudadesOrigen_PantallaInicial.setSelectedItem("");
                comboBoxCiudadesDestino_PantallaInicial.setSelectedItem("");
                txtFechaIda_PantallaInicial.setText("");
                txtIDIda_PantallaInicial.setText("");
                lblNoHayVuelosDisponibles_PantallaBilletesInicio.setVisible(false);
                lblNoHayVuelosDisponiblesID_PantallaBilletesInicio.setVisible(false);
                pnlPantallaInicial.setVisible(false);
                pnlPantallaRegistro.setVisible(true);
            }
            if(e.getSource()== btnBuscarDatos_PantallaInicial){
                if(origen.isEmpty()){
                    lblOrigen_PantallaInicial.setForeground(Color.RED);
                }
                else{
                    lblOrigen_PantallaInicial.setForeground(Color.BLACK);
                }
                if(destino.isEmpty()){
                    lblDestino_PantallaInicial.setForeground(Color.RED);
                }
                else{
                    lblDestino_PantallaInicial.setForeground(Color.BLACK);
                }
                if(fecha.length()==10) {
                    if (esEntero(fecha.substring(0, 2)) == true && esEntero(fecha.substring(3, 5)) == true && esEntero(fecha.substring(6, 10)) == true && fecha.substring(2, 3).equals("/")
                            && fecha.substring(5, 6).equals("/")) {
                        lblFechaIda_PantallaInicial.setForeground(Color.BLACK);
                    } else {
                        lblFechaIda_PantallaInicial.setForeground(Color.RED);
                    }
                }
                else{
                    lblFechaIda_PantallaInicial.setForeground(Color.RED);
                }
                if(lblOrigen_PantallaInicial.getForeground().equals(Color.BLACK) && lblDestino_PantallaInicial.getForeground().equals(Color.BLACK) && lblFechaIda_PantallaInicial.getForeground().equals(Color.BLACK)){
                    Avion avion  = new Avion(origen, destino, fecha);
                    String context = "/findAvion";
                    Client cliente = new Client();
                    boolean mensajeBuscarVuelo=cliente.sendMessage_Avion(context, avion);
                    if(mensajeBuscarVuelo==true){
                        String context1 = "/ListaVuelos";
                        Client cliente1 = new Client();
                        List<Avion> vuelos = cliente.buscarVuelos(context1, avion);
                        // Inicializamos la matriz con el tamaño adecuado
                        String[] nombresColumnas_PantallaBilletesInicio = {"ID Vuelo", "Origen", "Destino", "Fecha", "Hora", "Duración", "Asientos"};
                        Object[][] datosVuelo_PantallaBilletesInicio;
                        datosVuelo_PantallaBilletesInicio= new Object[vuelos.size()][7]; // 7 columnas en el formato que necesitamos
                        // Recorremos la lista de aviones y llenamos la matriz
                        for (int i = 0; i < vuelos.size(); i++) {
                            Avion avion_i = vuelos.get(i);
                            datosVuelo_PantallaBilletesInicio[i][0] = avion_i.getId().toString(); // Código del vuelo
                            datosVuelo_PantallaBilletesInicio[i][1] = avion_i.getOrigen(); // Origen
                            datosVuelo_PantallaBilletesInicio[i][2] = avion_i.getDestino(); // Destino
                            datosVuelo_PantallaBilletesInicio[i][3] = avion_i.getFecha(); // Fecha
                            datosVuelo_PantallaBilletesInicio[i][4] = avion_i.getHora_salida(); // Hora de salida
                            datosVuelo_PantallaBilletesInicio[i][5] = avion_i.getDuracion(); // Duración
                            datosVuelo_PantallaBilletesInicio[i][6] = avion_i.getAsientos_disp().toString() + "/" + avion_i.getAsientos_tot().toString(); // Asientos disponibles/totales
                        }

                        pnlSecundario_PantallaBilletesInicio=new JPanel();
                        pnlSecundario_PantallaBilletesInicio.setBackground(Color.BLACK);
                        pnlSecundario_PantallaBilletesInicio.setBounds(tamOrig,7*tamOrig,ancho-2*tamOrig,alto-20*tamOrig);
                        pnlSecundario_PantallaBilletesInicio.setLayout(new BorderLayout());

                        modelotablaVuelos_PantallaBilletesInicio = new DefaultTableModel(datosVuelo_PantallaBilletesInicio, nombresColumnas_PantallaBilletesInicio);
                        tablaVuelos_PantallaBilletesInicio = new JTable(modelotablaVuelos_PantallaBilletesInicio);
                        tablaVuelos_PantallaBilletesInicio.setRowHeight(50);
                        JTableHeader header = tablaVuelos_PantallaBilletesInicio.getTableHeader();
                        header.setPreferredSize(new Dimension(header.getWidth(), 50));
                        Font fontCeldas = new Font("Arial", Font.PLAIN, 19); // Cambia "Arial", "PLAIN" y tamaño según lo necesites
                        tablaVuelos_PantallaBilletesInicio.setFont(fontCeldas);
                        Font fontEncabezado = new Font("Arial", Font.BOLD, 20);
                        tablaVuelos_PantallaBilletesInicio.getTableHeader().setFont(fontEncabezado);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(0).setPreferredWidth(10);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(1).setPreferredWidth(230);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(2).setPreferredWidth(230);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(3).setPreferredWidth(40);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(4).setPreferredWidth(10);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(5).setPreferredWidth(10);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(6).setPreferredWidth(10);
                        scrollPaneTabla_PantallaBilletesInicio = new JScrollPane(tablaVuelos_PantallaBilletesInicio);
                        scrollPaneTabla_PantallaBilletesInicio.setPreferredSize(new Dimension(ancho, alto-11*tamOrig));

                        pnlSecundario_PantallaBilletesInicio.add(scrollPaneTabla_PantallaBilletesInicio, BorderLayout.CENTER);

                        pnlMostrarBilletesInicio.add(pnlSecundario_PantallaBilletesInicio);

                        comboBoxCiudadesOrigen_PantallaInicial.setSelectedItem("");
                        comboBoxCiudadesDestino_PantallaInicial.setSelectedItem("");
                        txtFechaIda_PantallaInicial.setText("");
                        txtIDIda_PantallaInicial.setText("");
                        lblNoHayVuelosDisponiblesID_PantallaBilletesInicio.setVisible(false);
                        lblNoHayVuelosDisponibles_PantallaBilletesInicio.setVisible(false);

                        pnlPantallaInicial.setVisible(false);
                        pnlMostrarBilletesInicio.setVisible(true);
                    }
                    else{
                        lblNoHayVuelosDisponiblesID_PantallaBilletesInicio.setVisible(false);
                        lblNoHayVuelosDisponibles_PantallaBilletesInicio.setVisible(true);
                    }
                }
            }
            if(e.getSource()== btnBuscarID_PantallaInicial){
                Pattern pattern = Pattern.compile("^ICAIR(\\d+)$");
                Matcher matcher = pattern.matcher(id);

                // Verificar si el texto coincide con el patrón
                if (matcher.matches()) {
                    lblIDIda_PantallaInicial.setForeground(Color.BLACK);
                }
                else {
                    lblIDIda_PantallaInicial.setForeground(Color.RED);
                }
                if(lblIDIda_PantallaInicial.getForeground().equals(Color.BLACK)) {
                    Avion avion = new Avion(Integer.parseInt(matcher.group(1)));
                    String context = "/findAvionID";
                    Client cliente = new Client();
                    boolean mensajeBuscarVuelo = cliente.sendMessage_AvionID(context, avion);
                    if (mensajeBuscarVuelo == true) {
                        String context1 = "/ListaVuelosID";
                        Client cliente1 = new Client();
                        List<Avion> vuelos = cliente.buscarVuelosID(context1, avion);
                        // Inicializamos la matriz con el tamaño adecuado
                        String[] nombresColumnas_PantallaBilletesInicio = {"ID Vuelo", "Origen", "Destino", "Fecha", "Hora", "Duración", "Asientos"};
                        Object[][] datosVuelo_PantallaBilletesInicio;
                        datosVuelo_PantallaBilletesInicio = new Object[vuelos.size()][7]; // 7 columnas en el formato que necesitamos
                        // Recorremos la lista de aviones y llenamos la matriz
                        for (int i = 0; i < vuelos.size(); i++) {
                            Avion avion_i = vuelos.get(i);
                            datosVuelo_PantallaBilletesInicio[i][0] = avion_i.getId().toString(); // Código del vuelo
                            datosVuelo_PantallaBilletesInicio[i][1] = avion_i.getOrigen(); // Origen
                            datosVuelo_PantallaBilletesInicio[i][2] = avion_i.getDestino(); // Destino
                            datosVuelo_PantallaBilletesInicio[i][3] = avion_i.getFecha(); // Fecha
                            datosVuelo_PantallaBilletesInicio[i][4] = avion_i.getHora_salida(); // Hora de salida
                            datosVuelo_PantallaBilletesInicio[i][5] = avion_i.getDuracion(); // Duración
                            datosVuelo_PantallaBilletesInicio[i][6] = avion_i.getAsientos_disp().toString() + "/" + avion_i.getAsientos_tot().toString(); // Asientos disponibles/totales
                        }

                        pnlSecundario_PantallaBilletesInicio = new JPanel();
                        pnlSecundario_PantallaBilletesInicio.setBackground(Color.BLACK);
                        pnlSecundario_PantallaBilletesInicio.setBounds(tamOrig, 7 * tamOrig, ancho - 2 * tamOrig, alto - 20 * tamOrig);
                        pnlSecundario_PantallaBilletesInicio.setLayout(new BorderLayout());

                        modelotablaVuelos_PantallaBilletesInicio = new DefaultTableModel(datosVuelo_PantallaBilletesInicio, nombresColumnas_PantallaBilletesInicio);
                        tablaVuelos_PantallaBilletesInicio = new JTable(modelotablaVuelos_PantallaBilletesInicio);
                        tablaVuelos_PantallaBilletesInicio.setRowHeight(50);
                        JTableHeader header = tablaVuelos_PantallaBilletesInicio.getTableHeader();
                        header.setPreferredSize(new Dimension(header.getWidth(), 50));
                        Font fontCeldas = new Font("Arial", Font.PLAIN, 19); // Cambia "Arial", "PLAIN" y tamaño según lo necesites
                        tablaVuelos_PantallaBilletesInicio.setFont(fontCeldas);
                        Font fontEncabezado = new Font("Arial", Font.BOLD, 20);
                        tablaVuelos_PantallaBilletesInicio.getTableHeader().setFont(fontEncabezado);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(0).setPreferredWidth(10);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(1).setPreferredWidth(230);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(2).setPreferredWidth(230);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(3).setPreferredWidth(40);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(4).setPreferredWidth(10);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(5).setPreferredWidth(10);
                        tablaVuelos_PantallaBilletesInicio.getColumnModel().getColumn(6).setPreferredWidth(10);
                        scrollPaneTabla_PantallaBilletesInicio = new JScrollPane(tablaVuelos_PantallaBilletesInicio);
                        scrollPaneTabla_PantallaBilletesInicio.setPreferredSize(new Dimension(ancho, alto - 11 * tamOrig));

                        pnlSecundario_PantallaBilletesInicio.add(scrollPaneTabla_PantallaBilletesInicio, BorderLayout.CENTER);

                        pnlMostrarBilletesInicio.add(pnlSecundario_PantallaBilletesInicio);

                        comboBoxCiudadesOrigen_PantallaInicial.setSelectedItem("");
                        comboBoxCiudadesDestino_PantallaInicial.setSelectedItem("");
                        txtFechaIda_PantallaInicial.setText("");
                        txtIDIda_PantallaInicial.setText("");
                        lblNoHayVuelosDisponiblesID_PantallaBilletesInicio.setVisible(false);
                        lblNoHayVuelosDisponibles_PantallaBilletesInicio.setVisible(false);

                        pnlPantallaInicial.setVisible(false);
                        pnlMostrarBilletesInicio.setVisible(true);
                    }
                    else {
                        lblNoHayVuelosDisponibles_PantallaBilletesInicio.setVisible(false);
                        lblNoHayVuelosDisponiblesID_PantallaBilletesInicio.setVisible(true);
                    }
                }
            }

        }
    }
    private class eventoBotonesPantallaBilletesInicio implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnVolverPantallaInicio_PantallaBilletesInicio) {
                pnlMostrarBilletesInicio.remove(pnlSecundario_PantallaBilletesInicio);
                pnlMostrarBilletesInicio.setVisible(false);
                pnlPantallaInicial.setVisible(true);
            }
        }
    }
    private class eventoBotonesPanelRegistro implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String Nombre= txtNombre_PantallaRegistro.getText().trim();
            String Apellido1= txtApellido1_PantallaRegistro.getText().trim();
            String Apellido2= txtApellido2_PantallaRegistro.getText().trim();
            String FechaNacimiento_String= txtFechaNacimiento_PantallaRegistro.getText().trim();
            Date FechaNacimiento_Date=fechaNacimiento_PantallaRegistro;
            String Nacionalidad= comboBoxNacionalidades_PantallaRegistro.getSelectedItem().toString().trim();
            String CorreoElectronico= txtCorreoElectronico_PantallaRegistro.getText().trim();
            String Contraseña1= txtContraseña1_PantallaRegistro.getText().trim();
            String Contraseña2= txtContraseña2_PantallaRegistro.getText().trim();

            if(e.getSource()== btnVolverPantallaInicio_PantallaRegistro){
                pnlPantallaRegistro.setVisible(false);
                pnlPantallaInicial.setVisible(true);
            }
            if(e.getSource()== btnRegistrarse_PantallaRegistro) {
                lblContraseñasDistintas.setVisible(false);
                lblUsuarioExistente.setVisible(false);
                lblUsuarioRegistradoConExito.setVisible(false);

                if(Nombre.isEmpty()){
                    lblNombre_PantallaRegistro.setForeground(Color.RED);
                }
                else{
                    lblNombre_PantallaRegistro.setForeground(Color.BLACK);
                }
                if(Apellido1.isEmpty()){
                    lblApellido1_PantallaRegistro.setForeground(Color.RED);
                }
                else{
                    lblApellido1_PantallaRegistro.setForeground(Color.BLACK);
                }
                if(Apellido2.isEmpty()){
                    lblApellido2_PantallaRegistro.setForeground(Color.RED);
                }
                else{
                    lblApellido2_PantallaRegistro.setForeground(Color.BLACK);
                }
                if(FechaNacimiento_String.length()==10) {
                    if (esEntero(FechaNacimiento_String.substring(0, 2)) == true && esEntero(FechaNacimiento_String.substring(3, 5)) == true && esEntero(FechaNacimiento_String.substring(6, 10)) == true && FechaNacimiento_String.substring(2, 3).equals("/")
                            && FechaNacimiento_String.substring(5, 6).equals("/")) {
                        lblFechaNacimiento_PantallaRegistro.setForeground(Color.BLACK);
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            fechaNacimiento_PantallaRegistro = formatoFecha.parse(FechaNacimiento_String);
                            lblFechaNacimiento_PantallaRegistro.setForeground(Color.BLACK);
                        } catch (ParseException ex) {
                            lblFechaNacimiento_PantallaRegistro.setForeground(Color.RED);
                        }
                    } else {
                        lblFechaNacimiento_PantallaRegistro.setForeground(Color.RED);
                    }
                }
                else{
                    lblFechaNacimiento_PantallaRegistro.setForeground(Color.RED);
                }
                if(Nacionalidad.isEmpty()){
                    lblNacionalidad_PantallaRegistro.setForeground(Color.RED);
                }
                else{
                    lblNacionalidad_PantallaRegistro.setForeground(Color.BLACK);
                }
                if(CorreoElectronico.isEmpty()){
                    lblCorreoElectronico_PantallaRegistro.setForeground(Color.RED);
                }
                else{
                    if (isValidEmail(CorreoElectronico)) {
                        lblCorreoElectronico_PantallaRegistro.setForeground(Color.BLACK);
                    } else {
                        lblCorreoElectronico_PantallaRegistro.setForeground(Color.RED);
                    }

                }
                if(Contraseña1.isEmpty()){
                    lblContraseña1_PantallaRegistro.setForeground(Color.RED);
                }
                else{
                    lblContraseña1_PantallaRegistro.setForeground(Color.BLACK);
                }
                if(Contraseña2.isEmpty()){
                    lblContraseña2_PantallaRegistro.setForeground(Color.RED);
                }
                else{
                    lblContraseña2_PantallaRegistro.setForeground(Color.BLACK);
                }
                if(!Contraseña1.equals(Contraseña2)){
                    lblContraseña1_PantallaRegistro.setForeground(Color.RED);
                    lblContraseña2_PantallaRegistro.setForeground(Color.RED);
                    lblContraseñasDistintas.setVisible(true);
                    lblUsuarioExistente.setVisible(false);
                    lblUsuarioRegistradoConExito.setVisible(false);
                }
                /* CONDICION DE QUE SI EL CORREO YA ESTAREGISTRADO
                if(){
                    lblCorreoElectronico_PantallaRegistro.setForeground(Color.RED);
                    lblContraseñasDistintas.setVisible(false);
                    lblUsuarioExistente.setVisible(true);
                    lblUsuarioRegistradoConExito.setVisible(false);
                }
                */
                if(lblNombre_PantallaRegistro.getForeground()==Color.BLACK && lblApellido1_PantallaRegistro.getForeground()==Color.BLACK && lblApellido2_PantallaRegistro.getForeground()==Color.BLACK && lblFechaNacimiento_PantallaRegistro.getForeground()==Color.BLACK &&
                        lblNacionalidad_PantallaRegistro.getForeground()==Color.BLACK && lblCorreoElectronico_PantallaRegistro.getForeground()==Color.BLACK && lblContraseña1_PantallaRegistro.getForeground()==Color.BLACK && lblContraseña2_PantallaRegistro.getForeground()==Color.BLACK){
                    /*
                    txtNombre_PantallaRegistro.setText("");
                    txtApellido1_PantallaRegistro.setText("");
                    txtApellido2_PantallaRegistro.setText("");
                    txtFechaNacimiento_PantallaRegistro.setText("");
                    comboBoxNacionalidades_PantallaRegistro.setSelectedItem("");
                    txtCorreoElectronico_PantallaRegistro.setText("");
                    txtContraseña1_PantallaRegistro.setText("");
                    txtContraseña2_PantallaRegistro.setText("");
                    */
                    User usuario = new User(Nombre, Apellido1, Apellido2, FechaNacimiento_String, Nacionalidad, CorreoElectronico, Contraseña1);
                    String context = "/regUser";
                    Client cliente = new Client();
                    boolean mensajeRegistro=cliente.sendMessage_User(context, usuario);
                    System.out.println(mensajeRegistro);
                    if(mensajeRegistro== true){
                        lblContraseñasDistintas.setVisible(false);
                        lblUsuarioExistente.setVisible(false);
                        lblUsuarioRegistradoConExito.setVisible(true);
                    }
                    else{
                        lblContraseñasDistintas.setVisible(false);
                        lblUsuarioExistente.setVisible(true);
                        lblUsuarioRegistradoConExito.setVisible(false);
                    }
                }
            }
        }
    }
    private class eventoBotonesPanelInicioSesion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String CorreoElectronico= txtCorreoElectronico_PantallaInicioSesion.getText().trim();
            String Contraseña= txtContraseña_PantallaInicioSesion.getText().trim();
            if (e.getSource() == btnVolverPantallaInicio_PantallaInicioSesion) {
                pnlPantallaInicioSesion.setVisible(false);
                pnlPantallaInicial.setVisible(true);
            }
            if(e.getSource()== btnIniciarSesion_PantallaInicioSesion){
                String correo= txtCorreoElectronico_PantallaInicioSesion.getText().trim();
                String contraseña= txtContraseña_PantallaInicioSesion.getText().trim();
                User usuario = new User(CorreoElectronico, Contraseña);
                String context = "/logUser";
                Client cliente = new Client();
                boolean mensajeLogin=cliente.sendMessage_User(context, usuario);
                if(mensajeLogin==true){
                    String context1 = "/getLoggedUser";
                    Client cliente1 = new Client();
                    User usuarioIniciado = cliente.buscarUsuarioIniciado(context1, usuario);

                    lblNombreUsuarioIniciado_PantallaOpciones =new JLabel(usuarioIniciado.getNombre()+" "+usuarioIniciado.getApellido1()+" "+usuarioIniciado.getApellido2());
                    lblNombreUsuarioIniciado_PantallaOpciones.setFont(new Font("Arial",Font.BOLD,30));
                    lblNombreUsuarioIniciado_PantallaOpciones.setBounds(ancho-950, tamOrig, 25*tamOrig, 4*tamOrig);
                    pnlOpcionesSesionIniciada.add(lblNombreUsuarioIniciado_PantallaOpciones);

                    if(usuarioIniciado.getPremium()==false){
                        btnSuscribirse_PantallaOpciones =new JButton("Suscribirse");
                        btnSuscribirse_PantallaOpciones.setBackground(Color.WHITE);
                        btnSuscribirse_PantallaOpciones.setFont(new Font("Arial",Font.BOLD,30));
                        btnSuscribirse_PantallaOpciones.setBounds(25*tamOrig, 25*tamOrig, 25*tamOrig, 5*tamOrig);
                        btnSuscribirse_PantallaOpciones.addActionListener(new eventoBotonesPanelOpcionesSesionIniciada());
                        pnlOpcionesSesionIniciada.add(btnSuscribirse_PantallaOpciones);
                    }
                    else{
                        btnCancelarSuscripcion_PantallaOpciones =new JButton("Cancelar suscribcion");
                        btnCancelarSuscripcion_PantallaOpciones.setBackground(Color.WHITE);
                        btnCancelarSuscripcion_PantallaOpciones.setFont(new Font("Arial",Font.BOLD,30));
                        btnCancelarSuscripcion_PantallaOpciones.setBounds(25*tamOrig, 25*tamOrig, 25*tamOrig, 5*tamOrig);
                        btnCancelarSuscripcion_PantallaOpciones.addActionListener(new eventoBotonesPanelOpcionesSesionIniciada());
                        pnlOpcionesSesionIniciada.add(btnCancelarSuscripcion_PantallaOpciones);
                    }

                    pnlPantallaInicioSesion.setVisible(false);
                    pnlOpcionesSesionIniciada.setVisible(true);
                    lblLoginFallido_PantallaInicioSesion.setVisible(false);

                }
                else{
                    lblLoginFallido_PantallaInicioSesion.setVisible(true);
                }
            }
        }
    }
    private class eventoBotonesPanelOpcionesSesionIniciada implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCerrarSesion_PantallaOpciones) {
                pnlOpcionesSesionIniciada.setVisible(false);
                pnlPantallaInicial.setVisible(true);
            }
            if (e.getSource() == btnComprarBilletes_PantallaOpciones){
                pnlOpcionesSesionIniciada.setVisible(false);
                pnlPantallaBusquedaBilletesSinPremium.setVisible(true);
            }
            if (e.getSource() == btnAñadirDatosBancarios_PantallaOpciones){
                pnlOpcionesSesionIniciada.setVisible(false);
                pnlAñadirDatosBancarios.setVisible(true);
            }
        }
    }
    private class eventoBotonesPanelAñadirDatosBancarios implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnVolverOpciones_PantallaDatosBancarios) {
                pnlAñadirDatosBancarios.setVisible(false);
                pnlOpcionesSesionIniciada.setVisible(true);
                lblDatosBancariosRegistradosConExito.setVisible(false);
            }
            if (e.getSource() == btnAñadirDatosBancarios_PantallaDatosBancarios){
                String NumeroTarjeta= txtNumeroTarjeta_PantallaDatosBancarios.getText().trim();
                String FechaCaducidad= txtFechaCaducidad_PantallaDatosBancarios.getText().trim();
                String CVC= txtCVC_PantallaDatosBancarios.getText().trim();
                if (NumeroTarjeta.length()==19) {
                    if(esEntero(NumeroTarjeta.substring(0,4))== true && esEntero(NumeroTarjeta.substring(5,9))==true && esEntero(NumeroTarjeta.substring(10,14))==true && esEntero(NumeroTarjeta.substring(15,19))==true
                            && NumeroTarjeta.substring(4,5).equals("-") && NumeroTarjeta.substring(9,10).equals("-") && NumeroTarjeta.substring(14,15).equals("-")){
                        lblNumeroTarjeta_PantallaDatosBancarios.setForeground(Color.BLACK);
                    }
                    else{
                        lblNumeroTarjeta_PantallaDatosBancarios.setForeground(Color.RED);
                    }
                }
                else {
                    lblNumeroTarjeta_PantallaDatosBancarios.setForeground(Color.RED);
                }
                if(FechaCaducidad.length()==7) {
                    if (esEntero(FechaCaducidad.substring(0, 2)) == true && esEntero(FechaCaducidad.substring(3, 7)) == true && FechaCaducidad.substring(2, 3).equals("/")) {
                        lblFechaCaducidad_PantallaDatosBancarios.setForeground(Color.BLACK);
                        SimpleDateFormat formatoFecha2 = new SimpleDateFormat("MM/yyyy");
                        try {
                            fechaCaducidadtarjeta_PantallaDatosBancarios = formatoFecha2.parse(FechaCaducidad);
                            lblFechaCaducidad_PantallaDatosBancarios.setForeground(Color.BLACK);
                        } catch (ParseException ex) {
                            lblFechaCaducidad_PantallaDatosBancarios.setForeground(Color.RED);
                        }
                    } else {
                        lblFechaCaducidad_PantallaDatosBancarios.setForeground(Color.RED);
                    }
                }
                else{
                    lblFechaCaducidad_PantallaDatosBancarios.setForeground(Color.RED);
                }
                if(CVC.length()==3){
                    if (esEntero(CVC.substring(0,3))==true) {
                        lblCVC_PantallaDatosBancarios.setForeground(Color.BLACK);
                    } else {
                        lblCVC_PantallaDatosBancarios.setForeground(Color.RED);
                    }
                }
                else{
                    lblCVC_PantallaDatosBancarios.setForeground(Color.RED);
                }
                if(lblNumeroTarjeta_PantallaDatosBancarios.getForeground()==Color.BLACK && lblFechaCaducidad_PantallaDatosBancarios.getForeground()==Color.BLACK && lblCVC_PantallaDatosBancarios.getForeground()==Color.BLACK) {
                    lblNumeroTarjeta_PantallaDatosBancarios.setForeground(Color.BLACK);
                    lblFechaCaducidad_PantallaDatosBancarios.setForeground(Color.BLACK);
                    lblCVC_PantallaDatosBancarios.setForeground(Color.BLACK);
                    lblDatosBancariosRegistradosConExito.setVisible(true);
                }
            }
        }
    }
    private class eventoBotonesPanelSesionIniciada implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnVolverPantallaOpciones_PantallaBBNoPremium) {
                pnlPantallaBusquedaBilletesSinPremium.setVisible(false);
                pnlOpcionesSesionIniciada.setVisible(true);
            }
        }
    }

    private void updateComboBox_Countries(List<String> countries) {
        //comboBoxModel.removeAllElements();
        for (String country : countries) {
            comboBoxModel_PantallaRegistro.addElement(country);
        }
    }
    private void updateComboBoxOrigen_Cities(List<String> cities) {
        //comboBoxModel.removeAllElements();
        for (String city : cities) {
            comboBoxModelOrigen_PantallaInicial.addElement(city);
        }
    }
    private void updateComboBoxDestino_Cities(List<String> cities) {
        //comboBoxModel.removeAllElements();
        for (String city : cities) {
            comboBoxModelDestino_PantallaInicial.addElement(city);
        }
    }
    public static boolean esEntero(String str) {
        try {
            Integer.parseInt(str); // Intenta convertir el String a un int
            return true;           // La conversión fue exitosa, así que es un int
        } catch (NumberFormatException e) {
            return false;          // Ocurrió una excepción, así que no es un int
        }
    }
    public static boolean isValidEmail(String email) {
        // Expresión regular para validar el formato del correo electrónico
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
