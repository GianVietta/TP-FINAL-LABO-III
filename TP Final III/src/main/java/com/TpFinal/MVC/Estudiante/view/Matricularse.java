package com.TpFinal.MVC.Estudiante.view;

import com.TpFinal.MVC.Comision.entity.Comision;
import com.TpFinal.MVC.Materia.model.Entity.Materia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Matricularse extends JFrame{


    private JPanel mainPanel;
    private JComboBox matBox;
    private JComboBox comBox;
    private JButton btnMatricularse;
    private JButton btnVolver;
    private JButton btnElegirMat;

    public Matricularse(){
        this.setTitle("STG-MATRICULARSE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.btnMatricularse.setEnabled(false);
        this.comBox.setEnabled(true);
        this.matBox.addItem(new Materia("SELECCIONE UNA MATERIA"));
        this.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(mainPanel);

        this.setVisible(true);
    }

    public JComboBox getMatBox() {
        return matBox;
    }

    public JComboBox getComBox() {
        return comBox;
    }

    public JButton getBtnMatricularse() {
        return btnMatricularse;
    }

    public void elegirMatListener(ActionListener listener){this.btnElegirMat.addActionListener(listener);}
    public void matricularseBtnListener(ActionListener actionListener){this.btnMatricularse.addActionListener(actionListener);}


}
