package com.TpFinal.MVC.Comision.view;

import com.TpFinal.MVC.Materia.model.Entity.Materia;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModCom extends JFrame {
    private JComboBox<Materia> matBox;
    private JTextField txtNumAbuscar;
    private JComboBox<Profesor> profBox;
    private JTextField txtNum;
    private JButton modBtn;
    private JButton volverBtn;
    private JButton buscarBtn;
    private JPanel modPanel;

    public ModCom() throws HeadlessException {
        this.setTitle("STG-GESTION DE MATERIAS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.profBox.setEnabled(false);
        this.matBox.addItem(new Materia("Seleccione una materia"));
        this.modBtn.setEnabled(false);
        this.txtNum.setEditable(false);
        this.volverBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(modPanel);
        this.setVisible(true);
    }

    public void modBtnListener(ActionListener actionListener){this.modBtn.addActionListener(actionListener);}
    public void buscarBtnListener(ActionListener actionListener){this.buscarBtn.addActionListener(actionListener);}


    public JTextField getTxtNumAbuscar() {
        return txtNumAbuscar;
    }

    public JComboBox<Materia> getMatBox() {
        return matBox;
    }

    public JComboBox<Profesor> getProfBox() {
        return profBox;
    }

    public JTextField getTxtNum() {
        return txtNum;
    }

    public JButton getModBtn() {
        return modBtn;
    }
}
