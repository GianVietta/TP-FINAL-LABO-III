package com.TpFinal.MVC.Estudiante.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CambiarContrasenia extends JFrame {
    private JPanel contraseniaPanel;
    private JTextField confirmarNueva;
    private JTextField contraseñaVieja;
    private JTextField nuevacontraseña;
    private JButton cambiarButton;
    private JButton volverButton;
    private JButton confirmarButton;

    public CambiarContrasenia(){
        confirmarNueva.setForeground(Color.white);
        contraseñaVieja.setForeground(Color.white);
        nuevacontraseña.setForeground(Color.white);
        this.setTitle("STG-GESTION DE ESTUDIANTES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.add(contraseniaPanel);
        this.setVisible(true);
    }

    public JTextField getConfirmarNueva() {
        return confirmarNueva;
    }

    public JTextField getContraseñaVieja() {
        return contraseñaVieja;
    }

    public JTextField getNuevacontraseña() {
        return nuevacontraseña;
    }

    public void cambiarButton(ActionListener listener){
        cambiarButton.addActionListener(listener);
    }

    public void volverButtonCambiarContrasenia(ActionListener listener){
        volverButton.addActionListener(listener);
    }
}
