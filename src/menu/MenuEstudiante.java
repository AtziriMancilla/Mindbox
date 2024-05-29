package menu;

import java.util.Scanner;

public class MenuEstudiante {
    private static Scanner scanner = new Scanner(System.in);
    public static void menu(){
        String id;
        String action;
        do {
            System.out.println("\n1 - ACCION");
            System.out.println("2 - ACCION");
            System.out.println("3 - ACCION");
            System.out.println("4 - ACCION");
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
    private static String obtenerId(){
        System.out.print("Ingrese numero de control: ");
        String id = scanner.next();
        return id;
    }
}
