package com.TpFinal.MVC.Administrativo.controller;

import com.TpFinal.MVC.Administrativo.model.repository.AdminRepository;
import com.TpFinal.MVC.Administrativo.view.MenuAdmin;
import com.TpFinal.MVC.Comision.Controller.ComisionController;
import com.TpFinal.MVC.Estudiante.controller.EstudianteController;
import com.TpFinal.MVC.Estudiante.model.Repository.EstudianteRepository;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Materia.controller.MateriaController;
import com.TpFinal.MVC.Profesor.controller.ProfesorControler;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;
import com.TpFinal.MVC.Profesor.model.repository.ProfesorRepository;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {
    private ProfesorControler profesorControler;
    private EstudianteController estudianteController;
    private MateriaController materiaController;

    private ComisionController comisionController;
    private AdminRepository adminRepository;

    public AdminController(ProfesorControler profesorControler, EstudianteController estudianteController, MateriaController materiaController, AdminRepository adminRepository,ComisionController comisionController) {
        this.profesorControler = profesorControler;
        this.estudianteController = estudianteController;
        this.materiaController=materiaController;
        this.comisionController=comisionController;
        this.adminRepository = adminRepository;
    }

    public void menuPrincipalAdmin(){
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.addEstListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estudianteController.agregarEstudiante();
            }
        });
        
        menuAdmin.removeEstListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estudianteController.removeEstudiante();
            }
        });
        
        menuAdmin.modEstListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estudianteController.modEstudiante();
            }
        });
        
        menuAdmin.viewEstListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estudianteController.listadoEstudiantes();
            }
        });

        menuAdmin.addProListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profesorControler.agregarProfesor();
            }
        });

        menuAdmin.removeProListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profesorControler.eliminarProfesor();
            }
        });

        menuAdmin.modProListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profesorControler.modProfesor();
            }
        });

        menuAdmin.viewProListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profesorControler.listaProfesores();
            }
        });

        menuAdmin.addMatListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                materiaController.agregarMateria();
            }
        });

        menuAdmin.removeMatListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                materiaController.removeMateria();
            }
        });

        menuAdmin.modMatListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                materiaController.modMateria();
            }
        });

        menuAdmin.viewMatListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    materiaController.viewMatList();
            }
        });

        menuAdmin.addComListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            comisionController.agregarComision();
            }
        });

        menuAdmin.removeComListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comisionController.removeComision();
            }
        });

        menuAdmin.modComListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comisionController.modComision();
            }
        });

        menuAdmin.listComListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comisionController.listComision();
            }
        });

    }












}
