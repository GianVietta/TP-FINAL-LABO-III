package com.tp.MVC.Model.Entities;

public class User<T> {
    private String user;
    private String pasword;
    private T t;
    private Integer id;
    private static Integer idAcc = 1;


    public User(String user, String pasword, T t) {
        this.user = user;
        this.pasword = pasword;
        this.t = t;
        this.id = idAcc++;
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

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


