package com.TpFinal.MVC.Users.Model.repository;


import com.TpFinal.Interdafaces.IRepository;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;
import com.TpFinal.MVC.Users.Model.entity.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashSet;

public class UsersRepository implements IRepository<User<?>> {
    private HashSet<User<?>> setUsuarios;
    private Gson gson = new Gson();
    private static String PATH = "src/main/resources/Users.json";


    public UsersRepository() {
        loadUsers();
        setIdAcc();
    }

    public void setIdAcc(){
        Integer id=1;
        for (User<?> user : this.setUsuarios){
            if (id<user.getId()){
                id= user.getId();
            }
        }
        id++;
        User.setIdAcc(id);
    }


    public void loadUsers(){
        try(Reader reader = new FileReader(PATH)){
            Type setType = new TypeToken <HashSet<User<?>>>(){}.getType();
            this.setUsuarios=gson.fromJson(reader,setType);
            if (this.setUsuarios == null){
                this.setUsuarios=new HashSet<>();
            }
        } catch (FileNotFoundException e) {
           this.setUsuarios = new HashSet<>();
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void saveUsers(){
        try(Writer writer = new FileWriter(PATH)){
            gson.toJson(this.setUsuarios,writer);
        } catch (IOException e) {
            e.getCause();
        }
    }

    public HashSet<User<?>> getSetUsuarios() {
        return setUsuarios;
    }

    @Override
    public void add(User<?> user) {
        this.setUsuarios.add(user);
    }


    @Override
    public void remove(User<?> user) {
        this.setUsuarios.remove(user);
    }

    @Override
    public void update(User<?> user) {
        this.setUsuarios.remove(user);
        this.setUsuarios.add(user);
    }

    @Override
    public User<?> find(User user) {
        for (User<?> aux : this.setUsuarios){
            if (aux.equals(user)){
                return aux;
            }
        }
        return null;
    }

    public User<?> consultarUsuario(String usuario){
        for (User<?> user : this.setUsuarios){
            if (user.getUser().equalsIgnoreCase(usuario)){
                return user;
            }
        }
        return null;
    }
    public User<?> consultarEmail(String email){
        for (User<?> user : this.setUsuarios){
            if (user.getEmail().equalsIgnoreCase(email)){
                return user;
            }
        }
        return null;
    }

    public boolean consultEst(Estudiante estudiante){
        for (User<?> aux : this.setUsuarios){
            if (aux.getT() instanceof Estudiante){
                if (estudiante.equals((Estudiante) aux.getT())){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean consultProf(Profesor profesor){
        for (User<?> aux : this.setUsuarios){
            if (aux.getT() instanceof Profesor){
                if (profesor.equals((Profesor) aux.getT())){
                    return true;
                }
            }
        }
        return false;
    }

}
