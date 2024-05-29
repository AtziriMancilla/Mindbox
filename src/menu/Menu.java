package menu;

import java.util.Objects;
import java.util.Scanner;

import mindbox.Sistema;
import mindbox.UsuarioEnSesion;

public class Menu {
    //NECESARIO: UsuarioEnSesion, Usuario, Rol, Coordinador, Estudiante, Profesor
    static Scanner scanner = new Scanner(System.in);


    public static void iniciarSesion() {
        boolean access = false;

        do {
            System.out.println("- - - Mindbox - - -");

            System.out.println("Inicia sesión para continuar");

            System.out.print("Usuario: ");
            String usuario = scanner.next();

            System.out.print("Contrasena: ");
            String contrasena = scanner.next();

            /*
            Usuario usuarioActual = Sistema.verificarInicioSesion(usuario, contrasena);

            if (usuarioActual != null) {
                UsuarioEnSesion.getInstancia().setUsuarioActual(usuarioActual);
                access = true;
                menu();
            } else {
                System.out.println("Usuario o contrasena incorrectos. Intenta de nuevo.");
            }
            */



        } while (!access);
    }

    public static void continuar(){
        String next;
        System.out.println("1 - Volver a iniciar sesión\n2 - Salir del programa");
        next = scanner.next();

        if (next.equals("1")){
            //UsuarioEnSesion.getInstancia().cerrarSesion();
            iniciarSesion();
        } else if (!next.equals("2")) {
            continuar();
        } else {
            System.out.println("Saliendo del programa...");
        }
    }

    // Seleccion de menu por rol
    public static void menu() {
        /*
        Usuario usuario = UsuarioEnSesion.getInstancia().getUsuarioActual();
        String rolLetras = String.valueOf(usuario.getRol());
        System.out.println(String.format("- - - Menu %s - - -", rolLetras.toLowerCase()));
        Rol rol = usuario.getRol();
        switch (rol) {
            case ESTUDIANTE:
                MenuEstudiante.menu();
                break;
            case PROFESOR:
                MenuProfesor.menu();
                break;
            case COORDINADOR:
                MenuCoordinador.menu();
                break;
            default:
        }
         */

        continuar();
    }

}