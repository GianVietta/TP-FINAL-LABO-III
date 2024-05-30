package com.tp.MVC.Controller;

import com.tp.MVC.Model.Entities.Estudiante;
import com.tp.MVC.Model.Repository.EstudianteRepository;
import com.tp.MVC.View.EstudiantView;

public class EstudianteController {

    public EstudiantView estudiantView;
    public EstudianteRepository estudianteRepository;

    public EstudianteController(EstudiantView estudiantView, EstudianteRepository estudianteRepository) {
        this.estudiantView = estudiantView;
        this.estudianteRepository = estudianteRepository;
    }

    public void nuevoEstudiante(){
     Estudiante estudiante=estudiantView.nuevoEstudiante();
     estudianteRepository.add(estudiante);
    }


    public void editarEstudiante(){
        Integer id = estudiantView.pedirID(this.estudianteRepository);
        Estudiante estudiante=(Estudiante) estudianteRepository.consult(id);
        if (estudiante!=null){
            estudiante=estudiantView.elegirMod(estudiante);
            estudianteRepository.update(estudiante);
        }else{
            estudiantView.errorIdnoEncontrado();
        }
    }


}
