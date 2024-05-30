package com.tp.MVC.Model.Entities;

import com.tp.AbstractClass.Persona;

public class Profesor extends Persona {
    private String especialidad;
    private String turno;
    private static int permisos=1;


    public Profesor(String nombre, String apellido, Integer dni, String especialidad, String turno) {
        super(nombre, apellido, dni);
        this.especialidad = especialidad;
        this.turno = turno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public static int getPermisos() {
        return permisos;
    }

    public static void setPermisos(int permisos) {
        Profesor.permisos = permisos;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
