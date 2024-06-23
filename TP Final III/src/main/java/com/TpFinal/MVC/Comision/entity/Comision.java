package com.TpFinal.MVC.Comision.entity;

import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;

import java.util.HashMap;

public class Comision {

    private Profesor profesor;
    private Integer numeroComision;
    private HashMap<Integer,String> mapEstudiantes;

    public Comision(Profesor profesor, Integer numeroComision) {
        this.profesor = profesor;
        this.numeroComision = numeroComision;
        this.mapEstudiantes=new HashMap<>();
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Integer getNumeroComision() {
        return numeroComision;
    }

    public void setNumeroComision(Integer numeroComision) {
        this.numeroComision = numeroComision;
    }

    public HashMap<Integer, String> getMapEstudiantes() {
        return mapEstudiantes;
    }

    public void setMapEstudiantes(HashMap<Integer, String> mapEstudiantes) {
        this.mapEstudiantes = mapEstudiantes;
    }

    @Override
    public String toString() {
        return "COMISION: "+(String.valueOf(numeroComision))+" Profesor: "+profesor.getNombre()+" "+profesor.getApellido();
    }
}
