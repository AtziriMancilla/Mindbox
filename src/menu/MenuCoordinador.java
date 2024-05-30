package menu;

import Usuarios.Coordinador;
import Usuarios.Profesor;
import Usuarios.utils.Rol;

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
            System.out.println("3 - Impartir materia");
            System.out.println("4 - Ver materias impartidas");
            System.out.println("5 - Avanzar grupo de semestre");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action){
                case "1": menuAlumnos();
                    break;
                case "2": menuProfesores();
                    break;
                case "3":
                    //Coordinador.impartirMateria();
                    break;
                case "4":
                    break;
                case "5":
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
}
