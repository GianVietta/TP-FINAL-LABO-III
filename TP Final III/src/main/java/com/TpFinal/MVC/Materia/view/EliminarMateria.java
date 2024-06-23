package com.TpFinal.MVC.Materia.view;

import com.TpFinal.MVC.Materia.model.Entity.Materia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarMateria extends JFrame{
    private JComboBox<Materia> matBox;
    private JPanel eliminarPanel;
    private JButton eliminarButton;
    private JButton volverBtn;

    public EliminarMateria() throws HeadlessException {
        this.setTitle("STG-GESTION DE MATERIAS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.matBox.addItem(new Materia("Seleccione una materia"));
        this.volverBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(eliminarPanel);
        this.setVisible(true);
    }

    public void eliminarBtnListener(ActionListener actionListener){this.eliminarButton.addActionListener(actionListener);}

    public JComboBox<Materia> getMatBox() {
        return matBox;
    }
}
