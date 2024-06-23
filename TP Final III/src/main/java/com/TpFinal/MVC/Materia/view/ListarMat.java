package com.TpFinal.MVC.Materia.view;

import com.TpFinal.MVC.Comision.view.ListCom;
import com.TpFinal.MVC.Materia.model.Entity.Materia;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListarMat extends JFrame{
    private JPanel listPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton btnVolver;

    public ListarMat(Object[][] data, String[] columnNames) throws HeadlessException {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        scrollPane.setBackground(new Color(70, 72, 75));
        scrollPane.getViewport().setBackground(new Color(70, 72, 75));
        NonEditableTableModel model = new NonEditableTableModel(data, columnNames);
        table.setModel(model);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(listPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600, 200, 600, 400);
        this.setVisible(true);
    }

    public class NonEditableTableModel extends DefaultTableModel {
        public NonEditableTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    }
}
