package com.TpFinal.MVC.Profesor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateProfesor extends JFrame{
    private JButton cargarProfesorButton;
    private JTextField dni;
    private JTextField apellido;
    private JTextField nombre;
    private JTextField especialidad;
    private JButton volverButton;
    private JPanel mainPanel;

    public CreateProfesor (){

        this.nombre.setForeground(Color.white);
        this.apellido.setForeground(Color.white);
        this.dni.setForeground(Color.white);
        this.especialidad.setForeground(Color.white);
        this.setTitle("SGT-GESTION DE PROFESORES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,500,300);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public JButton getCargarProfesorButton() {
        return cargarProfesorButton;
    }

    public JTextField getDni() {
        return dni;
    }

    public JTextField getApellido() {
        return apellido;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JTextField getEspecialidad() {
        return especialidad;
    }

    public JButton getVolverButton() {
        return volverButton;
    }

    public void CreateBtnProfesorListener(ActionListener listener){
            cargarProfesorButton.addActionListener(listener);
    }

    public void volverBtnListener(ActionListener listener){
            volverButton.addActionListener(listener);
    }
}
