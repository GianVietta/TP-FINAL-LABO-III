package com.TpFinal.MVC.Materia.controller;

import com.TpFinal.Exceptions.AlreadyExistException;
import com.TpFinal.Exceptions.EmptyFieldException;
import com.TpFinal.Exceptions.InvalidNumberException;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Estudiante.view.ListadoEstudiantes;
import com.TpFinal.MVC.Materia.model.Entity.Materia;
import com.TpFinal.MVC.Materia.model.repository.MateriaRepository;
import com.TpFinal.MVC.Materia.view.AddMat;
import com.TpFinal.MVC.Materia.view.EliminarMateria;
import com.TpFinal.MVC.Materia.view.ListarMat;
import com.TpFinal.MVC.Materia.view.ModMat;
import com.TpFinal.MVC.Profesor.model.repository.ProfesorRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MateriaController {
    private MateriaRepository materiaRepository;
    private ProfesorRepository profesorRepository;

    public MateriaController(MateriaRepository materiaRepository, ProfesorRepository profesorRepository) {
        this.materiaRepository = materiaRepository;
        this.profesorRepository = profesorRepository;
    }

    public  void agregarMateria() {
        AddMat addMat = new AddMat();
         class addBtnListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtNombreMat = addMat.getTxtNombreMat();
                JTextField txtCode = addMat.getTxtCodeMat();
                try{
                    if(txtNombreMat.getText().isEmpty() || txtCode.getText().isEmpty()){
                        throw new EmptyFieldException("Todos los campos son obligatorios");
                    }
                    Integer code;
                    try {
                        code = Integer.parseInt(txtCode.getText());
                    } catch (NumberFormatException ex) {
                        throw new InvalidNumberException("El Codigo debe contener solo números.");
                    }

                       code=Integer.parseInt(txtCode.getText());
                    if (materiaRepository.find(new Materia(txtNombreMat.getText()))==null){
                       if (materiaRepository.findXcode(code)==null){
                           materiaRepository.add(new Materia(txtNombreMat.getText(),code));
                           materiaRepository.saveList();
                           JOptionPane.showMessageDialog(null,"MATERIA CREADA CON EXITO");
                       }else {
                           throw new AlreadyExistException("EL CODIGO DE LA MATERIA YA ESTA EN USO");
                       }
                    }else {
                     throw new AlreadyExistException("EL NOMBRE DE LA MATERIA YA ESTA EN USO");
                    }
                }catch (EmptyFieldException | InvalidNumberException | AlreadyExistException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        }
        addMat.createBtnListener(new addBtnListener());
        addMat.setVisible(true);

    }

    public  void removeMateria() {
        EliminarMateria eliminarMateria = new EliminarMateria();
        JComboBox<Materia> matsBox = eliminarMateria.getMatBox();
        for (Materia mat : materiaRepository.getListaMaterias()){
            matsBox.addItem(mat);
        }
        class removeBtnListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (matsBox.getSelectedIndex() == 0){
                        throw new EmptyFieldException("DEBES ELEGIR UNA MATERIA PARA ELIMINAR");
                    }

                    Materia selectedMat =(Materia) matsBox.getSelectedItem();
                    materiaRepository.remove(selectedMat);
                    materiaRepository.saveList();
                    JOptionPane.showMessageDialog(null,"MATERIA ELIMINADA EXITOSAMENTE");

                }catch (EmptyFieldException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }
            }
        }
        eliminarMateria.eliminarBtnListener(new removeBtnListener());
        eliminarMateria.setVisible(true);

    }

    public MateriaRepository getMateriaRepository() {
        return materiaRepository;
    }

    public void setMateriaRepository(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public void modMateria() {
        ModMat modMat = new ModMat();
        JComboBox<Materia> matsBox = modMat.getMatBox();
        for (Materia mat : materiaRepository.getListaMaterias()){
            matsBox.addItem(mat);
        }

        class BuscarBtnListener implements ActionListener{
            Materia materia;

            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtNombre = modMat.getTxtNombre();
                JTextField txtCode = modMat.getTxtCode();

                try{
                    if (matsBox.getSelectedIndex()==0){
                        throw new EmptyFieldException("PRIMERO DEBES ELEGIR UNA MATERIA");
                    }
                    materia = (Materia) matsBox.getSelectedItem();
                    txtNombre.setText(materia.getNombre());
                    txtCode.setText(String.valueOf(materia.getCodigo()));
                    txtNombre.setEditable(true);
                    txtCode.setEditable(true);
                    modMat.getModBtn().setEnabled(true);


                }catch (EmptyFieldException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }
            }

            public BuscarBtnListener() {
            }

            public Materia getMateria() {
                return materia;
            }
        }

        BuscarBtnListener buscarBtnListener = new BuscarBtnListener();
        modMat.buscarBtnListener(buscarBtnListener);

        class ModBtnListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                Materia mat=buscarBtnListener.getMateria();
                JTextField txtNombre= modMat.getTxtNombre();
                JTextField txtCode = modMat.getTxtCode();
                try{
                    Integer code;
                    if (txtNombre.getText().isEmpty() || txtCode.getText().isEmpty()){
                        throw new EmptyFieldException("NO PUEDEN HABER CAMPOS VACIOS");
                    }
                    try{
                        code=Integer.parseInt(txtCode.getText());
                    }catch (NumberFormatException ex){
                        throw new InvalidNumberException("EL CODIGO DEBE CONTENER SOLO NUMEROS");
                    }
                    code=Integer.parseInt(txtCode.getText());
                    int i=materiaRepository.getListaMaterias().indexOf(mat);
                    mat.setCodigo(code);
                    mat.setNombre(txtNombre.getText());
                    materiaRepository.getListaMaterias().set(i,mat);
                    materiaRepository.saveList();
                    txtCode.setText("");
                    txtNombre.setText("");
                    txtCode.setEditable(false);
                    txtNombre.setEditable(false);
                    modMat.getModBtn().setEnabled(false);
                }catch (EmptyFieldException | InvalidNumberException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }


            }
        }
        modMat.modBtnListener(new ModBtnListener());
        modMat.setVisible(true);

    }

    public void viewMatList() {
        String[] columnaName={"CODIGO","NOMBRE DE MATERIA"};
        Object [][] data=new Object[this.materiaRepository.getListaMaterias().size()][2];

        int row=0;
        for(Materia materia: materiaRepository.getListaMaterias() ){
            data[row][0] = materia.getCodigo();
            data[row][1]= materia.getNombre();
            row++;
        }
        ListarMat listarMat = new ListarMat(data,columnaName);
    }
}
