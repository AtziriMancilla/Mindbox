package Secciones;

import Usuarios.Profesor;

import java.time.format.DateTimeFormatter;

public class Materia {
    private int id;
    private String nombre;
    private Carrera carrera;
    private Grupo grupo;
    private Profesor profesor;

    public Materia(int id, String nombre, Carrera carrera, Grupo grupo, Profesor profesor) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.grupo = grupo;
        this.profesor = profesor;
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

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
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
