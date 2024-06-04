package Graduados;

import Secciones.utils.NombreCarrera;
import Usuarios.Alumno;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import mindbox.Sistema;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Graduado {
    //Registro de los alumnos que ya se graduaron

    //private ArrayList<Graduados> graduados; creo que esto debería ir en sistema
    private String alumnoNombre;
    private double alumnoPromedio;
    private NombreCarrera alumnoCarrera;

    private LocalDate fechaGraduacion;
    String generacionDeGraduacion;

    public Graduado(String alumnoNombre, double alumnoPromedio, NombreCarrera alumnoCarrera, LocalDate fechaGraduacion, String generacionDeGraduacion) {
        this.alumnoNombre = alumnoNombre;
        this.alumnoPromedio = alumnoPromedio;
        this.alumnoCarrera = alumnoCarrera;
        this.fechaGraduacion = fechaGraduacion;
        this.generacionDeGraduacion = generacionDeGraduacion;
    }

    public LocalDate getFechaGraduacion() {
        return fechaGraduacion;
    }

    public void setFechaGraduacion(LocalDate fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion;
    }

    public void setGeneracionDeGraduacion(String generacionDeGraduacion) {
        this.generacionDeGraduacion = generacionDeGraduacion;
    }

    @Override
    public String toString(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fechaGraduacionFormateada = fechaGraduacion.format(pattern);

        String promedio = String.valueOf(alumnoPromedio);
        String carrera = alumnoCarrera.toString();

        return String.format("Nombre:%s Promedio:%s Carrera:%s  Fecha de Graduacion: %s, Generacion: %s", alumnoNombre, promedio, carrera, fechaGraduacionFormateada, generacionDeGraduacion);
    }
    public static void registrarGraduado(Alumno alumno){

        String alumnoNombre = alumno.getNombre()+" "+alumno.getApellidoPaterno()+" "+ alumno.getApellidoMaterno();
        double alumnoPromedio = alumno.getPromedio();
        NombreCarrera alumnoCarrera = alumno.getCarrera();
        //falta fecha de graduacion y generacion Generación de graduación. Ejemplo: Ene - Jun 2024, Ago - Dic 2024, etc.
        LocalDate fechaGraduacion = LocalDate.now();

        String generacionDeGraduacion = obtenerGeneracion(fechaGraduacion);

        Graduado graduado = new Graduado(alumnoNombre, alumnoPromedio, alumnoCarrera, fechaGraduacion, generacionDeGraduacion);
        //agregar a una lista
        Sistema.graduados.add(graduado);
        System.out.println("Alumno graduado registrado");
        //pero también tengo que quitarlo de la lista de alumnos
        Sistema.usuarios.get(Rol.ALUMNO).remove(alumno);
        System.out.println("Graduado eliminado de la lista de alumnos");
    }
     public static String obtenerGeneracion(LocalDate fechaGraduacion){
        int mes = fechaGraduacion.getMonthValue();
        String meses ="";
        if(mes<7){
            meses= "Ene - Jun";
        }
        else {meses= "Ago - Dic";}
        String anio = String.valueOf(fechaGraduacion.getYear());
        String generacionDeGraduacion = meses+" "+anio;
        return generacionDeGraduacion;
    }
    public static void modificarGraduado(){
        Scanner sc = new Scanner(System.in);
        mostrarGraduados();
        System.out.println("Selecciona al alumno graduado que deseas modificar: ");
        int numGraduado = pedirGraduado();
        int opt = 10;
        do{
            System.out.println("¿Qué información deseas editar?");
            System.out.println("1) Fecha de Graduación\n 0)Salir/Regresar");
            opt = DatosComun.pedirNumero();
            Graduado graduado = Sistema.graduados.get(numGraduado-1);
            switch(opt){
                case 1:
                    System.out.println("Ingrese fecha de graduación");
                    LocalDate nuevaFechaGraduacion =DatosComun.obtenerFechaNacimiento();
                    graduado.setFechaGraduacion(nuevaFechaGraduacion);
                    String nuevaGeneracion = graduado.obtenerGeneracion(nuevaFechaGraduacion);
                    graduado.setGeneracionDeGraduacion(nuevaGeneracion);
                    break;

                case 0:
                    System.out.println("Usted ha salido de modificar graduado");
                    //UsuarioEnSesion.getInstancia().cerrarSesion();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opt);

            }

        }while (opt != 0);
    }
    public static void mostrarGraduados() {
        System.out.println("\nGradudos: \n");
        if (Sistema.graduados.isEmpty()) {
            System.out.println("No hay graduados registrados");
        } else {
            for (int i = 0; i < Sistema.graduados.size(); i++) {
                Graduado graduado = Sistema.graduados.get(i);
                System.out.println((i + 1) + " " + graduado.toString());
            }
        }
    }
    private static int pedirGraduado() {
        Scanner sc = new Scanner(System.in);
        boolean confirmacion = false;
        int numGraduado = 0;

        do {
            confirmacion = false;
            try {
                System.out.println("Selecciona al graduado: ");
                numGraduado = DatosComun.pedirNumero();

                if (numGraduado < 1 || numGraduado > Sistema.graduados.size()) {
                    throw new IndexOutOfBoundsException("El dato ingresado está fuera del tamaño de la lista");
                } else {
                    return numGraduado;
                }
            } catch (IndexOutOfBoundsException error) {
                System.out.println("Error: " + error.getMessage());

            }
        } while (confirmacion);
        sc.nextLine();
        return numGraduado;
    }

}
