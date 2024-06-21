package com.TpFinal.MVC.Estudiante.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateEstudiant extends JFrame {
    private JTextField dni;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField legajo;
    private JTextField carrera;
    private JPanel mainPanel; // Asegúrate de tener un JPanel mainPanel
    private JButton volverButton;
    private JButton ingresarButton;

    public CreateEstudiant() {

        this.nombre.setForeground(Color.white);
        this.apellido.setForeground(Color.white);
        this.dni.setForeground(Color.white);
        this.legajo.setForeground(Color.white);
        this.carrera.setForeground(Color.white);
        this.setTitle("STG-GESTION DE ESTUDIANTES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.add(mainPanel);
        this.setVisible(true);


    }

    public JButton getIngresarButton() {
        return ingresarButton;
    }

    public JTextField getDniField() {
        return dni;
    }

    public JTextField getNombreField() {
        return nombre;
    }

    public JTextField getApellidoField() {
        return apellido;
    }

    public JTextField getLegajoField() {
        return legajo;
    }

    public JTextField getCarreraField() {
        return carrera;
    }

    public void createBtnListener(ActionListener listener){
        ingresarButton.addActionListener(listener);
    }

    public void volverBottonListener(ActionListener listener){
        volverButton.addActionListener(listener);
    }

}
