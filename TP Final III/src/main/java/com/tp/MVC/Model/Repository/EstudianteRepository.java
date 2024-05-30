package com.tp.MVC.Model.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tp.Interfaces.IRepository;
import com.tp.MVC.Model.Entities.Estudiante;
import com.tp.MVC.View.EstudiantView;

import java.io.*;
import java.lang.reflect.Type;
import java.util.TreeSet;

public class EstudianteRepository implements IRepository {
    private TreeSet<Estudiante> arbolEstudiante;
    private Gson gson = new Gson();
    private static String PATH = "src/main/resources/Estudiantes.json";

    public EstudianteRepository() {
        cargarArbol();
    }

    public TreeSet<Estudiante> getArbolEstudiante() {
        return arbolEstudiante;
    }

    public void setArbolEstudiante(TreeSet<Estudiante> arbolEstudiante) {
        this.arbolEstudiante = arbolEstudiante;
    }

     void cargarArbol(){
        try(Reader reader = new FileReader(PATH)) {
        Type treeType = new TypeToken<TreeSet<Estudiante>>() {}.getType();
        arbolEstudiante=gson.fromJson(reader,treeType);
        if (arbolEstudiante==null){
            arbolEstudiante=new TreeSet<>();
        }

        }catch (FileNotFoundException ex){
            System.out.println("ERROR:  " + ex.getMessage());;
        }catch (IOException io){
            System.out.println("Error: "+ io.getMessage());
        }
     }

    @Override
    public void add(Object obj) {
        this.arbolEstudiante.add((Estudiante)obj);
    }

    @Override
    public Object consult(Integer id) {
     Estudiante aux = new Estudiante(id);
     Estudiante encontrado=this.arbolEstudiante.ceiling(aux);
     if (encontrado!=null&&encontrado.getId().equals(id)){
         return encontrado;
     }
         return null;
    }

    @Override
    public void remove(Object obj) {
            this.arbolEstudiante.remove((Estudiante) obj);
    }

    @Override
    public void update(Object estudiante) {
        this.arbolEstudiante.remove((Estudiante) estudiante);
        this.arbolEstudiante.add((Estudiante) estudiante);
    }

    public void mostrarTodos(){;
        EstudiantView estudiantView=new EstudiantView();
        for (Estudiante estudiante : this.arbolEstudiante) {
            estudiantView.mostrarEstudiante(estudiante);
        }
    }


}
