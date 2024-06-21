package com.TpFinal.MVC.Administrativo.model.repository;

import com.TpFinal.AbstractClass.Persona;
import com.TpFinal.Interdafaces.IRepository;
import com.TpFinal.MVC.Administrativo.model.Entity.Admin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class AdminRepository implements IRepository<Admin> {
    private Gson gson = new Gson();
    private static String PATH = "src/main/resources/Admins.json";
    private ArrayList<Admin> adminList;

    public AdminRepository() {
        loadList();
        setIdAcc();
    }

    public void setIdAcc(){
        Integer id = Persona.getIdAcumulable();
        for (Admin a : this.adminList){
            if (id<a.getId()){
                id=a.getId();
            }
        }
        Persona.setIdAcumulable(id);
    }

    public void loadList(){
        try (Reader reader = new FileReader(PATH)){
            Type arrayType = new TypeToken<ArrayList<Admin>>(){}.getType();
            this.adminList=gson.fromJson(reader,arrayType);
            if (this.adminList==null){
                this.adminList=new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            this.adminList=new ArrayList<>();
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void saveList(){
        try(Writer writer = new FileWriter(PATH)){
            gson.toJson(this.adminList,writer);
        } catch (IOException e) {
            e.getCause();
        }
    }

    @Override
    public void add(Admin admin) {
        this.adminList.add(admin);
    }

    @Override
    public void remove(Admin admin) {
        this.adminList.remove(admin);
    }

    @Override
    public void update(Admin admin) {
        this.adminList.set(adminList.indexOf(admin),admin);
    }

    @Override
    public Admin find(Admin admin) {
        for (Admin a : this.adminList){
            if (a.equals(admin)){
                return a;
            }
        }
        return null;
    }
}
