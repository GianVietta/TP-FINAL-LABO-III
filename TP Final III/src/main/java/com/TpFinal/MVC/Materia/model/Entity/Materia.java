package com.TpFinal.MVC.Materia.model.Entity;

import com.TpFinal.MVC.Comision.entity.Comision;

import java.util.HashMap;

public class Materia {
    private String nombre;
    private Integer codigo;
    private HashMap<Integer, Comision> mapComisiones;

    private Integer id;

    private static Integer idAcc;

    public Materia(String nombre, Integer codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.mapComisiones=new HashMap<>();
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
}
