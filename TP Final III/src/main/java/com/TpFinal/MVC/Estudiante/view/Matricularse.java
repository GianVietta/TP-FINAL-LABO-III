package com.TpFinal.MVC.Estudiante.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Matricularse extends JFrame{
    private JTextField matriculacion;
    private JButton matricularseButton;
    private JButton volverButton;
    private JPanel mainPanel;

    public Matricularse(){
        this.matriculacion.setForeground(Color.white);
        this.setTitle("STG-GESTION DE PROFESORES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public JTextField getMatriculacion(){
        return matriculacion;
    }

    public void matricularseButton(ActionListener listener){
        matricularseButton.addActionListener(listener);
    }

    public void volverButtonMatricularse(ActionListener listener){
        volverButton.addActionListener(listener);
    }
}
