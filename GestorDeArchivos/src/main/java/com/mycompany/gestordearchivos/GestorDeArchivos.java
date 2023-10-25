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
        v.setVisible(true);
    }
}

class miVentana extends JFrame{
    public miVentana(){
        setBounds(100,100,1250,750);
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
        sur partesur = new sur();
        add(partesur,BorderLayout.SOUTH);
    }
}

class centro extends JPanel{
    private File f;
    public centro(){
        BotonPersonalizado bPermisos = new BotonPersonalizado("Permisos");
        BotonPersonalizado bCopiar = new BotonPersonalizado("Copiar");
        BotonPersonalizado bCrear = new BotonPersonalizado("Crear Fichero(i)");
        BotonPersonalizado bBorrar = new BotonPersonalizado("Borrar(i)");
        BotonPersonalizado bExtension = new BotonPersonalizado("Extensiones");
        BotonPersonalizado bLeer = new BotonPersonalizado("Leer Ficheros(i)");
        BotonPersonalizado bEscribir = new BotonPersonalizado("Escribir Fichero(i)");
        BotonPersonalizado bRuta = new BotonPersonalizado("Ruta Fichero(i)");
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
    }
}
class sur extends JPanel{
    private File ficheroSeleccionado;
    JLabel nombreRuta;
    public sur(){
        setLayout(new FlowLayout(FlowLayout.CENTER));
        nombreRuta = new JLabel("El Archivo Seleccionado");
        JButton botonSeleccionArchivo = new JButton("Selecciona Archivo");
        BotonCircular botonAyuda = new BotonCircular("?");
        add(botonSeleccionArchivo);
        add(nombreRuta);
        add(botonAyuda);
        botonSeleccionArchivo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                jf.showOpenDialog(jf);
                ficheroSeleccionado = jf.getSelectedFile();
                nombreRuta.setText(ficheroSeleccionado.getName());
            }
            
        });
    }
    public void Resetear(){
        nombreRuta.setText("El Archivo Seleccionado");
    }
}

class BotonPersonalizado extends JButton {
    public BotonPersonalizado(String texto) {
        super(texto);
        setContentAreaFilled(false); // Hace que el fondo del botón sea transparente
        setFocusPainted(false); // Elimina el borde de enfoque
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

class BotonCircular extends JButton {
    public BotonCircular(String texto) {
        super(texto);
        setContentAreaFilled(false); // Hace que el fondo del botón sea transparente
        setFocusPainted(false); // Elimina el borde de enfoque
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Creamos un círculo que se ajuste al tamaño del botón
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, width, height);

        // Establecemos el color del fondo del botón
        if (getModel().isArmed()) {
            // Si se hace clic en el botón, utiliza un color más oscuro
            g2d.setColor(new Color(128, 128, 128));
        } else {
            g2d.setColor(getBackground());
        }

        g2d.fill(circle); // Rellena el círculo con el color de fondo

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Dibuja un borde alrededor del botón
        g.setColor(Color.BLUE);
        int width = getWidth();
        int height = getHeight();
        g.drawOval(0, 0, width - 1, height - 1); // Dibuja un óvalo como borde
    }
    }
