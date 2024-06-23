package com.TpFinal.MVC.Materia.model.Entity;

import com.TpFinal.MVC.Comision.entity.Comision;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;

import javax.sound.sampled.spi.FormatConversionProvider;
import java.util.HashMap;
import java.util.Objects;

public class Materia {
    private String nombre;
    private Integer codigo;
    private HashMap<Integer, Comision> mapComisiones;

    private Integer id;

    private static Integer idAcc=1;

    public Materia(String nombre){
        this.nombre = nombre;
    }
    public Materia(String nombre, Integer codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.mapComisiones=new HashMap<>();
        this.id=idAcc++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public HashMap<Integer, Comision> getMapComisiones() {
        return mapComisiones;
    }

    public void setMapComisiones(HashMap<Integer, Comision> mapComisiones) {
        this.mapComisiones = mapComisiones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static Integer getIdAcc() {
        return idAcc;
    }

    public static void setIdAcc(Integer idAcc) {
        Materia.idAcc = idAcc;
    }

    public Comision buscarEst(Estudiante estudiante){
        for(Comision aux : this.mapComisiones.values()){
            if(aux.getMapEstudiantes().containsKey(estudiante)){
                return aux;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public Comision buscarPro(Profesor profesor){
        for (Comision aux : this.mapComisiones.values()){
            if (aux.getProfesor().equals(profesor)){
                return aux;
            }
        }
        return null;
    }

    public Comision buscarCom(Integer com){
        for (Comision aux : this.mapComisiones.values()){
            if (aux.getNumeroComision().equals(com)){
                return aux;
            }
        }
        return null;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materia materia = (Materia) o;
        return Objects.equals(nombre, materia.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
