package com.tp.MVC.Model.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tp.MVC.Model.Entities.Administrativo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class AdministrativoRepository {
    private ArrayList<Administrativo> administrativos;
    private static String PATH = "src/main/resources/AdministrativoView.json";
    private Gson gson;

    public AdministrativoRepository() {
    cargarAdmins();
    }

    public void cargarAdmins(){
        try(Reader reader = new FileReader(PATH)){
            Type arrayType = new TypeToken<ArrayList<Administrativo>>(){}.getType();
            this.administrativos=gson.fromJson(reader,arrayType);
            if (this.administrativos==null){
                this.administrativos=new ArrayList<>();
            }
        }catch (FileNotFoundException e){
            System.out.println("ERROR: "+e.getMessage());
        }catch (IOException io){
            System.out.println("ERROR: "+io.getMessage());
        }
    }

}
