package com.TpFinal.MVC.Profesor.view;

import com.TpFinal.MVC.Comision.entity.Comision;
import com.TpFinal.MVC.Comision.view.ListCom;
import com.TpFinal.MVC.Materia.model.Entity.Materia;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionNotas extends JFrame{
    private JPanel gestNotPanel;
    private JTable table1;
    private JButton btnGuardar;
    private JButton btnVolver;
    private JComboBox<Materia> boxMat;
    private JButton btnBuscar;
    private JScrollPane scrollPane;
    private JComboBox<Comision> boxCom;
    private JButton btnLlenar;

    public GestionNotas() throws HeadlessException {

        setTitle("Lista de Estudiantes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        String[] columnNames = {"ID", "Nombre", "Notas"};

        boxMat.addItem(new Materia("ELIJA UNA MATERIA"));
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table1.getTableHeader().setReorderingAllowed(false);

        // Center alignment for cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table1.setDefaultRenderer(Object.class, centerRenderer);

        add(gestNotPanel);
        btnLlenar.setEnabled(false);
        btnGuardar.setEnabled(false);

        setVisible(true);


    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void buscarBtnListener(ActionListener listener){
       this.btnBuscar.addActionListener(listener);
    }

    public void llenarBtnListener(ActionListener listener){this.btnLlenar.addActionListener(listener);}

    public void guardarBtnListener(ActionListener listener){this.btnGuardar.addActionListener(listener);}

    public void updateTable(CustomTableModel model) {

        table1.setModel(model);
    }


    public JComboBox<Comision> getBoxCom() {
        return boxCom;
    }

    public JButton getBtnLlenar() {
        return btnLlenar;
    }

    public JComboBox<Materia> getBoxMat() {
        return boxMat;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public static class CustomTableModel extends DefaultTableModel {
        public CustomTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            // Hacer la columna de notas editable (columna 2, índice 2)
            return column == 4;
        }
    }
}
