package Usuarios;

import Secciones.Grupo;
import Secciones.Materia;

import Secciones.utils.NombreCarrera;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import mindbox.Sistema;
import mindbox.UsuarioEnSesion;
import mindbox.utils.Generador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Profesor extends Trabajador {
    private String numControl;
    ArrayList<Materia> materias;

    public Profesor(String nombre, String apellidoPaterno, String apellidoMaterno, int anioNacimiento, LocalDate fechaNacimiento, String ciudad, String estado, String direccion, String curp, LocalDate fechaRegistro, String usuario, String contrasena, Rol rol, String rfc, double salario, String numControl) {
        super(nombre, apellidoPaterno, apellidoMaterno, anioNacimiento, fechaNacimiento, ciudad, estado, direccion, curp, fechaRegistro, usuario, contrasena, rol, rfc, salario);
        this.numControl = numControl;
    }

    @Override
    public String toString() {
        return String.format("%s, numero de Control: %s", super.toString(), numControl);
    }

    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    ////////////
    public void darDeAlta() {

    }

    public static void verInformacion() {
        System.out.println((UsuarioEnSesion.getInstancia().getUsuarioActual()).toString());
        System.out.println("Materias asignadas: ");
        ((Profesor)UsuarioEnSesion.getInstancia().getUsuarioActual()).mostrarMaterias();
    }
    public void actualizarInformacion() {
        System.out.println("Cambiar Nombre de Usuario");
        System.out.println("Cambiar contraseña");

    }
    public void mostrarMaterias() {
        int i = 0;
        for (Materia materia : ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias()) {
            i++;
            System.out.println(i + " " + materia.getNombre()+" "+materia.getGrupo());
        }
    }

    //Aplicar después de cambiar Semestre y asignar Profesores, para que al Profesor se le registren las materias que tiene
    public void asignarMaterias() {
        materias.clear();
        if (!Sistema.grupos.isEmpty()) {
            for (Map.Entry<Integer, Grupo> grupo : Sistema.grupos.entrySet()) {
                for (Materia[] materias_ : grupo.getValue().getMaterias()) {
                    for (Materia materia : materias_) {
                        if (materia.getProfesor().getNumControl().equals(numControl)) {
                            materias.add(materia);
                        }
                    }
                }
            }
        }
    }

    public static void verGrupos() {
        int opc = 0;
        System.out.println("Grupos: ");
        for (Materia materia : ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias()) {
            System.out.println("Grupo " + materia.getGrupo().getSemestre() + materia.getGrupo().getTipoGrupo());
        }



        while (opc != 4) {
            System.out.println("1. Mostrar Alumnos de un Grupo ");
            System.out.println("2. Mostrar Alumnos de una Materia");
            System.out.println("3 Mostrar Alumnos de un semestre");
            System.out.println("4. Salir");
            opc = DatosComun.pedirNumero();
            switch (opc) {
                case 1:
                    System.out.println("Ingrese ID del grupo: ");
                    int id = DatosComun.pedirNumero();
                    mostrarAlumnosGrupo(id);
                    break;
                case 2:
                    mostrarAlumnosMateria(numDeMateria());
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Usted ha salido de ver Grupos");
                    break;

                default:
                    System.out.println("Esa opción no se encuentra");
            }
        }
    }

    public static void mostrarAlumnosGrupo(int id) {
        int opc = 0;
        for (Materia materia : ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias()) {
            if (materia.getGrupo().getId() == id) {
                for (Alumno alumno : materia.getGrupo().getAlumnos()) {
                    System.out.print(alumno.getNombre() + " ");
                               /* for (Calificacion calificacion : alumno.getCalificaciones()) {
                                    if (calificacion.getMateria().getProfesor() == ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual())) {
                                        System.out.print(calificacion.getMateria().getNombre() + " " + calificacion.getCalificacion() + " ");
                                    }
                                }*/
                    System.out.println();

                }
            }
        }
    }

    public static void mostrarAlumnosSemestre() {
    }

    public static int numDeMateria() {
        System.out.println("Materias: ");
        int mat = 0;
        {
            ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).mostrarMaterias();
            while (mat < 1 || mat >= ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().size()) {
                System.out.println("Ingrese la materia: ");
                mat = DatosComun.pedirNumero();
            }
            mat--;
        }
        return mat;
    }

    public static void mostrarAlumnosMateria(int mat) {
        int i=1;
        if (!((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().isEmpty()) {
            for (Alumno alumno : ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat).getGrupo().getAlumnos()) {
                System.out.println(i+" "+alumno.getNombre());
               /* for(Calificacion calificacion: alumno.getCalificaciones()){

                }*/
                i++;
            }

        } else {
            System.out.println("No tiene materias registradas");
        }
    }

    private void asignarCalificacion() {

    }

    private void modificarCalificacion() {

    }

    //CRUD DE PROFESOR
    public static void registrarProfesor(NombreCarrera carrera) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> datosComun = DatosComun.registrarDatosComun(Rol.PROFESOR);
        String nombre = datosComun.get(0);
        String apellidoPaterno = datosComun.get(1);
        String apellidoMaterno = datosComun.get(2);
        String anioNacimiento = datosComun.get(3);
        String fechaNacimientoCadena = datosComun.get(4);
        String ciudad = datosComun.get(5);
        String estado = datosComun.get(6);
        String direccion = datosComun.get(7);
        String CURP = datosComun.get(8);
        String fechaRegistroCadena = datosComun.get(9);
        String nombreUsuario = datosComun.get(10);
        String contrasena = datosComun.get(11);
        LocalDate fechaRegistro = LocalDate.parse(fechaRegistroCadena);
        String numContol = Generador.generarNumControl(Rol.PROFESOR, carrera, nombre, fechaRegistro);
        //ocupo volver int el año de nacimiento y LocalDate la fecha de nacimiento
        int anioNacimientoint = Integer.parseInt(anioNacimiento);
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoCadena);
        //
        System.out.println("Ingrese el salario. ");
        double salario = DatosComun.pedirValorDouble();
        String rfc = Generador.generarRFC(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento);
        Profesor profesor = new Profesor(nombre, apellidoPaterno, apellidoMaterno, anioNacimientoint, fechaNacimiento, ciudad, estado, direccion, CURP, fechaRegistro, nombreUsuario, contrasena, Rol.PROFESOR, rfc, salario, numContol);
        Sistema.usuarios.get(Rol.PROFESOR).add(profesor);
        System.out.println(">Profesor registrado<");

    }

    public static void modificarProfesor() {
        Scanner sc = new Scanner(System.in);
        mostrarProfesores();
        System.out.println("Selecciona al profesor: ");
        int numProfesor = pedirProfesor();
        int opt = 10;
        do {
            System.out.println("¿Qué información deseas editar?");
            System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento\n 7)Contraseña\n 0)Salir/Regresar");
            opt = DatosComun.pedirNumero();
            Profesor profesor = (Profesor) Sistema.usuarios.get(Rol.PROFESOR).get(numProfesor - 1);
            switch (opt) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre: ");
                    profesor.setNombre(DatosComun.pedirDatoString());
                    Sistema.usuarios.get(Rol.PROFESOR).set(numProfesor - 1, profesor);
                    String curpAntigua = profesor.getCurp();
                    char sexo = curpAntigua.charAt(10);
                    String nuevacurp = Generador.generarCURP(profesor.getNombre(), profesor.getApellidoPaterno(), profesor.getApellidoMaterno(), profesor.getFechaNacimiento(), sexo, profesor.getEstado());
                    String nuevorfc = Generador.generarRFC(profesor.getNombre(), profesor.getApellidoPaterno(), profesor.getApellidoMaterno(), profesor.getFechaNacimiento());

                    profesor.setRfc(nuevorfc);
                    profesor.setCurp(nuevacurp);
                    System.out.println("Nombre modificado");
                    break;

                case 2:
                    System.out.println("Ingrese el nuevo apellido Paterno: ");
                    profesor.setApellidoPaterno(DatosComun.pedirDatoString());
                    System.out.println("Ingrese el nuevo apellido Materno: ");
                    profesor.setApellidoMaterno(DatosComun.pedirDatoString());
                    String curpAntigua1 = profesor.getCurp();
                    char sexo1 = curpAntigua1.charAt(10);
                    String nuevaCurp = Generador.generarCURP(profesor.getNombre(), profesor.getApellidoPaterno(), profesor.getApellidoMaterno(), profesor.getFechaNacimiento(), sexo1, profesor.getEstado());
                    String nuevorfc1 = Generador.generarRFC(profesor.getNombre(), profesor.getApellidoPaterno(), profesor.getApellidoMaterno(), profesor.getFechaNacimiento());
                    profesor.setRfc(nuevorfc1);
                    profesor.setCurp(nuevaCurp);
                    Sistema.usuarios.get(Rol.PROFESOR).set(numProfesor - 1, profesor);
                    System.out.println("Apellido modificado");
                    break;

                case 3:
                    System.out.println("Ingrese nueva ciudad: ");
                    profesor.setCiudad(DatosComun.pedirDatoString());
                    Sistema.usuarios.get(Rol.PROFESOR).set(numProfesor - 1, profesor);
                    System.out.println("Ciudad actualizada");
                    break;

                case 4:
                    System.out.println("Ingrese nuevo estado: ");
                    profesor.setEstado(DatosComun.pedirDatoString());
                    Sistema.usuarios.get(Rol.PROFESOR).set(numProfesor - 1, profesor);
                    System.out.println("Estado actualizado");
                    break;

                case 5:
                    System.out.println("Ingrese nueva direccion: ");
                    profesor.setDireccion(DatosComun.pedirDireccion());
                    Sistema.usuarios.get(Rol.PROFESOR).set(numProfesor - 1, profesor);
                    System.out.println("Dirección actualizada");
                    break;

                case 6:
                    System.out.println("Fecha de nacimiento");
                    LocalDate nuevaFechaNacimiento = DatosComun.obtenerFechaNacimiento();
                    profesor.setFechaNacimiento(nuevaFechaNacimiento);
                    int anioNacimiento = nuevaFechaNacimiento.getYear();
                    profesor.setAnioNacimiento(anioNacimiento);
                    String curpAntigua2 = profesor.getCurp();
                    char sexo2 = curpAntigua2.charAt(10);
                    String curpNueva2 = Generador.generarCURP(profesor.getNombre(), profesor.getApellidoPaterno(), profesor.getApellidoMaterno(), profesor.getFechaNacimiento(), sexo2, profesor.getEstado());
                    String RFCNuevo2 = Generador.generarRFC(profesor.getNombre(), profesor.getApellidoPaterno(), profesor.getApellidoMaterno(), profesor.getFechaNacimiento());
                    profesor.setRfc(RFCNuevo2);
                    profesor.setCurp(curpNueva2);
                    System.out.println("Fecha Nacimiento Actualizada");
                    break;

                case 7:
                    System.out.println("Ingrese nueva contraseña");
                    String nuevaContrasena = sc.nextLine();
                    profesor.setContrasena(nuevaContrasena);
                    System.out.println("Contrasena Actualizada");
                    break;
                case 0:
                    System.out.println("Usted ha salido de modificar profesor. ");
                    //UsuarioEnSesion.getInstancia().cerrarSesion();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opt);

            }

        } while (opt != 0);

    }

    private static int pedirProfesor() {
        Scanner sc = new Scanner(System.in);
        boolean confirmacion = false;
        int numProfesor = 0;

        do {
            try {
                confirmacion=true;
                System.out.println("Selecciona al profesor: ");
                numProfesor = DatosComun.pedirNumero();

                if (numProfesor < 1 || numProfesor > Sistema.usuarios.get(Rol.PROFESOR).size()) {
                    throw new IndexOutOfBoundsException("El dato ingresado está fuera del tamaño de la lista");
                } else {

                    return numProfesor;
                }
            } catch (IndexOutOfBoundsException error) {
                confirmacion=false;
                System.out.println("Error: " + error.getMessage());

            }
        } while (confirmacion);
        sc.nextLine();
        return numProfesor;
    }

    public static void mostrarProfesores() {
        System.out.println("\nProfesores: \n");
        if (Sistema.usuarios.get(Rol.PROFESOR).isEmpty()) {
            System.out.println("No hay profesores registrados");
        } else {
            for (int i = 0; i < Sistema.usuarios.get(Rol.PROFESOR).size(); i++) {
                Profesor profesor = (Profesor) Sistema.usuarios.get(Rol.PROFESOR).get(i);
                System.out.println((i + 1) + " " + profesor.toString());
            }
        }
    }

}
