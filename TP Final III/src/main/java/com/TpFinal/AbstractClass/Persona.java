package com.TpFinal.AbstractClass;

import java.util.Objects;

public abstract class Persona {

     private String nombre;
     private String apellido;
     private Integer dni;
     private Integer id;
     static Integer idAcumulable = 1;

    public Persona(Integer dni) {
        this.dni = dni;
    }

    public Persona() {
    }

    public Persona(String nombre, String apellido, Integer dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.id=idAcumulable;
        idAcumulable++;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Integer getId() {
        return id;
    }

    public static void setIdAcumulable(Integer idAcumulable) {
        Persona.idAcumulable = idAcumulable;
    }

    public static Integer getIdAcumulable() {
        return idAcumulable;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }
}
