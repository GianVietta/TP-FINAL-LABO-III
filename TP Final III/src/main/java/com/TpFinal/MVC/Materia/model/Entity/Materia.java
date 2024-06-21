package com.TpFinal.MVC.Materia.model.Entity;

import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;

import java.util.HashMap;

public class Materia {
    private Profesor profesor;
    private String nombreMateria;
    private Integer id;
    private static Integer idAcc;
    private HashMap<Estudiante,Integer> mapEstudiantes;

    public Materia(Profesor profesor, String nombreMateria) {
        this.profesor = profesor;
        this.nombreMateria = nombreMateria;
        this.id = idAcc++;
        this.mapEstudiantes=new HashMap<>();
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HashMap<Estudiante, Integer> getMapEstudiantes() {
        return mapEstudiantes;
    }

    public void setMapEstudiantes(HashMap<Estudiante, Integer> mapEstudiantes) {
        this.mapEstudiantes = mapEstudiantes;
    }

    public static void setIdAcc(Integer idAcc) {
        Materia.idAcc = idAcc;
    }
}
