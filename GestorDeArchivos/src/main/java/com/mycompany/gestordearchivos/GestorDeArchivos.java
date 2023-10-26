/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.gestordearchivos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Jorge Wang Wang
 */
public class GestorDeArchivos {

    public static void main(String[] args) {
        miVentana v = new miVentana();
        centro botones = new centro(v);
        v.setVisible(true);
    }
}

class miVentana extends JFrame {

    public miVentana() {

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int anchura = (int) (0.5 * (int) screenSize.getWidth());
        int altura = (int) ((int) screenSize.getHeight() * 0.5);
        int posicionx = (int) ((int) screenSize.getHeight() * 0.5);
        int posiciony = (int) ((int) screenSize.getWidth() * 0.1);

        setIconImage(new ImageIcon("Icono.jpg").getImage());
        setBounds(posicionx, posiciony, anchura, altura);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel pan = new panel();
         centro botones = new centro(this);
        pan.add(botones, BorderLayout.CENTER);
        add(pan);
        setTitle("Gestor de Archivos");
    }
}

class panel extends JPanel {

    public panel() {
        setLayout(new BorderLayout());
       
        JLabel titulo = new JLabel("Gestor de Archivos");
        Font timesNewRomanBold = new Font("Times New Roman", Font.BOLD, 50);
        titulo.setFont(timesNewRomanBold);
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelNorte.add(titulo);
        add(panelNorte, BorderLayout.NORTH);
        sur panelSur = new sur();
        add(panelSur, BorderLayout.SOUTH);
    }
}

