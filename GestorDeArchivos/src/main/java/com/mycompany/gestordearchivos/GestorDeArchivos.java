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
        //creamos la ventana
        miVentana v = new miVentana();
        
        //llamamos el JFrame al panel centro
        centro botones = new centro(v);
        v.setVisible(true);//lo hacemos visible
    }
}

class miVentana extends JFrame {

    public miVentana() {
        //calculamos la anchura de la pantalla para adaptar el tamaño y la posicion
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int anchura = (int) (0.5 * (int) screenSize.getWidth());
        int altura = (int) ((int) screenSize.getHeight() * 0.5);
        int posicionx = (int) ((int) screenSize.getHeight() * 0.5);
        int posiciony = (int) ((int) screenSize.getWidth() * 0.1);
        
        //pone un icono a la ventana
        setIconImage(new ImageIcon("Icono.jpg").getImage());
        setBounds(posicionx, posiciony, anchura, altura);//pone el tamaño
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//cuando cierre el programa, se cierre el run de java
        panel pan = new panel();//creamos un panel con todo los layout
         centro botones = new centro(this);//llamamos centro
        pan.add(botones, BorderLayout.CENTER);//lo añadimos en el centro del layout
        add(pan);//lo añadimos al layout
        setTitle("Gestor de Archivos");//ponemos titulo al archivo
    }
}

class panel extends JPanel {

