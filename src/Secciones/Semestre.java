package Secciones;

import Usuarios.utils.DatosComun;

import java.util.ArrayList;
import java.util.Scanner;

public class Semestre {
    private int id;
    private int numSemestre;
    private Carrera carrera;
    private ArrayList<Grupo> grupos;
    private ArrayList<Materia> materias;
    private static int NUM_SEMESTRES = 1;

    public Semestre(int numSemestre, Carrera carrera) {
        this.id = NUM_SEMESTRES;
        this.numSemestre = numSemestre;
        this.carrera = carrera;
        NUM_SEMESTRES++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumSemestre() {
        return numSemestre;
    }

    public void setNumSemestre(int numSemestre) {
        this.numSemestre = numSemestre;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    // PASAR ARRAY A SISTEMA
    public static  ArrayList<Semestre> semestres = new ArrayList<Semestre>();


    // Otros metodos

    @Override
    public String toString(){
        return String.format("ID: ; Carrera: ; Semestre: ", id, carrera, numSemestre);
    }

    public static Semestre obtenerSemestre(){
        Scanner scanner = new Scanner(System.in);
        Semestre se=null;
        mostrarSemestres();
        System.out.println("Seleccionar semestre por ID");
        int id = DatosComun.pedirNumero();
        for (Semestre semestre : Semestre.semestres) {
            if (semestre.getId() == id){
                se = semestre;
            }
        }
        return se;
    }

    public static void mostrarSemestres(){
        for (Semestre semestre : Semestre.semestres) {
            semestre.toString();
        }
    }


}
