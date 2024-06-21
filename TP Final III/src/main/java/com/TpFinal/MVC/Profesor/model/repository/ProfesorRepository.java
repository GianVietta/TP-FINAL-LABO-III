package com.TpFinal.MVC.Profesor.model.repository;

import com.TpFinal.AbstractClass.Persona;
import com.TpFinal.Interdafaces.IRepository;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashSet;

public class ProfesorRepository implements IRepository<Profesor> {
    private Gson gson = new Gson();
    private static String PATH = "src/main/resources/Profesores.json";
    private HashSet<Profesor> profesorHashSet;

    public ProfesorRepository() {
        loadHash();
        setIDacc();
    }

    private void setIDacc() {
        Integer id = Persona.getIdAcumulable();
        for (Profesor p : this.profesorHashSet){
            if (id<p.getDni()){
                id=p.getId();
            }
        }
        Persona.setIdAcumulable(id);
    }
    public void saveHash(){
        try(Writer writer = new FileWriter(PATH)){
            gson.toJson(this.profesorHashSet,writer);
        } catch (IOException e) {
            e.getCause();
        }
    }

    private void loadHash() {
        try(Reader reader = new FileReader(PATH)){
            Type hashType = new TypeToken<HashSet<Profesor>>(){}.getType();
            this.profesorHashSet=gson.fromJson(reader,hashType);
            if (this.profesorHashSet==null){
                this.profesorHashSet=new HashSet<>();
            }
        } catch (FileNotFoundException e) {
            this.profesorHashSet=new HashSet<>();
        } catch (IOException e) {
            e.getCause();
        }
    }

    @Override
    public void add(Profesor profesor) {
        this.profesorHashSet.add(profesor);
    }

    @Override
    public void remove(Profesor profesor) {
        this.profesorHashSet.remove(profesor);
    }

    @Override
    public void update(Profesor profesor) {
        this.profesorHashSet.remove(profesor);
        this.profesorHashSet.add(profesor);
    }

    @Override
    public Profesor find(Profesor profesor) {
        for (Profesor pro : this.profesorHashSet){
            if (pro.equals(profesor)){
                return pro;
            }
        }
        return null;
    }
}