    public panel() {
        //le ponemos el layout de Border
        setLayout(new BorderLayout());
       
        //creamos un label
        JLabel titulo = new JLabel("Gestor de Archivos");
        Font timesNewRomanBold = new Font("Times New Roman", Font.BOLD, 50);//creamos un font
        titulo.setFont(timesNewRomanBold);//le ponemos font al label
        //creamos otro panel para que se centre el label
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelNorte.add(titulo);//lo añadimos
        //lo insertamos arriba del panel
        add(panelNorte, BorderLayout.NORTH);
        //creamos una instancia de la clase sur
        sur panelSur = new sur();
        //lo añadimos al de arriba
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
        //ponemos dimensiones a la ventana de escribir y leer
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int anchura = (int) (0.5 * (int) screenSize.getWidth());
        int altura = (int) ((int) screenSize.getHeight() * 0.5);
        int posicionx = (int) ((int) screenSize.getHeight() * 0.5);
        int posiciony = (int) ((int) screenSize.getWidth() * 0.1);
        //le ponemos titulo icono, posición y que al cerrar se acabe el java.
        ventanaLeer.setTitle("Gestor de Archivos");
        ventanaLeer.setIconImage(new ImageIcon("Icono.jpg").getImage());
        ventanaLeer.setBounds(posicionx, posiciony, anchura, altura);
        ventanaLeer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //creamos un layout para la ventanaLeer
        JPanel panelLeer = new JPanel(new BorderLayout());
        //creamos un textarea con su scrollPane. en caso de que supere el tamaño del textarea
        JTextArea ta = new JTextArea();
        JScrollPane jp = new JScrollPane(ta);
        
        //lo añadimos al centro
        panelLeer.add(jp, BorderLayout.CENTER);
        //le ponemos titulo con su fuente y lo añadimos en el centro con un FlowLayout
        JLabel tituloLeer = new JLabel("Titulo");
        Font timesNewRomanBold = new Font("Times New Roman", Font.BOLD, 50);
        tituloLeer.setFont(timesNewRomanBold);
        JPanel panelTituloLeer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTituloLeer.add(tituloLeer);
        //creamos los botones para modificar la ventana de escribir y leer.
        JButton volverLeer = new JButton("Volver a Menu");
        JButton OtroArchivoLeer = new JButton("Seleccionar Otro Archivo");
        JLabel archivoActualLeer = new JLabel("Nombre Archivo");
        JButton Confirmar = new JButton("Confirmar");
        JPanel abajoLeer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //añadimos todos los componentes.
        abajoLeer.add(volverLeer);
        abajoLeer.add(OtroArchivoLeer);
        abajoLeer.add(archivoActualLeer);
        abajoLeer.add(Confirmar);
        //añadimos el panel a la ventana
        panelLeer.add(panelTituloLeer, BorderLayout.NORTH);
        panelLeer.add(abajoLeer, BorderLayout.SOUTH);
        ventanaLeer.add(panelLeer);
        
        //creamos los botones del menu 
        BotonPersonalizado bPermisos = new BotonPersonalizado("Permisos");
        BotonPersonalizado bCopiar = new BotonPersonalizado("Copiar");
        BotonPersonalizado bCrear = new BotonPersonalizado("Crear Fichero");
        BotonPersonalizado bBorrar = new BotonPersonalizado("Borrar");
        BotonPersonalizado bExtension = new BotonPersonalizado("Extensiones");
        BotonPersonalizado bLeer = new BotonPersonalizado("Leer Ficheros");
        BotonPersonalizado bEscribir = new BotonPersonalizado("Escribir Fichero");
        BotonPersonalizado bRuta = new BotonPersonalizado("Ruta Fichero");
        BotonPersonalizado bListadoD = new BotonPersonalizado("Listado Directorios");
        
        //ponemos que el layout es grid para este JPanel
        setLayout(new GridLayout(3, 3, 10, 5));
        
        //cogemos la ruta actual de la carpeta y le metemos el nombre de los iconos. Ademas se lo añadimos a sus respectivos botones
        String currentDirectory = System.getProperty("user.dir");
        ImageIcon iconoPermisos = new ImageIcon(currentDirectory+"\\Permisos.png");
        bPermisos.setIcon(iconoPermisos);

        ImageIcon iconoCopiar = new ImageIcon(currentDirectory+"\\copiar.png");
        bCopiar.setIcon(iconoCopiar);

        ImageIcon iconoCrear = new ImageIcon(currentDirectory+"\\crear.png");
        bCrear.setIcon(iconoCrear);

        ImageIcon iconoBorrar = new ImageIcon(currentDirectory+"\\papelera.png");
        bBorrar.setIcon(iconoBorrar);

        ImageIcon iconoExtension = new ImageIcon(currentDirectory+"\\extension.png");
        bExtension.setIcon(iconoExtension);

        ImageIcon iconoLeer = new ImageIcon(currentDirectory+"\\leer.png");
        bLeer.setIcon(iconoLeer);

        ImageIcon iconoEscribir = new ImageIcon(currentDirectory+"\\escribir.png");
        bEscribir.setIcon(iconoEscribir);

        ImageIcon iconoRuta = new ImageIcon(currentDirectory+"\\ruta.png");
        bRuta.setIcon(iconoRuta);

        ImageIcon iconoLista = new ImageIcon(currentDirectory+"\\lista.png");
        bListadoD.setIcon(iconoLista);
        
        //añadimos los botones a botones al panel
        add(bPermisos);
        add(bCopiar);
        add(bCrear);
        add(bBorrar);
        add(bExtension);
        add(bLeer);
        add(bEscribir);
        add(bRuta);
        add(bListadoD);
        
        //damos funciones a los botones. 
        bPermisos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] menu = {"Fichero", "Directorios"};//creamos un string con los datos del menu
                int elegir = JOptionPane.showOptionDialog(null, "Seleccione algo del menu", "Permisos", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, menu, menu[0]);
                switch (elegir) {
                    case 0:
                        //bucle de archivo
                        boolean bucleArchivoPermiso = true;
                        //abre el menu de archivos
                        JFileChooser jfArchivos = new JFileChooser();
                        int cancelar = jfArchivos.showOpenDialog(null);
                        //en caso de que cancele no de de error
                        if (cancelar != JFileChooser.CANCEL_OPTION) {
                            File archivoPermiso = jfArchivos.getSelectedFile();
                            while (bucleArchivoPermiso) {//bucle para que muestre los permisos
                                bucleArchivoPermiso = MenuPermisos(archivoPermiso, bucleArchivoPermiso);
                            }
                        }
                        break;
                    case 1:
                        boolean bucleDirectorioPermiso = true;
                        JFileChooser jfDirectorio = new JFileChooser();
                        jfDirectorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//solo recoge directorios
                        int cancenlar = jfDirectorio.showOpenDialog(null);
                        if (cancenlar != JFileChooser.CANCEL_OPTION) {
                            File directorioPermiso = jfDirectorio.getSelectedFile();
                            while (bucleDirectorioPermiso) {//repite el menu hasta que le de cancelar
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
                //marcamos que eleccion a elegido el Usuario
                dato=1;
                //abrimos el menu
                JFileChooser jf = new JFileChooser();
                int cancelar =jf.showOpenDialog(jf);
                if(cancelar != JFileChooser.CANCEL_OPTION){
                    //guardamos el fichero seleccionado
                File f = jf.getSelectedFile();
                archivoActualLeer.setText(f.getAbsolutePath());//cambiamos el nombre del label
                LeerFichero(f,ta);
                //cerramos el menu y abrimos la ventan leer
                ventanaLeer.setVisible(true);
                ventanaPrincipal.setVisible(false);
                ta.setEditable(false);//ponemos el text area que no pueda leer.
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
                if(dato==0){//comprueba si la ventana esta en modo leer o escribir.
                    int elegir = JOptionPane.showConfirmDialog(null, "Deseas guardar los cambios?");
                    //depende de que elija tendra que hacer una u otra
                    if(elegir==0){//si dice que si guardara la información que haya editado y lo metera
                        File guardar = new File(archivoActualLeer.getText());//coge el archivo que esta actualmente puesto en el JTextArea.
                        EscribirFichero(guardar,ta);//guardamos el texto al fichero
                        LeerFichero(f,ta);//y ponemos el nuevo texto
                        archivoActualLeer.setText(f.getAbsolutePath());//y actualizamos el label
                    }else if(elegir==1){
                        LeerFichero(f,ta);
                        archivoActualLeer.setText(f.getAbsolutePath());
                    }
                }else{//en caso que este en ventana leer
                    //actualiza el label y textarea
                    LeerFichero(f,ta);
                    archivoActualLeer.setText(f.getAbsolutePath());
                }
                }
            }
        });
        bEscribir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dato=0;//marcamos que la ventana esta en modo escribir
                JFileChooser jf = new JFileChooser();//abrimos la ventana de archivos
                int cancelar =jf.showOpenDialog(jf);
                if(cancelar != JFileChooser.CANCEL_OPTION){//en caso de que cancele no de error
                //guardamos el fichero
                File f = jf.getSelectedFile();
                LeerFichero(f,ta);//cambiamos el textarea con los datos del fichero
                archivoActualLeer.setText(f.getAbsolutePath());//actualizamos el label
                //cambiamos de ventana
                ventanaLeer.setVisible(true);
                ventanaPrincipal.setVisible(false);
                ta.setEditable(true);//dejamos el texto editable
                }
            }
        });
        Confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dato==0){
                    if(confirmarEscribir(archivoActualLeer,ta)){//confirmamos que si quiere guardar los datos. 

                    ventanaLeer.setVisible(false);
                ventanaPrincipal.setVisible(true);
                    }
                    }else{//si es leer solo vuelve al menu principal
                    ventanaLeer.setVisible(false);
                ventanaPrincipal.setVisible(true);
                }
                
            }

            
        });
        volverLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaLeer.setVisible(false);//vuelve al Menu
                ventanaPrincipal.setVisible(true);
            }
        });
        bRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              JFileChooser jf = new JFileChooser();//abre el seleccionador de archivos
              int cancelar =jf.showOpenDialog(jf);
              if(cancelar != JFileChooser.CANCEL_OPTION){
                  File f = jf.getSelectedFile();//guarda el fichero seleccionado
                  JOptionPane.showMessageDialog(null, f.getAbsolutePath());//imprime la ruta
              }
            }
        });
        bListadoD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              JFileChooser jf = new JFileChooser();
              jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//solo seleccionamos que puedan coger a los directorioss
              int cancelar =jf.showOpenDialog(jf);//abre el seleccionador de archivo
              if(cancelar != JFileChooser.CANCEL_OPTION){
                  File f = jf.getSelectedFile();//guarda el directorio
                  File[] lista_Fichero = f.listFiles();//recoge los datos del directorio en una lista
                  String datos = "";//guardara los nombre
                  for(File fi :lista_Fichero){
                      datos += fi.getName()+"\n";//concatetamos los nombres
                  }
                  JOptionPane.showMessageDialog(null, datos);//imprimimos los resultados
              }
            }
        });

    }
    /**
     * Confirma que el usuario quiera guardar su fichero, en caso que le de a confirmar.
     * @param archivoActualLeer label que guarda el path del archivo actual actualizando
     * @param ta el text area que contiene el texto a guardar.
     * @return un booleano si confirma o no.
     * @throws HeadlessException 
     */
