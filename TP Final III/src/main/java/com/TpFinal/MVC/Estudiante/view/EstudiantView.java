package com.TpFinal.MVC.Estudiante.view;

import com.TpFinal.MVC.Estudiante.model.Estudiante;

import java.util.Scanner;

public class EstudiantView {

    public void mostrarEstudiante(Estudiante estudiante){
        System.out.println("-------------------|Legajo: "+estudiante.getLegajo()+"|-------------------");
        System.out.println("Nombre: "+estudiante.getNombre());
        System.out.println("Apellido: "+estudiante.getApellido());
        System.out.println("Carrera: "+estudiante.getCarrera());
        System.out.println("ID: "+estudiante.getId());
        System.out.println("----------------------------------------------------------------------------");
    }


}
