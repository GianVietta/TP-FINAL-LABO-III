package com.TpFinal.MVC.Users.Controller;



import com.TpFinal.MVC.Users.Model.entity.User;
import com.TpFinal.MVC.Users.Model.repository.UsersRepository;
import com.TpFinal.MVC.Users.View.LogIn;
import com.TpFinal.MVC.Users.View.Register;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController {
    private UsersRepository usersRepository;


    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void iniciarSeccion() {
        LogIn logIn = new LogIn();

        class logInListener implements ActionListener {
            @Override

            public void actionPerformed(ActionEvent e) {
                JTextField txtUser = logIn.getTxtUser();
                JPasswordField txtPasword = logIn.getTxtPasword();

                if (!txtUser.getText().isEmpty() && !txtPasword.getText().isEmpty()) {
                    try {
                        String user = txtUser.getText();
                        String pasword = txtPasword.getText();
                        User usuario=usersRepository.consultarUsuario(user);
                        if ( usuario!= null) {
                            if (usuario.getPasword().equals(pasword)) {
                                logIn.setVisible(false);
                                menu(usuario);
                                JOptionPane.showMessageDialog(null, "INICIO DE SECCION EXITOSO");
                            } else {
                                JOptionPane.showMessageDialog(null, "CONTRASEÑA INCORRECTA");
                            }
                        }

                    } catch (NumberFormatException | HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
                    }
                }
            }
        }

        class registerSeccionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                logIn.setVisible(false);
                register();
            }
        }

        logIn.registerSeccionListener(new registerSeccionListener());
        logIn.iniciarSeccionListener(new logInListener());
        logIn.setVisible(true);
    }

    public void register(){
        Register register = new Register();
        class RegisterListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtUser = register.getTxtUsuario();
                JTextField txtEmail = register.getTxtEmail();
                JPasswordField txtPasword = register.getTxtPasword();
                JPasswordField txtPaswordConfirm = register.getTxtPaswordConfirm();
                if (txtUser.getText().isEmpty() || txtEmail.getText().isEmpty() ||(new String(txtPasword.getPassword()).isEmpty()) ||  (new String(txtPaswordConfirm.getPassword()).isEmpty())){
                    JOptionPane.showMessageDialog(null,"TODOS LOS CAMPOS DEBEN ESTAR COMPLETOS");
                }else{
                 try{
                     String nombre = txtUser.getText();
                     String email = txtEmail.getText();
                     String contraseña = new String(txtPasword.getPassword());
                     User usuario = usersRepository.consultarUsuario(nombre);
                     if (usuario==null){
                         usuario = usersRepository.consultarEmail(email);
                         if (usuario==null){
                             if (contraseña.equals(new String(txtPaswordConfirm.getPassword()))){
                                 usersRepository.add(new User(nombre,contraseña,email));
                                 usersRepository.saveUsers();
                                 iniciarSeccion();
                                 register.setVisible(false);
                             }else {
                                 JOptionPane.showMessageDialog(null,"LAS CONTRASEÑAS NO COINSIDEN");
                             }
                         }else{
                             JOptionPane.showMessageDialog(null,"EL EMAIL INGRESADO YA CORRESPONDE A UN USUARIO CREADO ANTERIORMENTE.");
                         }
                     }else {
                         JOptionPane.showMessageDialog(null,"EL NOMBRE INGRESADO YA CORRESPONDE A UN USUARIO CREADO ANTERIORMENTE.");
                     }

                 }catch (NumberFormatException | HeadlessException ex){
                     JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
                    }
                }
            }
        }

        class AlreadyRegisterListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                register.setVisible(false);
                iniciarSeccion();
            }
        }

        register.alreadyRegisterListener(new AlreadyRegisterListener());
        register.registerListener(new RegisterListener());
        register.setVisible(true);


    }


    public void menu (User usuario){






    }


}
