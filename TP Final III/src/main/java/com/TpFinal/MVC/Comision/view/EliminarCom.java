package com.TpFinal.MVC.Comision.view;

import com.TpFinal.MVC.Materia.model.Entity.Materia;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarCom extends JFrame{
    private JPanel remCom;
    private JComboBox matBox;
    private JTextField txtNumCom;
    private JButton eliminarButton;
    private JButton volverButton;

    public EliminarCom() throws HeadlessException {
        this.setTitle("STG-GESTION DE MATERIAS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.matBox.addItem(new Materia("Seleccione una materia"));

        this.volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(remCom);
        this.setVisible(true);
    }

    public JTextField getTxtNumCom() {
        return txtNumCom;
    }

    public JComboBox getMatBox() {
        return matBox;
    }

    public void remBtnListener(ActionListener actionListener){this.eliminarButton.addActionListener(actionListener);}
}
