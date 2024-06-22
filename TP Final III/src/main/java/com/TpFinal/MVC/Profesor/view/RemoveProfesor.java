package com.TpFinal.MVC.Profesor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RemoveProfesor extends JFrame {
    private JTextField buscarDni;
    private JButton buscarButton;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField dni;
    private JTextField especialidad;
    private JButton eliminarButton;
    private JButton volverButton;
    private JPanel removePanel;


    public RemoveProfesor() {

        this.nombre.setForeground(Color.white);
        this.apellido.setForeground(Color.white);
        this.dni.setForeground(Color.white);
        this.buscarDni.setForeground(Color.white);
        this.especialidad.setForeground(Color.white);
        this.nombre.setEditable(false);
        this.apellido.setEditable(false);
        this.dni.setEditable(false);
        this.especialidad.setEditable(false);
        this.setTitle("STG-GESTION DE ESTUDIANTES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600, 200, 650, 300);
        this.add(removePanel);
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

    public void buscarBtnListener(ActionListener listener){
        this.buscarButton.addActionListener(listener);
    }

    public void removeBtnListener(ActionListener listener){this.eliminarButton.addActionListener(listener);}
    public void volverBtn(ActionListener listener){this.volverButton.addActionListener(listener);}
    public void enableRemoveBtn(boolean enabled) {
        eliminarButton.setEnabled(enabled);
    }
}
