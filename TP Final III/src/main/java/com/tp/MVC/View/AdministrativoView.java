package com.tp.MVC.View;

import com.tp.MVC.Model.Entities.Administrativo;

import java.util.Scanner;

public class AdministrativoView {
    public Administrativo addAdministrativo(){
        Scanner scan= new Scanner(System.in);

        System.out.println("Ingrese datos del administrativo:");
        System.out.println("Nombre:");
        String nombre= scan.nextLine();

        System.out.println("Apellido:");
        String apellido= scan.nextLine();

        System.out.println("DNI");
        Integer dni= scan.nextInt();
        scan.nextLine();

        Administrativo administrativo= new Administrativo(nombre, apellido, dni);
        return administrativo;
    }

    public void mostrarAdministrativo(Administrativo administrativo){
        System.out.println("DATOS DEL ADMINISTRATIVO");
        System.out.println("Nombre: "+administrativo.getNombre());
        System.out.println("Apellido: "+administrativo.getApellido());
        System.out.println("Identificador: "+administrativo.getId());
        System.out.println("DNI: "+administrativo.getDni());
    }
}
