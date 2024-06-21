package com.TpFinal.MVC.Profesor.controller;

import com.TpFinal.MVC.Materia.model.repository.MateriaRepository;
import com.TpFinal.MVC.Profesor.model.repository.ProfesorRepository;

import javax.swing.*;

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
        JOptionPane.showMessageDialog(null,"CREACION EN PROCESO");
    }

    public void eliminarProfesor() {
        JOptionPane.showMessageDialog(null,"CREACION EN PROCESO");
    }

    public void modProfesor() {
        JOptionPane.showMessageDialog(null,"CREACION EN PROCESO");
    }

    public void viewProfesor() {
        JOptionPane.showMessageDialog(null,"CREACION EN PROCESO");

    }


}
