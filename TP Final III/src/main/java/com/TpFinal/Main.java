package com.TpFinal;

import com.TpFinal.MVC.Administrativo.model.Entity.Admin;
import com.TpFinal.MVC.Administrativo.model.repository.AdminRepository;
import com.TpFinal.MVC.Administrativo.view.MenuPrincipalAdmins;
import com.TpFinal.MVC.Estudiante.model.Repository.EstudianteRepository;
import com.TpFinal.MVC.Profesor.model.repository.ProfesorRepository;
import com.TpFinal.MVC.Users.Controller.UserController;
import com.TpFinal.MVC.Users.Model.entity.User;
import com.TpFinal.MVC.Users.Model.repository.UsersRepository;


public class Main {
    public static void main(String[] args) {
        AdminRepository adminRepository = new AdminRepository();
        ProfesorRepository profesorRepository=new ProfesorRepository();
        EstudianteRepository estudianteRepository = new EstudianteRepository();
        UsersRepository usersRepository = new UsersRepository();
        /*Admin a = new Admin("Gian","Vietta",46420089);
        adminRepository.add(a);
        usersRepository.add(new User<Admin>("Gian","Hola123","Gianfvietta07@gmail.com",a));
        UserController userController = new UserController(usersRepository,profesorRepository,estudianteRepository);

        User<?> u=userController.logIn();
        if (u.getT() instanceof Admin){
            Admin ad =(Admin) u.getT();
            System.out.println(ad.getPermisos());
            System.out.println(ad.getDni());
        }

         */
        MenuPrincipalAdmins menuPrincipalAdmins = new MenuPrincipalAdmins();


    }
}