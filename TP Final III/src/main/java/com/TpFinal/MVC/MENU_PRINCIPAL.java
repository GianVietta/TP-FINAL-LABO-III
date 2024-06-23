package com.TpFinal.MVC;

import com.TpFinal.MVC.Administrativo.controller.AdminController;
import com.TpFinal.MVC.Administrativo.model.Entity.Admin;
import com.TpFinal.MVC.Estudiante.controller.EstudianteController;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Materia.controller.MateriaController;
import com.TpFinal.MVC.Materia.model.repository.MateriaRepository;
import com.TpFinal.MVC.Profesor.controller.ProfesorControler;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;
import com.TpFinal.MVC.Users.Controller.UserController;
import com.TpFinal.MVC.Users.Model.entity.User;

public class MENU_PRINCIPAL {
    private EstudianteController estudianteController;
    private ProfesorControler profesorControler;
    private MateriaController materiaControler ;
    private AdminController adminController;
    private UserController userController;

    public MENU_PRINCIPAL(EstudianteController estudianteController, ProfesorControler profesorControler, MateriaController materiaController, AdminController adminController, UserController userController) {
        this.estudianteController = estudianteController;
        this.profesorControler = profesorControler;
        this.materiaControler = materiaController;
        this.adminController = adminController;
        this.userController = userController;
    }

    public void inicApp(){
        User<?> u=userController.logIn();

        if (u.getT() instanceof Admin){
            adminController.menuPrincipalAdmin();
        }else if (u.getT() instanceof Estudiante){
            estudianteController.menuEstudiantes(u);
        } else if(u.getT() instanceof Profesor){
            profesorControler.menuProfesor(u);
        }
    }


}
