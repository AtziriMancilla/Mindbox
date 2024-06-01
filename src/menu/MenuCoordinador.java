package menu;

import Secciones.Grupo;
import Secciones.Materia;
import Secciones.utils.NombreCarrera;
import Secciones.utils.NombreMaterias;
import Usuarios.Coordinador;
import Usuarios.Profesor;
import Usuarios.utils.Rol;
import mindbox.UsuarioEnSesion;

import java.util.Scanner;

public class MenuCoordinador {
    private static Scanner scanner = new Scanner(System.in);
    public static void menu(){
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
            switch (action){
                case "1": menuAlumnos();
                    break;
                case "2": menuProfesores();
                    break;
                case "3": menuGrupos();
                    break;
                case "4":
                    Grupo grupo = Grupo.obtenerGrupo();
                    menuGrupo(grupo);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opcion inexistente");
            }
        } while (!action.equals("0"));
    }
    private static void menuAlumnos(){
        String action, numControl;
        do {
            System.out.println("\n1 - Crear alumno");
            System.out.println("2 - Modificar alumno");
            System.out.println("3 - Eliminar alumno");
            System.out.println("4 - Buscar alumno");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action){
                case "1":
                    //Alumno.crear();
                    break;
                case "2":
                    numControl = Menu.obtenerNumeroDeControl();
                    //Alumno.modificar(numControl);
                    break;
                case "3":
                    numControl = Menu.obtenerNumeroDeControl();
                    //Alumno.eliminar(numControl);
                    break;
                case "4":
                    Menu.buscar(Rol.ALUMNO);
                    break;
                case "0":
                    System.out.println("Regresando");
                    break;
                default:
                    System.out.println("Opcion inexistente");
            }
        } while (!action.equals("0"));
    }
    private static void menuProfesores(){
        String action, numControl;
        do {
            System.out.println("\n1 - Crear profesor");
            System.out.println("2 - Modificar profesor");
            System.out.println("3 - Eliminar profesor");
            System.out.println("4 - Buscar profesor");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action){
                case "1":
                    //Profesor.crear();
                    break;
                case "2":
                    numControl = Menu.obtenerNumeroDeControl();
                    //Profesor.modificar(numControl);
                    break;
                case "3":
                    numControl = Menu.obtenerNumeroDeControl();
                    //Profesor.eliminar(numControl);
                    break;
                case "4":
                    Menu.buscar(Rol.PROFESOR);
                    break;
                case "0":
                    System.out.println("Regresando");
                    break;
                default:
                    System.out.println("Opcion inexistente");
            }
        } while (!action.equals("0"));
    }
    private static void menuGrupos(){
        String action, numControl;
        NombreCarrera carrera = ((Coordinador)UsuarioEnSesion.getInstancia().getUsuarioActual()).getCarrera();
        do {
            System.out.println("\n1 - Crear grupo");
            System.out.println("2 - Modificar grupo");
            System.out.println("3 - Eliminar grupo");
            System.out.println("4 - Mostrar grupo");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action){
                case "1":
                    Grupo.crearGrupo(carrera);
                    break;
                case "2":
                    break;
                case "3": // No se si se debe crear la opcino ELIMINAR GRUPO
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
    private static void menuGrupo(Grupo grupo){
        String action, actAux;
        NombreCarrera carrera = ((Coordinador)UsuarioEnSesion.getInstancia().getUsuarioActual()).getCarrera();
        do {
            System.out.println("\n1 - Avanzar grupo de semestre");
            System.out.println("2 - Añadir materia");
            System.out.println("3 - Modificar materia");
            System.out.println("4 - Mostrar materias");
            System.out.println("5 - Añadir alumno");
            System.out.println("6 - Modificar alumno");
            System.out.println("7 - Eliminar alumno");
            System.out.println("8 - Mostrar alumnos");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action){
                case "1":
                    Grupo.avanzarGrupo(carrera, grupo);
                    break;
                case "2":
                    // añadir materia a grupo
                    NombreMaterias materia = Materia.seleccionarMateria(carrera);
                    if (!Grupo.materiaExistente(grupo, materia)){
                        // Falta obtener profesor
                        // Profesor profesor = Profesor.obtenerProfesor();
                        // Grupo.addMaterias(grupo, materia, profesor);
                    } else {
                        System.out.print("La materia ya existe en el grupo, ¿Desea modificarla?\n1 - Modificar\n0 - Continuar en el menu\nAcción: ");
                        actAux = scanner.next();
                        if (actAux.equals("1")){
                            Grupo.modificarMateria(grupo, materia);
                        }
                    }
                    break;
                case "3":
                    Materia mat = Grupo.obtenerMateria(grupo);
                    Grupo.modificarMateria(grupo, mat.getMateria());
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
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
