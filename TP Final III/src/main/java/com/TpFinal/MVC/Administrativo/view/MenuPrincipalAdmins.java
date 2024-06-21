package com.TpFinal.MVC.Administrativo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MenuPrincipalAdmins extends JFrame{
    private int posX;
    private int posY;

    public MenuPrincipalAdmins() throws HeadlessException {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar y dibujar la imagen de fondo
                Image imagenFondo = new ImageIcon("src/imagenes/Fondo.jpg").getImage();
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - posX;
                int y = e.getYOnScreen() - posY;
                MenuPrincipalAdmins.this.setLocation(x, y);
            }
        });

        panel.setLayout(new BorderLayout());
        getContentPane().add(panel);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(59,59,59));
        menuBar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JMenu estMenu = new JMenu("Estudiante");
        estMenu.setFont(new Font("Bahnschrift",Font.BOLD,18));
        JMenuItem addEst = new JMenuItem("Agregar alumno");
        JMenuItem updateEst = new JMenuItem("Modificar algun alumno");
        JMenuItem removeEst = new JMenuItem("Eliminar alumno");
        estMenu.add(addEst);
        estMenu.add(updateEst);
        estMenu.add(removeEst);

        JMenu proMenu = new JMenu("Profesor");
        estMenu.setFont(new Font("Bahnschrift",Font.BOLD,18));
        JMenuItem addPro = new JMenuItem("Agregar profesor");
        JMenuItem updatePro = new JMenuItem("Modificar algun profesor");
        JMenuItem removePro = new JMenuItem("Eliminar profesor");
        estMenu.add(addPro);
        estMenu.add(updatePro);
        estMenu.add(removePro);

        JMenu matMenu = new JMenu("Materias");
        estMenu.setFont(new Font("Bahnschrift",Font.BOLD,18));
        JMenuItem addMat = new JMenuItem("Agregar materia");
        JMenuItem updateMat = new JMenuItem("Modificar algun materia");
        JMenuItem removeMat = new JMenuItem("Eliminar materia");
        estMenu.add(addMat);
        estMenu.add(updateMat);
        estMenu.add(removeMat);

        menuBar.add(estMenu);
        menuBar.add(proMenu);
        menuBar.add(matMenu);

        setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Centrar en pantalla
        setVisible(true);

    }




}
