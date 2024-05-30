package com.tp.MVC.Model.Entities;

import com.tp.AbstractClass.Persona;

public class Estudiante extends Persona implements Comparable<Estudiante> {
    private String carrera;
    private Integer legajo;
    private static int permisos=2;

    public Estudiante(String nombre, String apellido, Integer dni, Integer legajo, String carrera) {
        super(nombre, apellido, dni);
        this.legajo = legajo;
        this.carrera = carrera;
    }

    public Estudiante(Integer id) {
        super(id);
    }

    public static int getPermisos() {
        return permisos;
    }

    public static void setPermisos(int permisos) {
        Estudiante.permisos = permisos;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }


    @Override
    public int compareTo(Estudiante o) {
        return this.getId().compareTo(o.getId());
    }
}
