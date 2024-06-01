package Usuarios.utils;

import Usuarios.Alumno;

import java.time.LocalDate;
import java.util.ArrayList;

public class Historial {
    private int grupo;
    private int semestre;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private ArrayList<Calificacion> calificaciones=new ArrayList<>();//se llena cada vez que un maestro le registra la calificacion a un alumno
    public int getSemestre() {
        return semestre;
    }
    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    public int getGrupo() {
        return grupo;
    }


    public static void generarHistorial(Alumno alumno){
        Historial historial=new Historial();
        historial.setSemestre(alumno.getSemestre());
        historial.setGrupo(alumno.getGrupo().getId());
        historial.setFechaFinal(LocalDate.now());
        alumno.getHistorial().add(historial);
    }
    //meter a un condicional para ver si el alumno tiene las 3 calificaciones regostradas para ejecutarlo
    public String toString(){
        return String.format("Semestre: %d, Grupo: %d, Materia: %s, Calificación: %f\nSemestre: %d, Grupo: %d, Materia: %s, Calificación: %f\nSemestre: %d, Grupo: %d, Materia: %s, Calificación: %f",semestre,grupo,calificaciones.get(0).materia.getNombre(),calificaciones.get(0).getCalificacion(),semestre,grupo,calificaciones.get(1).materia.getNombre(),calificaciones.get(1).getCalificacion(),semestre,grupo,calificaciones.get(2).materia.getNombre(),calificaciones.get(2).getCalificacion());
    }

}
