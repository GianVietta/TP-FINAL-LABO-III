package com.TpFinal.MVC.Estudiante.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DarDeBaja extends JFrame{
    private JPanel dardebajamain;
    private JTextField darDeBajatxt;
    private JButton volverButton;
    private JButton darDeBajaButton;

    public DarDeBaja(){
        this.darDeBajatxt.setForeground(Color.white);
        this.setTitle("STG-GESTION DE PROFESORES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.add(dardebajamain);
        this.setVisible(true);
    }

    public JTextField getDarDeBajatxt() {
        return darDeBajatxt;
    }

    public void volverButtonDarDeBaja(ActionListener listener){
        volverButton.addActionListener(listener);
    }

    public void darDeBajaButton(ActionListener listener){
        darDeBajaButton.addActionListener(listener);
    }
}
