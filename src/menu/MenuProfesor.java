package menu;

import Usuarios.Profesor;
import Usuarios.utils.Calificacion;
import mindbox.UsuarioEnSesion;

import java.util.Scanner;

public class MenuProfesor {
    private static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        String action;

        do {
            System.out.println("\n1 - Ver mi perfil");
            System.out.println("2 - Actualizar mi perfil");
            System.out.println("3 - Ver mis grupos");
            System.out.println("4 - Asignar calificaciones");
            System.out.println("5 - Modificar calificaciones");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action) {
                case "1":
                    Profesor.verInformacion();
                    break;
                case "2":
                    Profesor.actualizarInformacion();
                    break;
                case "3":
                    Profesor.verGrupos();
                    break;
                case "4":
                    Calificacion.registrarCalificacion();
                    break;
                case "0":
                    UsuarioEnSesion.getInstancia().cerrarSesion();
                    Menu.iniciarSesion();
                    break;
                default:
                    System.out.println("Opcion inexistente");
            }
        } while (!action.equals("0"));
    }
}
