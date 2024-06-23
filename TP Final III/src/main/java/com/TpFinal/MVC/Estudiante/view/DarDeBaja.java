package com.TpFinal.MVC.Estudiante.view;

import com.TpFinal.MVC.Materia.model.Entity.Materia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DarDeBaja extends JFrame{
    private JPanel dardebajamain;
    private JButton volverButton;
    private JButton darDeBajaButton;
    private JComboBox matBox;

    public DarDeBaja(){
        this.setTitle("STG-GESTION DE PROFESORES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        matBox.addItem(new Materia("ELIJA UNA MATERIA"));
        this.add(dardebajamain);
        this.setVisible(true);
    }

    public JComboBox getMatBox() {
        return matBox;
    }



    public void darDeBajaButton(ActionListener listener){
        darDeBajaButton.addActionListener(listener);
    }
}
