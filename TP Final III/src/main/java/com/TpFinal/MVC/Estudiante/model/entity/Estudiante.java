package com.TpFinal.MVC.Estudiante.model.entity;


import com.TpFinal.AbstractClass.Persona;


public class Estudiante extends Persona implements Comparable<Estudiante> {
    private String carrera;
    private Integer legajo;
    private int permisos;
    private static int permisosDef=2;

    public Estudiante(String nombre, String apellido, Integer dni, Integer legajo, String carrera) {
        super(nombre, apellido, dni);
        this.legajo = legajo;
        this.carrera = carrera;
        this.permisos=permisosDef;
    }

    public Estudiante(Integer dni) {
        super(dni);
    }


    public  int getPermisos() {
        return permisos;
    }

    public static void setPermisos(int permisos) {
        Estudiante.permisosDef = permisos;
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
        return this.getDni().compareTo(o.getDni());
    }
}
