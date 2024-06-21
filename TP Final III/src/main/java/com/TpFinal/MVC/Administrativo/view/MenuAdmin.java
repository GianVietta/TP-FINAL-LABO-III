package com.TpFinal.MVC.Administrativo.view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.util.HashMap;

public class MenuAdmin extends JFrame{
    private JPanel MenuAdminPanel;

    public MenuAdmin() {
       this.MenuAdminPanel = new JPanel(){
             protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                 // Cargar y dibujar la imagen de fondo
                  Image imagenFondo = new ImageIcon("TP Final III/src/imagenes/Fondo.jpg").getImage();
                  g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                      }
                 };
       




       JMenuBar menuBar = new JMenuBar();

       JMenu gestionEstudiantes = new JMenu();
       gestionEstudiantes.setText("Gestion Estudiantes");
       gestionEstudiantes.setFont(new Font("Arial Black",Font.TYPE1_FONT,18));
       gestionEstudiantes.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
       //gestionEstudiantes.setBackground(new Color(59,59,59));
       //gestionEstudiantes.setForeground(Color.white);
       gestionEstudiantes.setUI(new CustomMenuUI());

       JMenuItem agregaEst = new JMenuItem("Agregar Estudiante");
       agregaEst.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
       agregaEst.setBackground(new Color(59,59,59));
       agregaEst.setForeground(Color.white);

       JMenuItem eliminarEst = new JMenuItem("Eliminar Estudiante");
       eliminarEst.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
       eliminarEst.setBackground(new Color(59,59,59));
       eliminarEst.setForeground(Color.white);

       JMenuItem modificarEst = new JMenuItem("Modificar Estudiante");
       modificarEst.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
       modificarEst.setBackground(new Color(59,59,59));
       modificarEst.setForeground(Color.white);

       JMenuItem listarEst = new JMenuItem("Listar Estudiante");
       listarEst.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
       listarEst.setForeground(Color.white);
       listarEst.setBackground(new Color(59,59,59));

        gestionEstudiantes.add(agregaEst);
        gestionEstudiantes.add(eliminarEst);
        gestionEstudiantes.add(modificarEst);
        gestionEstudiantes.add(listarEst);
        
        JMenu gestionProfesores = new JMenu("Gestion Profesores");
        gestionProfesores.setFont(new Font("Arial Black",Font.TYPE1_FONT,18));
        gestionProfesores.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JMenuItem agregaPro = new JMenuItem("Agregar Profesor");
        agregaPro.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        agregaPro.setBackground(new Color(59,59,59));
        agregaPro.setForeground(Color.white);

        JMenuItem eliminarPro = new JMenuItem("Eliminar Profesor");
        eliminarPro.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        eliminarPro.setBackground(new Color(59,59,59));
        eliminarPro.setForeground(Color.white);

        JMenuItem modificarPro = new JMenuItem("Modificar Profesor");
        modificarPro.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        modificarPro.setBackground(new Color(59,59,59));
        modificarPro.setForeground(Color.white);

        JMenuItem listarPro = new JMenuItem("Listar Profesor");
        listarPro.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        listarPro.setForeground(Color.white);
        listarPro.setBackground(new Color(59,59,59));

        JMenu gestionMaterias = new JMenu("Gestion Materias");
        gestionMaterias.setUI(new CustomMenuUI());
        gestionMaterias.setFont(new Font("Arial Black",Font.TYPE1_FONT,18));
        gestionMaterias.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JMenuItem agregaMat = new JMenuItem("Agregar Materia");
        agregaMat.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        agregaMat.setBackground(new Color(59,59,59));
        agregaMat.setForeground(Color.white);

        JMenuItem eliminarMat = new JMenuItem("Eliminar Materia");
        eliminarMat.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        eliminarMat.setBackground(new Color(59,59,59));
        eliminarMat.setForeground(Color.white);

        JMenuItem modificarMat = new JMenuItem("Modificar Materia");
        modificarMat.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        modificarMat.setBackground(new Color(59,59,59));
        modificarMat.setForeground(Color.white);

        JMenuItem listarMat = new JMenuItem("Listar Materia");
        listarMat.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        listarMat.setForeground(Color.white);
        listarMat.setBackground(new Color(59,59,59));

        gestionMaterias.add(agregaMat);
        gestionMaterias.add(eliminarMat);
        gestionMaterias.add(modificarMat);
        gestionMaterias.add(listarMat);


        gestionMaterias.setFont(new Font("Arial Black",Font.TYPE1_FONT,18));
        gestionProfesores.setUI(new CustomMenuUI());
        gestionProfesores.add(agregaPro);
        gestionProfesores.add(eliminarPro);
        gestionProfesores.add(modificarPro);
        gestionProfesores.add(listarPro);


        
        menuBar.add(gestionEstudiantes);
        menuBar.add(gestionProfesores);
        menuBar.add(gestionMaterias);
        this.setTitle("STG-GESTION DE ESTUDIANTES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.MenuAdminPanel.setBackground(new Color(59,59,59));
        this.MenuAdminPanel.setForeground(Color.white);
        this.setBackground(new Color(59,59,59));
        this.setForeground(Color.white);
        this.MenuAdminPanel.add(menuBar);
        this.add(MenuAdminPanel);
        this.setBounds(600,200,700,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    class CustomMenuUI extends BasicMenuUI {
        @Override
        protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
            g.setColor(new Color(59,59,59));
            g.fillRect(0, 0, menuItem.getWidth(), menuItem.getHeight());
        }

        @Override
        protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
            g.setColor(Color.WHITE);
            super.paintText(g, menuItem, textRect, text);
        }
    }

    // Clase personalizada para el estilo de los JMenuItem
    class CustomMenuItemUI extends BasicMenuItemUI {
        @Override
        protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, menuItem.getWidth(), menuItem.getHeight());
        }

        @Override
        protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
            g.setColor(Color.WHITE);
            super.paintText(g, menuItem, textRect, text);
        }
    }


}
