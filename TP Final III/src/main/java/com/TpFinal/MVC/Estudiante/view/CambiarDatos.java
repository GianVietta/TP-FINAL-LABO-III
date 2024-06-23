package com.TpFinal.MVC.Estudiante.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CambiarDatos extends JFrame{
    private JPanel panelCambiar;
    private JTextField apellido;
    private JTextField nombre;
    private JTextField dni;
    private JTextField usuario;
    private JTextField contrasenia;
    private JButton confirmarButton;
    private JButton cambiarButton;
    private JButton volverButton;

    public CambiarDatos(){

        apellido.setForeground(Color.white);
        nombre.setForeground(Color.white);
        dni.setForeground(Color.white);
        usuario.setForeground(Color.white);
        contrasenia.setForeground(Color.white);
        this.setTitle("STG-GESTION DE ESTUDIANTES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.add(panelCambiar);
        this.setVisible(true);
    }

    public JTextField getApellido() {
        return apellido;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JTextField getDni() {
        return dni;
    }

    public JTextField getUsuario() {
        return usuario;
    }

    public JTextField getContrasenia() {
        return contrasenia;
    }

    public void confirmarButton(ActionListener listener){
        confirmarButton.addActionListener(listener);
    }

    public void cambiarButton(ActionListener listener){
        cambiarButton.addActionListener(listener);
    }

    public void volverButtonCambiarDatos(ActionListener listener){
        volverButton.addActionListener(listener);
    }
}
