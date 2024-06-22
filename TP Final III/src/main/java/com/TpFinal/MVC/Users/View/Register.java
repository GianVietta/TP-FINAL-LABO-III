package com.TpFinal.MVC.Users.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Register extends JFrame{
    private JPanel registerPannel;
    private JTextField txtUsuario;
    private JPasswordField txtPasword;
    private JTextField txtEmail;
    private JButton registerBtn;
    private JPasswordField txtPaswordConfirm;
    private JButton alreadyRegisterBtn;
    private JTextField txtDni;


    public Register() {
     //   try {
     //       BufferedImage imagen = ImageIO.read(new File("src/imagenes/Logo.png"));
     //       this.setIconImage(imagen);
     //   } catch (IOException e) {
     //       throw new RuntimeException(e);
     //   }
        this.txtDni.setForeground(Color.white);
        this.txtEmail.setForeground(Color.white);
        this.txtUsuario.setForeground(Color.white);
        this.txtPasword.setForeground(Color.white);
        this.txtPaswordConfirm.setForeground(Color.white);
        this.setTitle("STG-GESTION DE ESTUDIANTES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,450,325);
        this.add(registerPannel);
    }

    public void registerListener(ActionListener listener){
        this.registerBtn.addActionListener(listener);
    }

    public void alreadyRegisterListener(ActionListener listener){
        this.alreadyRegisterBtn.addActionListener(listener);
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JPasswordField getTxtPasword() {
        return txtPasword;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JPasswordField getTxtPaswordConfirm() {
        return txtPaswordConfirm;
    }

    public JTextField getTxtDni() {
        return txtDni;
    }
}



