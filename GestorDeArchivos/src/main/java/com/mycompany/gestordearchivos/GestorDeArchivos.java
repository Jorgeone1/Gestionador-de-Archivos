/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestordearchivos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.*;
import javax.swing.*;
/**
 *
 * @author Jorge Wang Wang
 */
public class GestorDeArchivos {

    public static void main(String[] args) {
        miVentana v = new miVentana();
        centro panel = new centro();
        v.setVisible(true);
    }
}

class miVentana extends JFrame{
    public miVentana(){
        
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int anchura = (int) (0.8 *(int)screenSize.getWidth());
        int altura = (int) ((int)screenSize.getHeight()*0.8);
        
        setSize(anchura,altura);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel pan = new panel();
        add(pan);
    }
}

class panel extends JPanel{
    public panel(){
        setLayout(new BorderLayout());
        centro botones = new centro();
        add(botones,BorderLayout.CENTER);
        JLabel titulo = new JLabel("Manejador de Archivos");
        Font timesNewRomanBold = new Font("Times New Roman", Font.BOLD, 50);
        titulo.setFont(timesNewRomanBold);
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelNorte.add(titulo);
        add(panelNorte,BorderLayout.NORTH);
        sur panelSur = new sur();
        add(panelSur,BorderLayout.SOUTH);
    }
}

class centro extends JPanel{
    private JFrame ventanaLeer;
    public centro(){
        ventanaLeer = new JFrame();
         Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int anchura = (int) (0.8 *(int)screenSize.getWidth());
        int altura = (int) ((int)screenSize.getHeight()*0.8);
        
        ventanaLeer.setSize(anchura,altura);
        ventanaLeer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelLeer = new JPanel(new BorderLayout());
        JTextArea ta = new JTextArea();
        JScrollPane jp = new JScrollPane(ta);
        panelLeer.add(jp,BorderLayout.CENTER);
        
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
        panelLeer.add(abajoLeer,BorderLayout.SOUTH);
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
        setLayout(new GridLayout(3,3,50,30));
        
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
        bPermisos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             
            } 
        });
        bCopiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             
            } 
        });
        bCrear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             
            } 
        });
        bBorrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             
            } 
        });
        bExtension.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             extensiones();
            } 
        });
        bLeer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             
            } 
        });
        bEscribir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             
            } 
        });
        bRuta.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             
            } 
        });
        bListadoD.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             
            } 
        });
        
    }
    public static void extensiones(){
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jf.showOpenDialog(jf);
        File directorios = jf.getSelectedFile();
        String extension = JOptionPane.showInputDialog(null, "Inserte el tipo de extension que quiera buscar");
        File[] listaArchivos = directorios.listFiles();
        String lista ="";
        for(File f: listaArchivos){
            if(extension == f.getName().substring(f.getName().lastIndexOf(".")+1)){
                lista += f.getName() +"\n";
            }
        }
        JOptionPane.showMessageDialog(null, lista);
    }
}
class sur extends JPanel{
    public sur(){
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
        setForeground(Color.WHITE); // Color del texto

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Creamos un degradado lineal para el fondo del botón
        GradientPaint gradient = new GradientPaint(0, 0, new Color(131, 238, 47), width, height, new Color(128, 128, 255));
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
