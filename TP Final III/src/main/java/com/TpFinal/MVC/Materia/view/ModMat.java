package com.TpFinal.MVC.Materia.view;

import com.TpFinal.MVC.Materia.model.Entity.Materia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModMat extends JFrame{
    private JComboBox matBox;
    private JTextField txtNombre;
    private JTextField txtCode;
    private JButton buscarBtn;
    private JButton modBtn;
    private JButton volverBtn;
    private JPanel modPanel;

    public ModMat() throws HeadlessException {

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
        this.modBtn.setEnabled(false);
        this.txtNombre.setEditable(false);
        this.txtCode.setEditable(false);
        this.add(modPanel);
        this.setVisible(true);
    }

    public void buscarBtnListener(ActionListener actionListener){
        this.buscarBtn.addActionListener(actionListener);
    }
    public void modBtnListener(ActionListener actionListener){this.modBtn.addActionListener(actionListener);}

    public JComboBox getMatBox() {
        return matBox;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtCode() {
        return txtCode;
    }

    public JButton getModBtn() {
        return modBtn;
    }
}
