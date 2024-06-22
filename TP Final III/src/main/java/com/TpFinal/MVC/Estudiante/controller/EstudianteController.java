package com.TpFinal.MVC.Estudiante.controller;

import com.TpFinal.Exceptions.AlreadyExistException;
import com.TpFinal.Exceptions.DontExistException;
import com.TpFinal.Exceptions.EmptyFieldException;
import com.TpFinal.Exceptions.InvalidNumberException;
import com.TpFinal.MVC.Administrativo.view.MenuAdmin;
import com.TpFinal.MVC.Estudiante.model.Repository.EstudianteRepository;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Estudiante.view.CreateEstudiant;
import com.TpFinal.MVC.Estudiante.view.ListadoEstudiantes;
import com.TpFinal.MVC.Estudiante.view.ModEstudiante;
import com.TpFinal.MVC.Estudiante.view.RemoveEstudiante;
import com.TpFinal.MVC.Materia.model.repository.MateriaRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class EstudianteController {
    private EstudianteRepository estudianteRepository;
    private MateriaRepository materiaRepository;

    public EstudianteController(EstudianteRepository estudianteRepository, MateriaRepository materiaRepository) {
        this.estudianteRepository = estudianteRepository;
        this.materiaRepository = materiaRepository;
    }

    public void agregarEstudiante(){
        CreateEstudiant createEstudiant = new CreateEstudiant();
        class CreateListener implements ActionListener {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JTextField dniText = createEstudiant.getDniField();
                    JTextField nombreText = createEstudiant.getNombreField();
                    JTextField apellidoText = createEstudiant.getApellidoField();
                    JTextField legajoText = createEstudiant.getLegajoField();
                    JTextField carreraText = createEstudiant.getCarreraField();

                    if (dniText.getText().isEmpty() || nombreText.getText().isEmpty() || apellidoText.getText().isEmpty() || legajoText.getText().isEmpty() || carreraText.getText().isEmpty()) {
                        throw new EmptyFieldException("Todos los campos son obligatorios.");
                    }

                    int dniNumber;
                    int legajoNumber;
                    try {
                        dniNumber = Integer.parseInt(dniText.getText());
                    } catch (NumberFormatException ex) {
                        throw new InvalidNumberException("El DNI debe contener solo números.");
                    }
                    try {
                        legajoNumber = Integer.parseInt(legajoText.getText());
                    } catch (NumberFormatException ex) {
                        throw new InvalidNumberException("El legajo debe contener solo números.");
                    }
                    dniNumber = Integer.valueOf(dniText.getText());
                    legajoNumber = Integer.valueOf(legajoText.getText());

                    Estudiante nuevoEstudiante = new Estudiante(nombreText.getText(), apellidoText.getText(), dniNumber, legajoNumber, carreraText.getText());


                    if (estudianteRepository.find(nuevoEstudiante) != null) {
                        throw new AlreadyExistException("El estudiante ya existe.");
                    }

                    estudianteRepository.add(nuevoEstudiante);
                    estudianteRepository.saveTree();
                    createEstudiant.getDniField().setText("");
                    createEstudiant.getNombreField().setText("");
                    createEstudiant.getApellidoField().setText("");
                    createEstudiant.getLegajoField().setText("");
                    createEstudiant.getCarreraField().setText("");

                    JOptionPane.showMessageDialog(createEstudiant, "Estudiante ingresado con éxito.");
                } catch (EmptyFieldException | InvalidNumberException | AlreadyExistException ex) {
                    JOptionPane.showMessageDialog(createEstudiant, "Error: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(createEstudiant, "Error inesperado: " + ex.getMessage());
                }
            }
            
        }

        class volverListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
               createEstudiant.dispose();
            }
        }


        CreateListener listener = new CreateListener();
        createEstudiant.volverBottonListener(new volverListener());
        createEstudiant.createBtnListener(listener);
        createEstudiant.setVisible(true);

    }


    public EstudianteRepository getEstudianteRepository() {
        return estudianteRepository;
    }

    public void setEstudianteRepository(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public MateriaRepository getMateriaRepository() {
        return materiaRepository;
    }

    public void setMateriaRepository(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public void removeEstudiante() {
        RemoveEstudiante removeEstudiante = new RemoveEstudiante();
        Estudiante estudiante;
        class BuscarBtnListener implements ActionListener{
            Estudiante est=null;
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JTextField txtSearchDni = removeEstudiante.getTxtSeatchDni();
                    if (!txtSearchDni.getText().isEmpty()){
                        Integer dni = Integer.valueOf(txtSearchDni.getText());

                        est =estudianteRepository.find(new Estudiante(dni));
                        if (est!=null){
                            JTextField txtNombre = removeEstudiante.getTxtNombre();
                            JTextField txtApellido = removeEstudiante.getTxtApellido();
                            JTextField txtDni = removeEstudiante.getTxtDni();
                            JTextField txtLegajo = removeEstudiante.getTxtLegajo();
                            JTextField txtCarrera = removeEstudiante.getTxtCarrera();

                            txtNombre.setText(est.getNombre());
                            txtApellido.setText(est.getApellido());
                            txtDni.setText(String.valueOf(est.getDni()));
                            txtLegajo.setText(String.valueOf(est.getLegajo()));
                            txtCarrera.setText(est.getCarrera());

                            removeEstudiante.enableRemoveBtn(true);

                        }else {
                            throw new DontExistException("EL DNI NO ESTA ASOCIADO A NINGUN ESTUDIANTE");
                        }
                    }else {
                        throw new EmptyFieldException("ANTES DE BUSCAR DEBES COMPLETAR EL CAMPO DE BUSQUEDA.");
                    }

                }catch (DontExistException | EmptyFieldException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }

            }
            public Estudiante getEst(){return this.est;}

        }


        BuscarBtnListener buscarBtnListener = new BuscarBtnListener();
        estudiante=buscarBtnListener.getEst();


        class RemoveBtnListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField txtNombre = removeEstudiante.getTxtNombre();
                JTextField txtApellido = removeEstudiante.getTxtApellido();
                JTextField txtDni = removeEstudiante.getTxtDni();
                JTextField txtLegajo = removeEstudiante.getTxtLegajo();
                JTextField txtCarrera = removeEstudiante.getTxtCarrera();
                JTextField txtDniSearch  = removeEstudiante.getTxtDni();
                Estudiante estudiante1;
                try{
                    if (buscarBtnListener.getEst() == null) {
                        throw new EmptyFieldException("PRIMERO DEBES BUSCAR UN ESTUDIANTE.");
                    }else {
                        estudiante1=buscarBtnListener.getEst();

                        estudianteRepository.remove(estudiante1);
                        estudianteRepository.saveTree();
                        JOptionPane.showMessageDialog(null,"ALUMNO ELIMINADO CON EXITO");
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtDni.setText("");
                        txtLegajo.setText("");
                        txtCarrera.setText("");
                        removeEstudiante.enableRemoveBtn(false);
                    }

                }catch (EmptyFieldException exception){

                }

            }
       }
        class VolverBtnRetListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEstudiante.dispose();
            }
        }

       removeEstudiante.volverBtn(new VolverBtnRetListener());
       removeEstudiante.removeBtnListener(new RemoveBtnListener());
       removeEstudiante.buscarBtnListener(buscarBtnListener);
       removeEstudiante.setVisible(true);
    }
    public void modEstudiante() {
        ModEstudiante modEstudiante = new ModEstudiante();
        Estudiante estudiante;
        class BuscarBtnListener implements ActionListener{
            Estudiante est=null;
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JTextField txtSearchDni = modEstudiante.getTxtSeatchDni();
                    if (!txtSearchDni.getText().isEmpty()){
                        Integer dni = Integer.valueOf(txtSearchDni.getText());

                        est =estudianteRepository.find(new Estudiante(dni));
                        if (est!=null){
                            JTextField txtNombre = modEstudiante.getTxtNombre();
                            JTextField txtApellido = modEstudiante.getTxtApellido();
                            JTextField txtDni = modEstudiante.getTxtDni();
                            JTextField txtLegajo = modEstudiante.getTxtLegajo();
                            JTextField txtCarrera = modEstudiante.getTxtCarrera();

                            txtNombre.setText(est.getNombre());
                            txtApellido.setText(est.getApellido());
                            txtDni.setText(String.valueOf(est.getDni()));
                            txtLegajo.setText(String.valueOf(est.getLegajo()));
                            txtCarrera.setText(est.getCarrera());

                            modEstudiante.enableModBtn(true);

                        }else {
                            throw new DontExistException("EL DNI NO ESTA ASOCIADO A NINGUN ESTUDIANTE");
                        }
                    }else {
                        throw new EmptyFieldException("ANTES DE BUSCAR DEBES COMPLETAR EL CAMPO DE BUSQUEDA.");
                    }

                }catch (DontExistException | EmptyFieldException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }

            }
            public Estudiante getEst(){return this.est;}

        }


        BuscarBtnListener buscarBtnListener = new BuscarBtnListener();
        estudiante=buscarBtnListener.getEst();


        class ModBtnListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {

               JTextField txtNombre = modEstudiante.getTxtNombre();
               JTextField txtApellido = modEstudiante.getTxtApellido();
               JTextField txtDni = modEstudiante.getTxtDni();
               JTextField txtLegajo = modEstudiante.getTxtLegajo();
               JTextField txtCarrera = modEstudiante.getTxtCarrera();
               JTextField txtDniSearch  = modEstudiante.getTxtDni();
                Estudiante estudiante1;
               try{
                   if (buscarBtnListener.getEst() == null) {
                       throw new EmptyFieldException("PRIMERO DEBES BUSCAR UN ESTUDIANTE.");
                   }else {
                       estudiante1=buscarBtnListener.getEst();
                   }
                   if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtDni.getText().isEmpty() || txtLegajo.getText().isEmpty() || txtCarrera.getText().isEmpty()){
                       throw new EmptyFieldException("TODOS LOS CAMPOS DEBEN TENER DATOS");
                   }else {
                       int dniNumber;
                       int legajoNumber;
                       try {
                           dniNumber = Integer.parseInt(txtDni.getText());
                       } catch (NumberFormatException ex) {
                           throw new InvalidNumberException("El DNI debe contener solo números.");
                       }
                       try {
                           legajoNumber = Integer.parseInt(txtLegajo.getText());
                       } catch (NumberFormatException ex) {
                           throw new InvalidNumberException("El legajo debe contener solo números.");
                       }
                       dniNumber = Integer.valueOf(txtDni.getText());
                       legajoNumber = Integer.valueOf(txtLegajo.getText());
                       estudiante1.setNombre(txtNombre.getText());
                       estudiante1.setApellido(txtApellido.getText());
                       estudiante1.setCarrera(txtCarrera.getText());
                       estudiante1.setDni(dniNumber);
                       estudiante1.setLegajo(legajoNumber);
                       estudianteRepository.update(estudiante1);
                       estudianteRepository.saveTree();
                       JOptionPane.showMessageDialog(null,"MODIFICACION CORRECTAMENTE REALIZADA");
                       txtNombre.setText("");
                       txtApellido.setText("");
                       txtDni.setText("");
                       txtLegajo.setText("");
                       txtCarrera.setText("");
                       modEstudiante.enableModBtn(false);
                   }

               }catch (EmptyFieldException exception){

               }

            }
        }

        class volverBtnListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                modEstudiante.dispose();
            }
        }

        modEstudiante.buscarBtnListener(buscarBtnListener);
        modEstudiante.modBtnListener(new ModBtnListener());
        modEstudiante.volverBtn(new volverBtnListener());
        modEstudiante.setVisible(true);

    }


    public void listadoEstudiantes() {

                String[] columnaName={"Nombre", "Apellido", "DNI", "Legajo", "Carrera", "Id"};
                Object [][] data=new Object[this.estudianteRepository.getArbolEstudiante().size()][6];


                int row=0;
                for(Estudiante estudiante: estudianteRepository.getArbolEstudiante() ){
                    data[row][0] = estudiante.getNombre();
                    data[row][1]= estudiante.getApellido();
                    data[row][2] = estudiante.getDni();
                    data[row][3] = estudiante.getLegajo();
                    data[row][4] = estudiante.getCarrera();
                    data[row][5] = estudiante.getId();
                    row++;
                }
                ListadoEstudiantes listadoEstudiantes = new ListadoEstudiantes(data,columnaName);

    }
}
