package com.TpFinal.MVC.Profesor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ModProfesor extends JFrame {
    private JPanel modProfe;
    private JTextField buscarDni;
    private JButton buscarButton;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField dni;
    private JTextField especialidad;
    private JButton modificarButton;
    private JButton volverButton;

    public ModProfesor(){

        this.nombre.setForeground(Color.white);
        this.apellido.setForeground(Color.white);
        this.dni.setForeground(Color.white);
        this.especialidad.setForeground(Color.white);
        this.buscarDni.setForeground(Color.white);
        this.setTitle("STG-GESTION DE PROFESORES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.add(modProfe);
        this.setVisible(true);
    }

    public JTextField getBuscarDni() {
        return buscarDni;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JTextField getApellido() {
        return apellido;
    }

    public JTextField getDni() {
        return dni;
    }

    public JTextField getEspecialidad() {
        return especialidad;
    }

    public void buscarButton(ActionListener listener){
        buscarButton.addActionListener(listener);
    }

    public void modificarButton(ActionListener listener){
        modificarButton.addActionListener(listener);
    }

    public void volverButton(ActionListener listener){
        volverButton.addActionListener(listener);
    }

    public void enableButton(boolean enable){
        modificarButton.setEnabled(enable);
    }
}
