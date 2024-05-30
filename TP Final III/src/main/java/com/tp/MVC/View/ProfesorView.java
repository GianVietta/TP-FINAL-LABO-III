package com.tp.MVC.View;


import com.tp.MVC.Model.Entities.Profesor;

import java.util.Scanner;

public class ProfesorView {
    Scanner scanner = new Scanner(System.in);
    public Profesor addProfesor(){
            System.out.println("Ingrese datos del profesor: ");

            System.out.print("DNI: ");
            Integer dni = scanner.nextInt();

            System.out.print("Nombre: ");
            String nombre = scanner.next();

            System.out.print("Apellido: ");
            String apellido = scanner.next();

            System.out.print("Turno: ");
            String turno = scanner.next();

            System.out.print("Especialidad: ");
            String especialidad = scanner.next();

            Profesor profesor = new Profesor(nombre,apellido,dni,especialidad,turno);
            return profesor;
        }

    public void mostrarProfesor(Profesor profesor){
        System.out.println("DATOS DEL PROFESOR");
        System.out.println("Nombre: " + profesor.getNombre());
        System.out.println("Apellido: " + profesor.getApellido());
        System.out.println("Dni: " + profesor.getDni());
        System.out.println("Turno: " + profesor.getTurno());
        System.out.println("Especialidad: " + profesor.getEspecialidad());
        System.out.println("Identificador: " + profesor.getId());
    }

}

