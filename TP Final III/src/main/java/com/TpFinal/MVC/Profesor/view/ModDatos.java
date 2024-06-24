package com.TpFinal.MVC.Profesor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ModDatos extends JFrame{
    private JPanel modDatosPanel;
    private JTextField usuario;
    private JTextField repetirContraseña;
    private JTextField nuevaContrasenia;
    private JTextField contrasenia;
    private JButton confirmarButton;
    private JButton cambiarButton;
    private JButton volverButton;

    public ModDatos(){
        usuario.setForeground(Color.white);
        repetirContraseña.setForeground(Color.white);
        nuevaContrasenia.setForeground(Color.white);
        contrasenia.setForeground(Color.white);
        this.setTitle("STG-GESTION DE PROFESORES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.add(modDatosPanel);
        this.setVisible(true);
    }

    public JTextField getUsuario() {
        return usuario;
    }

    public JTextField getRepetirContraseña() {
        return repetirContraseña;
    }

    public JTextField getNuevaContrasenia() {
        return nuevaContrasenia;
    }

    public JTextField getContrasenia() {
        return contrasenia;
    }

    public void cambiarButton(ActionListener listener){
        cambiarButton.addActionListener(listener);
    }
    public void confirmarButton(ActionListener listener){
        confirmarButton.addActionListener(listener);
    }
    public void volverButton(ActionListener listener){
        volverButton.addActionListener(listener);
    }
    public void enabledButton(boolean enable){
        confirmarButton.setEnabled(enable);
    }
}
