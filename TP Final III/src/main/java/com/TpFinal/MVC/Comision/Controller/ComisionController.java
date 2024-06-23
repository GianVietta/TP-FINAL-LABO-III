package com.TpFinal.MVC.Comision.Controller;

import com.TpFinal.Exceptions.AlreadyExistException;
import com.TpFinal.Exceptions.DontExistException;
import com.TpFinal.Exceptions.EmptyFieldException;
import com.TpFinal.Exceptions.InvalidNumberException;
import com.TpFinal.MVC.Comision.entity.Comision;
import com.TpFinal.MVC.Comision.view.AddCom;
import com.TpFinal.MVC.Comision.view.EliminarCom;
import com.TpFinal.MVC.Comision.view.ListCom;
import com.TpFinal.MVC.Comision.view.ModCom;
import com.TpFinal.MVC.Estudiante.model.Repository.EstudianteRepository;
import com.TpFinal.MVC.Materia.model.Entity.Materia;
import com.TpFinal.MVC.Materia.model.repository.MateriaRepository;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;
import com.TpFinal.MVC.Profesor.model.repository.ProfesorRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class ComisionController {
    private MateriaRepository materiaRepository;
    private ProfesorRepository profesorRepository;

    private EstudianteRepository estudianteRepository;

    public ComisionController(MateriaRepository materiaRepository, ProfesorRepository profesorRepository, EstudianteRepository estudianteRepository) {
        this.materiaRepository = materiaRepository;
        this.profesorRepository = profesorRepository;
        this.estudianteRepository = estudianteRepository;
    }

    public void agregarComision() {
        AddCom addCom = new AddCom();
        JComboBox<Materia> matsBox = addCom.getMateriasBox();
        JComboBox<Profesor> profesBox = addCom.getProfesoresBox();

        for (Materia mat : materiaRepository.getListaMaterias()){
            matsBox.addItem(mat);
        }
        for (Profesor profesor : profesorRepository.getProfesorHashSet()){
            profesBox.addItem(profesor);
        }
        class createBtnListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField txtNumCom = addCom.getTxtNmbCom();
                try{

                    if (txtNumCom.getText().isEmpty()){
                        throw new EmptyFieldException("Todos los campos son obligatorios");
                    }
                    if (matsBox.getSelectedIndex() == 0 || profesBox.getSelectedIndex() == 0) {
                        throw new EmptyFieldException("Debe seleccionar una materia y un profesor");
                    }

                    int comNum;
                    try {
                        comNum= parseInt(txtNumCom.getText());
                    }catch (NumberFormatException ex){
                        throw new InvalidNumberException("EL NUMERO DE COMISION NO PUEDE CONTENER CARACTERES DISTINTOS A NUMEROS ");
                    }

                    comNum= parseInt(txtNumCom.getText());
                    Materia selectedMateria = (Materia) matsBox.getSelectedItem();
                    Profesor selectedProfesor = (Profesor) profesBox.getSelectedItem();
                    if (selectedMateria.buscarCom(comNum)!=null){
                        throw new AlreadyExistException("EN ESTA MATERIA YA EXISTE UNA COMISION CON ESE NUMERO");
                    }
                    selectedMateria.getMapComisiones().put(comNum,new Comision(selectedProfesor,comNum));
                    materiaRepository.update(selectedMateria);
                    materiaRepository.saveList();
                    JOptionPane.showMessageDialog(null,"COMISION AGREGADA CON EXITO");

                }catch (InvalidNumberException | AlreadyExistException | EmptyFieldException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }
            }
        }
        addCom.createComBtn(new createBtnListener());
        addCom.setVisible(true);

    }

    public void removeComision(){
        EliminarCom eliminarCom = new EliminarCom();
        JComboBox<Materia> matsBox = eliminarCom.getMatBox();
        for (Materia mat : materiaRepository.getListaMaterias()){
            matsBox.addItem(mat);
        }
        class remBtnListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtNum = eliminarCom.getTxtNumCom();
            try {


                if (matsBox.getSelectedIndex()==0 || txtNum.getText().isEmpty()){
                    throw new EmptyFieldException("DEBE COMPLETAR TODOS LOS CAMPOS");
                }

                Integer numCOm;
                try{
                    numCOm= parseInt(txtNum.getText());
                }catch (NumberFormatException ex){
                    throw new InvalidNumberException("EL NUMERO DE COMISION NO PUEDE CONTENER LETRAS");
                }

                Materia mat=(Materia) matsBox.getSelectedItem();
                if (mat.buscarCom(numCOm)==null){
                   throw new DontExistException("NO HAY UNA COMISION BAJO ESTE MISMO NUMERO");
                }
                mat.getMapComisiones().remove(numCOm);
                materiaRepository.update(mat);
                materiaRepository.saveList();
                JOptionPane.showMessageDialog(null,"COMISION ELIMINADA CORRECTAMENTE");
            }catch (InvalidNumberException | EmptyFieldException | DontExistException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            }
        }
        eliminarCom.remBtnListener(new remBtnListener());
        eliminarCom.setVisible(true);
    }

    public void modComision(){
        ModCom modCom = new ModCom();
        JComboBox<Materia> materiaBox = modCom.getMatBox();
        for (Materia mat : materiaRepository.getListaMaterias()){
            materiaBox.addItem(mat);
        }
        JComboBox<Profesor> profesorBox = modCom.getProfBox();

        class BuscarBtnListener implements ActionListener{
            Materia materia;
            Integer numBuscar;

            public BuscarBtnListener() {
            }

            public Materia getMateria() {
                return materia;
            }

            public Integer getNumBuscar() {
                return numBuscar;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtNumBuscar = modCom.getTxtNumAbuscar();
                JTextField txtNum = modCom.getTxtNum();
                try{
                    if (materiaBox.getSelectedIndex()==0 || txtNumBuscar.getText().isEmpty()){
                        throw new EmptyFieldException("DEBE COMPLETAR LOS CAMPOS DE MATERIA Y DE NUMERO A BUSCAR");
                    }

                    try{
                        numBuscar=Integer.parseInt(txtNumBuscar.getText());
                    }catch (NumberFormatException ex){
                        throw new InvalidNumberException("EL NUMERO DE LA COMISION SOLO DEBE CONTENER NUMEROS");
                    }
                    numBuscar=parseInt(txtNumBuscar.getText());
                     materia = (Materia) materiaBox.getSelectedItem();
                    if (materia.buscarCom(numBuscar)==null){
                        throw new DontExistException("NO HAY COMISION CON ESE NUMERO EN LA MATERIA SELECIONADA");
                    }
                    profesorBox.addItem(materia.buscarCom(numBuscar).getProfesor());
                    profesorBox.setEnabled(true);
                    txtNum.setText(String.valueOf(numBuscar));
                    txtNum.setEditable(true);
                    modCom.getModBtn().setEnabled(true);
                    for (Profesor profesor : profesorRepository.getProfesorHashSet()){
                        if (!profesor.equals(profesorBox.getItemAt(0))){
                            profesorBox.addItem(profesor);
                        }
                    }

                }catch (EmptyFieldException | DontExistException | InvalidNumberException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }
            }
        }


        BuscarBtnListener buscarBtnListener=new BuscarBtnListener();
        modCom.buscarBtnListener(buscarBtnListener);


        class modBtnListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtNum = modCom.getTxtNum();
                try{
                    if (txtNum.getText().isEmpty()){
                        throw new EmptyFieldException("EL CAMPO DE NUMERO DE COMISION NO DEBE ESTAR VACIO");
                    }
                    Integer num;
                    try{
                        num=Integer.parseInt(txtNum.getText());
                    }catch (NumberFormatException exception){
                        throw new InvalidNumberException("EL NUMERO DE COMISION CONTIENE CARACTERES INVALIDOS");
                    }
                    num=parseInt(txtNum.getText());
                    Materia mat = buscarBtnListener.getMateria();
                    if (num!=buscarBtnListener.numBuscar){
                        if (mat.buscarCom(num)!=null){
                            throw new AlreadyExistException("El NUMERO DE COMISION YA ESTA ASIGNADO A OTRA COMISION");
                        }
                    }
                    Comision com = mat.buscarCom(buscarBtnListener.numBuscar);
                    com.setProfesor((Profesor) profesorBox.getSelectedItem());
                    com.setNumeroComision(num);
                    mat.getMapComisiones().remove(buscarBtnListener.numBuscar);
                    mat.getMapComisiones().put(num,com);
                    materiaRepository.update(mat);
                    materiaRepository.saveList();
                    profesorBox.setEnabled(false);
                    profesorBox.removeAllItems();
                    txtNum.setEditable(false);
                    txtNum.setText("");
                    modCom.getModBtn().setEnabled(false);
                    JOptionPane.showMessageDialog(null,"MODIFICACION REALIZADA");

                }catch (EmptyFieldException | InvalidNumberException | AlreadyExistException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }
            }
        }


    modCom.modBtnListener(new modBtnListener());
       modCom.setVisible(true);

    }
    public void listComision(){
        ListCom listCom = new ListCom();
        JComboBox<Materia> matBox = listCom.getMatBox();
        for (Materia materia : materiaRepository.getListaMaterias()){
            matBox.addItem(materia);
        }

        class BuscarBtnListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (matBox.getSelectedIndex()==0){
                        throw new EmptyFieldException("PRIMERO DEBES ELEGIR UNA MATERIA");
                    }
                    Materia selectedMat =(Materia) matBox.getSelectedItem();
                    if (selectedMat.getMapComisiones()==null){
                        throw new EmptyFieldException("LA MATERIA SELECCIONADA NO CUENTA CON COMISIONES");
                    }
                    HashMap<Integer,Comision> comisiones = selectedMat.getMapComisiones();
                    Object[][] data = new Object[comisiones.size()][2];
                    int index = 0;
                    for (HashMap.Entry<Integer, Comision> entry : comisiones.entrySet()) {
                        data[index][0] = entry.getKey();
                        data[index][1] = entry.getValue().getProfesor().toString();
                        index++;
                    }
                    String[] columnNames = {"Número de Comisión", "Profesor"};
                    listCom.updateTable(data, columnNames);

                }catch (EmptyFieldException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }
            }
        }

        listCom.buscarBtnListener(new BuscarBtnListener());
        listCom.setVisible(true);

    }
}
