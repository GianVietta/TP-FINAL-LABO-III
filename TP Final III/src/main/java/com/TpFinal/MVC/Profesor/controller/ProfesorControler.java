package com.TpFinal.MVC.Profesor.controller;

import com.TpFinal.Exceptions.AlreadyExistException;
import com.TpFinal.Exceptions.DontExistException;
import com.TpFinal.Exceptions.EmptyFieldException;
import com.TpFinal.Exceptions.InvalidNumberException;
import com.TpFinal.MVC.Comision.entity.Comision;
import com.TpFinal.MVC.Estudiante.model.Repository.EstudianteRepository;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Estudiante.view.ModEstudiante;
import com.TpFinal.MVC.Estudiante.view.RemoveEstudiante;
import com.TpFinal.MVC.Estudiante.view.VerCursadas;
import com.TpFinal.MVC.Materia.model.Entity.Materia;
import com.TpFinal.MVC.Materia.model.repository.MateriaRepository;
import com.TpFinal.MVC.Profesor.view.*;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;
import com.TpFinal.MVC.Profesor.model.repository.ProfesorRepository;
import com.TpFinal.MVC.Users.Model.entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ProfesorControler {
    private ProfesorRepository profesorRepository;
    private MateriaRepository materiaRepository;

    private EstudianteRepository estudianteRepository;

    public ProfesorControler(ProfesorRepository profesorRepository, MateriaRepository materiaRepository,EstudianteRepository estudianteRepository) {
        this.profesorRepository = profesorRepository;
        this.materiaRepository = materiaRepository;
        this.estudianteRepository=estudianteRepository;
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
                        for (Materia mat : materiaRepository.getListaMaterias()){
                            if (mat.buscarPro(profesor1)!=null){
                               throw new AlreadyExistException("EL PROFESOR ESTA ACTUALMENTE EN UNA COMISION PRIMERO DEBES LIBERARLO");
                            }
                        }
                        profesorRepository.remove(profesor1);
                        profesorRepository.saveHash();
                        JOptionPane.showMessageDialog(null,"PROFESOR ELIMINADO CON EXITO");
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtDni.setText("");
                        txtEspecialidad.setText("");
                        removeProfesor.enableRemoveBtn(false);
                    }

                }catch (EmptyFieldException | AlreadyExistException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
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
                Materia materia=null;
                Comision com = null;
                try{
                    if (buscarBtnListener.getPro() == null) {
                        throw new EmptyFieldException("PRIMERO DEBES BUSCAR UN ESTUDIANTE.");
                    }else {
                        profesor1=buscarBtnListener.getPro();
                        for (Materia mat : materiaRepository.getListaMaterias()){
                            if (mat.buscarPro(profesor1)!=null){
                                materia=mat;
                                com=materia.buscarPro(profesor1);
                            }
                        }
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
                        if (materia!=null){
                         com.setProfesor(profesor1);
                         materia.getMapComisiones().put(com.getNumeroComision(),com);
                         materiaRepository.update(materia);
                         materiaRepository.saveList();
                        }
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


    public void menuProfesor(User<?> u) {
        MenuProfe menuProfe = new MenuProfe();

        menuProfe.gestionNotasListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionNotas((Profesor) u.getT());
            }
        });

        menuProfe.verMateriasListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verMaterias((Profesor) u.getT());
            }
        });

        menuProfe.cambiarDatosListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            cambiarDatos((User<Profesor>) u);
            }
        });

        menuProfe.cambiarContraseniaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });







    }

    private void verMaterias(Profesor profesor) {

            String[] columnaName={"CODIGO","NOMBRE DE MATERIA","NUMERO COMISION","PROFESOR"};
            Object [][] data=new Object[this.materiaRepository.getListaMaterias().size()][4];

            int row=0;
            for(Materia materia: materiaRepository.getListaMaterias() ){
                if (materia.buscarPro(profesor)!=null) {
                        Comision comision = materia.buscarPro(profesor);
                        data[row][0] = materia.getCodigo();
                        data[row][1] = materia.getNombre();
                        data[row][2] = comision.getNumeroComision();
                        data[row][3] = comision.getProfesor();
                        row++;
                }
            }
            VerCursadas verCursadas= new VerCursadas(data,columnaName);



    }

    public void gestionNotas(Profesor profesor){
        GestionNotas gestionNotas = new GestionNotas();
        JComboBox<Comision> comBox = gestionNotas.getBoxCom();
        JComboBox<Materia> matBox = gestionNotas.getBoxMat();
        for (Materia mat : materiaRepository.getListaMaterias()){
            if (mat.buscarPro(profesor)!=null){
                matBox.addItem(mat);
            }
        }

        class BuscarBtnListener implements ActionListener{
            Materia materia;
            @Override
            public void actionPerformed(ActionEvent e) {
            try {

                if (matBox.getSelectedIndex()==0){
                    throw new EmptyFieldException("PRIMERO DEBES ELEGIR UNA MATERIA");
                }
                Materia selectedMat =(Materia) matBox.getSelectedItem();
                for (Comision com : selectedMat.getMapComisiones().values()){
                    if (com.getProfesor().equals(profesor)){
                        comBox.addItem(com);
                    }
                }
                gestionNotas.getBtnLlenar().setEnabled(true);
            }catch (EmptyFieldException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }

            }
        }

        gestionNotas.buscarBtnListener(new BuscarBtnListener());

        class LlenarBtnListener implements ActionListener{
            GestionNotas.CustomTableModel model;

            @Override
            public void actionPerformed(ActionEvent e) {
                Comision selectedCom =(Comision) comBox.getSelectedItem();
                HashMap<Integer,String> estudiantes = selectedCom.getMapEstudiantes();
                Object[][] data = new Object[estudiantes.size()][5];
                int index = 0;
                for (HashMap.Entry<Integer, String> entry : estudiantes.entrySet()) {
                    Estudiante estudiante=estudianteRepository.findxId(entry.getKey());
                    data[index][0] = estudiante.getId();
                    data[index][1] = estudiante.getDni();
                    data[index][2] = estudiante.getNombre() + estudiante.getApellido();
                    data[index][3] = estudiante.getLegajo();
                    data[index][4] = entry.getValue();
                    index++;
                }
                String[] columnNames = {"Id", "Dni","Nombre y Apellido", "Legajo","Nota"};

                model = new GestionNotas.CustomTableModel(data,columnNames);
                gestionNotas.getBtnGuardar().setEnabled(true);
                gestionNotas.updateTable(model);
            }
        }
        LlenarBtnListener llenarBtnListener = new LlenarBtnListener();
        gestionNotas.llenarBtnListener(llenarBtnListener);

        class saveBtnListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionNotas.CustomTableModel model = llenarBtnListener.model;
                int rowCount = model.getRowCount();
                Comision com = (Comision) comBox.getSelectedItem();
                Materia selectedMat = (Materia) matBox.getSelectedItem();

                for (int i = 0; i < rowCount; i++) {
                    Integer studentId = (Integer) model.getValueAt(i, 0);
                    String nota = (String) model.getValueAt(i, 4);

                    Estudiante estudiante = estudianteRepository.findxId(studentId);
                    JOptionPane.showMessageDialog(null,estudiante.getId()+" "+estudiante.getNombre());
                    if (estudiante != null) {
                        com.getMapEstudiantes().remove(studentId);
                        com.getMapEstudiantes().put(estudiante.getId(),nota);
                        selectedMat.getMapComisiones().remove(com);
                        selectedMat.getMapComisiones().put(com.getNumeroComision(),com);
                        materiaRepository.update(selectedMat);
                        materiaRepository.saveList();
                    }
                }
                comBox.removeAllItems();

                gestionNotas.getBtnLlenar().setEnabled(false);
                gestionNotas.getBtnGuardar().setEnabled(false);
            }
        }
        gestionNotas.guardarBtnListener(new saveBtnListener());
        gestionNotas.setVisible(true);



    }

    public void cambiarDatos(User<Profesor> user){

    }

}
