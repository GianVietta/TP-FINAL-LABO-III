package com.TpFinal.MVC.Profesor.controller;

import com.TpFinal.Exceptions.AlreadyExistException;
import com.TpFinal.Exceptions.DontExistException;
import com.TpFinal.Exceptions.EmptyFieldException;
import com.TpFinal.Exceptions.InvalidNumberException;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Estudiante.view.ModEstudiante;
import com.TpFinal.MVC.Estudiante.view.RemoveEstudiante;
import com.TpFinal.MVC.Materia.model.repository.MateriaRepository;
import com.TpFinal.MVC.Profesor.view.CreateProfesor;
import com.TpFinal.MVC.Profesor.view.ListaProfesores;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;
import com.TpFinal.MVC.Profesor.model.repository.ProfesorRepository;
import com.TpFinal.MVC.Profesor.view.ModProfesor;
import com.TpFinal.MVC.Profesor.view.RemoveProfesor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfesorControler {
    private ProfesorRepository profesorRepository;
    private MateriaRepository materiaRepository;

    public ProfesorControler(ProfesorRepository profesorRepository, MateriaRepository materiaRepository) {
        this.profesorRepository = profesorRepository;
        this.materiaRepository = materiaRepository;
    }

    public ProfesorRepository getProfesorRepository() {
        return profesorRepository;
    }

    public MateriaRepository getMateriaRepository() {
        return materiaRepository;
    }

    public void agregarProfesor() {
        CreateProfesor createProfesor = new CreateProfesor();
        class CreateListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nombretxt = createProfesor.getNombre();
                JTextField apellidotxt = createProfesor.getApellido();
                JTextField dnitxt = createProfesor.getDni();
                JTextField especialidad = createProfesor.getEspecialidad();
                try {
                    if (nombretxt.getText().isEmpty() || apellidotxt.getText().isEmpty() || dnitxt.getText().isEmpty() || especialidad.getText().isEmpty()) {
                        throw new EmptyFieldException("Todos los campos son obligatorios.");
                    }

                    int dninumber;
                    try {
                        dninumber = Integer.parseInt(dnitxt.getText());
                    } catch (NumberFormatException ex) {
                        throw new InvalidNumberException("El dni solo debe contener numeros");
                    }
                    dninumber = Integer.valueOf(dnitxt.getText());

                    Profesor profesor = new Profesor(nombretxt.getText(), apellidotxt.getText(), dninumber, especialidad.getText());

                    if (profesorRepository.find(profesor) != null) {
                        throw new AlreadyExistException("El profesor ya existe");
                    }
                    profesorRepository.add(profesor);
                    profesorRepository.saveHash();
                    nombretxt.setText("");
                    apellidotxt.setText("");
                    dnitxt.setText("");
                    especialidad.setText("");
                    JOptionPane.showMessageDialog(createProfesor, "Profesor ingresado con éxito.");
                } catch (EmptyFieldException | InvalidNumberException | AlreadyExistException ex) {
                    JOptionPane.showMessageDialog(createProfesor, "Error: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(createProfesor, "Error inesperado: " + ex.getMessage());
                }
            }

        }

        class VolverListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                createProfesor.dispose();
            }
        }

        CreateListener listener = new CreateListener();
        createProfesor.CreateBtnProfesorListener(listener);
        createProfesor.volverBtnListener(new VolverListener());
        createProfesor.setVisible(true);
    }

    public void eliminarProfesor() {
        RemoveProfesor removeProfesor = new RemoveProfesor();
        Profesor profesor;
        class BuscarBtnListener implements ActionListener{
            Profesor pro=null;
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JTextField txtSearchDni = removeProfesor.getBuscarDni();
                    if (!txtSearchDni.getText().isEmpty()){
                        Integer dni = Integer.valueOf(txtSearchDni.getText());

                        pro = profesorRepository.find(new Profesor(dni));
                        if (pro!=null){
                            JTextField txtNombre = removeProfesor.getNombre();
                            JTextField txtApellido = removeProfesor.getApellido();
                            JTextField txtDni = removeProfesor.getDni();
                            JTextField txtEspecialidad = removeProfesor.getEspecialidad();

                            txtNombre.setText(pro.getNombre());
                            txtApellido.setText(pro.getApellido());
                            txtDni.setText(String.valueOf(pro.getDni()));
                            txtEspecialidad.setText(String.valueOf(pro.getEspecialidad()));

                            removeProfesor.enableRemoveBtn(true);

                        }else {
                            throw new DontExistException("EL DNI NO ESTA ASOCIADO A NINGUN PROFESOR");
                        }
                    }else {
                        throw new EmptyFieldException("ANTES DE BUSCAR DEBES COMPLETAR EL CAMPO DE BUSQUEDA.");
                    }

                }catch (DontExistException | EmptyFieldException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }

            }
            public Profesor getPro(){return this.pro;}

        }


        BuscarBtnListener buscarBtnListener = new BuscarBtnListener();
        profesor=buscarBtnListener.getPro();


        class RemoveBtnListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField txtNombre = removeProfesor.getNombre();
                JTextField txtApellido = removeProfesor.getApellido();
                JTextField txtDni = removeProfesor.getDni();
                JTextField txtEspecialidad = removeProfesor.getEspecialidad();
                JTextField txtDniSearch  = removeProfesor.getDni();
                Profesor profesor1;
                try{
                    if (buscarBtnListener.getPro() == null) {
                        throw new EmptyFieldException("PRIMERO DEBES BUSCAR UN ESTUDIANTE.");
                    }else {
                        profesor1=buscarBtnListener.getPro();

                        profesorRepository.remove(profesor1);
                        profesorRepository.saveHash();
                        JOptionPane.showMessageDialog(null,"PROFESOR ELIMINADO CON EXITO");
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtDni.setText("");
                        txtEspecialidad.setText("");
                        removeProfesor.enableRemoveBtn(false);
                    }

                }catch (EmptyFieldException exception){

                }

            }
        }
        class VolverBtnRetListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProfesor.dispose();
            }
        }

        removeProfesor.volverBtn(new VolverBtnRetListener());
        removeProfesor.removeBtnListener(new RemoveBtnListener());
        removeProfesor.buscarBtnListener(buscarBtnListener);
        removeProfesor.setVisible(true);
    }

    public void modProfesor() {
        ModProfesor modProfesor = new ModProfesor();
        Profesor profesor;
        class BuscarBtnListener implements ActionListener{
            Profesor pro=null;
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JTextField txtSearchDni = modProfesor.getBuscarDni();
                    if (!txtSearchDni.getText().isEmpty()){
                        Integer dni = Integer.valueOf(txtSearchDni.getText());

                        pro = profesorRepository.find(new Profesor(dni));
                        if (pro!=null){
                            JTextField txtNombre = modProfesor.getNombre();
                            JTextField txtApellido = modProfesor.getApellido();
                            JTextField txtDni = modProfesor.getDni();
                            JTextField txtEspecialidad = modProfesor.getEspecialidad();

                            txtNombre.setText(pro.getNombre());
                            txtApellido.setText(pro.getApellido());
                            txtDni.setText(String.valueOf(pro.getDni()));
                            txtEspecialidad.setText(pro.getEspecialidad());

                            modProfesor.enableButton(true);

                        }else {
                            throw new DontExistException("EL DNI NO ESTA ASOCIADO A NINGUN PROFESOR");
                        }
                    }else {
                        throw new EmptyFieldException("ANTES DE BUSCAR DEBES COMPLETAR EL CAMPO DE BUSQUEDA.");
                    }

                }catch (DontExistException | EmptyFieldException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }

            }
            public Profesor getPro(){return this.pro;}

        }


        BuscarBtnListener buscarBtnListener = new BuscarBtnListener();
        profesor = buscarBtnListener.getPro();


        class ModBtnListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField txtNombre = modProfesor.getNombre();
                JTextField txtApellido = modProfesor.getApellido();
                JTextField txtDni = modProfesor.getDni();
                JTextField txtEspecialidad = modProfesor.getEspecialidad();
                JTextField txtBuscarDni  = modProfesor.getDni();
                Profesor profesor1;
                try{
                    if (buscarBtnListener.getPro() == null) {
                        throw new EmptyFieldException("PRIMERO DEBES BUSCAR UN ESTUDIANTE.");
                    }else {
                        profesor1=buscarBtnListener.getPro();
                    }
                    if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtDni.getText().isEmpty() || txtEspecialidad.getText().isEmpty()){
                        throw new EmptyFieldException("TODOS LOS CAMPOS DEBEN TENER DATOS");
                    }else {
                        int dniNumber;
                        try {
                            dniNumber = Integer.parseInt(txtDni.getText());
                        } catch (NumberFormatException ex) {
                            throw new InvalidNumberException("El DNI debe contener solo números.");
                        }
                        dniNumber = Integer.valueOf(txtDni.getText());
                        profesor1.setNombre(txtNombre.getText());
                        profesor1.setApellido(txtApellido.getText());
                        profesor1.setDni(dniNumber);
                        profesor1.setEspecialidad(txtEspecialidad.getText());
                        profesorRepository.update(profesor1);
                        profesorRepository.saveHash();
                        JOptionPane.showMessageDialog(null,"MODIFICACION CORRECTAMENTE REALIZADA");
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtDni.setText("");
                        txtEspecialidad.setText("");
                        modProfesor.enableButton(false);
                    }

                }catch (EmptyFieldException exception){

                }

            }
        }

        class volverBtnListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                modProfesor.dispose();
            }
        }

        modProfesor.buscarButton(buscarBtnListener);
        modProfesor.modificarButton(new ModBtnListener());
        modProfesor.volverButton(new volverBtnListener());
        modProfesor.setVisible(true);
    }


    public void listaProfesores() {

        String[] columnaName = {"Nombre", "Apellido", "Dni", "Especialidad", "Id"};
        Object [][] data = new Object[this.profesorRepository.getProfesorHashSet().size()][5];

        int row=0;
        for (Profesor profesor : profesorRepository.getProfesorHashSet()){
            data[row][0] = profesor.getNombre();
            data[row][1] = profesor.getApellido();
            data[row][2] = profesor.getDni();
            data[row][3] = profesor.getEspecialidad();
            data[row][4] = profesor.getId();
            row++;
        }
        ListaProfesores listaProfesores = new ListaProfesores(data,columnaName);
    }


}
