package Secciones;

import Secciones.utils.NombreCarrera;
import Secciones.utils.NombreMaterias;
import Usuarios.Alumno;
import Usuarios.Profesor;
import Usuarios.Usuario;
import Usuarios.utils.DatosComun;
import mindbox.Sistema;
import mindbox.UsuarioEnSesion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Grupo {
    private NombreCarrera carrera;
    private ArrayList<Alumno> alumnos;
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

    public static void crearGrupo(NombreCarrera carrera){
        // Pidiendo datos
        Grupo grupo;
        int semestre;

        System.out.println("Semestre del grupo: ");
        do {
            semestre = DatosComun.pedirNumero();
            if (semestre > 3 || semestre < 1){
                 System.out.println("El dato debe corresponder a un numero de semestre");
             }
        } while (semestre > 3 || semestre < 1);

        System.out.println("Si algo truena aqui fue en la condicion de agregar grupo de grupo.java xd");

        if (Semestre.semestres.get(semestre-1).getGrupos().isEmpty()){
            grupo = new Grupo(carrera, semestre, TipoGrupo.A);
            Semestre.semestres.get(semestre-1).getGrupos().add(grupo);
            System.out.println("Grupo A agregado");
        } else if (Semestre.semestres.get(semestre-1).getGrupos().size() == 1){
            grupo = new Grupo(carrera, semestre, TipoGrupo.B);
            Semestre.semestres.get(semestre-1).getGrupos().add(grupo);
            System.out.println("Grupo B agregado");
        } else {
            System.out.println("Limite de grupos alcanzado");
        }

    }




    ////// ACTUALIZACION
    // Este array se debe pasar a Sistema
    public static ArrayList<Materia> materiasTotales = new ArrayList<Materia>();

    public static Grupo obtenerGrupo(){
        Grupo grupo=null;
        int id;
        mostrarGrupos();
        System.out.println("Seleccionar grupo por ID");
        do {
            id = DatosComun.pedirNumero();
            for (int i = 0; i < 3; i++) {
                for (Grupo gru : Semestre.semestres.get(i).getGrupos()) {
                    if (gru.getId() == id){
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

    // De momento con 0 se muestran todos los grupos de all semestres
    public static void mostrarGrupos(){
        System.out.print("0 - Mostrar grupos de todos los semestres\n1 - Mostrar grupos de un semestre\n");
        int semestre = DatosComun.pedirNumero();
        if (semestre == 0){
            for (int i = 0; i < 3; i++) {
                for (Grupo grupo : Semestre.semestres.get(i).getGrupos()) {
                    grupo.toString();
                }
            }
        }
        else if (semestre == 1){
            semestre = Semestre.obtenerSemestre().getNumSemestre();
            for (Grupo grupo : Semestre.semestres.get(semestre).getGrupos()) {
                grupo.toString();
            }
        }
        else {
            System.out.println("Opción inexistente");
        }
    }



    //// Metodos de materias pero relacionados con grupo?
    public static void mostrarMaterias(Grupo grupo){
        System.out.println("Mostrar Materias");
        int semestre = grupo.getSemestre();
        for (int x = 0; x < semestre; x++) {
            System.out.println("\nMaterias semestre "+(x+1));
            for (Materia mat : grupo.materia.get(semestre)) {
                System.out.println(mat.toString());
            }
        }
    }

    // este metodo se usa antes de addMaterias
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

    // Este metodo será llamado cuando se quiera añadir una materia al grupo
    // Pendiente revisar si puedo usar el setter de esta forma, sino lo modificaré
    public static void addMaterias(Grupo grupo, Profesor profesor){
        // Recopilando datos que ya estan guardados en grupo
        int semestre = grupo.getSemestre();
        NombreCarrera carrera = grupo.getCarrera();

        NombreMaterias mateNomb = Materia.seleccionarMateria(carrera);

        // Creando
        Materia materia = new Materia(mateNomb, carrera, grupo, profesor);

        // Añadiendo materia a donde la piden, para borrar o modificar buscaremos por su ID
        materiasTotales.add(materia);
        grupo.getMateria().get(grupo.getSemestre()).add(materia);
        Semestre.semestres.get(semestre-1).getMaterias().add(materia);

    }

    // Este metodo se usará antes de modificar materia
    public static Materia obtenerMateria(Grupo grupo){
        Materia materia=null;
        int id;
        mostrarMaterias(grupo);
        System.out.println("Seleccionar materia por ID");
        do {
            id = DatosComun.pedirNumero();
            for (int i = 0; i < 3; i++) {
                for (Materia mat : grupo.getMateria().get(grupo.getSemestre())) {
                    if (mat.getId() == id){
                        materia = mat;
                    }
                }
            }
            if (materia == null){
                System.out.println("Materia no encontrada, intente de nuevo");
            }
        } while (materia == null);

        return materia;
    }
    public static void modificarMateria(Grupo grupo, NombreMaterias materia){
        System.out.println("Modificar Materia\n");
        // Faltante aun sorry, lo haré pronto :c

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
