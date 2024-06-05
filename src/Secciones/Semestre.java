package Secciones;

import Secciones.utils.NombreCarrera;
import Usuarios.utils.DatosComun;
import mindbox.Sistema;

import java.util.ArrayList;
import java.util.Scanner;

public class Semestre {
    private int id;
    private int numSemestre;
    private NombreCarrera carrera;
    private ArrayList<Grupo> grupos = new ArrayList<>();
    private ArrayList<Materia> materias = new ArrayList<>();
    private static int NUM_SEMESTRES = 1;

    public Semestre(int numSemestre, NombreCarrera carrera) {
        this.id = NUM_SEMESTRES;
        this.numSemestre = numSemestre;
        this.carrera = carrera;
        NUM_SEMESTRES++;
    }

    public int getSemestre() {
        return numSemestre;
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

    public NombreCarrera getCarrera() {
        return carrera;
    }

    public void setCarrera(NombreCarrera carrera) {
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
        for (Semestre semestre : Sistema.semestres) {
            if (semestre.getId() == id){
                se = semestre;
            }
        }
        return se;
    }

    public static void mostrarSemestres(){
        for (Semestre semestre : Sistema.semestres) {
            semestre.toString();
        }
    }

    public static void inicializarSemestres(){
        Semestre sISC, sIMAT, sELC;
        for (int i = 1; i <= 3; i++) {
            sISC = new Semestre(i, NombreCarrera.ISC);
            sIMAT = new Semestre(i, NombreCarrera.IMAT);
            sELC = new Semestre(i, NombreCarrera.ELC);
            Sistema.semestres.add(sISC);
            Sistema.semestres.add(sIMAT);
            Sistema.semestres.add(sELC);
        }
    }

}