public boolean confirmarEscribir(JLabel archivoActualLeer,JTextArea ta) throws HeadlessException {
                
                    int preguntar = JOptionPane.showConfirmDialog(null, "Estas seguro que desea guardar los cambios");
                    if(preguntar==0){
                        File f =new File(archivoActualLeer.getText());
                        EscribirFichero(f,ta);
                        ventanaLeer.setVisible(false);
                        ventanaPrincipal.setVisible(true);
                        return true;
                    }else{
                        return false;
                    }
                        
                
            }
/**
 * Escribe el texto del text area en el fichero.
 * @param f fichero en donde se va a guardar el texto
 * @param ta textarea que contiene el texto
 */
    public static void EscribirFichero(File f, JTextArea ta){
        try {
            FileWriter fw = new FileWriter(f);
            String a = ta.getText();
            fw.write(a);
            fw.flush();//limpia el buffer
            fw.close();///cerramos el close
               
        } catch (IOException ex) {
            Logger.getLogger(centro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Lee el fichero, lo guarda en el string y luego lo plasma en el textarea
     * @param f fichero con la informacion y el texto
     * @param ta cuadro de texto que plasmara la información
     */
    public static void LeerFichero(File f, JTextArea ta){
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            String texto="";
            while((line=br.readLine())!=null){//va de linea en linea imprimiendo y guardando en texto
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
    /**
     * borra los archivos que selecciones dependiendo si es fichero o directorio
     */
    public static void borrarArchivo() {
        try {
            //abrimos el menu de seleccion en donde la seleccion podra ser directorio os ficheros, y empezara en d
            JFileChooser jfd = new JFileChooser();
            jfd.setCurrentDirectory(new File("D:\\"));
            jfd.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int i = jfd.showOpenDialog(jfd);

            File directorio = jfd.getSelectedFile();
            
            //comprobacion que no le de a cancel, 
            if (i == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "Cancelastes la seleccion de archivo");
            } else {
                if (directorio.isDirectory()) {//si es directorio
                    //pregunta si confirma borrar el directorio
                    int confirmar2 = JOptionPane.showConfirmDialog(null, "Estas seguro de querer borrar este directorio?");
                    if (confirmar2 == 0) {
                        if (deleteDirectory(directorio)) {//borra el directorio y comprueba si tuvo exito o no
                            JOptionPane.showMessageDialog(null, "El Directorio " + directorio.getName() + " Fue borrado con exito");

                        } else {
                            JOptionPane.showMessageDialog(null, "El directorio no tuvo exito o tuvo un error al borrar");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cancelastes el borrado de directorio");
                    }

                } else {//si en caso de otro fichero que no sea directorio
                    //pregunta si quiere confirmar
                    int opcion = JOptionPane.showConfirmDialog(null, "Estas seguro que quieres borrar este archivo?");
                    if (opcion == 0) {//borra el archivo
                        if(directorio.delete()){
                        JOptionPane.showMessageDialog(null, "Borrastes el archivo "+directorio.getName());
                        }else{
                            JOptionPane.showMessageDialog(null, "Error no se pudo borrrar el archivo"+ directorio.getName());
                        }
                            
                    } else {
                        JOptionPane.showMessageDialog(null, "Cancelastes el borrado");
                    }

                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cancelastes directorio");
        }
    }
/**
 * metodo que borra un directorio debido a que si uno contiene datos dentro no te permitira borrarlo.
 * @param directory fichero que contiene el directorio
 * @return devuelve si borro o no el directorio
 */
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
/**
 * crea un fichero nuevo.
 */
    public static void crearFichero() {
        try {
            //abre el seleccionador de archivos en solo directorios para buscar la localizacion
            JFileChooser jf = new JFileChooser();
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            JOptionPane.showMessageDialog(null, "Seleccione el directorio primero");
            int cancelar = jf.showOpenDialog(jf);
            if (cancelar != JFileChooser.CANCEL_OPTION) {
                //lo abre un option pane para que escriba el nombre completo del archivo la cual quiera crear.
                String nombreArchivo = JOptionPane.showInputDialog("Seleccione el nombre del archivo y su extension");
                String vacio = nombreArchivo.trim();
                if (vacio != "") {//comprueba que no este vacio.
                    //crea un archivo en su localizacion y el nombre
                    File f = new File(jf.getSelectedFile().getAbsoluteFile() + "\\" + nombreArchivo);
                    if (!f.exists()) {//comprueba que existe el fichero
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
/**
 * lista los ficheros cuya extension la cual el usuario eligira en un directorio.
 */
    public static void extensiones() {
        try {
            //abre el menu de archivo la cual solo permita directorios
            JFileChooser jf2 = new JFileChooser();
            jf2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int seleccionador = jf2.showOpenDialog(null);
            if (seleccionador == JFileChooser.APPROVE_OPTION) {
                //pregunta la extension
                String extension = JOptionPane.showInputDialog("Escriba el extension sin el punto");
                extension = extension.trim();
                if (!"".equals(extension)) {//comprueba que no este vacia
                    extension = extension.toLowerCase();//lo pone en minusculas en caso de que lo ponga
                    File listaFicheros = jf2.getSelectedFile();//guardamos el directorio
                    File[] listeri = listaFicheros.listFiles();//creamos una lista con los ficheros y directorios que contengoa
                    String listaextensible = "Esta lista con la extension ." + extension + " es:\n";
                    int contador = 0;//comprueba si hay o no contandolo la cantidad que haya
                    for (File f : listeri) {
                        if (f.getName().endsWith("." + extension)) {//comprueba que el nombre del archivo acabe en .exe
                            listaextensible += f.getName() + "\n";
                            contador++;
                        }
                    }
                    if (contador == 0) {//comprueba si existe o no para imprimir si hay documentos con esa extension.
                        JOptionPane.showMessageDialog(null, "Error no hay ningun archivo con la extension ." + extension);
                    } else {
                        JOptionPane.showMessageDialog(null, listaextensible);
                    }
                }
            }
        } catch (NullPointerException e) {

        }
    }
/**
 * Abre un menu en donde te permite seleccionar comprobar si tiene esos permisos.
 * @param fichero fichero la cual se le comprobara los permisos
 * @param bucles mantiene vivo el menu hasta que el usuario decida
 * @return devuelve el final del menu o si continua
 * @throws HeadlessException 
 */
    public static boolean MenuPermisos(File fichero, boolean bucles) throws HeadlessException {
        String[] menupermisos = {"Permisos lectura", "Permisos Escritura", "Permisos de ejecucion", "Volver"};
        int elegir2 = JOptionPane.showOptionDialog(null, "Seleccione lo que quiera analizar", "Permisos", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, menupermisos, menupermisos[0]);
        switch (elegir2) {
            case 0:
                if (fichero.canRead()) {//comprueba si puede leer
                    JOptionPane.showMessageDialog(null, "Si tiene el permiso de leer");
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene el permiso de leer");
                }
                break;
            case 1:
                if (fichero.canWrite()) {//comprueba si se puede escribir
                    JOptionPane.showMessageDialog(null, "Si tiene el permiso de escribir");
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene el permiso de escribir");
                }
                break;
            case 2:
                if (fichero.canExecute()) {//comprueba si se puede ejecutar
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
    /**
     * Metodo que permite copiar los datos a otro fichero, y si en un directorio no existe, que pueda crear un nuevo fichero con el texto.
     * @throws HeadlessException 
     */
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
/**
 * Metodo que hace la funcion de copiar.
 * @param origen el archivo que contiene los datos a copiar
 * @param destino el archivo donde se va a copiar los datos del origen
 */
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
        //pone el font y layout para los creditos del programa.
        JLabel autor = new JLabel("Creado por Jorge Wang");
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(autor);
        Font timesNewRomanBold = new Font("Times New Roman", Font.ITALIC, 20);
        autor.setFont(timesNewRomanBold);
    }
}
//estilos del boton
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
