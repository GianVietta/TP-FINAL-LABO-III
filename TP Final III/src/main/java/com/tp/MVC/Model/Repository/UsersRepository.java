package com.tp.MVC.Model.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tp.Interfaces.IRepository;
import com.tp.MVC.Model.Entities.Estudiante;
import com.tp.MVC.Model.Entities.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UsersRepository implements IRepository<User> {
    Gson gson;
    private static String PATH = "src/main/resources/Users.json";
    private ArrayList<User> usuarios;

    public UsersRepository() {
        cargarUsers();
    }

    public void cargarUsers(){
        try(Reader reader = new FileReader(PATH)) {
            Type ArrayType = new TypeToken<ArrayList<User>>(){}.getType();
            this.usuarios=gson.fromJson(reader,ArrayType);
            if (this.usuarios==null){
                this.usuarios=new ArrayList<>();
            }
        }catch(FileNotFoundException e){
            System.out.println("Error: "+e.getMessage());
        }catch (IOException io){
            System.out.println("Error: "+io.getMessage());
        }
    }

    public void saveUsers(){
        try(Writer writer = new FileWriter(PATH)){
            gson.toJson(this.usuarios,writer);
        }catch (IOException io){
            System.out.println("Error: "+io.getMessage());
        }
    }



    @Override
    public void remove(User user) {
        this.usuarios.remove(user);
    }

    @Override
    public void update(User user) {
        for (int i = 0; i < this.usuarios.size(); i++) {
            if (this.usuarios.get(i).getId().equals(user.getId())){
                this.usuarios.set(i,user);
            }
        }
    }

    @Override
    public void add(User user) {
        this.usuarios.add(user);
    }

    @Override
    public User consult(Integer id) {
        User encontrado=null;
        for (int i = 0; i < this.usuarios.size(); i++) {
            if (this.usuarios.get(i).getId().equals(id)){
                encontrado=this.usuarios.get(i);
            }
        }
        return null;
    }
}
