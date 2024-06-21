package com.TpFinal.MVC.Estudiante.controller;

import com.TpFinal.Exceptions.AlreadyExistException;
import com.TpFinal.Exceptions.EmptyFieldException;
import com.TpFinal.Exceptions.InvalidNumberException;
import com.TpFinal.MVC.Administrativo.view.MenuAdmin;
import com.TpFinal.MVC.Estudiante.model.Repository.EstudianteRepository;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Estudiante.view.CreateEstudiant;
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
               createEstudiant.setVisible(false);
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
        JOptionPane.showMessageDialog(null,"CREACION EN PROCESO");

    }

    public void modEstudiante() {
        JOptionPane.showMessageDialog(null,"CREACION EN PROCESO");
    }


    public void listadoEstudiantes() {
        JOptionPane.showMessageDialog(null,"CREACION EN PROCESO");
    }
}
