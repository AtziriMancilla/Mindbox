package Secciones;

import Secciones.utils.NombreCarrera;
import Secciones.utils.NombreMaterias;
import Usuarios.Profesor;
import Usuarios.utils.DatosComun;

import java.time.format.DateTimeFormatter;

public class Materia {
    private int id;
    private String nombre;
    private NombreMaterias materia;
    private NombreCarrera carrera;
    private Grupo grupo;
    private Profesor profesor;
    private static int NUM_MATERIA = 1;

    public Materia(NombreMaterias materia, NombreCarrera carrera, Grupo grupo, Profesor profesor) {
        this.id = NUM_MATERIA;
        this.materia = materia;
        this.nombre = (materia.toString().toLowerCase() + " " + grupo.getSemestre());
        this.carrera = carrera;
        this.grupo = grupo;
        this.profesor = profesor;
        NUM_MATERIA++;
    }
    @Override
    public String toString(){
        if (profesor!=null){
            return String.format("Id: %d, Nombre: %s , Carrera: %s, Grupo: %s, Profesor: %s", id, nombre, carrera, grupo, profesor.getNombre());
        } else {
            return String.format("Id: %d, Nombre: %s , Carrera: %s, Grupo: %s, Profesor: No asignado", id, nombre, carrera, grupo);
        }
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

    public NombreMaterias getMateria() {
        return materia;
    }

    public void setMateria(NombreMaterias materia) {
        this.materia = materia;
    }

    ///////////////////////////////

    public static NombreMaterias seleccionarMateria(NombreCarrera carrera){
        NombreMaterias materia=null;
        int action;
        System.out.println("Elige materia: ");
        switch (carrera){
            case ISC:
                System.out.println("1 - "+NombreMaterias.PROGRAMACION.toString().toLowerCase());
                System.out.println("2 - "+NombreMaterias.PROBABILIDAD.toString().toLowerCase());
                System.out.println("3 - "+NombreMaterias.CALCULO.toString().toLowerCase());
                action = DatosComun.pedirNumero();
                if (action == 1){
                    materia = NombreMaterias.PROGRAMACION;
                } else if (action == 2) {
                    materia = NombreMaterias.PROBABILIDAD;
                } else if (action == 3) {
                    materia = NombreMaterias.CALCULO;
                } else {
                    System.out.println("Materia inexistente");
                }
                break;
            case IMAT:
                System.out.println("1 - "+NombreMaterias.ESTADISTICA.toString().toLowerCase());
                System.out.println("2 - "+NombreMaterias.CONTABILIDAD.toString().toLowerCase());
                System.out.println("3 - "+NombreMaterias.CALCULO.toString().toLowerCase());
                action = DatosComun.pedirNumero();
                if (action == 1){
                    materia = NombreMaterias.ESTADISTICA;
                } else if (action == 2) {
                    materia = NombreMaterias.CONTABILIDAD;
                } else if (action == 3) {
                    materia = NombreMaterias.CALCULO;
                } else {
                    System.out.println("Materia inexistente");
                }
                break;
            case ELC:
                System.out.println("1 - "+NombreMaterias.REDES.toString().toLowerCase());
                System.out.println("2 - "+NombreMaterias.CIRCUITOS.toString().toLowerCase());
                System.out.println("3 - "+NombreMaterias.CALCULO.toString().toLowerCase());
                action = DatosComun.pedirNumero();
                if (action == 1){
                    materia = NombreMaterias.REDES;
                } else if (action == 2) {
                    materia = NombreMaterias.CIRCUITOS;
                } else if (action == 3) {
                    materia = NombreMaterias.CALCULO;
                } else {
                    System.out.println("Materia inexistente");
                }
                break;
            default:
        }
        return materia;
    }

}
