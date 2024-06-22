package com.TpFinal.MVC.Estudiante.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ModEstudiante extends JFrame{
    private JPanel ModEst;
    private JTextField txtCarrera;
    private JButton modificarButton;
    private JTextField txtLegajo;
    private JTextField txtDni;
    private JTextField txtApellido;
    private JTextField txtNombre;
    private JLabel modLabel;
    private JTextField txtSeatchDni;
    private JButton buscarButton;
    private JButton volverButton;

    public ModEstudiante() {

        this.txtNombre.setForeground(Color.white);
        this.txtApellido.setForeground(Color.white);
        this.txtDni.setForeground(Color.white);
        this.txtSeatchDni.setForeground(Color.white);
        this.txtLegajo.setForeground(Color.white);
        this.txtCarrera.setForeground(Color.white);
        this.setTitle("STG-GESTION DE ESTUDIANTES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.add(ModEst);
        this.setVisible(true);


    }

    public JTextField getTxtCarrera() {
        return txtCarrera;
    }

    public JTextField getTxtLegajo() {
        return txtLegajo;
    }

    public JTextField getTxtDni() {
        return txtDni;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtSeatchDni() {
        return txtSeatchDni;
    }

    public void buscarBtnListener(ActionListener listener){
        this.buscarButton.addActionListener(listener);
    }

    public void modBtnListener(ActionListener listener){this.modificarButton.addActionListener(listener);}
    public void volverBtn(ActionListener listener){this.volverButton.addActionListener(listener);}
    public void enableModBtn(boolean enabled) {
        modificarButton.setEnabled(enabled);
    }

}
