package com.tp.MVC.Model.Entities;

import com.tp.AbstractClass.Persona;

public class Administrativo extends Persona {
    private static int permiso=0;

    public Administrativo(String nombre, String apellido, Integer dni) {
        super(nombre, apellido, dni);
    }

    public static int getPermiso() {
        return permiso;
    }

    public static void setPermiso(int permiso) {
        Administrativo.permiso = permiso;
    }

}
