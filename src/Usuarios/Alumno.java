package Usuarios;

import Secciones.Carrera;
import Secciones.Grupo;
import Secciones.utils.NombreCarrera;
import Usuarios.utils.Calificacion;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import mindbox.Sistema;
import mindbox.utils.Generador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Alumno extends Usuario{
    private NombreCarrera carrera;
    private int semestre;
   private Grupo grupo;
   private Calificacion[] calificaciones;
    private double promedio;
    private String numControl;

    public Alumno(String nombre, String apellidoPaterno, String apellidoMaterno, int anioNacimiento, LocalDate fechaNacimiento, String ciudad, String estado, String direccion, String curp, LocalDate fechaRegistro, String usuario, String contrasena, Rol rol, NombreCarrera nombreCarrera, String numControl) {
        super(nombre, apellidoPaterno, apellidoMaterno, anioNacimiento, fechaNacimiento, ciudad, estado, direccion, curp, fechaRegistro, usuario, contrasena, rol);

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
    public static void registrarAlumno(NombreCarrera carrera){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> datosComun = DatosComun.registrarDatosComun(Rol.ALUMNO);
        String nombre = datosComun.get(0);
        String apellidoPaterno = datosComun.get(1);
        String apellidoMaterno = datosComun.get(2);
        String anioNacimiento = datosComun.get(3);
        String fechaNacimientoCadena= datosComun.get(4);
        String ciudad = datosComun.get(5);
        String estado = datosComun.get(6);
        String direccion = datosComun.get(7);
        String CURP = datosComun.get(8);
        String fechaRegistroCadena=datosComun.get(9);
        String nombreUsuario=datosComun.get(10);
        String contrasena = datosComun.get(11);
        LocalDate fechaRegistro = LocalDate.parse(fechaRegistroCadena);
        String numContol= Generador.generarNumControl(Rol.ALUMNO,carrera,nombre,fechaRegistro);
        //ocupo volver int el año de nacimiento y LocalDate la fecha de nacimiento
        int anioNacimientoint = Integer.parseInt(anioNacimiento);
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoCadena);
        //

        Alumno alumno = new Alumno(nombre, apellidoPaterno, apellidoMaterno, anioNacimientoint,fechaNacimiento,ciudad, estado, direccion,CURP, fechaRegistro, nombreUsuario, contrasena, Rol.ALUMNO,carrera,numContol);
        Sistema.usuarios.get(Rol.ALUMNO).add(alumno);
        System.out.println(">Alumno registrado<");

    }
}
