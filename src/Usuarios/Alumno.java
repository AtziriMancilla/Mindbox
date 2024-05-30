package Usuarios;

import Secciones.Carrera;
import Secciones.Grupo;
import Usuarios.utils.Calificacion;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import mindbox.Sistema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Alumno extends Usuario{
    private Carrera carrera;
    private int semestre;
   private Grupo grupo;
   private Calificacion calificacion;
    private double promedio;
    private String numControl;

    public Alumno(String nombre, String apellidoPaterno, String apellidoMaterno, int anioNacimiento, LocalDate fechaNacimiento, String ciudad, String estado, String direccion, String curp, LocalDate fechaRegistro, String usuario, String contrasena, Rol rol, int semestre, String numControl) {
        super(nombre, apellidoPaterno, apellidoMaterno, anioNacimiento, fechaNacimiento, ciudad, estado, direccion, curp, fechaRegistro, usuario, contrasena, rol);
       this.semestre=semestre;
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
    public static void darDeAlta(){

    }

    private static void revisarCalificaciones(){

    }
    public static void registrarAlumno(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> datosComun = DatosComun.registrarDatosComun(Rol.ALUMNO);
        String nombre = datosComun.get(0);
        String apellidoPaterno = datosComun.get(1);
        String apellidoMaterno = datosComun.get(2);
        String ciudad = datosComun.get(3);
        String estado = datosComun.get(4);
        String CURP = datosComun.get(5);
        String direccion = datosComun.get(6);
        String anioNacimiento = datosComun.get(7);
        String fechaNacimientoCadena= datosComun.get(8);
        String RFC = datosComun.get(9);
        String nombreUsuario=datosComun.get(10);
        String contrasena = datosComun.get(11);

        System.out.println("Ingrese salario");
        double salario = DatosComun.pedirValorDouble();
        //ocupo volver int el año de nacimiento y LocalDate la fecha de nacimiento
        int anioNacimientoint = Integer.parseInt(anioNacimiento);
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoCadena);

//        Alumno alumno = new Alumno(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, CURP, direccion, anioNacimientoint, fechaNacimiento, RFC, nombreUsuario, contrasena, salario);
//        Sistema.usuarios.get(Rol.ALUMNO).add(alumno);
//        System.out.println(">Capturista registrado<");

    }
}
