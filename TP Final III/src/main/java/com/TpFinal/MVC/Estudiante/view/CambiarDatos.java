package com.TpFinal.MVC.Estudiante.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CambiarDatos extends JFrame{
    private JPanel panelCambiar;
    private JTextField repetirContrasenia;
    private JTextField contraseniaNueva;
    private JTextField usuario;
    private JTextField contrasenia;
    private JButton confirmarButton;
    private JButton cambiarButton;
    private JButton volverButton;

    public CambiarDatos(){

        repetirContrasenia.setForeground(Color.white);
        contraseniaNueva.setForeground(Color.white);
        usuario.setForeground(Color.white);
        contrasenia.setForeground(Color.white);
        this.setTitle("STG-GESTION DE ESTUDIANTES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.add(panelCambiar);
        this.setVisible(true);
    }

    public JTextField getRepetirContrasenia() {
        return repetirContrasenia;
    }

    public JTextField getContraseniaNueva() {
        return contraseniaNueva;
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

    public void enabledButton(boolean enabled){
        confirmarButton.setEnabled(true);
    }

    public void volverButton(ActionListener listener){
        volverButton.addActionListener(listener);
    }
}
