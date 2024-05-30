package Usuarios;

import Usuarios.utils.Rol;

import java.time.LocalDate;

public class Alumno extends Usuario{
    //private Carrera carrera;
    private int semestre;
   // private Grupo grupo;
   // private Calificaciones calificaciones;
    private double promedio;
    private String numControl;

    public Alumno(String nombre, String apellidoPaterno, String apellidoMaterno, int anioNacimiento, LocalDate fechaNacimiento, String ciudad, String estado, String direccion, String curp, LocalDate fechaRegistro, String usuario, String contrasena, Rol rol, int semestre, double promedio, String numControl) {
        super(nombre, apellidoPaterno, apellidoMaterno, anioNacimiento, fechaNacimiento, ciudad, estado, direccion, curp, fechaRegistro, usuario, contrasena, rol);
       this.semestre=semestre;
       this.promedio=promedio;
       this.numControl= numControl;
    }
    @Override
    public String toString(){
        return String.format("%s, Semestre: %d, Promedio: %f, Numero de Control: %s", super.toString(), semestre, promedio, numControl);
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }
    /////////////////////////////////////////
    public void darDeAlta(){

    }

    private void revisarCalificaciones(){

    }
    private void verInformacion(){

    }
}
