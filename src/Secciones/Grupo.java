package Secciones;

import Secciones.utils.NombreCarrera;
import Usuarios.Alumno;

import java.util.ArrayList;

public class Grupo {
    private NombreCarrera carrera;
    private ArrayList<Alumno> alumnos;
    private int cantidadAlumnos;
    private ArrayList<Materia> materias;
    private int id;
    private Semestre semestre;
    private TipoGrupo tipoGrupo;

    public Grupo(NombreCarrera carrera, int cantidadAlumnos, int id, Semestre semestre, TipoGrupo tipoGrupo) {
        this.carrera = carrera;
        this.cantidadAlumnos = cantidadAlumnos;
        this.id = id;
        this.semestre = semestre;
        this.tipoGrupo = tipoGrupo;
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

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public TipoGrupo getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(TipoGrupo tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }
    ///////////////////////////
    public void avanzarGrupo(){

    }
    public void actualizarMaterias(){

    }
    public void registrarGrupo(){

    }
}
