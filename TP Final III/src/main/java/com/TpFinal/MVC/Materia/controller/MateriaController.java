package com.TpFinal.MVC.Materia.controller;

import com.TpFinal.MVC.Materia.model.repository.MateriaRepository;
import com.TpFinal.MVC.Profesor.model.repository.ProfesorRepository;

import javax.swing.*;

public class MateriaController {
    private MateriaRepository materiaRepository;
    private ProfesorRepository profesorRepository;

    public MateriaController(MateriaRepository materiaRepository, ProfesorRepository profesorRepository) {
        this.materiaRepository = materiaRepository;
        this.profesorRepository = profesorRepository;
    }

    public  void agregarMateria() {
        JOptionPane.showMessageDialog(null,"CREACION EN PROCESO");
    }

    public  void removeMateria() {
        JOptionPane.showMessageDialog(null,"CREACION EN PROCESO");
    }

    public MateriaRepository getMateriaRepository() {
        return materiaRepository;
    }

    public void setMateriaRepository(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public void modMateria() {
        JOptionPane.showMessageDialog(null,"CREACION EN PROCESO");
    }

    public void viewMatList() {
        JOptionPane.showMessageDialog(null,"CREACION EN PROCESO");
    }
}
