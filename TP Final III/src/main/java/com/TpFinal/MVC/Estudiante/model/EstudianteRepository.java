package com.TpFinal.MVC.Estudiante.model;

import com.TpFinal.Interdafaces.IRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.TpFinal.MVC.Estudiante.view.EstudiantView;

import java.io.*;
import java.lang.reflect.Type;
import java.util.TreeSet;

public class EstudianteRepository implements IRepository<Estudiante> {
    private TreeSet<Estudiante> arbolEstudiante;
    private Gson gson = new Gson();
    private static String PATH = "src/main/resources/Estudiantes.json";

    public EstudianteRepository() {
        loadTree();
    }

    public void loadTree(){
     try(Reader reader = new FileReader(PATH)) {
         Type treeType = new TypeToken <TreeSet<Estudiante>>(){}.getType();
         this.arbolEstudiante=gson.fromJson(reader,treeType);
         if (this.arbolEstudiante==null){
             this.arbolEstudiante= new TreeSet<>();
         }

     } catch (FileNotFoundException e) {
        this.arbolEstudiante = new TreeSet<>();
     } catch (IOException e) {
         throw new RuntimeException(e);
     }
    }

    public void saveTree(){
    try(Writer writer = new FileWriter(PATH)){
        gson.toJson(this.arbolEstudiante,writer);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }

    public TreeSet<Estudiante> getArbolEstudiante() {
        return this.arbolEstudiante;
    }

    public void setArbolEstudiante(TreeSet<Estudiante> arbolEstudiante) {
        this.arbolEstudiante = arbolEstudiante;
    }

    @Override
    public void add(Estudiante estudiante) {
        this.arbolEstudiante.add(estudiante);
    }

    @Override
    public void remove(Estudiante estudiante) {
        this.arbolEstudiante.remove(estudiante);
    }

    @Override
    public void update(Estudiante estudiante) {
        this.arbolEstudiante.remove(estudiante);
        this.arbolEstudiante.add(estudiante);
    }

    @Override
    public Estudiante find(Estudiante estudiante) {
        Estudiante encontrado=this.arbolEstudiante.ceiling(estudiante);
        if (encontrado!=null&&encontrado.getDni().equals(estudiante.getDni())){
            return encontrado;
        }
        return null;
    }

    public void mostrarTodos(){;
        EstudiantView estudiantView=new EstudiantView();
        for (Estudiante estudiante : this.arbolEstudiante) {
            estudiantView.mostrarEstudiante(estudiante);
        }
    }




    }



