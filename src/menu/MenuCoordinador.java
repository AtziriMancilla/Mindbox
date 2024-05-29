package menu;

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
            System.out.println("\n1 - Estudiantes...");
            System.out.println("2 - Profesores...");
            System.out.println("3 - Impartir materia");
            System.out.println("4 - Ver materias impartidas");
            System.out.println("5 - Avanzar grupo de semestre");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
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

                    break;
                case "2":
                    numControl = Menu.obtenerNumeroDeControl();

                    break;
                case "3":
                    numControl = Menu.obtenerNumeroDeControl();
                    break;
                case "4":

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
