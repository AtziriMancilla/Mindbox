package menu;

import Secciones.Grupo;
import Secciones.Materia;
import Secciones.utils.NombreCarrera;
import Secciones.utils.NombreMaterias;
import Usuarios.Alumno;
import Usuarios.Coordinador;
import Usuarios.Profesor;
import Usuarios.Usuario;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import mindbox.Sistema;
import mindbox.UsuarioEnSesion;

import java.util.Scanner;

public class MenuCoordinador {
    private static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        String action;
        /*
        Acciones:
        Dar alta estudiante DE SU CARRERA
        Dar alta profesor DE SU CARRERA
        Puede impartir 1 o mas clases en cualquier carrera
        Avanzar semestre DE SU CARRERA
         */
        do {
            System.out.println("\n1 - Alumnos...");
            System.out.println("2 - Profesores...");
            System.out.println("3 - Grupos... (CRUD)");
            System.out.println("4 - Gestionar grupo...");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action) {
                case "1":
                    menuAlumnos();
                    break;
                case "2":
                    menuProfesores();
                    break;
                case "3":
                    menuGrupos();
                    break;
                case "4":
                    if (Grupo.hayGrupos()){
                        menuGrupo(Grupo.obtenerGrupo());
                    } else {
                        System.out.println("No hay grupos");
                    }
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opcion inexistente");
            }
        } while (!action.equals("0"));
    }

    private static void menuAlumnos() {
        String action, numControl;
        do {
            System.out.println("\n1 - Crear alumno");
            System.out.println("2 - Modificar alumno");
            System.out.println("3 - Eliminar alumno");
            System.out.println("4 - Ver alumnos");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action) {
                case "1":
                    //obtener de que carrera es el coordinador
                    Coordinador coordinador = (Coordinador) UsuarioEnSesion.getInstancia().getUsuarioActual();
                    Alumno.registrarAlumno(coordinador.getCarrera());
                    break;
                case "2":
                    Coordinador coordinador2 = (Coordinador) UsuarioEnSesion.getInstancia().getUsuarioActual();
                    Alumno.modificarAlumno(coordinador2.getCarrera());
                    break;
                case "3":
                    Coordinador coordinador3 = (Coordinador) UsuarioEnSesion.getInstancia().getUsuarioActual();
                    Alumno.eliminarAlumno(coordinador3.getCarrera());
                    break;
                case "4":
                    Coordinador coordinador4 = (Coordinador) UsuarioEnSesion.getInstancia().getUsuarioActual();
                    Alumno.mostrarAlumnos(coordinador4.getCarrera());
                    break;

                case "0":
                    System.out.println("Regresando");
                    break;
                default:
                    System.out.println("Opcion inexistente");
            }
        } while (!action.equals("0"));
    }

    private static void menuProfesores() {
        String action, numControl;
        Coordinador coordinador = (Coordinador) UsuarioEnSesion.getInstancia().getUsuarioActual();
        do {
            System.out.println("\n1 - Crear profesor");
            System.out.println("2 - Modificar profesor");
            System.out.println("3 - Eliminar profesor");
            System.out.println("4 - Buscar profesor por numero de control");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action) {
                case "1":
                    Profesor.registrarProfesor(coordinador.getCarrera());
                    break;
                case "2":
                    Profesor.modificarProfesor();
                    break;
                case "3":
                    Profesor.borrarProfesor();
                    break;
                case "4":
                    Profesor.buscarProfesor();
                    break;
                case "0":
                    System.out.println("Regresando");
                    break;
                default:
                    System.out.println("Opcion inexistente");
            }
        } while (!action.equals("0"));
    }

    private static void menuGrupos() {
        String action, numControl;
        NombreCarrera carrera = ((Coordinador) UsuarioEnSesion.getInstancia().getUsuarioActual()).getCarrera();
        do {
            System.out.println("\n1 - Crear grupo");
            System.out.println("2 - Modificar grupo");
            System.out.println("3 - Eliminar grupo");
            System.out.println("4 - Mostrar grupo");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action) {
                case "1":
                    Grupo.crearGrupo(carrera);
                    break;
                case "2": // Muchos atributos no son modificables, de hecho solo es modificar o materia o alumnos
                    // Conclusion: esta cosa usa los mismos metodos que 3 y 6 en el menuGrupo (abajo)
                    Grupo.modificarGrupo();
                    break;
                case "3":
                    Grupo.eliminarGrupo();
                    break;
                case "4":
                    Grupo.mostrarGrupos();
                    break;
                case "0":
                    System.out.println("Regresando");
                    break;
                default:
                    System.out.println("Opcion inexistente");
            }
        } while (!action.equals("0"));
    }

    private static void menuGrupo(Grupo grupo) {
        String action;
        Alumno alumno;
        NombreCarrera carrera = ((Coordinador) UsuarioEnSesion.getInstancia().getUsuarioActual()).getCarrera();
        do {
            System.out.println("\n1 - Avanzar grupo de semestre");
            System.out.println("2 - Asignar profesor a materia");
            System.out.println("3 - Modificar profesor de materia");
            System.out.println("4 - Mostrar materias");
            System.out.println("5 - Añadir alumno");
            System.out.println("6 - Modificar alumno");
            System.out.println("7 - Eliminar alumno");
            System.out.println("8 - Mostrar alumnos");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action) {
                case "1":
                    Grupo.avanzarGrupo(carrera, grupo);
                    break;
                case "2":
                    Profesor.mostrarProfesores();
                    Profesor profesor = (Profesor) Sistema.usuarios.get(Rol.PROFESOR).get(Profesor.pedirProfesorIndice());
                    Grupo.addProfeMateria(grupo, profesor);

                    break;
                case "3":
                    Grupo.modificarMateria(grupo);
                    break;
                case "4":
                    Grupo.mostrarMaterias(grupo);
                    break;
                case "5":
                    Grupo.agregarAlumnoGrupo(carrera,grupo);
                    break;
                case "6":
                    alumno = Grupo.obtenerAlumnoGrupo(grupo);
                    Grupo.modificarAlumno(alumno);
                    break;
                case "7":
                    if (grupo.getAlumnos().size() > 3){
                        alumno = Grupo.obtenerAlumnoGrupo(grupo);
                        Grupo.eliminarAlumno(alumno, grupo);
                    } else {
                        System.out.println("Cantidad de alumnos en grupo es: "+grupo.getAlumnos().size());
                        System.out.println("Desea:\n1 - Eliminar alumno\n2 - Cancelar");
                        int aux;
                        do {
                            aux = DatosComun.pedirNumero();
                            if (aux < 1 || aux > 2){
                                System.out.println("Desea:\n1 - Eliminar alumno\n2 - Cancelar");
                            }
                        } while (aux < 1 || aux > 2);
                        if (aux == 1){
                            alumno = Grupo.obtenerAlumnoGrupo(grupo);
                            Grupo.eliminarAlumno(alumno, grupo);
                        } else {
                            System.out.println("Operacion cancelada");
                        }
                    }
                    break;
                case "8":
                    Grupo.mostrarAlumnos(grupo);
                    break;
                case "0":
                    System.out.println("Regresando");
                    break;
                default:
                    System.out.println("Opcion inexistente");
            }
        } while (!action.equals("0"));
    }
}
