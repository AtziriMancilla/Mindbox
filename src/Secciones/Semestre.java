package Secciones;

import java.util.ArrayList;

public class Semestre {
    private int id;
    private int numSemestre;
    private Carrera carrera;
    private ArrayList<Grupo> grupos;
    private ArrayList<Materia> materias;

    public Semestre(int id, int numSemestre, Carrera carrera) {
        this.id = id;
        this.numSemestre = numSemestre;
        this.carrera = carrera;
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
}
