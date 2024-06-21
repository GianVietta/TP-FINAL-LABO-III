package com.TpFinal.MVC.Estudiante.controller;

import com.TpFinal.MVC.Administrativo.view.MenuAdmin;
import com.TpFinal.MVC.Estudiante.model.Repository.EstudianteRepository;
import com.TpFinal.MVC.Materia.model.repository.MateriaRepository;

import javax.swing.*;

public class EstudianteController {
    private EstudianteRepository estudianteRepository;
    private MateriaRepository materiaRepository;

    public EstudianteController(EstudianteRepository estudianteRepository, MateriaRepository materiaRepository) {
        this.estudianteRepository = estudianteRepository;
        this.materiaRepository = materiaRepository;
    }

    public void agregarEstudiante(){
        JOptionPane.showMessageDialog(null,"CREACION EN PROCESO");
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
