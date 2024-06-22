package com.TpFinal;

import com.TpFinal.MVC.Administrativo.controller.AdminController;
import com.TpFinal.MVC.Administrativo.model.Entity.Admin;
import com.TpFinal.MVC.Administrativo.model.repository.AdminRepository;
import com.TpFinal.MVC.Administrativo.view.MenuAdmin;
import com.TpFinal.MVC.Estudiante.controller.EstudianteController;
import com.TpFinal.MVC.Estudiante.model.Repository.EstudianteRepository;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Materia.controller.MateriaController;
import com.TpFinal.MVC.Materia.model.repository.MateriaRepository;
import com.TpFinal.MVC.Profesor.controller.ProfesorControler;
import com.TpFinal.MVC.Profesor.model.repository.ProfesorRepository;
import com.TpFinal.MVC.Users.Controller.UserController;
import com.TpFinal.MVC.Users.Model.entity.User;
import com.TpFinal.MVC.Users.Model.repository.UsersRepository;

import java.lang.reflect.Array;


public class Main {
    public static void main(String[] args) {
      AdminRepository adminRepository = new AdminRepository();
      ProfesorRepository profesorRepository=new ProfesorRepository();
      EstudianteRepository estudianteRepository = new EstudianteRepository();
        MateriaRepository materiaRepository = new MateriaRepository();
      UsersRepository usersRepository = new UsersRepository();
        ProfesorControler profesorControler =new ProfesorControler(profesorRepository,materiaRepository);
        EstudianteController estudianteController = new EstudianteController(estudianteRepository,materiaRepository);
        MateriaController materiaController = new MateriaController(materiaRepository,profesorRepository);
      UserController userController = new UserController(usersRepository,profesorRepository,estudianteRepository);
        AdminController adminController = new AdminController(profesorControler,estudianteController,materiaController,adminRepository);
        Admin admin = new Admin("Gian","Vietta",46420089);
        User<Admin> userAd= new User<>("Gian","1234","gianfvietta07@gmail.com",admin);
        adminRepository.add(admin);
        adminRepository.saveList();
        usersRepository.add(userAd);
        usersRepository.saveUsers();

        User<?> u=userController.logIn();

      if (u.getT() instanceof Admin){
          Admin ads =(Admin) u.getT();
          System.out.println(ads.getPermisos());
          System.out.println(ads.getDni());
          adminController.menuPrincipalAdmin();
      }else if (u.getT() instanceof Estudiante){
          System.out.println("HOLA");
      }          else {
          System.out.println(u.getT());
      }



    }
}