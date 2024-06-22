package com.TpFinal.MVC.Estudiante.model.Repository;

import com.TpFinal.AbstractClass.Persona;
import com.TpFinal.Interdafaces.IRepository;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.TreeSet;

public class EstudianteRepository implements IRepository<Estudiante> {
    private TreeSet<Estudiante> arbolEstudiante;
    private Gson gson = new Gson();
    private static String PATH = "TP Final III/src/main/resources/Estudiantes.json";

    public EstudianteRepository() {
        loadTree();
        setIdACC();
    }
    public void setIdACC(){
      Integer id= Persona.getIdAcumulable();
      for (Estudiante e : this.arbolEstudiante){
          if (id<e.getId()){
              id=e.getId();
          }
      }
      Persona.setIdAcumulable(id);
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
        for (Estudiante aux : this.arbolEstudiante){

            if (aux.equals(estudiante)){
                return aux;
            }
        }
        return null;
    }

}



