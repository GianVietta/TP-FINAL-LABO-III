package com.TpFinal.MVC.Materia.view;

import com.TpFinal.MVC.Comision.view.AddCom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMat extends JFrame{
    private JTextField txtNombreMat;
    private JTextField txtCodeMat;
    private JButton crearButton;
    private JButton volverButton;
    private JPanel addMatPanel;

    public AddMat() throws HeadlessException {

        this.setTitle("STG-GESTION DE MATERIAS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(addMatPanel);
        this.setVisible(true);
    }

    public void createBtnListener(ActionListener actionListener){this.crearButton.addActionListener(actionListener);}

    public JTextField getTxtNombreMat() {
        return txtNombreMat;
    }

    public JTextField getTxtCodeMat() {
        return txtCodeMat;
    }
}
