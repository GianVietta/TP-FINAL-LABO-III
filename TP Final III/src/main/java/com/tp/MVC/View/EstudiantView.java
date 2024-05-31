package com.tp.MVC.View;

import com.tp.MVC.Model.Entities.Estudiante;
import com.tp.MVC.Model.Repository.EstudianteRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class EstudiantView {

        public Integer pedirDNI(EstudianteRepository estudianteRepository){
        estudianteRepository.mostrarTodos();
            System.out.print("Ingrese el numero de documento del estudiante deseado: ");
            Scanner scan=new Scanner(System.in);
            Integer dni=scan.nextInt();
            scan.nextLine();
            return dni;
        }
        public void errorDNI(){
            System.out.println("NO SE ENCONTRO ESTUDIANTE CON ESE DNI");
        }
        public Estudiante nuevoEstudiante(){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Ingrese datos del estudiante: ");

                System.out.print("DNI: ");
                Integer dni = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Nombre: ");
                String nombre = scanner.next();

                System.out.print("Apellido: ");
                String apellido = scanner.next();

                System.out.print("Legajo: ");
                Integer legajo = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Carrera: ");
                String carrera = scanner.next();

                Estudiante estudiante = new Estudiante(nombre,apellido,dni,legajo,carrera);
                return estudiante;
        }

    public void mostrarEstudiante(Estudiante estudiante){
        System.out.println("-------------------------------------");
            System.out.println("DATOS DEL ESTUDIANTE");
        System.out.println("Nombre: " + estudiante.getNombre());
        System.out.println("Apellido: " + estudiante.getApellido());
        System.out.println("DNI: " + estudiante.getDni());
        System.out.println("Legajo: " + estudiante.getLegajo());
        System.out.println("Carrera: " + estudiante.getCarrera());
        System.out.println("Identificador: " + estudiante.getId());
        System.out.println("-------------------------------------");
    }

    public Estudiante elegirMod(Estudiante estudiante){
            Scanner scan=new Scanner(System.in);
            char elec;
            do {
            System.out.println("Elija la opcion del campo que desea editar.");
            System.out.println("1- Nombre.");
            System.out.println("2- Apellido.");
            System.out.println("3- Dni.");
            System.out.println("4- Legajo.");
            System.out.println("5- Carrera.");
            System.out.print("Su eleccion es: ");
            int op = scan.nextInt();
            scan.nextLine();
            estudiante = modCampo(estudiante, op);
                System.out.print("Si no desea modificar nada mas ingrese 's': ");
                elec=scan.next().charAt(0);
        }while (elec!='s');


        return estudiante;
    }

    public Estudiante modCampo(Estudiante estudiante, int op){
           Scanner scan=new Scanner(System.in);
            switch (op){
               case 1:
                   System.out.print("Ingrese el nuevo nombre: ");
                   String nombre=scan.nextLine();
                   estudiante.setNombre(nombre);
                   break;
               case 2:
                   System.out.print("Ingrese el nuevo apellido: ");
                   String apellido=scan.nextLine();
                   estudiante.setApellido(apellido);
                   break;
               case 3:
                   System.out.print("Ingrese el nuevo dni: ");
                   Integer dni=scan.nextInt();
                   scan.nextLine();
                   estudiante.setDni(dni);
                   break;
               case 4:
                   System.out.print("Ingrese el nuevo legajo: ");
                   Integer legajo=scan.nextInt();
                   scan.nextLine();
                   estudiante.setLegajo(legajo);
                   break;
               case 5:
                   System.out.print("Ingrese la nueva carrera: ");
                   String carrera=scan.next();
                   estudiante.setCarrera(carrera);
                   break;
               default:
                   //crear exception
                   System.out.println("ELECCION INVALIDA");
                   break;

           }

           return estudiante;
    }

}
