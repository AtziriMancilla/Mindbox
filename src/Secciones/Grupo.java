package Secciones;

import Secciones.utils.NombreCarrera;
import Secciones.utils.NombreMaterias;
import Usuarios.Alumno;
import Usuarios.Coordinador;
import Usuarios.Profesor;
import Usuarios.Usuario;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import mindbox.Sistema;
import mindbox.UsuarioEnSesion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Grupo {
    private NombreCarrera carrera;
    private ArrayList<Alumno> alumnos = new ArrayList<>();
    private int cantidadAlumnos=0;
    private HashMap<Integer, ArrayList<Materia>> materia= new HashMap<>();
    private int id;
    private int semestre;
    private TipoGrupo tipoGrupo;

    private static int NUM_GRUPO = 1;

    public Grupo(NombreCarrera carrera, int semestre, TipoGrupo tipoGrupo) {
        this.id = NUM_GRUPO;
        this.carrera = carrera;
        this.cantidadAlumnos = 0;
        this.semestre = semestre;
        this.tipoGrupo = tipoGrupo;
        NUM_GRUPO++;
    }

    public NombreCarrera getCarrera() {
        return carrera;
    }

    public void setCarrera(NombreCarrera carrera) {
        this.carrera = carrera;
    }

    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(int cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public TipoGrupo getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(TipoGrupo tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public HashMap<Integer, ArrayList<Materia>> getMateria() {
        return materia;
    }

    public void setMateria(HashMap<Integer, ArrayList<Materia>> materia) {
        this.materia = materia;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString(){
        return String.format("ID: ; Carrera: ; Semestre: ; Grupo: ", id, carrera, semestre, tipoGrupo);
    }

    ///////////////////////////
    public static void avanzarGrupo(NombreCarrera carrera, Grupo grupo){
        System.out.println("Avanzar grupo de semestre");
        if (grupo.getSemestre()<3){
            grupo.setSemestre(grupo.getSemestre()+1);

            // Me falta resetear sus materias :> o bueno, quizas ni se ocupa porque uso el semestre
            // para esas cosas, falta checar entonces xd


        } else {
            // GRADUAR GRUPO
        }
    }

    // CRUD grupo ----------------------------------------------------------------------------------------------------
    public static void crearGrupo(NombreCarrera carrera){
        // Pidiendo datos
        Grupo grupo;

        if (Sistema.semestres.get(0).getGrupos().isEmpty()){
            grupo = new Grupo(carrera, 1, TipoGrupo.A);
            Sistema.semestres.get(0).getGrupos().add(grupo);
            inicializarMaterias(grupo);
            System.out.println("Grupo A agregado");
        } else if (Sistema.semestres.get(0).getGrupos().size() == 1 && Sistema.semestres.get(0).getGrupos().get(0).getCantidadAlumnos() >= 3){
            grupo = new Grupo(carrera, 1, TipoGrupo.B);
            Sistema.semestres.get(0).getGrupos().add(grupo);
            inicializarMaterias(grupo);
            System.out.println("Grupo B agregado");
        } else {
            System.out.println("Limite de grupos alcanzado");
        }

    }

    public static void modificarGrupo(){
        int act;
        Grupo grupo = obtenerGrupo();
        System.out.println("Modificar grupo");
        System.out.println("1 - Modificar alumnos en grupo");
        System.out.println("2 - Modificar profesor en materia");
        System.out.println("0 - Cancelar");
        System.out.print("Accion: ");
        do {
            act = DatosComun.pedirNumero();
            if (act < 0 || act > 2){
                System.out.println("Opcion inexistente");
            }
        } while (act < 0 || act > 2);
        if (act == 1){
            // llamar a metodo de pedir alumno
            // llamar metodo de modificar alumnos en grupo
        } else if (act == 2) {
            Profesor profesor = null;
            // Falta metodo para pedir profesor
            addProfeMateria(grupo, profesor);
        }

    }

    public static void eliminarGrupo(){
        System.out.println("Eliminar grupo");
        Grupo grupo = obtenerGrupo();
        if (grupo.getCantidadAlumnos() == 0){
            // llamar metodo que verifique que las materias tengan profe null
            Sistema.semestres.get(grupo.getSemestre()).getGrupos().remove(grupo);
        }
    }

    public static void mostrarGrupos(){
        System.out.print("0 - Mostrar grupos de todos los semestres\n1 - Mostrar grupos de un semestre\n");
        int semestre = DatosComun.pedirNumero();
        if (semestre == 0){
            for (int i = 0; i < 3; i++) {
                for (Grupo grupo : Sistema.semestres.get(i).getGrupos()) {
                    if (grupo.getCarrera() == ((Coordinador)UsuarioEnSesion.getInstancia().getUsuarioActual()).getCarrera()){
                        grupo.toString();
                    }
                }
            }
        }
        else if (semestre == 1){
            semestre = Semestre.obtenerSemestre().getNumSemestre();
            for (Grupo grupo : Sistema.semestres.get(semestre).getGrupos()) {
                grupo.toString();
            }
        }
        else {
            System.out.println("Opción inexistente");
        }
    }

    // Metodos utiles -------------------------------------------------------------------------------------------------
    public static Grupo obtenerGrupo(){
        Grupo grupo=null;
        int id;
        mostrarGrupos();
        System.out.println("Seleccionar grupo por ID");
        do {
            id = DatosComun.pedirNumero();
            for (int i = 0; i < 3; i++) {
                for (Grupo gru : Sistema.semestres.get(i).getGrupos()) {
                    if (gru.getId() == id && gru.getCarrera() == ((Coordinador)UsuarioEnSesion.getInstancia().getUsuarioActual()).getCarrera()){
                        grupo = gru;
                    }
                }
            }
            if (grupo == null){
                System.out.println("Grupo no encontrado, intente de nuevo");
            }
        } while (grupo == null);
        return grupo;
    }



    // Metodos de materias pero relacionados con grupo ----------------------------------------------------------------

    // Este metodo será llamado cuando se quiera modificar el profesor de una materia al grupo
    public static void addProfeMateria(Grupo grupo, Profesor profesor){
        System.out.println("Asignar profesor");

        NombreCarrera carrera = grupo.getCarrera();
        NombreMaterias mateNomb = Materia.seleccionarMateria(carrera);
        String nombre = (mateNomb.toString().toLowerCase() + " " + grupo.getSemestre());

        // Obteniendo materia
        for (Materia mat : grupo.getMateria().get(grupo.getSemestre())) {
            if (mat.getNombre().equals(nombre)){
                asignarProfe(mat, profesor);
            }
        }
    }
    private static void asignarProfe(Materia materia, Profesor profesor){
        if (materia.getProfesor() == null){
            // Añadiendo profesor a materia - Caso: prof no estaba asignado
            materia.setProfesor(profesor);
        } else {
            System.out.println("El profesor asignado anteriormente será reemplazado, ¿Desea continuar?");
            System.out.println("1 - Sí\n2 - No");
            int act;
            do {
                act = DatosComun.pedirNumero();
            } while (act < 1 || act > 2);
            if (act == 1){
                // Añadiendo profesor a materia - Caso: prof ya estaba asignado
                materia.setProfesor(profesor);
            } else {
                System.out.println("Operación cancelada");
            }
        }
    }

    public static void modificarMateria(Grupo grupo){
        Materia materia = obtenerMateria(grupo);
        System.out.println("Modificar Materia (Profesor en materia)\n");
        // El unico atributo modificable es profesor
        Profesor.mostrarProfesores();
        Profesor profesor = (Profesor) Sistema.usuarios.get(Rol.PROFESOR).get(Profesor.pedirProfesor());
        asignarProfe(materia, profesor);
    }

    public static void mostrarMaterias(Grupo grupo){
        int semestre = grupo.getSemestre();
        System.out.println("Mostrar Materias del semestre " + semestre);
        for (Materia mat : grupo.materia.get(semestre)) {
            System.out.println(mat.toString());
        }
    }

    public static void inicializarMaterias(Grupo grupo){
        // Recopilando datos que ya estan guardados en grupo
        int semestre = grupo.getSemestre();
        NombreCarrera carrera = grupo.getCarrera();
        Materia materia;

        for (NombreMaterias mat : NombreMaterias.values()) {
            // Creando
            materia = new Materia(mat, carrera, grupo, null);
            // Añadiendo materia a donde la piden, para borrar o modificar buscaremos por su ID
            grupo.getMateria().get(grupo.getSemestre()).add(materia);
            Sistema.semestres.get(semestre-1).getMaterias().add(materia);
        }
    }

    // Este metodo se usará para modificar materia
    public static Materia obtenerMateria(Grupo grupo){
        Materia materia=null;
        int id;
        mostrarMaterias(grupo);
        System.out.println("Seleccionar materia por ID");
        do {
            id = DatosComun.pedirNumero();
            for (Materia mat : grupo.getMateria().get(grupo.getSemestre())) {
                if (mat.getId() == id){
                    materia = mat;
                }
            }
            if (materia == null){
                System.out.println("Materia no encontrada, intente de nuevo");
            }
        } while (materia == null);

        return materia;
    }



    // este metodo se usa antes de addMaterias - ACTU: este metodo dejara de ser utilizado, se queda de momento por si acaso
    public static boolean materiaExistente(Grupo grupo, NombreMaterias materia){
        boolean yaEsta = false;
        String nombreProv = (materia.toString().toLowerCase() + " " + grupo.getSemestre());
        for (Materia mat : grupo.getMateria().get(grupo.getSemestre())) {
            if (mat.getNombre().equals(nombreProv)){
                yaEsta = true;
            }
        }
        return yaEsta;
    }


    // Metodos de alumnos para grupos --------------------------------------------------------------------------------

    public static Alumno obtenerAlumno(NombreCarrera carrera){
        Alumno.mostrarAlumnos(carrera);
        Alumno alumno = (Alumno) Sistema.usuarios.get(Rol.ALUMNO).get(Alumno.pedirAlumno());
        return alumno;
    }

    public static void addAlumno(Alumno alumno, Grupo grupo){
        if (grupo.getCantidadAlumnos() >= 20){
            System.out.println("Limite de alumnos alcanzado");
        } else {
            grupo.setCantidadAlumnos((grupo.getCantidadAlumnos()+1));
            grupo.getAlumnos().add(alumno);
            System.out.println("Alumno agregado");
        }
    }
}
