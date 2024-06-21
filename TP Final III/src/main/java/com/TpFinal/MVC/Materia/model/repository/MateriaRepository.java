package com.TpFinal.MVC.Materia.model.repository;

import com.TpFinal.Interdafaces.IRepository;
import com.TpFinal.MVC.Materia.model.Entity.Materia;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MateriaRepository implements IRepository<Materia> {
    private ArrayList<Materia> listaMaterias;
    private Gson gson = new Gson();
    private static String PATH = "src/main/resources/Materias.Json";

    public MateriaRepository() {
        loadList();
        setIdAcc();
    }

    public void loadList(){
        try(Reader reader = new FileReader(PATH)){
            Type arrayType = new TypeToken<ArrayList<Materia>>(){}.getType();
            this.listaMaterias=gson.fromJson(reader,arrayType);
            if (this.listaMaterias==null){
                this.listaMaterias=new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            this.listaMaterias=new ArrayList<>();
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void saveList(){
        try(Writer writer = new FileWriter(PATH)){
            gson.toJson(this.listaMaterias,writer);
        } catch (IOException e) {
            e.getCause();
        }
    }


    public void setIdAcc(){
        Integer id=0;
        for (Materia materia : this.listaMaterias){
            if (id<materia.getId()){
                id= materia.getId();
            }
        }
        Materia.setIdAcc(id);
    }
    @Override
    public void add(Materia materia) {
        this.listaMaterias.add(materia);
    }

    @Override
    public void remove(Materia materia) {
        this.listaMaterias.remove(materia);
    }

    @Override
    public void update(Materia materia) {
        this.listaMaterias.set(listaMaterias.indexOf(materia),materia);
    }

    @Override
    public Materia find(Materia materia) {
        for (Materia aux : this.listaMaterias){
            if (aux.equals(materia)){
                return aux;
            }
        }
        return null;
    }
}
