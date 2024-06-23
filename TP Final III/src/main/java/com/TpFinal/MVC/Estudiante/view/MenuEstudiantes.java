package com.TpFinal.MVC.Estudiante.view;

import com.TpFinal.MVC.Administrativo.view.MenuAdmin;

import javax.swing.*;
import javax.swing.plaf.MenuItemUI;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEstudiantes extends JFrame{
        private JPanel menuEstPanel;
        private JMenuBar menuBar;
        private JMenu gestionDeCursada;
        private JMenuItem verMaterias;
        private JMenuItem verCursadas;
        private JMenuItem matricularse;
        private JMenuItem darDeBaja;
        private JMenu opciones;
        private JMenuItem cambiarContrasenia;
        private JMenuItem cambiarDatos;
        private JMenuItem salir;

        public MenuEstudiantes() {
            this.menuEstPanel = new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Image imagenFondo = new ImageIcon("TP Final III/src/imagenes/Fondo.jpg").getImage();
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            };
            this.menuBar = new JMenuBar();

            this.gestionDeCursada = new JMenu("Gestión de Cursada");
            gestionDeCursada.setFont(new Font("Arial Black", Font.TYPE1_FONT, 18));
            gestionDeCursada.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            //gestionDeCursada.setBackground(new Color(12, 12, 14));
            //gestionDeCursada.setBackground(new Color(59,59,59));
            //gestionDeCursada.setForeground(Color.white);

            this.verMaterias = new JMenuItem("Ver Materias");
            verMaterias.setFont(new Font("Arial Black", Font.TYPE1_FONT, 14));
            verMaterias.setBackground(new Color(59,59,59));
            verMaterias.setForeground(Color.white);

            this.verCursadas = new JMenuItem("Ver Cursadas");
            verCursadas.setFont(new Font("Arial Black", Font.TYPE1_FONT, 14));
            verCursadas.setBackground(new Color(59,59,59));
            verCursadas.setForeground(Color.white);

            this.matricularse = new JMenuItem("Matricularse");
            matricularse.setFont(new Font("Arial Black", Font.TYPE1_FONT, 14));
            matricularse.setBackground(new Color(59,59,59));
            matricularse.setForeground(Color.white);

            this.darDeBaja = new JMenuItem("Dar de Baja");
            darDeBaja.setFont(new Font("Arial Black", Font.TYPE1_FONT, 14));
            darDeBaja.setBackground(new Color(59,59,59));
            darDeBaja.setForeground(Color.white);

            gestionDeCursada.add(verMaterias);
            gestionDeCursada.add(verCursadas);
            gestionDeCursada.add(matricularse);
            gestionDeCursada.add(darDeBaja);

            this.opciones = new JMenu("Opciones");
            opciones.setFont(new Font("Arial Black", Font.TYPE1_FONT, 18));
            opciones.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            this.cambiarContrasenia = new JMenuItem("Cambiar Contraseña");
            cambiarContrasenia.setFont(new Font("Arial Black", Font.TYPE1_FONT, 14));
            cambiarContrasenia.setBackground(new Color(59,59,59));
            cambiarContrasenia.setForeground(Color.white);

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
            opciones.add(cambiarContrasenia);
            opciones.add(cambiarDatos);
            opciones.addSeparator();
            opciones.add(salir);

            menuBar.add(gestionDeCursada);
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

        public void verCursadasListener(ActionListener actionListener) {
            this.verCursadas.addActionListener(actionListener);
        }

        public void matricularseListener(ActionListener actionListener) {
            this.matricularse.addActionListener(actionListener);
        }

        public void darDeBajaListener(ActionListener actionListener) {
            this.darDeBaja.addActionListener(actionListener);
        }

        public void cambiarContraseniaListener(ActionListener actionListener) {
            this.cambiarContrasenia.addActionListener(actionListener);
        }

        public void cambiarDatosListener(ActionListener actionListener) {
            this.cambiarDatos.addActionListener(actionListener);
        }


    }


