package Usuarios;

import Secciones.Grupo;
import Secciones.utils.NombreCarrera;
import Usuarios.utils.Calificacion;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Historial;
import Usuarios.utils.Rol;
import mindbox.Sistema;
import mindbox.utils.Generador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Alumno extends Usuario{
    private NombreCarrera carrera;
    private int semestre;
   private Grupo grupo;
   private Calificacion[] calificaciones;
    private double promedio;
    private String numControl;
    private ArrayList<Historial> historial=new ArrayList<>();

    public Alumno(String nombre, String apellidoPaterno, String apellidoMaterno, int anioNacimiento, LocalDate fechaNacimiento, String ciudad, String estado, String direccion, String curp, LocalDate fechaRegistro, String usuario, String contrasena, Rol rol, NombreCarrera nombreCarrera, String numControl) {
        super(nombre, apellidoPaterno, apellidoMaterno, anioNacimiento, fechaNacimiento, ciudad, estado, direccion, curp, fechaRegistro, usuario, contrasena, rol);
       this.carrera=nombreCarrera;
       this.numControl= numControl;
    }
    @Override
    public String toString(){
        return String.format("%s, Semestre: %d, Promedio: %f, Numero de Control: %s", super.toString(), semestre, promedio, numControl);
    }
    public ArrayList<Historial> getHistorial() {
        return historial;
    }

    public Calificacion[] getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(Calificacion[] calificaciones) {
        this.calificaciones = calificaciones;
    }

    public NombreCarrera getCarrera() {
        return carrera;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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
    public static void modificarAlumno(NombreCarrera carrera) {
        Scanner sc = new Scanner(System.in);
        mostrarAlumnos(carrera);
        int numAlumno = pedirAlumno();
        int opt = 10;
        do {
            Alumno alumno = (Alumno) Sistema.usuarios.get(Rol.ALUMNO).get(numAlumno - 1);
            if (alumno.carrera.equals(carrera)){
                System.out.println("¿Qué información deseas editar?");
                System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento\n 7)Contraseña\n 0)Salir/Regresar");
                opt = DatosComun.pedirNumero();

                switch (opt) {
                    case 1:
                        System.out.println("Ingrese el nuevo nombre: ");
                        alumno.setNombre(DatosComun.pedirDatoString());
                        Sistema.usuarios.get(Rol.ALUMNO).set(numAlumno - 1, alumno);
                        String curpAntigua = alumno.getCurp();
                        char sexo = curpAntigua.charAt(10);
                        String nuevacurp = Generador.generarCURP(alumno.getNombre(), alumno.getApellidoPaterno(), alumno.getApellidoMaterno(), alumno.getFechaNacimiento(), sexo, alumno.getEstado());

                        alumno.setCurp(nuevacurp);
                        System.out.println("Nombre modificado");
                        break;

                    case 2:
                        System.out.println("Ingrese el nuevo apellido Paterno: ");
                        alumno.setApellidoPaterno(DatosComun.pedirDatoString());
                        System.out.println("Ingrese el nuevo apellido Materno: ");
                        alumno.setApellidoMaterno(DatosComun.pedirDatoString());
                        String curpAntigua1 = alumno.getCurp();
                        char sexo1 = curpAntigua1.charAt(10);
                        String nuevaCurp = Generador.generarCURP(alumno.getNombre(), alumno.getApellidoPaterno(), alumno.getApellidoMaterno(), alumno.getFechaNacimiento(), sexo1, alumno.getEstado());

                        alumno.setCurp(nuevaCurp);
                        Sistema.usuarios.get(Rol.ALUMNO).set(numAlumno - 1, alumno);
                        System.out.println("Apellido modificado");
                        break;

                    case 3:
                        System.out.println("Ingrese nueva ciudad: ");
                        alumno.setCiudad(DatosComun.pedirDatoString());
                        Sistema.usuarios.get(Rol.ALUMNO).set(numAlumno - 1, alumno);
                        System.out.println("Ciudad actualizada");
                        break;

                    case 4:
                        System.out.println("Ingrese nuevo estado: ");
                        alumno.setEstado(DatosComun.pedirDatoString());
                        Sistema.usuarios.get(Rol.ALUMNO).set(numAlumno - 1, alumno);
                        System.out.println("Estado actualizado");
                        break;

                    case 5:
                        System.out.println("Ingrese nueva direccion: ");
                        alumno.setDireccion(DatosComun.pedirDireccion());
                        Sistema.usuarios.get(Rol.ALUMNO).set(numAlumno - 1, alumno);
                        System.out.println("Dirección actualizada");
                        break;

                    case 6:
                        System.out.println("Fecha de nacimiento");
                        LocalDate nuevaFechaNacimiento = DatosComun.obtenerFechaNacimiento();
                        alumno.setFechaNacimiento(nuevaFechaNacimiento);
                        int anioNacimiento = nuevaFechaNacimiento.getYear();
                        alumno.setAnioNacimiento(anioNacimiento);
                        String curpAntigua2 = alumno.getCurp();
                        char sexo2 = curpAntigua2.charAt(10);
                        String curpNueva2 = Generador.generarCURP(alumno.getNombre(), alumno.getApellidoPaterno(), alumno.getApellidoMaterno(), alumno.getFechaNacimiento(), sexo2, alumno.getEstado());

                        alumno.setCurp(curpNueva2);
                        System.out.println("Fecha Nacimiento Actualizada");
                        break;

                    case 7:
                        System.out.println("Ingrese nueva contraseña");
                        String nuevaContrasena = sc.nextLine();
                        alumno.setContrasena(nuevaContrasena);
                        System.out.println("Contrasena Actualizada");
                        break;
                    case 0:
                        System.out.println("Usted ha salido de modificar alumno. ");
                        //UsuarioEnSesion.getInstancia().cerrarSesion();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + opt);

                }

            }
            else {
                System.out.println("El alumno que eligió no era parte de sus opciones dentro de la carrera que coordina");
                opt=0;
            }

        } while (opt != 0);

    }

    private static int pedirAlumno() {
        Scanner sc = new Scanner(System.in);
        boolean confirmacion = false;
        int numAlumno = 0;

        do {
            confirmacion = false;
            try {
                System.out.println("Selecciona al alumno: ");
                numAlumno = DatosComun.pedirNumero();

                if (numAlumno < 1 || numAlumno > Sistema.usuarios.get(Rol.ALUMNO).size()) {
                    throw new IndexOutOfBoundsException("El dato ingresado está fuera del tamaño de la lista");
                } else {
                    return numAlumno;
                }
            } catch (IndexOutOfBoundsException error) {
                System.out.println("Error: " + error.getMessage());

            }
        } while (confirmacion);
        sc.nextLine();
        return numAlumno;
    }

    public static void mostrarAlumnos(NombreCarrera carrera) {
        System.out.println("\nAlumnos: \n");
        if (Sistema.usuarios.get(Rol.ALUMNO).isEmpty()) {
            System.out.println("No hay alumnos registrados");
        } else {
            for (int i = 0; i < Sistema.usuarios.get(Rol.ALUMNO).size(); i++) {
                Alumno alumno = (Alumno) Sistema.usuarios.get(Rol.ALUMNO).get(i);
                if(carrera.equals(alumno.getCarrera())){
                    System.out.println((i + 1) + " " + alumno.toString());
                }

            }
        }
    }

    public static void mostrarAlmunosPorFiltro(){


    }
    public void mostrarHistorial(){
        if(historial.isEmpty())
            System.out.println("No nada para mostrar");
        else for (Historial historial1 : historial) {
            System.out.println(historial1.toString());
        }
    }

    /*public static Alumno devolverAlumnoporNumControl(String numControl) {
        System.out.println("\nAlumnos: \n");
        if (Sistema.usuarios.get(Rol.ALUMNO).isEmpty()) {
            System.out.println("No hay alumnos registrados");
        } else {
            for (int i = 0; i < Sistema.usuarios.get(Rol.ALUMNO).size(); i++) {

                Alumno alumno = (Alumno) Sistema.usuarios.get(Rol.ALUMNO).get(i);
                if( alumno.getNumControl().equals(numControl)){
                    return alumno;
                }

            }
        }
    }*/
    public static void eliminarAlumno(NombreCarrera carrera){
        Scanner sc=new Scanner(System.in);
        mostrarAlumnos(carrera);
        int numAlumno=0;
        boolean band;
        do {
            try {
                band=false;
                System.out.println("Selecciona el Alumno que deseas eliminar");
                numAlumno = DatosComun.pedirNumero();
                Sistema.usuarios.get(Rol.ALUMNO).get(numAlumno - 1);
            } catch (IndexOutOfBoundsException | InputMismatchException error) {
                System.out.println("Opcion no valida");
                band=true;
            }
            finally {
                sc.nextLine();
            }
        }while(band);
        System.out.println("Seleccionaste a: ");
        System.out.println(Sistema.usuarios.get(Rol.ALUMNO).get(numAlumno - 1).toString());
        System.out.println("¿Deseas eliminarlo? 1) Sí, Otro número) Cancelar");
        int opcion = DatosComun.pedirNumero();
        if (opcion == 1) {
            Sistema.usuarios.get(Rol.ALUMNO).remove(numAlumno - 1);
            System.out.println("Alumno eliminado");
        }
        if (opcion != 1) {
            System.out.println("Se cancelo la eliminación");
        }
    }

}