class centro extends JPanel {
    private int dato;
    private JFrame ventanaLeer;
    private JFrame ventanaPrincipal;
    public centro(JFrame ventanaPrincipal) {
        this.ventanaPrincipal=ventanaPrincipal;
        //para que el FileChooser y los JOptionPane tenga la aparecencia de Windows.
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ventanaLeer = new JFrame();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int anchura = (int) (0.5 * (int) screenSize.getWidth());
        int altura = (int) ((int) screenSize.getHeight() * 0.5);
        int posicionx = (int) ((int) screenSize.getHeight() * 0.5);
        int posiciony = (int) ((int) screenSize.getWidth() * 0.1);
        ventanaLeer.setTitle("Gestor de Archivos");
        ventanaLeer.setIconImage(new ImageIcon("Icono.jpg").getImage());
        ventanaLeer.setBounds(posicionx, posiciony, anchura, altura);
        ventanaLeer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelLeer = new JPanel(new BorderLayout());
        JTextArea ta = new JTextArea();
        JScrollPane jp = new JScrollPane(ta);
        panelLeer.add(jp, BorderLayout.CENTER);
        
        JLabel tituloLeer = new JLabel("Titulo");
        Font timesNewRomanBold = new Font("Times New Roman", Font.BOLD, 50);
        tituloLeer.setFont(timesNewRomanBold);
        JPanel panelTituloLeer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTituloLeer.add(tituloLeer);
        
        JButton volverLeer = new JButton("Volver a Menu");
        JButton OtroArchivoLeer = new JButton("Seleccionar Otro Archivo");
        JLabel archivoActualLeer = new JLabel("Nombre Archivo");
        JButton Confirmar = new JButton("Confirmar");
        JPanel abajoLeer = new JPanel(new FlowLayout(FlowLayout.CENTER));

        abajoLeer.add(volverLeer);
        abajoLeer.add(OtroArchivoLeer);
        abajoLeer.add(archivoActualLeer);
        abajoLeer.add(Confirmar);

        panelLeer.add(panelTituloLeer, BorderLayout.NORTH);
        panelLeer.add(abajoLeer, BorderLayout.SOUTH);
        ventanaLeer.add(panelLeer);

        BotonPersonalizado bPermisos = new BotonPersonalizado("Permisos");
        BotonPersonalizado bCopiar = new BotonPersonalizado("Copiar");
        BotonPersonalizado bCrear = new BotonPersonalizado("Crear Fichero");
        BotonPersonalizado bBorrar = new BotonPersonalizado("Borrar");
        BotonPersonalizado bExtension = new BotonPersonalizado("Extensiones");
        BotonPersonalizado bLeer = new BotonPersonalizado("Leer Ficheros");
        BotonPersonalizado bEscribir = new BotonPersonalizado("Escribir Fichero");
        BotonPersonalizado bRuta = new BotonPersonalizado("Ruta Fichero");
        BotonPersonalizado bListadoD = new BotonPersonalizado("Listado Directorios");
        setLayout(new GridLayout(3, 3, 10, 5));

        ImageIcon iconoPermisos = new ImageIcon("Permisos.png");
        bPermisos.setIcon(iconoPermisos);

        ImageIcon iconoCopiar = new ImageIcon("copiar.png");
        bCopiar.setIcon(iconoCopiar);

        ImageIcon iconoCrear = new ImageIcon("crear.png");
        bCrear.setIcon(iconoCrear);

        ImageIcon iconoBorrar = new ImageIcon("papelera.png");
        bBorrar.setIcon(iconoBorrar);

        ImageIcon iconoExtension = new ImageIcon("extension.png");
        bExtension.setIcon(iconoExtension);

        ImageIcon iconoLeer = new ImageIcon("leer.png");
        bLeer.setIcon(iconoLeer);

        ImageIcon iconoEscribir = new ImageIcon("escribir.png");
        bEscribir.setIcon(iconoEscribir);

        ImageIcon iconoRuta = new ImageIcon("ruta.png");
        bRuta.setIcon(iconoRuta);

        ImageIcon iconoLista = new ImageIcon("lista.png");
        bListadoD.setIcon(iconoLista);

        bPermisos.setIconTextGap(5);
        add(bPermisos);
        add(bCopiar);
        add(bCrear);
        add(bBorrar);
        add(bExtension);
        add(bLeer);
        add(bEscribir);
        add(bRuta);
        add(bListadoD);
        bPermisos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] menu = {"Fichero", "Directorios"};
                int elegir = JOptionPane.showOptionDialog(null, "Seleccione algo del menu", "Permisos", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, menu, menu[0]);
                switch (elegir) {
                    case 0:
                        boolean bucleArchivoPermiso = true;
                        JFileChooser jfArchivos = new JFileChooser();
                        int cancelar = jfArchivos.showOpenDialog(null);
                        if (cancelar != JFileChooser.CANCEL_OPTION) {
                            File archivoPermiso = jfArchivos.getSelectedFile();
                            while (bucleArchivoPermiso) {
                                bucleArchivoPermiso = MenuPermisos(archivoPermiso, bucleArchivoPermiso);
                            }
                        }
                        break;
                    case 1:
                        boolean bucleDirectorioPermiso = true;
                        JFileChooser jfDirectorio = new JFileChooser();
                        jfDirectorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        int cancenlar = jfDirectorio.showOpenDialog(null);
                        if (cancenlar != JFileChooser.CANCEL_OPTION) {
                            File directorioPermiso = jfDirectorio.getSelectedFile();
                            while (bucleDirectorioPermiso) {
                                bucleDirectorioPermiso = MenuPermisos(directorioPermiso, bucleDirectorioPermiso);
                            }
                        }
                        break;
                }
            }
        });
        bCopiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CopiarArchivo();
            }
        });
        bCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearFichero();
            }
        });
        bBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarArchivo();
            }
        });
        bExtension.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                extensiones();
            }
        });
        bLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dato=1;
                JFileChooser jf = new JFileChooser();
                int cancelar =jf.showOpenDialog(jf);
                if(cancelar != JFileChooser.CANCEL_OPTION){
                File f = jf.getSelectedFile();
                archivoActualLeer.setText(f.getAbsolutePath());
                LeerFichero(f,ta);
                ventanaLeer.setVisible(true);
                ventanaPrincipal.setVisible(false);
                ta.setEditable(false);
                }
            }
        });
        OtroArchivoLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                int cancelar =jf.showOpenDialog(jf);
                File f = jf.getSelectedFile();
                if(cancelar != JFileChooser.CANCEL_OPTION){
                if(dato==0){
                    int elegir = JOptionPane.showConfirmDialog(null, "Deseas guardar los cambios?");
                    if(elegir==0){
                        File guardar = new File(archivoActualLeer.getText());
                        EscribirFichero(guardar,ta);
                        LeerFichero(f,ta);
                        archivoActualLeer.setText(f.getAbsolutePath());
                    }else if(elegir==1){
                        LeerFichero(f,ta);
                        archivoActualLeer.setText(f.getAbsolutePath());
                    }
                }else{
                    LeerFichero(f,ta);
                    archivoActualLeer.setText(f.getAbsolutePath());
                }
                }
                
                

                
            }
        });
        bEscribir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dato=0;
                JFileChooser jf = new JFileChooser();
                int cancelar =jf.showOpenDialog(jf);
                if(cancelar != JFileChooser.CANCEL_OPTION){
                File f = jf.getSelectedFile();
                LeerFichero(f,ta);
                archivoActualLeer.setText(f.getAbsolutePath());
                ventanaLeer.setVisible(true);
                ventanaPrincipal.setVisible(false);
                ta.setEditable(true);
                }
            }
        });
        Confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dato==0){
                    confirmarEscribir(archivoActualLeer,ta);
                    ventanaLeer.setVisible(false);
                ventanaPrincipal.setVisible(true);
                }else{
                    ventanaLeer.setVisible(false);
                ventanaPrincipal.setVisible(true);
                }
                
            }

            
        });
        volverLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaLeer.setVisible(false);
                ventanaPrincipal.setVisible(true);
                
            }
        });
        bRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              JFileChooser jf = new JFileChooser();
              int cancelar =jf.showOpenDialog(jf);
              if(cancelar != JFileChooser.CANCEL_OPTION){
                  File f = jf.getSelectedFile();
                  JOptionPane.showMessageDialog(null, f.getAbsolutePath());
              }
            }
        });
        bListadoD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              JFileChooser jf = new JFileChooser();
              jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
              int cancelar =jf.showOpenDialog(jf);
              if(cancelar != JFileChooser.CANCEL_OPTION){
                  File f = jf.getSelectedFile();
                  File[] lista_Fichero = f.listFiles();
                  String datos = "";
                  for(File fi :lista_Fichero){
                      datos += fi.getName()+"\n";
                  }
                  JOptionPane.showMessageDialog(null, datos);
              }
            }
        });

    }
