package Secciones;

import Secciones.utils.NombreCarrera;
import Secciones.utils.NombreMaterias;
import Usuarios.Profesor;

import java.time.format.DateTimeFormatter;

public class Materia {
    private int id;
    private String nombre;
    private NombreCarrera carrera;
    private Grupo grupo;
    private Profesor profesor;
    private static int NUM_MATERIA = 1;

    public Materia(NombreMaterias nombre, NombreCarrera carrera, Grupo grupo, Profesor profesor) {
        this.id = NUM_MATERIA;
        this.nombre = (nombre.toString().toLowerCase() + " " + grupo.getSemestre());
        this.carrera = carrera;
        this.grupo = grupo;
        this.profesor = profesor;
        NUM_MATERIA++;
    }
    @Override
    public String toString(){

        return String.format("Id: %d, Nombre: %s , Carrera: %s, Grupo: %s, Profesor: %s", id, nombre, carrera,  profesor.getNombre());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public NombreCarrera getCarrera() {
        return carrera;
    }

    public void setCarrera(NombreCarrera carrera) {
        this.carrera = carrera;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
