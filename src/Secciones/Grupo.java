package Secciones;

import Secciones.utils.NombreCarrera;
import Secciones.utils.NombreMaterias;
import Usuarios.Alumno;
import Usuarios.Profesor;
import Usuarios.utils.DatosComun;
import mindbox.UsuarioEnSesion;

import java.util.ArrayList;
import java.util.Scanner;

public class Grupo {
    private NombreCarrera carrera;
    private ArrayList<Alumno> alumnos;
    private int cantidadAlumnos=0;
    private Materia[][] materias = {{null, null, null}, {null, null, null}, {null, null, null}};
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

    public Materia[][] getMaterias() {
        return materias;
    }

    public void setMaterias(Materia[][] materias) {
        this.materias = materias;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    ///////////////////////////
    public void avanzarGrupo(){

    }
    public void actualizarMaterias(){

    }
    public void registrarGrupo(){
        Scanner scanner = new Scanner(System.in);
        //NombreCarrera nombreCarrera = UsuarioEnSesion.getInstancia().getUsuarioActual().getCarrera();
        // FALTA getNombreCarrera en Usuarios o coordinador

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

        if (Semestre.semestres.get(0).getGrupos().isEmpty()){
            grupo = new Grupo(NombreCarrera.IMAT, 1, TipoGrupo.A);
            Semestre.semestres.get(0).getGrupos().add(grupo);
            System.out.println("Grupo A agregado");
        } else if (Semestre.semestres.get(0).getGrupos().size() == 1){
            grupo = new Grupo(NombreCarrera.IMAT, 1, TipoGrupo.B);
            Semestre.semestres.get(0).getGrupos().add(grupo);
            System.out.println("Grupo B agregado");
        } else {
            System.out.println("Limite de grupos alcanzado");
        }

    }


    public static void addAlumno(Alumno alumno, Grupo grupo){
        if (grupo.getCantidadAlumnos() >= 20){
            System.out.println("Limite de alumnos alcanzado");
        } else {
            grupo.setCantidadAlumnos((grupo.getCantidadAlumnos()+1));
            grupo.getAlumnos().add(alumno);
        }
    }

    ////// ACTUALIZACION
    // Este array se debe pasar a Sistema
    public static  ArrayList<Materia> materiasTotales = new ArrayList<Materia>();


    // Este metodo será llamado cuando se quiera añadir una materia al grupo
    // Esta pensado para facilitar saber cuando nos falta una materia para el grupo
    // Pendiente revisar si puedo usar el setter de esta forma, sino puedo buscar una forma
    // manual de busqueda o dejar el atributo publico
    public static void addMaterias(NombreMaterias mateNomb, Grupo grupo, Profesor profesor){
        // Recopilando datos que ya estan guardados en grupo
        int semestre = grupo.getSemestre();
        NombreCarrera carrera = grupo.getCarrera();

        Materia materia = new Materia(mateNomb, carrera, grupo, profesor);;
        Materia[][] mat = grupo.getMaterias();
        materiasTotales.add(materia);
        Semestre.semestres.get(semestre-1).getMaterias().add(materia);

        if (mateNomb == NombreMaterias.CALCULO){
            if (mat[semestre-1][2] != null){
                mat[semestre-1][2] = materia;
            } else {
                System.out.println("Adición cancelada, materia ya existente");
            }
        }
        else {
            switch (carrera){
                case ISC:
                    if (mateNomb == NombreMaterias.PROGRAMACION && mat[semestre-1][0] != null){
                        mat[semestre-1][0] = materia;
                    } else if (mateNomb == NombreMaterias.PROBABILIDAD && mat[semestre-1][1] != null) {
                        mat[semestre-1][1] = materia;
                    } else if (mateNomb == NombreMaterias.PROGRAMACION || mateNomb == NombreMaterias.PROBABILIDAD){
                        System.out.println("Adición cancelada, materia ya existente");
                    } else {
                        System.out.println("Adición cancelada, materia no correspondiente a la carrera");
                    }
                    break;
                case IMAT:
                    if (mateNomb == NombreMaterias.ESTADISTICA && mat[semestre-1][0] != null){
                        mat[semestre-1][0] = materia;
                    } else if (mateNomb == NombreMaterias.CONTABILIDAD && mat[semestre-1][1] != null) {
                        mat[semestre-1][1] = materia;
                    } else if (mateNomb == NombreMaterias.ESTADISTICA || mateNomb == NombreMaterias.CONTABILIDAD){
                        System.out.println("Adición cancelada, materia ya existente");
                    } else {
                        System.out.println("Adición cancelada, materia no correspondiente a la carrera");
                    }
                    break;
                case ELC:
                    if (mateNomb == NombreMaterias.REDES && mat[semestre-1][0] != null){
                        mat[semestre-1][0] = materia;
                    } else if (mateNomb == NombreMaterias.CIRCUITOS && mat[semestre-1][1] != null) {
                        mat[semestre-1][1] = materia;
                    } else if (mateNomb == NombreMaterias.REDES || mateNomb == NombreMaterias.CIRCUITOS){
                        System.out.println("Adición cancelada, materia ya existente");
                    } else {
                        System.out.println("Adición cancelada, materia no correspondiente a la carrera");
                    }
                    break;
                default:
            }
        }
        grupo.setMaterias(mat);
    }
}
