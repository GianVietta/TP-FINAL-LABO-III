package com.TpFinal.MVC.Administrativo.view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.HashMap;

public class MenuAdmin extends JFrame{
    private JPanel MenuAdminPanel;
    private JMenuBar menuBar;
    private JMenu gestionEstudiantes;
    private JMenuItem agregaEst;
    private JMenuItem eliminarEst;
    private JMenuItem modificarEst;
    private JMenuItem listarEst;
    private JMenu gestionProfesores;
    private JMenuItem agregaPro;
    private JMenuItem eliminarPro;
    private JMenuItem modificarPro;
    private JMenuItem listarPro;
    private JMenu gestionMaterias;
    private JMenuItem agregaMat;
    private JMenuItem addCom;
    private JMenuItem modCom;
    private JMenuItem remCom;
    private JMenuItem eliminarMat;
    private JMenuItem modificarMat;
    private JMenuItem listarMat;
    private JMenuItem listarComs;





    public MenuAdmin() {
       this.MenuAdminPanel = new JPanel(){
             protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                 // Cargar y dibujar la imagen de fondo
                  Image imagenFondo = new ImageIcon("TP Final III/src/imagenes/Fondo.jpg").getImage();
                  g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                      }
                 };
       


       this.menuBar = new JMenuBar();

       this.gestionEstudiantes = new JMenu();
       gestionEstudiantes.setText("Gestion Estudiantes");
       gestionEstudiantes.setFont(new Font("Arial Black",Font.TYPE1_FONT,18));
       gestionEstudiantes.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
       //gestionEstudiantes.setBackground(new Color(59,59,59));
       //gestionEstudiantes.setForeground(Color.white);
        gestionEstudiantes.setUI(new CustomMenuUI());

       this.agregaEst = new JMenuItem("Agregar Estudiante");
       agregaEst.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
       agregaEst.setBackground(new Color(59,59,59));
       agregaEst.setForeground(Color.white);

       this.eliminarEst = new JMenuItem("Eliminar Estudiante");
       eliminarEst.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
       eliminarEst.setBackground(new Color(59,59,59));
       eliminarEst.setForeground(Color.white);

       this.modificarEst = new JMenuItem("Modificar Estudiante");
       modificarEst.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
       modificarEst.setBackground(new Color(59,59,59));
       modificarEst.setForeground(Color.white);

       this.listarEst = new JMenuItem("Listar Estudiante");
       listarEst.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
       listarEst.setForeground(Color.white);
       listarEst.setBackground(new Color(59,59,59));

        gestionEstudiantes.add(agregaEst);
        gestionEstudiantes.add(eliminarEst);
        gestionEstudiantes.add(modificarEst);
        gestionEstudiantes.add(listarEst);
        
        this.gestionProfesores = new JMenu("Gestion Profesores");
        gestionProfesores.setFont(new Font("Arial Black",Font.TYPE1_FONT,18));
        gestionProfesores.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        this.agregaPro = new JMenuItem("Agregar Profesor");
        agregaPro.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        agregaPro.setBackground(new Color(59,59,59));
        agregaPro.setForeground(Color.white);

        this.eliminarPro = new JMenuItem("Eliminar Profesor");
        eliminarPro.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        eliminarPro.setBackground(new Color(59,59,59));
        eliminarPro.setForeground(Color.white);

        this.modificarPro = new JMenuItem("Modificar Profesor");
        modificarPro.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        modificarPro.setBackground(new Color(59,59,59));
        modificarPro.setForeground(Color.white);

        this.listarPro = new JMenuItem("Listar Profesor");
        listarPro.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        listarPro.setForeground(Color.white);
        listarPro.setBackground(new Color(59,59,59));

       this.gestionMaterias = new JMenu("Gestion Materias");
        gestionMaterias.setUI(new CustomMenuUI());
        gestionMaterias.setFont(new Font("Arial Black",Font.TYPE1_FONT,18));
        gestionMaterias.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        this.agregaMat = new JMenuItem("Agregar Materia");
        agregaMat.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        agregaMat.setBackground(new Color(59,59,59));
        agregaMat.setForeground(Color.white);

        this.addCom = new JMenuItem("Agregar Comision");
        addCom.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        addCom.setBackground(new Color(59,59,59));
        addCom.setForeground(Color.white);


        this.eliminarMat = new JMenuItem("Eliminar Materia");
        eliminarMat.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        eliminarMat.setBackground(new Color(59,59,59));
        eliminarMat.setForeground(Color.white);

        this.remCom = new JMenuItem("Eliminar Comision");
        remCom.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        remCom.setBackground(new Color(59,59,59));
        remCom.setForeground(Color.white);

        this.modificarMat = new JMenuItem("Modificar Materia");
        modificarMat.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        modificarMat.setBackground(new Color(59,59,59));
        modificarMat.setForeground(Color.white);

        this.modCom = new JMenuItem("Modificar Comision");
        modCom.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        modCom.setBackground(new Color(59,59,59));
        modCom.setForeground(Color.white);

        this.listarMat = new JMenuItem("Listar Materia");
        listarMat.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        listarMat.setForeground(Color.white);
        listarMat.setBackground(new Color(59,59,59));

        this.listarComs = new JMenuItem("Listar Comisiones");
        listarComs.setFont(new Font("Arial Black",Font.TYPE1_FONT,14));
        listarComs.setForeground(Color.white);
        listarComs.setBackground(new Color(59,59,59));

        gestionMaterias.add(agregaMat);
        gestionMaterias.add(eliminarMat);
        gestionMaterias.add(modificarMat);
        gestionMaterias.add(listarMat);
        gestionMaterias.add(addCom);
        gestionMaterias.add(remCom);
        gestionMaterias.add(modCom);
        gestionMaterias.add(listarComs);


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

    public void addEstListener(ActionListener actionListener){this.agregaEst.addActionListener(actionListener);}
    public void removeEstListener(ActionListener actionListener){this.eliminarEst.addActionListener(actionListener);}
    public void modEstListener(ActionListener actionListener){this.modificarEst.addActionListener(actionListener);}
    public void viewEstListener(ActionListener actionListener){this.listarEst.addActionListener(actionListener);}

    public void addProListener(ActionListener actionListener){this.agregaPro.addActionListener(actionListener);}
    public void removeProListener(ActionListener actionListener){this.eliminarPro.addActionListener(actionListener);}
    public void modProListener(ActionListener actionListener){this.modificarPro.addActionListener(actionListener);}
    public void viewProListener(ActionListener actionListener){this.listarPro.addActionListener(actionListener);}

    public void addMatListener(ActionListener actionListener){this.agregaMat.addActionListener(actionListener);}
    public void removeMatListener(ActionListener actionListener){this.eliminarMat.addActionListener(actionListener);}
    public void modMatListener(ActionListener actionListener){this.modificarMat.addActionListener(actionListener);}
    public void viewMatListener(ActionListener actionListener){this.listarMat.addActionListener(actionListener);}
    public void addComListener(ActionListener actionListener){this.addCom.addActionListener(actionListener);}

    public void removeComListener(ActionListener actionListener){this.remCom.addActionListener(actionListener);}

    public void modComListener(ActionListener actionListener){this.modCom.addActionListener(actionListener);}

    public void listComListener(ActionListener actionListener){this.listarComs.addActionListener(actionListener);}



}
