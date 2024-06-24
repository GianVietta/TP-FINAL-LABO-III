package com.TpFinal.MVC.Profesor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuProfe
        extends JFrame{
        private JPanel menuEstPanel;
        private JMenuBar menuBar;
        private JMenu misMaterias;
        private JMenuItem verMaterias;
        private JMenuItem gestionNotas;
        private JMenu opciones;
        private JMenuItem cambiarDatos;
        private JMenuItem salir;

        public MenuProfe() {
            this.menuEstPanel = new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Image imagenFondo = new ImageIcon("TP Final III/src/imagenes/Fondo.jpg").getImage();
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            };
            this.menuBar = new JMenuBar();

            this.misMaterias = new JMenu("Mis Materias");
            misMaterias.setFont(new Font("Arial Black", Font.TYPE1_FONT, 18));
            misMaterias.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            //misMaterias.setBackground(new Color(12, 12, 14));
            //misMaterias.setBackground(new Color(59,59,59));
            //misMaterias.setForeground(Color.white);

            this.verMaterias = new JMenuItem("Ver Materias");
            verMaterias.setFont(new Font("Arial Black", Font.TYPE1_FONT, 14));
            verMaterias.setBackground(new Color(59,59,59));
            verMaterias.setForeground(Color.white);

            this.gestionNotas = new JMenuItem("Gestion Notas");
            gestionNotas.setFont(new Font("Arial Black", Font.TYPE1_FONT, 14));
            gestionNotas.setBackground(new Color(59,59,59));
            gestionNotas.setForeground(Color.white);


            misMaterias.add(verMaterias);
            misMaterias.add(gestionNotas);


            this.opciones = new JMenu("Opciones");
            opciones.setFont(new Font("Arial Black", Font.TYPE1_FONT, 18));
            opciones.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            this.cambiarDatos = new JMenuItem("Cambiar Datos");
            cambiarDatos.setFont(new Font("Arial Black", Font.TYPE1_FONT, 14));
            cambiarDatos.setBackground(new Color(59,59,59));
            cambiarDatos.setForeground(Color.white);

            this.salir = new JMenuItem("Salir");
            salir.setFont(new Font("Arial Black", Font.TYPE1_FONT, 14));
            salir.setBackground(new Color(59,59,59));
            salir.setForeground(Color.white);
            salir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            opciones.add(cambiarDatos);
            opciones.addSeparator();
            opciones.add(salir);

            menuBar.add(misMaterias);
            menuBar.add(opciones);
            this.setTitle("Menú Estudiantes");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.menuEstPanel.setBackground(new Color(59, 59, 59));
            this.menuEstPanel.setForeground(Color.white);
            this.setBackground(new Color(59, 59, 59));
            this.setForeground(Color.white);
            this.menuEstPanel.add(menuBar);
            this.add(menuEstPanel);
            this.setBounds(600, 200, 700, 600);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }

        public void verMateriasListener(ActionListener actionListener) {
            this.verMaterias.addActionListener(actionListener);
        }

        public void gestionNotasListener(ActionListener actionListener) {
            this.gestionNotas.addActionListener(actionListener);
        }


        public void cambiarDatosListener(ActionListener actionListener) {
            this.cambiarDatos.addActionListener(actionListener);
        }


    }


