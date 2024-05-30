import com.tp.MVC.Controller.EstudianteController;
import com.tp.MVC.Model.Repository.EstudianteRepository;
import com.tp.MVC.View.EstudiantView;

import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        EstudiantView estudiantView =new EstudiantView();
        EstudianteRepository estudianteRepository=new EstudianteRepository();
        EstudianteController estudianteController=new EstudianteController(estudiantView,estudianteRepository);
        estudianteController.nuevoEstudiante();
        estudianteController.nuevoEstudiante();
        estudianteController.nuevoEstudiante();
        estudianteController.editarEstudiante();
        estudianteController.estudianteRepository.mostrarTodos();


    }
}