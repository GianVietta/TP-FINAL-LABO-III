package com.TpFinal.MVC.Comision.view;

import com.TpFinal.MVC.Materia.model.Entity.Materia;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListCom extends JFrame{
    private JPanel listCom;
    private JComboBox<Materia> matBox;
    private JButton mostrarBtn;
    private JButton btnVolver;
    private JTable table;
    private JScrollPane scrollPane;

    public ListCom() throws HeadlessException {

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        matBox.addItem(new Materia("Seleccione una materia para listar sus comisiones"));
        scrollPane.setBackground(new Color(70, 72, 75));
        scrollPane.getViewport().setBackground(new Color(70, 72, 75));
        matBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(listCom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600, 200, 600, 400);
        this.setVisible(true);
    }


    public JComboBox<Materia> getMatBox() {
        return matBox;
    }

    public void buscarBtnListener(ActionListener actionListener){this.mostrarBtn.addActionListener(actionListener);}

    public void updateTable(Object[][] data, String[] columnNames) {
        NonEditableTableModel model = new NonEditableTableModel(data, columnNames);
        table.setModel(model);
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
