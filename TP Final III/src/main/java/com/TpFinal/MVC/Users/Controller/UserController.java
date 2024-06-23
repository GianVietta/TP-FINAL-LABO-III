package com.TpFinal.MVC.Users.Controller;



import com.TpFinal.AbstractClass.Persona;
import com.TpFinal.Exceptions.AlreadyExistException;
import com.TpFinal.Exceptions.DontExistException;
import com.TpFinal.Exceptions.DontMatchException;
import com.TpFinal.Exceptions.MaxDigitsException;
import com.TpFinal.MVC.Estudiante.model.Repository.EstudianteRepository;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;
import com.TpFinal.MVC.Profesor.model.repository.ProfesorRepository;
import com.TpFinal.MVC.Users.Model.entity.User;
import com.TpFinal.MVC.Users.Model.repository.UsersRepository;
import com.TpFinal.MVC.Users.View.LogIn;
import com.TpFinal.MVC.Users.View.Register;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class UserController {
    private UsersRepository usersRepository;
    private ProfesorRepository profesores;
    private EstudianteRepository estudiantes;


    public UserController(UsersRepository usersRepository, ProfesorRepository profesores, EstudianteRepository estudiantes) {
        this.usersRepository = usersRepository;
        this.profesores = profesores;
        this.estudiantes = estudiantes;
    }

    public User<?> logIn() {
        CountDownLatch latch = new CountDownLatch(1);
        LogIn logIn = new LogIn();
        class logInListener implements ActionListener {
            private User<?> user;


            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtUser = logIn.getTxtUser();
                JPasswordField txtPasword = logIn.getTxtPasword();


                if (!txtUser.getText().isEmpty() && !txtPasword.getText().isEmpty()) {
                    try {
                        String user = txtUser.getText();
                        String pasword = txtPasword.getText();
                        User<?> usuario=usersRepository.consultarUsuario(user);

                        if ( usuario!= null) {
                            if (usuario.getPasword().equals(pasword)) {
                                logIn.setVisible(false);
                                this.user=usuario;
                                JOptionPane.showMessageDialog(null, "INICIO DE SESION EXITOSO");
                                latch.countDown();
                            } else {
                                throw new DontMatchException("CONTRASEÑA INCORRECTA");
                            }
                        }else {
                            throw new DontExistException("USUARIO INCORRECTO");
                        }

                    } catch (NumberFormatException | HeadlessException | DontMatchException | DontExistException ex) {
                        JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"COMPLETE TODOS LOS CAMPOS PARA CONTINUAR");
                }
            }
            public User<?> getUser(){
                return this.user;
            }

        }

        class registerSeccionListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        }

        logInListener listener = new logInListener();
        logIn.registerSeccionListener(new registerSeccionListener());
        logIn.logInListener(listener);
        logIn.setVisible(true);
        try {

            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return listener.getUser();
    }

    public void register(){
        Register register = new Register();
        CountDownLatch latchA = new CountDownLatch(1);
        class RegisterListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtUser = register.getTxtUsuario();
                JTextField txtEmail = register.getTxtEmail();
                JTextField txtDNI = register.getTxtDni();
                JPasswordField txtPasword = register.getTxtPasword();
                JPasswordField txtPaswordConfirm = register.getTxtPaswordConfirm();
                int max = 8;
                if (txtUser.getText().isEmpty()|| txtDNI.getText().isEmpty() || txtEmail.getText().isEmpty() ||(new String(txtPasword.getPassword()).isEmpty()) ||  (new String(txtPaswordConfirm.getPassword()).isEmpty())){
                    JOptionPane.showMessageDialog(null,"TODOS LOS CAMPOS DEBEN ESTAR COMPLETOS");
                }else{
                 try {
                     if (max<txtDNI.getText().length()){
                     throw new MaxDigitsException("SE HAN SUPERADO EL MAXIMO DE DIGITOS DEL DNI");
                    }
                     Integer dni = Integer.valueOf(txtDNI.getText());

                     Estudiante est = estudiantes.find(new Estudiante(dni));
                     Profesor pro = profesores.find(new Profesor(dni));

                     if (est!=null){
                         if (!usersRepository.consultEst(est)) {
                             User<Estudiante> user = new User<>(est);
                             cargarUsuario(user);
                         }else {
                             throw new AlreadyExistException("EL DNI INGRESADO YA CUENTA CON UNA CUENTA");
                         }
                     } else if (pro!=null) {
                         if (!usersRepository.consultProf(pro)) {
                             User<Profesor> user = new User<>(pro);
                             cargarUsuario(user);

                         }else {
                             throw new AlreadyExistException("EL DNI INGRESADO YA CUENTA CON UNA CUENTA");
                         }
                         }else{
                        throw new DontExistException("NO SE ENCONTRO SU DNI EN LOS REGISTROS REVISE LOS DATOS O COMUNIQUESE CON UN ADMINISTRADOR");
                     }
                 }catch (NumberFormatException ex){
                     JOptionPane.showMessageDialog(null, "ERROR EL DNI SOLO DEBE CONTENER CARACTERES NUMERICOS" );
                 }catch (MaxDigitsException | DontExistException | AlreadyExistException | DontMatchException |
                         HeadlessException exception) {
                     JOptionPane.showMessageDialog(null, "ERROR "+exception.getMessage());
                 }
            }
            }
            public void cargarUsuario(User user){
                JTextField txtUser = register.getTxtUsuario();
                JTextField txtEmail = register.getTxtEmail();
                JPasswordField txtPasword = register.getTxtPasword();
                JPasswordField txtPaswordConfirm = register.getTxtPaswordConfirm();
                String nombre = txtUser.getText();
                String email = txtEmail.getText();
                String contraseña = new String(txtPasword.getPassword());
                User usuario = usersRepository.consultarUsuario(nombre);
                if (usuario==null){
                    usuario = usersRepository.consultarEmail(email);
                    if (usuario==null){
                        if (contraseña.equals(new String(txtPaswordConfirm.getPassword()))){
                            user.setUser(nombre);
                            user.setEmail(email);
                            user.setPasword(contraseña);
                            usersRepository.add(user);
                            usersRepository.saveUsers();

                            register.dispose();


                        }else {
                            throw new DontMatchException("LAS CONTRASEÑAS NO COINCIDEN");
                        }
                    }else{
                        throw new AlreadyExistException("EL EMAIL INGRESADO YA CORRESPONDE A UN USUARIO CREADO ANTERIORMENTE.");

                    }
                }else {
                    throw new AlreadyExistException("EL NOMBRE INGRESADO YA CORRESPONDE A UN USUARIO CREADO ANTERIORMENTE.");

                }
            }
        }

        class AlreadyRegisterListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                register.dispose();

            }
        }

        register.alreadyRegisterListener(new AlreadyRegisterListener());
        register.registerListener(new RegisterListener());
        register.setVisible(true);


    }
}
