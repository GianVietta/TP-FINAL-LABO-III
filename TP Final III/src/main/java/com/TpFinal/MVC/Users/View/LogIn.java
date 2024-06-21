package com.TpFinal.MVC.Users.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LogIn extends JFrame{
    private JLabel jLabel1;
    private JPanel LogInPannel;
    private JButton registerBtn;
    private JButton logInBtn;
    private JTextField txtUser;
    private JPasswordField txtPasword;
    private JLabel Title;

    public LogIn() {
     // try {
     //     BufferedImage imagen = ImageIO.read(new File("src/imagenes/Logo.png"));
     //    this.setIconImage(imagen);
     //
     // } catch (IOException e) {
     //     throw new RuntimeException(e);
     // }
        this.txtUser.setForeground(Color.white);
        this.txtPasword.setForeground(Color.white);
        this.setTitle("STG-GESTION DE ESTUDIANTES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,300);
        //this.jLabel1.setIcon(new ImageIcon(this.getClass().getResource("/src/imagenes/LOGIN.png")));
        //this.jLabel1.setEnabled(false);
        this.add(LogInPannel);
        this.setVisible(true);
    }

    public void logInListener(ActionListener listener){
        logInBtn.addActionListener(listener);
    }

    public void registerSeccionListener(ActionListener listener){
        registerBtn.addActionListener(listener);
    }


    public JTextField getTxtUser() {
        return txtUser;
    }

    public JPasswordField getTxtPasword() {
        return txtPasword;
    }
}
