package com.TpFinal.MVC.Estudiante.controller;

import com.TpFinal.Exceptions.AlreadyExistException;
import com.TpFinal.Exceptions.DontExistException;
import com.TpFinal.Exceptions.EmptyFieldException;
import com.TpFinal.Exceptions.InvalidNumberException;
import com.TpFinal.MVC.Administrativo.view.MenuAdmin;
import com.TpFinal.MVC.Comision.entity.Comision;
import com.TpFinal.MVC.Estudiante.model.Repository.EstudianteRepository;
import com.TpFinal.MVC.Estudiante.model.entity.Estudiante;
import com.TpFinal.MVC.Estudiante.view.*;
import com.TpFinal.MVC.Materia.model.Entity.Materia;
import com.TpFinal.MVC.Materia.model.repository.MateriaRepository;
import com.TpFinal.MVC.Materia.view.ListarMat;
import com.TpFinal.MVC.Users.Model.entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class EstudianteController {
    private EstudianteRepository estudianteRepository;
    private MateriaRepository materiaRepository;

    public EstudianteController(EstudianteRepository estudianteRepository, MateriaRepository materiaRepository) {
        this.estudianteRepository = estudianteRepository;
        this.materiaRepository = materiaRepository;
    }

    public void menuEstudiantes(User<?> user){
        MenuEstudiantes menuEstudiantes = new MenuEstudiantes();
        menuEstudiantes.verMateriasListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verMaterias((Estudiante) user.getT());
            }
        });
        menuEstudiantes.verCursadasListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verCursada((Estudiante) user.getT());
            }
        });
        menuEstudiantes.matricularseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matricularse((Estudiante) user.getT());
                materiaRepository.saveList();
            }
        });
        menuEstudiantes.darDeBajaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                darDeBaja((Estudiante) user.getT());
            }
        });
        menuEstudiantes.setVisible(true);
    }


    public void verMaterias(Estudiante estudiante){
        String[] columnaName={"CODIGO","NOMBRE DE MATERIA","NOTA"};
        ArrayList<Materia> materiasFiltradas=new ArrayList<>();


        int row=0;
        for(Materia materia: materiaRepository.getListaMaterias() ){
            if (materia.consultEstNota(estudiante)){
                materiasFiltradas.add(materia);
            }
        }
        Object [][] data=new Object[materiasFiltradas.size()][3];
        for (Materia materia:materiasFiltradas){
            Comision comision= materia.buscarEst(estudiante);
            data[row][0] = materia.getCodigo();
            data[row][1]= materia.getNombre();
            data[row][2]=comision.getMapEstudiantes().get(estudiante.getId());
            row++;
        }

        ListaMaterias listarMat = new ListaMaterias(data,columnaName);
    }

    public void verCursada(Estudiante estudiante){
        String[] columnaName={"CODIGO","NOMBRE DE MATERIA","NUMERO COMISION","PROFESOR"};

        ArrayList<Materia> materiasFiltradas = new ArrayList<>();
        int row=0;
        for(Materia materia: materiaRepository.getListaMaterias() ){
            if (materia.buscarEst(estudiante)!=null) {
                if (!materia.consultEstNota(estudiante)) {
                   materiasFiltradas.add(materia);
                }
            }
        }
        Object [][] data=new Object[materiasFiltradas.size()][4];
        for (Materia materia : materiasFiltradas){
            Comision comision = materia.buscarEst(estudiante);
            data[row][0] = materia.getCodigo();
            data[row][1] = materia.getNombre();
            data[row][2] = comision.getNumeroComision();
            data[row][3] = comision.getProfesor();
        }

        row++;
        VerCursadas verCursadas= new VerCursadas(data,columnaName);
    }

    public void darDeBaja(Estudiante estudiante){
        DarDeBaja darDeBaja = new DarDeBaja();
        JComboBox<Materia> matBox = darDeBaja.getMatBox();

        for(Materia materia: materiaRepository.getListaMaterias() ){
            if (materia.buscarEst(estudiante)!=null) {
                if (!materia.consultEstNota(estudiante)) {
                    matBox.addItem(materia);
                }
            }
        }

        class bajaBtnListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (matBox.getSelectedIndex()==0){
                        throw new EmptyFieldException("PRIMERO DEBES SELECCIONAR UNA MATERIA");
                    }

                    Materia materia =(Materia) matBox.getSelectedItem();
                    int i = matBox.getSelectedIndex();
                    Comision com = materia.buscarEst(estudiante);
                    com.getMapEstudiantes().remove(estudiante.getId());
                    materia.getMapComisiones().remove(com);
                    materia.getMapComisiones().put(com.getNumeroComision(),com);
                    materiaRepository.update(materia);
                    materiaRepository.saveList();
                    matBox.removeItemAt(i);

                }catch (EmptyFieldException ex){
                    JOptionPane.showMessageDialog(null,ex);
                }
            }
        }
        darDeBaja.darDeBajaButton(new bajaBtnListener());
        darDeBaja.setVisible(true);

    }

    public void matricularse(Estudiante estudiante){
        Matricularse matricularse = new Matricularse();
        JComboBox<Comision> comBox = matricularse.getComBox();
        JComboBox<Materia> matsBox = matricularse.getMatBox();
        for (Materia mat : materiaRepository.getListaMaterias()){
            if (mat.getMapComisiones()!=null) {
                matsBox.addItem(mat);
            }
        }
        class ElegirBtnListener implements ActionListener{
            Materia materia;

            public ElegirBtnListener() {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
            try{
                if (matsBox.getSelectedIndex()==0){
                    throw new EmptyFieldException("DEBES ELEGIR UNA MATERIA");
                }
                materia=(Materia) matsBox.getSelectedItem();
                if (materia.buscarEst(estudiante)!=null) {
                throw new AlreadyExistException("YA ESTAS MATRICULADO EN OTRA COMISION DE ESTA MISMA MATERIA");
                }
                if (materia.getMapComisiones().isEmpty()){
                    throw new DontExistException("LA MATERIA ELEJIDA NO TIENE COMISIONES");
                }
                for (Comision comision : materia.getMapComisiones().values()){
                    comBox.addItem(comision);
                }
                matricularse.getBtnMatricularse().setEnabled(true);
            }catch (EmptyFieldException |DontExistException | AlreadyExistException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            }

            public Materia getMateria() {
                return materia;
            }
        }

        ElegirBtnListener elegirBtnListener = new ElegirBtnListener();
        Materia materia = elegirBtnListener.getMateria();



        class MatBtnListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
               try {

                   Comision com = (Comision) comBox.getSelectedItem();
                   Materia mat=(Materia) elegirBtnListener.getMateria();
                   com.getMapEstudiantes().put(estudiante.getId(),"");
                   mat.getMapComisiones().remove(com);
                   mat.getMapComisiones().put(com.getNumeroComision(),com);
                   materiaRepository.update(mat);
                   materiaRepository.saveList();
                   comBox.removeAllItems();
                   matricularse.getBtnMatricularse().setEnabled(false);
               }catch (EmptyFieldException ex){
                   JOptionPane.showMessageDialog(null,ex.getMessage());
               }
            }
        }
        matricularse.elegirMatListener(elegirBtnListener);
        matricularse.matricularseBtnListener(new MatBtnListener());
        matricularse.setVisible(true);

    }

    public void agregarEstudiante(){
        CreateEstudiant createEstudiant = new CreateEstudiant();
        class CreateListener implements ActionListener {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JTextField dniText = createEstudiant.getDniField();
                    JTextField nombreText = createEstudiant.getNombreField();
                    JTextField apellidoText = createEstudiant.getApellidoField();
                    JTextField legajoText = createEstudiant.getLegajoField();
                    JTextField carreraText = createEstudiant.getCarreraField();

                    if (dniText.getText().isEmpty() || nombreText.getText().isEmpty() || apellidoText.getText().isEmpty() || legajoText.getText().isEmpty() || carreraText.getText().isEmpty()) {
                        throw new EmptyFieldException("Todos los campos son obligatorios.");
                    }

                    int dniNumber;
                    int legajoNumber;
                    try {
                        dniNumber = Integer.parseInt(dniText.getText());
                    } catch (NumberFormatException ex) {
                        throw new InvalidNumberException("El DNI debe contener solo números.");
                    }
                    try {
                        legajoNumber = Integer.parseInt(legajoText.getText());
                    } catch (NumberFormatException ex) {
                        throw new InvalidNumberException("El legajo debe contener solo números.");
                    }
                    dniNumber = Integer.valueOf(dniText.getText());
                    legajoNumber = Integer.valueOf(legajoText.getText());

                    Estudiante nuevoEstudiante = new Estudiante(nombreText.getText(), apellidoText.getText(), dniNumber, legajoNumber, carreraText.getText());


                    if (estudianteRepository.find(nuevoEstudiante) != null) {
                        throw new AlreadyExistException("El estudiante ya existe.");
                    }

                    estudianteRepository.add(nuevoEstudiante);
                    estudianteRepository.saveTree();
                    createEstudiant.getDniField().setText("");
                    createEstudiant.getNombreField().setText("");
                    createEstudiant.getApellidoField().setText("");
                    createEstudiant.getLegajoField().setText("");
                    createEstudiant.getCarreraField().setText("");

                    JOptionPane.showMessageDialog(createEstudiant, "Estudiante ingresado con éxito.");
                } catch (EmptyFieldException | InvalidNumberException | AlreadyExistException ex) {
                    JOptionPane.showMessageDialog(createEstudiant, "Error: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(createEstudiant, "Error inesperado: " + ex.getMessage());
                }
            }
            
        }

        class volverListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
               createEstudiant.dispose();
            }
        }


        CreateListener listener = new CreateListener();
        createEstudiant.volverBottonListener(new volverListener());
        createEstudiant.createBtnListener(listener);
        createEstudiant.setVisible(true);

    }


    public EstudianteRepository getEstudianteRepository() {
        return estudianteRepository;
    }

    public void setEstudianteRepository(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public MateriaRepository getMateriaRepository() {
        return materiaRepository;
    }

    public void setMateriaRepository(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public void removeEstudiante() {
        RemoveEstudiante removeEstudiante = new RemoveEstudiante();
        Estudiante estudiante;
        class BuscarBtnListener implements ActionListener{
            Estudiante est=null;
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JTextField txtSearchDni = removeEstudiante.getTxtSeatchDni();
                    if (!txtSearchDni.getText().isEmpty()){
                        Integer dni = Integer.valueOf(txtSearchDni.getText());

                        est =estudianteRepository.find(new Estudiante(dni));
                        if (est!=null){
                            JTextField txtNombre = removeEstudiante.getTxtNombre();
                            JTextField txtApellido = removeEstudiante.getTxtApellido();
                            JTextField txtDni = removeEstudiante.getTxtDni();
                            JTextField txtLegajo = removeEstudiante.getTxtLegajo();
                            JTextField txtCarrera = removeEstudiante.getTxtCarrera();

                            txtNombre.setText(est.getNombre());
                            txtApellido.setText(est.getApellido());
                            txtDni.setText(String.valueOf(est.getDni()));
                            txtLegajo.setText(String.valueOf(est.getLegajo()));
                            txtCarrera.setText(est.getCarrera());

                            removeEstudiante.enableRemoveBtn(true);

                        }else {
                            throw new DontExistException("EL DNI NO ESTA ASOCIADO A NINGUN ESTUDIANTE");
                        }
                    }else {
                        throw new EmptyFieldException("ANTES DE BUSCAR DEBES COMPLETAR EL CAMPO DE BUSQUEDA.");
                    }

                }catch (DontExistException | EmptyFieldException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }

            }
            public Estudiante getEst(){return this.est;}

        }


        BuscarBtnListener buscarBtnListener = new BuscarBtnListener();
        estudiante=buscarBtnListener.getEst();


        class RemoveBtnListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField txtNombre = removeEstudiante.getTxtNombre();
                JTextField txtApellido = removeEstudiante.getTxtApellido();
                JTextField txtDni = removeEstudiante.getTxtDni();
                JTextField txtLegajo = removeEstudiante.getTxtLegajo();
                JTextField txtCarrera = removeEstudiante.getTxtCarrera();
                JTextField txtDniSearch  = removeEstudiante.getTxtDni();
                Estudiante estudiante1;
                try{
                    if (buscarBtnListener.getEst() == null) {
                        throw new EmptyFieldException("PRIMERO DEBES BUSCAR UN ESTUDIANTE.");
                    }else {
                        estudiante1=buscarBtnListener.getEst();

                        estudianteRepository.remove(estudiante1);
                        estudianteRepository.saveTree();
                        JOptionPane.showMessageDialog(null,"ALUMNO ELIMINADO CON EXITO");
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtDni.setText("");
                        txtLegajo.setText("");
                        txtCarrera.setText("");
                        removeEstudiante.enableRemoveBtn(false);
                    }

                }catch (EmptyFieldException exception){

                }

            }
       }
        class VolverBtnRetListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEstudiante.dispose();
            }
        }

       removeEstudiante.volverBtn(new VolverBtnRetListener());
       removeEstudiante.removeBtnListener(new RemoveBtnListener());
       removeEstudiante.buscarBtnListener(buscarBtnListener);
       removeEstudiante.setVisible(true);
    }
    public void modEstudiante() {
        ModEstudiante modEstudiante = new ModEstudiante();
        Estudiante estudiante;
        class BuscarBtnListener implements ActionListener{
            Estudiante est=null;
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JTextField txtSearchDni = modEstudiante.getTxtSeatchDni();
                    if (!txtSearchDni.getText().isEmpty()){
                        Integer dni = Integer.valueOf(txtSearchDni.getText());

                        est =estudianteRepository.find(new Estudiante(dni));
                        if (est!=null){
                            JTextField txtNombre = modEstudiante.getTxtNombre();
                            JTextField txtApellido = modEstudiante.getTxtApellido();
                            JTextField txtDni = modEstudiante.getTxtDni();
                            JTextField txtLegajo = modEstudiante.getTxtLegajo();
                            JTextField txtCarrera = modEstudiante.getTxtCarrera();

                            txtNombre.setText(est.getNombre());
                            txtApellido.setText(est.getApellido());
                            txtDni.setText(String.valueOf(est.getDni()));
                            txtLegajo.setText(String.valueOf(est.getLegajo()));
                            txtCarrera.setText(est.getCarrera());

                            modEstudiante.enableModBtn(true);

                        }else {
                            throw new DontExistException("EL DNI NO ESTA ASOCIADO A NINGUN ESTUDIANTE");
                        }
                    }else {
                        throw new EmptyFieldException("ANTES DE BUSCAR DEBES COMPLETAR EL CAMPO DE BUSQUEDA.");
                    }

                }catch (DontExistException | EmptyFieldException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }

            }
            public Estudiante getEst(){return this.est;}

        }


        BuscarBtnListener buscarBtnListener = new BuscarBtnListener();
        estudiante=buscarBtnListener.getEst();


        class ModBtnListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {

               JTextField txtNombre = modEstudiante.getTxtNombre();
               JTextField txtApellido = modEstudiante.getTxtApellido();
               JTextField txtDni = modEstudiante.getTxtDni();
               JTextField txtLegajo = modEstudiante.getTxtLegajo();
               JTextField txtCarrera = modEstudiante.getTxtCarrera();
               JTextField txtDniSearch  = modEstudiante.getTxtDni();
                Estudiante estudiante1;
               try{
                   if (buscarBtnListener.getEst() == null) {
                       throw new EmptyFieldException("PRIMERO DEBES BUSCAR UN ESTUDIANTE.");
                   }else {
                       estudiante1=buscarBtnListener.getEst();
                   }
                   if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtDni.getText().isEmpty() || txtLegajo.getText().isEmpty() || txtCarrera.getText().isEmpty()){
                       throw new EmptyFieldException("TODOS LOS CAMPOS DEBEN TENER DATOS");
                   }else {
                       int dniNumber;
                       int legajoNumber;
                       try {
                           dniNumber = Integer.parseInt(txtDni.getText());
                       } catch (NumberFormatException ex) {
                           throw new InvalidNumberException("El DNI debe contener solo números.");
                       }
                       try {
                           legajoNumber = Integer.parseInt(txtLegajo.getText());
                       } catch (NumberFormatException ex) {
                           throw new InvalidNumberException("El legajo debe contener solo números.");
                       }
                       dniNumber = Integer.valueOf(txtDni.getText());
                       legajoNumber = Integer.valueOf(txtLegajo.getText());
                       if (dniNumber!=estudiante1.getDni()){
                           if (estudianteRepository.find(new Estudiante(dniNumber))!=null){
                               throw new AlreadyExistException("EL DNI NUEVO YA PERTENECE A OTRO ALUMNO");
                           }
                       }
                       if (legajoNumber!=estudiante1.getLegajo()){
                           if (estudianteRepository.findxLegajo(legajoNumber)!=null){
                               throw new AlreadyExistException("EL NUEVO LEGAJO YA PERTENECE A UN ALUMNO");
                           }
                       }
                       estudiante1.setNombre(txtNombre.getText());
                       estudiante1.setApellido(txtApellido.getText());
                       estudiante1.setCarrera(txtCarrera.getText());
                       estudiante1.setDni(dniNumber);
                       estudiante1.setLegajo(legajoNumber);
                       estudianteRepository.update(estudiante1);
                       estudianteRepository.saveTree();
                       JOptionPane.showMessageDialog(null,"MODIFICACION CORRECTAMENTE REALIZADA");
                       txtNombre.setText("");
                       txtApellido.setText("");
                       txtDni.setText("");
                       txtLegajo.setText("");
                       txtCarrera.setText("");
                       modEstudiante.enableModBtn(false);
                   }

               }catch (EmptyFieldException| InvalidNumberException | AlreadyExistException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
               }

            }
        }

        class volverBtnListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                modEstudiante.dispose();
            }
        }

        modEstudiante.buscarBtnListener(buscarBtnListener);
        modEstudiante.modBtnListener(new ModBtnListener());
        modEstudiante.volverBtn(new volverBtnListener());
        modEstudiante.setVisible(true);

    }


    public void listadoEstudiantes() {
                String[] columnaName={"Nombre", "Apellido", "DNI", "Legajo", "Carrera", "Id"};
                Object [][] data=new Object[this.estudianteRepository.getArbolEstudiante().size()][6];

                int row=0;
                for(Estudiante estudiante: estudianteRepository.getArbolEstudiante() ){
                    data[row][0] = estudiante.getNombre();
                    data[row][1]= estudiante.getApellido();
                    data[row][2] = estudiante.getDni();
                    data[row][3] = estudiante.getLegajo();
                    data[row][4] = estudiante.getCarrera();
                    data[row][5] = estudiante.getId();
                    row++;
                }
                ListadoEstudiantes listadoEstudiantes = new ListadoEstudiantes(data,columnaName);
    }




}
