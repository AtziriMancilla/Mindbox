package menu;

import java.util.Scanner;

public class MenuProfesor {
    private static Scanner scanner = new Scanner(System.in);
    public static void menu(){
        String id;
        String action;
        /*
        Acciones:
        Ver su info
        Actualizarla
        Ver sus gupos
        Asignar calificaciones
        Modificar calificaciones
         */
        do {
            System.out.println("\n1 - Ver mi perfil");
            System.out.println("2 - Actualizar mi perfil");
            System.out.println("3 - Ver mis grupos");
            System.out.println("4 - Asignar calificaciones");
            System.out.println("5 - Modificar calificaciones");
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
