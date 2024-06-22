package com.TpFinal.MVC.Profesor.model.entity;

import com.TpFinal.AbstractClass.Persona;

public class Profesor extends Persona {
    private String especialidad;
    private int permisos;
    private static int permisosDef = 1;

    public Profesor(String nombre, String apellido, Integer dni, String especialidad) {
        super(nombre, apellido, dni);
        this.especialidad = especialidad;
        this.permisos=permisosDef;
    }

    public Profesor(Integer dni) {
        super(dni);
    }

    public Profesor() {
        super();
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public static Integer getPermisos() {
        return permisosDef;
    }

    public void setPermisos(int i) {
        this.permisos=i;
    }
}
