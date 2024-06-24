package com.TpFinal.MVC.Users.Model.repository;


import com.TpFinal.Interdafaces.IRepository;
import com.TpFinal.MVC.Administrativo.model.Entity.Admin;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Profesor.model.entity.Profesor;
import com.TpFinal.MVC.Users.Model.entity.User;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class UsersRepository implements IRepository<User<?>> {
    private HashSet<User<?>> setUsuarios;
    private Gson gson;
    private static String PATH = "TP Final III/src/main/resources/Users.json";


    public UsersRepository() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(User.class, new UserTypeAdapter());
        this.gson = builder.create();
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


    public void saveUsers() {
        try (Writer writer = new FileWriter(PATH)) {
            gson.toJson(this.setUsuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUsers() {
        try (Reader reader = new FileReader(PATH)) {
            Type setType = new TypeToken<HashSet<User<?>>>() {}.getType();
            this.setUsuarios = gson.fromJson(reader, setType);
            if (this.setUsuarios == null) {
                this.setUsuarios = new HashSet<>();
            }
        } catch (FileNotFoundException e) {
            this.setUsuarios = new HashSet<>();
        } catch (IOException e) {
            e.printStackTrace();
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

    public class UserTypeAdapter implements JsonSerializer<User<?>>, JsonDeserializer<User<?>> {
        @Override
        public JsonElement serialize(User<?> src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("user", src.getUser());
            jsonObject.addProperty("password", src.getPasword());
            jsonObject.addProperty("email", src.getEmail());
            jsonObject.addProperty("id", src.getId());

            JsonElement tElement = context.serialize(src.getT());
            JsonObject tObject = tElement.getAsJsonObject();

            if (src.getT() instanceof Estudiante) {
                tObject.addProperty("type", "Estudiante");
            } else if (src.getT() instanceof Profesor) {
                tObject.addProperty("type", "Profesor");
            } else if (src.getT() instanceof Admin) {
                tObject.addProperty("type", "Admin");
            }

            jsonObject.add("t", tObject);
            return jsonObject;
        }

        @Override
        public User<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String user = jsonObject.get("user").getAsString();
            String password = jsonObject.get("password").getAsString();
            String email = jsonObject.get("email").getAsString();
            //int id = jsonObject.get("id").getAsInt();

            JsonObject tObject = jsonObject.get("t").getAsJsonObject();
            String type = tObject.get("type").getAsString();

            Object t = null;
            switch (type) {
                case "Estudiante":
                    t = context.deserialize(tObject, Estudiante.class);
                    break;
                case "Profesor":
                    t = context.deserialize(tObject, Profesor.class);
                    break;
                case "Admin":
                    t = context.deserialize(tObject, Admin.class);
                    break;
            }

            User<Object> userObject = new User<>(user, password, email, t);
            //userObject.setId(id);
            return userObject;
        }
    }

    public User<?> consultarPorContrasenia(String contrasenia){
        for (User<?> user : this.setUsuarios){
            if (user.getPasword().equals(contrasenia)){
                return user;
            }
        }
        return null;
    }

}
