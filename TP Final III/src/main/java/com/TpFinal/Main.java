package com.TpFinal;

import com.TpFinal.MVC.Users.Controller.UserController;
import com.TpFinal.MVC.Users.Model.repository.UsersRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepository();
        UserController userController = new UserController(usersRepository);
        userController.iniciarSeccion();
    }
}