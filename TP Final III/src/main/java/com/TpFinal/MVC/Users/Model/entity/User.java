package com.TpFinal.MVC.Users.Model.entity;

import java.util.Objects;

public class User{
    private String user;
    private String pasword;
    private String email;
    private Integer id;
    private static Integer idAcc = 1;


    public User(String user, String pasword, String email) {
        this.user = user;
        this.pasword = pasword;
        this.email=email;
        this.id = idAcc++;
    }

    public User(String user, String pasword) {
        this.user = user;
        this.pasword = pasword;
    }

    public User(String user) {
        this.user = user;
    }

    public User(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void setIdAcc(Integer idAcc) {
        User.idAcc = idAcc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user1 = (User) o;
        return Objects.equals(user, user1.user) && Objects.equals(pasword, user1.pasword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, pasword);
    }
}


