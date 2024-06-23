package com.TpFinal.MVC.Profesor.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerMaterias {
    private JPanel panelCursadas;

    public VerMaterias(Object data[][], String columnName[]) throws HeadlessException {

        NonEditableTableModel model = new NonEditableTableModel(data,columnName);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(new Color(70,72,73));
        table.getTableHeader().setForeground(Color.white);
        table.setBackground(new Color(70,72,73));
        table.setForeground(Color.white);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);


        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(59,59,59));
        btnVolver.setForeground(Color.white);


        JFrame jFrame = new JFrame("Lista de Cursadas");
        panelCursadas.setBackground(new Color(59,59,59));
        jFrame.add(panelCursadas);
        panelCursadas.setBackground(new Color(70, 72, 75));
        scrollPane.setBackground(new Color(70, 72, 75));
        scrollPane.getViewport().setBackground(new Color(70, 72, 75));
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(scrollPane);
        jFrame.add(btnVolver,BorderLayout.SOUTH);
        jFrame.setBounds(600, 200, 600, 400);
        jFrame.setVisible(true);
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
