package Usuarios;

import Secciones.Grupo;
import Secciones.Materia;
import Secciones.utils.NombreCarrera;
import Usuarios.utils.Calificacion;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import mindbox.Sistema;
import mindbox.UsuarioEnSesion;
import mindbox.utils.Generador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Profesor extends Trabajador {
    private String numControl;
    ArrayList<Materia> materias= new ArrayList<>();

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

    public static void verInformacion() {
        System.out.println((UsuarioEnSesion.getInstancia().getUsuarioActual()).toString());
        System.out.println("Materias asignadas: ");

            ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).mostrarMaterias();


    }

    public static void actualizarInformacion() {
        int opc = 0;
        System.out.println("Ingrese Contraseña Actual");
        String contrasena = DatosComun.pedirDatoString();
        if (contrasena.equals(((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getContrasena())) {
            while (opc != 3) {
                System.out.println("1. Cambiar Usuario");
                System.out.println("2. Cambiar Contraseña");
                System.out.println("3. Salir");
                System.out.println("Ingrese opción: ");
                opc = DatosComun.pedirNumero();
                switch (opc) {
                    case 1:
                        System.out.println("Ingrese nuevo Usuario:  ");
                        String usuario = DatosComun.obtenerNombreUsuario(Rol.PROFESOR);
                        ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).setUsuario(usuario);
                        break;
                    case 2:
                        System.out.println("Ingrese nueva contraseña: ");
                        contrasena = DatosComun.pedirDatoUsuario();
                        ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).setContrasena(contrasena);
                        break;
                    case 3:
                        System.out.println("Usted ha salido de modificar. ");
                        break;
                    default:
                        System.out.println("Esa opción no se encuentra");
                        break;
                }
            }
        } else {
            System.out.println("No se pueden actualizar datos sin contraseña");
        }

    }

    public void mostrarMaterias() {
        int i = 0;
        if (!((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().isEmpty())
            for (Materia materia : ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias()) {
                i++;
                System.out.println(i + " " + materia.getNombre() + "  " + materia.getGrupo().getSemestre() + " " + materia.getGrupo().getTipoGrupo());
            }
        else{
            System.out.println("No tienes materias asignadas");
        }
    }


    //Aplicar después de cambiar Semestre y asignar Profesores, para que al Profesor se le registren las materias que tiene
    public void asignarMaterias() {
        materias.clear();
        if (!Sistema.grupos.isEmpty()) {
            for (Map.Entry<Integer, Grupo> grupo : Sistema.grupos.entrySet()) {
                for (Map.Entry<Integer, ArrayList<Materia>> matEntry : grupo.getValue().getMateria().entrySet()) {
                    if (grupo.getKey().equals(matEntry.getKey())) {
                        for (Materia materia : matEntry.getValue()) {
                            if (materia.getProfesor().getNumControl().equals(numControl)) {
                                materias.add(materia);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void verGrupos() {
        int opc = 0, mostrar = 0;
        System.out.println("Grupos: ");
        if (!((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().isEmpty()) {
            for (Materia materia : ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias()) {
                System.out.println("Grupo " + materia.getGrupo().getSemestre() + materia.getGrupo().getTipoGrupo());
                System.out.println("Materia: " + materia.getMateria());
                System.out.println("Id del grupo: " + materia.getGrupo().getId());
            }
            while (opc < 1 || opc > 4) {
                System.out.println("1. Mostrar todos los alumnos");
                System.out.println("2. Mostrar Aprobados");
                System.out.println("3. Mostrar Reprobados");
                System.out.println("4. Salir");
                opc = DatosComun.pedirNumero();
                switch (opc) {
                    case 1:
                        mostrar = opc;
                        break;
                    case 2:
                        mostrar = opc;
                        break;
                    case 3:
                        mostrar = opc;
                        break;
                    case 4:
                        System.out.println("Usted ha salido de ver Grupos");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }
            if (opc == 4) {
                opc = 6;
            }

            while (opc != 6) {
                System.out.println("1. Mostrar alumnos de un Grupo ");
                System.out.println("2. Mostrar alumnos de una Materia y Grupo");
                System.out.println("3. Mostrar alumnos de un Semestre ");
                System.out.println("4. Mostrar a todos los alumnos");
                System.out.println("5. Salir");
                opc = DatosComun.pedirNumero();
                switch (opc) {
                    case 1:
                        System.out.println("Ingrese ID del grupo: ");
                        int id = DatosComun.pedirNumero();
                        mostrarAlumnosGrupo(id, mostrar);
                        break;
                    case 2:
                        mostrarAlumnosMateria(numDeMateria(), mostrar);
                        break;
                    case 3:
                        System.out.println("Ingrese Semestre: ");
                        int semestre = 0;
                        while (semestre < 1 || semestre > 3) {
                            semestre = DatosComun.pedirNumero();
                        }
                        mostrarAlumnosSemestre(semestre, mostrar);
                        break;
                    case 4:
                        mostrarTodosAlumnos(mostrar);

                    case 5:
                        System.out.println("Usted ha salido de ver Grupos");
                        break;

                    default:
                        System.out.println("Esa opción no se encuentra");
                }
            }

        } else {
            System.out.println("No hay materias registradas. ");
        }
    }

    public static void mostrarAlumnosGrupo(int id, int mostrar) {
        boolean todoBien = false;
        if (!((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().isEmpty()) {
            for (Materia materia : ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias()) {
                if (materia.getGrupo().getId() == id) {
                    todoBien = true;
                    for (Alumno alumno : materia.getGrupo().getAlumnos()) {
                        if (mostrar == 1) {
                            System.out.print("\n" + alumno.getNombre() + " ");
                        }
                        if (alumno.getCalificaciones().length != 0) {
                            for (Calificacion calificacion : alumno.getCalificaciones()) {
                                if (calificacion.getMateria().getProfesor().getNumControl().equals(((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getNumControl())) {

                                    if (mostrar == 1) {
                                        System.out.print(calificacion.getMateria().getMateria() + " " +
                                                calificacion.getMateria().getGrupo().getSemestre() + " " + calificacion.getCalificacion() + "\n");
                                    }
                                    if (mostrar == 2) {
                                        if (calificacion.isAprobado()) {
                                            System.out.print("\n" + alumno.getNombre() + " ");
                                            System.out.print(calificacion.getMateria().getMateria() + " " +
                                                    calificacion.getMateria().getGrupo().getSemestre() + " " + calificacion.getCalificacion() + "\n");
                                        }
                                    }
                                    if (mostrar == 3) {
                                        if (!calificacion.isAprobado()) {
                                            System.out.print("\n" + alumno.getNombre() + " ");
                                            System.out.print(calificacion.getMateria().getMateria() + " " +
                                                    calificacion.getMateria().getGrupo().getSemestre() + " " + calificacion.getCalificacion() + "\n");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            }
            if (!todoBien) {
                System.out.println("No se ha encontrado al Grupo");
            }
        } else {
            System.out.println("No tiene materias asignadas. ");
        for (Materia materia : ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias()) {
            if (materia.getGrupo().getId() == id) {
                todoBien = true;
                for (Alumno alumno : materia.getGrupo().getAlumnos()) {
                    if (mostrar == 1) {
                        System.out.print("\n" + alumno.getNombre() + " ");
                    }
                    if (alumno.getCalificaciones().size() != 0) {
                        for (Calificacion calificacion : alumno.getCalificaciones()) {
                            if (calificacion.getMateria().getMateria().equals(materia.getMateria())) {
                                if (mostrar == 1) {
                                    System.out.print(calificacion.getMateria().getMateria() + " " +
                                            calificacion.getMateria().getGrupo().getSemestre() + " " + calificacion.getCalificacion() + "\n");
                                }
                                if (mostrar == 2) {
                                    if (calificacion.isAprobado()) {
                                        System.out.print("\n" + alumno.getNombre() + " ");
                                        System.out.print(calificacion.getMateria().getMateria() + " " +
                                                calificacion.getMateria().getGrupo().getSemestre() + " " + calificacion.getCalificacion() + "\n");
                                    }
                                }
                                if (mostrar == 3) {
                                    if (!calificacion.isAprobado()) {
                                        System.out.print("\n" + alumno.getNombre() + " ");
                                        System.out.print(calificacion.getMateria().getMateria() + " " +
                                                calificacion.getMateria().getGrupo().getSemestre() + " " + calificacion.getCalificacion() + "\n");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            break;
        }
        if (!todoBien) {
            System.out.println("No se ha encontrado al Grupo");
        }
    }

    public static int numDeMateria() {
        System.out.println("Materias: ");
        int mat;
        if (!((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().isEmpty()) {
            ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).mostrarMaterias();
            do {
                System.out.println("Ingrese la materia: ");
                mat = DatosComun.pedirNumero();
            } while (mat < 1 || mat > ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().size());
            mat--;
            return mat;
        } else {
            return -1;
        }
    }

    public static void mostrarAlumnosMateria(int mat, int mostrar) {
        int i = 1;
        if (!((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().isEmpty()) {
            System.out.println(((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat).getMateria() + " " +
                    ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat).getGrupo().getSemestre() + " " +
                    ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat).getGrupo().getTipoGrupo() + ": ");
            for (Alumno alumno : ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat).getGrupo().getAlumnos()) {
                if (mostrar == 1) {
                    System.out.println(i + " " + alumno.getNombre() + " ");
                }
                if (alumno.getCalificaciones().length != 0) {
                    for (Calificacion calificacion : alumno.getCalificaciones()) {
                        if (((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat).getMateria().equals(calificacion.getMateria().getMateria())) {
                            if (mostrar == 1) {
                                System.out.println(calificacion.getCalificacion());
                            }
                            if (mostrar == 2) {
                                if (calificacion.isAprobado()) {
                                    System.out.print(i + " " + alumno.getNombre() + " ");
                                    System.out.println(calificacion.getCalificacion());
                                }
                            }
                            if (mostrar == 3) {
                                if (!calificacion.isAprobado()) {
                                    System.out.print(i + " " + alumno.getNombre() + " ");
                                    System.out.println(calificacion.getCalificacion());
                                }
                            }
                            System.out.println();

                        }
                    }
                }
                i++;
            }

        } else {
            System.out.println("No tiene materias registradas");
        }
    }

    public static void mostrarAlumnosSemestre(int semestre, int mostrar) {
        System.out.println("Alumnos de Semestre: " + semestre);
        for (Materia materia : ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias()) {
            if (materia.getGrupo().getSemestre() == semestre) {
                System.out.println("Materia: " + materia.getMateria());
                for (Alumno alumno : materia.getGrupo().getAlumnos()) {
                    if (mostrar == 1) {
                        System.out.println(alumno.getNombre() + " ");
                    }
                    if (alumno.getCalificaciones().size() != 0) {
                        for (Calificacion calificacion : alumno.getCalificaciones()) {
                            if (calificacion.getMateria().getMateria().equals(materia.getMateria())) {
                                if (mostrar == 1) {
                                    System.out.println(calificacion.getCalificacion());
                                }
                                if (mostrar == 2) {
                                    if (calificacion.isAprobado()) {
                                        System.out.print(alumno.getNombre() + " ");
                                        System.out.println(calificacion.getCalificacion());
                                    }
                                }
                                if (mostrar == 3) {
                                    if (!calificacion.isAprobado()) {
                                        System.out.print(alumno.getNombre() + " ");
                                        System.out.println(calificacion.getCalificacion());
                                    }
                                }
                                System.out.println();

                                }
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("No tiene materias registradas");
        }

    }

    public static void mostrarTodosAlumnos(int mostrar) {
        for (Materia materia : ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias()) {
            System.out.println("Materia: " + materia.getMateria() + " Grupo: " + materia.getGrupo().getSemestre() + materia.getGrupo().getTipoGrupo());
            for (Alumno alumno : materia.getGrupo().getAlumnos()) {
                if (mostrar == 1) {
                    System.out.println(alumno.getNombre() + " ");
                }
                if (alumno.getCalificaciones().size() != 0) {
                    for (Calificacion calificacion : alumno.getCalificaciones()) {
                        if (calificacion.getMateria().getMateria().equals(materia.getMateria())) {
                            if (mostrar == 1) {
                                System.out.println(calificacion.getCalificacion());
                            }
                            if (mostrar == 2) {
                                if (calificacion.isAprobado()) {
                                    System.out.print(alumno.getNombre() + " ");
                                    System.out.println(calificacion.getCalificacion());
                                }
                            }
                            if (mostrar == 3) {
                                if (!calificacion.isAprobado()) {
                                    System.out.print(alumno.getNombre() + " ");
                                    System.out.println(calificacion.getCalificacion());
                                }
                            }
                            System.out.println();
                        }
                    }
                }
            }
        }
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
        int numProfesor = pedirProfesor();
        if(numProfesor!=0){
            int opt = 10;
            do {
                System.out.println("¿Qué información deseas editar?");
                System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento\n7)Contraseña\n0)Salir/Regresar");
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
                        System.out.println("Opción no válida.\n");
                        break;
                    /*try {
                        throw new IllegalStateException();
                    } catch (IllegalStateException e) {
                        System.out.println("Opción no válida.\n");
                    }*/
                }

            } while (opt != 0);
        }
        else {
            System.out.println("Regresando");
        }


    }

    public static int pedirProfesor() {
        Scanner sc = new Scanner(System.in);
        boolean confirmacion = false;
        int numProfesor = 0;

        do {
            confirmacion = false;
            try {
                System.out.println("Selecciona al profesor: ");
                System.out.println("ingrese 0) Regresar/Salir");
                numProfesor = DatosComun.pedirNumero();

                if (numProfesor < 0 || numProfesor > Sistema.usuarios.get(Rol.PROFESOR).size()) {
                    throw new IndexOutOfBoundsException("El dato ingresado está fuera del tamaño de la lista");
                } else {

                    return numProfesor;
                }
            } catch (IndexOutOfBoundsException error) {

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

    public static void buscarProfesor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el numero de control");
        String opcion=DatosComun.pedirDatoUsuario();
        boolean band=true;
        for(int i=0; i<Sistema.usuarios.get(Rol.PROFESOR).size();i++) {
            Profesor profesor=(Profesor) Sistema.usuarios.get(Rol.PROFESOR).get(i);
            if (profesor.getNumControl().equals(opcion)){
                System.out.println("El profesor buscado es: "+profesor.toString());
                band=false;
            }
        }
        if(band){
            System.out.println("No se encontró el profesor");
        }
    }

    //    public static void eliminar(){ metodo sin terminar que tal vez se use
//        System.out.println("**Eliminar**");
//        int opcion= pedirProfesor();
//        Profesor profesor=(Profesor) Sistema.usuarios.get(Rol.PROFESOR).get(-1);
//        if (profesor.getMaterias().isEmpty()){
//          Sistema.usuarios.get(Rol.PROFESOR).remove(opcion-1) ;
//            System.out.println("Profesor eliminado");
//        }
//    }
    public static void borrarProfesor() {
        Scanner sc = new Scanner(System.in);
        mostrarProfesores();
        int numProfesor = 0;
        boolean band;
        do {
            try {
                band = false;
                System.out.println("Selecciona el profesor que deseas eliminar");
                numProfesor = sc.nextInt();
                Sistema.usuarios.get(Rol.PROFESOR).get(numProfesor - 1);
            } catch (IndexOutOfBoundsException | InputMismatchException error) {
                System.out.println("Opcion no valida");
                band = true;
                //revisar sc.nextLine
            }
        } while (band);
        Profesor profesor = (Profesor) Sistema.usuarios.get(Rol.PROFESOR).get(numProfesor - 1);
        if (profesor.getMaterias().isEmpty()) {
            System.out.println("Seleccionaste a: ");
            System.out.println(Sistema.usuarios.get(Rol.PROFESOR).get(numProfesor - 1).toString());
            System.out.println("¿Deseas eliminarlo? 1) Sí, Otro número) Cancelar");
            int opcion = DatosComun.pedirNumero();
            if (opcion == 1) {
                Sistema.usuarios.get(Rol.PROFESOR).remove(numProfesor - 1);
                System.out.println("Profesor eliminado");
            }
            if (opcion != 1) {
                System.out.println("Se cancelo la eliminación");
            }
        } else {
            System.out.println("No se puede eliminar a este profesor");
        }
    }
}
