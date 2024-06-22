package com.TpFinal.MVC.Estudiante.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RemoveEstudiante extends JFrame{
    private JPanel removePanel;
    private JTextField txtCarrera;
    private JButton removeButton;
    private JTextField txtLegajo;
    private JTextField txtDni;
    private JTextField txtApellido;
    private JTextField txtNombre;
    private JLabel modLabel;
    private JTextField txtSeatchDni;
    private JButton buscarButton;
    private JButton volverButton;

    public RemoveEstudiante() {

        this.txtNombre.setForeground(Color.white);
        this.txtApellido.setForeground(Color.white);
        this.txtDni.setForeground(Color.white);
        this.txtSeatchDni.setForeground(Color.white);
        this.txtLegajo.setForeground(Color.white);
        this.txtCarrera.setForeground(Color.white);
        this.txtNombre.setEditable(false);
        this.txtApellido.setEditable(false);
        this.txtDni.setEditable(false);
        this.txtLegajo.setEditable(false);
        this.txtCarrera.setEditable(false);
        this.setTitle("STG-GESTION DE ESTUDIANTES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.add(removePanel);
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

    public void removeBtnListener(ActionListener listener){this.removeButton.addActionListener(listener);}
    public void volverBtn(ActionListener listener){this.volverButton.addActionListener(listener);}
    public void enableRemoveBtn(boolean enabled) {
        removeButton.setEnabled(enabled);
    }

}
