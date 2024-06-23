package com.TpFinal.MVC.Comision.view;

import com.TpFinal.MVC.Materia.model.Entity.Materia;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCom extends JFrame{
    private JComboBox<Materia> materiasBox;
    private JComboBox<Profesor> profesoresBox;
    private JTextField txtNmbCom;
    private JButton crearButton;

    private JButton volverButton;
    private JPanel addComPanel;

    public AddCom() throws HeadlessException {

        this.setTitle("STG-GESTION DE MATERIAS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        this.materiasBox.addItem(new Materia("Seleccione una materia"));
        this.profesoresBox.addItem(new Profesor("Seleccione un profesor","",0,""));

        this.volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(addComPanel);
        this.setVisible(true);

    }

    public JComboBox<Materia> getMateriasBox() {
        return materiasBox;
    }

    public void setMateriasBox(JComboBox<Materia> materiasBox) {
        this.materiasBox = materiasBox;
    }

    public JComboBox<Profesor> getProfesoresBox() {
        return profesoresBox;
    }

    public void setProfesoresBox(JComboBox<Profesor> profesoresBox) {
        this.profesoresBox = profesoresBox;
    }

    public JTextField getTxtNmbCom() {
        return txtNmbCom;
    }

    public void createComBtn(ActionListener actionListener){this.crearButton.addActionListener(actionListener);}

}
