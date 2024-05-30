package menu;

import java.util.Scanner;

public class MenuAlumno {
    private static Scanner scanner = new Scanner(System.in);
    public static void menu(){
        String id;
        String action;
        /*
        Acciones:
        Alumno puede ver abtodo lo que un alumno puede hacer en un sistema de gestion escolar
         */
        do {
            System.out.println("\n1 - Ver mi perfil");
            System.out.println("2 - Ver mi grupo");
            System.out.println("3 - Ver mis materias");
            System.out.println("4 - Ver mis profesores");
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
                case "0":
                    break;
                default:
                    System.out.println("Opcion inexistente");
            }
        } while (!action.equals("0"));
    }
}