public void confirmarEscribir(JLabel archivoActualLeer,JTextArea ta) throws HeadlessException {
                
                    int preguntar = JOptionPane.showConfirmDialog(null, "Estas seguro que desea guardar los cambios");
                    if(preguntar==0){
                        File f =new File(archivoActualLeer.getText());
                        EscribirFichero(f,ta);
                        ventanaLeer.setVisible(false);
                        ventanaPrincipal.setVisible(true);
                    }
                
            }
    public static void EscribirFichero(File f, JTextArea ta){
        try {
            FileWriter fw = new FileWriter(f);
            String a = ta.getText();
            fw.write(a);
            fw.flush();
            fw.close();
               
        } catch (IOException ex) {
            Logger.getLogger(centro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void LeerFichero(File f, JTextArea ta){
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            String texto="";
            while((line=br.readLine())!=null){
                texto +=line +"\n";
            }
            ta.setText(texto);
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(centro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(centro.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    public static void borrarArchivo() {
        try {
            //lo mismo pero que en setFilesSelectionMode solo se puedan escoger directorios
            JFileChooser jfd = new JFileChooser();
            jfd.setCurrentDirectory(new File("D:\\"));
            jfd.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int i = jfd.showOpenDialog(jfd);

            File directorio = jfd.getSelectedFile();

            //comprobacion
            if (i == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "Cancelastes la seleccion de archivo");
            } else {
                if (directorio.isDirectory()) {
                    int confirmar2 = JOptionPane.showConfirmDialog(null, "Estas seguro de querer borrar este directorio?");
                    if (confirmar2 == 0) {
                        if (deleteDirectory(directorio)) {
                            JOptionPane.showMessageDialog(null, "El Directorio " + directorio.getName() + " Fue borrado con exito");

                        } else {
                            JOptionPane.showMessageDialog(null, "El directorio no tuvo exito o tuvo un error al borrar");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cancelastes el borrado de directorio");
                    }

                } else {
                    int opcion = JOptionPane.showConfirmDialog(null, "Estas seguro que quieres borrar este archivo?");
                    if (opcion == 0) {
                        directorio.delete();
                        JOptionPane.showMessageDialog(null, "Borrastes el archivo "+directorio.getName());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cancelastes el borrado");
                    }

                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cancelastes directorio");
        }
    }

    public static boolean deleteDirectory(File directory) {
        //comprueba que el fichero es un directorio
        if (directory.isDirectory()) {
            //crea un array de los ficheros que contengan el directorio
            File[] files = directory.listFiles();
            //si file no es nulo.
            if (files != null) {
                for (File file : files) {//recorre la lista
                    if (file.isDirectory()) {
                        //hacemos recursividad en caso de que el directorio contengan mas directorios
                        deleteDirectory(file);
                    } else {//sino borra el directorio
                        file.delete();
                    }
                }
            }
        }
        return directory.delete();//borra el directorio final
    }

    public static void crearFichero() {
        try {
            JFileChooser jf = new JFileChooser();
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            JOptionPane.showMessageDialog(null, "Seleccione el directorio primero");
            int cancelar = jf.showOpenDialog(jf);
            if (cancelar != JFileChooser.CANCEL_OPTION) {
                String nombreArchivo = JOptionPane.showInputDialog("Seleccione el nombre del archivo y su extension");
                String vacio = nombreArchivo.trim();
                if (vacio != "") {
                    File f = new File(jf.getSelectedFile().getAbsoluteFile() + "\\" + nombreArchivo);
                    if (!f.exists()) {
                        try {
                            f.createNewFile();
                        } catch (IOException ex) {

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya existe el archivo");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error el nombre de archivo que pusistes esta vacio");
                }
            }
        } catch (NullPointerException e) {

        }
    }

    public static void extensiones() {
        try {
            JFileChooser jf2 = new JFileChooser();
            jf2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int seleccionador = jf2.showOpenDialog(null);
            if (seleccionador == JFileChooser.APPROVE_OPTION) {
                String extension = JOptionPane.showInputDialog("Escriba el extension sin el punto");
                extension = extension.trim();
                if (!"".equals(extension)) {
                    extension = extension.toLowerCase();
                    File listaFicheros = jf2.getSelectedFile();
                    File[] listeri = listaFicheros.listFiles();
                    String listaextensible = "Esta lista con la extension ." + extension + " es:\n";
                    int contador = 0;
                    for (File f : listeri) {
                        if (f.getName().endsWith("." + extension)) {
                            listaextensible += f.getName() + "\n";
                            contador++;
                        }
                    }
                    if (contador == 0) {
                        JOptionPane.showMessageDialog(null, "Error no hay ningun archivo con la extension ." + extension);
                    } else {
                        JOptionPane.showMessageDialog(null, listaextensible);
                    }
                }
            }
        } catch (NullPointerException e) {

        }
    }

    public static boolean MenuPermisos(File fichero, boolean bucles) throws HeadlessException {
        String[] menupermisos = {"Permisos lectura", "Permisos Escritura", "Permisos de ejecucion", "Volver"};
        int elegir2 = JOptionPane.showOptionDialog(null, "Seleccione lo que quiera analizar", "Permisos", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, menupermisos, menupermisos[0]);
        switch (elegir2) {
            case 0:
                if (fichero.canRead()) {
                    JOptionPane.showMessageDialog(null, "Si tiene el permiso de leer");
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene el permiso de leer");
                }
                break;
            case 1:
                if (fichero.canWrite()) {
                    JOptionPane.showMessageDialog(null, "Si tiene el permiso de escribir");
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene el permiso de escribir");
                }
                break;
            case 2:
                if (fichero.canExecute()) {
                    JOptionPane.showMessageDialog(null, "Si tiene el permiso de executar");
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene el permiso de executar");
                }
                break;
            case 3:
                bucles = false;
                break;
        }
        return bucles;
    }

    public static void CopiarArchivo() throws HeadlessException {
        //Pregunta si existe la primera ruta
        JFileChooser jf = new JFileChooser();
        int cancelar = jf.showOpenDialog(jf);
        File origen = jf.getSelectedFile();
        if (cancelar != JFileChooser.CANCEL_OPTION) {
            //Comprueba si existe el archivo sino se acaba el programa
            if (origen.exists()) {
                //pide la ruta del archivo 1
                JOptionPane.showMessageDialog(null, "Seleccione el Archivo o directorio donde quieras copiar");
                JFileChooser jf2 = new JFileChooser();
                jf2.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int cancelar2 = jf2.showOpenDialog(jf);
                File destino = jf2.getSelectedFile();
                if (cancelar2 != JFileChooser.CANCEL_OPTION) {
                    //comprueba que no sean el mismo archivo debido a que no tiene sentido copiar el archivo
                    if (origen.getAbsolutePath() != destino.getAbsolutePath()) {

                        //en caso que sea directorio
                        if (destino.isDirectory()) {

                            //crea una ruta con el nombre del archivo a crear
                            String nombreArchivo = destino.getAbsolutePath() + "\\" + origen.getName();
                            File fichero = new File(nombreArchivo);//lo crea
                            if (nombreArchivo != origen.getAbsolutePath()) {//comprueba que el directorio si tiene otro nombre
                                CopiarArchivo(origen, fichero);

                            } else {
                                JOptionPane.showMessageDialog(null, "Error ya existe un archivo con ese nombre en este directorio");
                            }
                        } else if (destino.isFile()) {//si es un fichero
                            //pide confirmacion para copiar
                            int confirmar = JOptionPane.showConfirmDialog(null, "Desea continuar con la operacion? Recuerda que el contenido del archivo sera borrado y sustituido");
                            if (confirmar == JOptionPane.YES_OPTION) {
                                CopiarArchivo(origen, destino);
                            } else {
                                JOptionPane.showMessageDialog(null, "Cancelastes el copiado de archivos");
                            }
                        } else {
                            CopiarArchivo(origen, destino);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error no se puede copiar y pegar el archivo en el mismo destino");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error no existe el archivo");
            }
        }
    }

    public static void CopiarArchivo(File origen, File destino) {
        try {
            Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            JOptionPane.showMessageDialog(null, "Copiastes con exito el archivo");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al copiar el archivo." + ex.getMessage());
        }
    }
}

class sur extends JPanel {

    public sur() {
        JLabel autor = new JLabel("Creado por Jorge Wang");
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(autor);
        Font timesNewRomanBold = new Font("Times New Roman", Font.ITALIC, 20);
        autor.setFont(timesNewRomanBold);
    }
}

class BotonPersonalizado extends JButton {

    public BotonPersonalizado(String texto) {
        super(texto);
        setContentAreaFilled(false); // Hace que el fondo del botón sea transparente
        setFocusPainted(false); // Elimina el borde de enfoque

        // Cambia el estilo de fuente y color del texto
        setFont(new Font("Arial", Font.BOLD, 18)); // Personaliza la fuente (nombre, estilo, tamaño)
        setForeground(Color.BLACK); // Color del texto

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Creamos un degradado lineal para el fondo del botón
        GradientPaint gradient = new GradientPaint(0, 0, new Color(41, 180, 197), width, height, new Color(128, 128, 255));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height); // Dibuja un rectángulo como fondo

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Dibuja un borde alrededor del botón
        g.setColor(new Color(96, 96, 192));
        int width = getWidth();
        int height = getHeight();
        g.drawRect(0, 0, width - 1, height - 1); // Dibuja un rectángulo como borde
    }
}
