package com.TpFinal.MVC.Administrativo.model.Entity;

import com.TpFinal.AbstractClass.Persona;

public class Admin extends Persona {
    private static int permisosDef = 0;
    private int permisos;
    public Admin(String nombre, String apellido, Integer dni) {
        super(nombre, apellido, dni);
        this.permisos=permisosDef;
    }

    public  Integer getPermisos() {
        return permisos;
    }

    public void setPermisos(int permisos) {
        this.permisos = permisos;
    }

    public Admin() {
        super();
    }
}
