package menu;

import java.util.Objects;
import java.util.Scanner;

import Usuarios.Usuario;
import Usuarios.utils.Rol;
import mindbox.Sistema;
import mindbox.UsuarioEnSesion;

public class Menu {
    //NECESARIO: GETTERS DE USUARIO
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


            Usuario usuarioActual = Sistema.verificarInicioSesion(usuario, contrasena);

            if (usuarioActual != null) {
                UsuarioEnSesion.getInstancia().setUsuarioActual(usuarioActual);
                access = true;
                menu();
            } else {
                System.out.println("Usuario o contrasena incorrectos. Intenta de nuevo.");
            }




        } while (!access);
    }

    public static void continuar(){
        String next;
        System.out.println("1 - Volver a iniciar sesión\n2 - Salir del programa");
        next = scanner.next();

        if (next.equals("1")){
            UsuarioEnSesion.getInstancia().cerrarSesion();
            iniciarSesion();
        } else if (!next.equals("2")) {
            continuar();
        } else {
            System.out.println("Saliendo del programa...");
        }
    }

    // Seleccion de menu por rol
    public static void menu() {
        Usuario usuario = UsuarioEnSesion.getInstancia().getUsuarioActual();

        String rolLetras = String.valueOf(usuario.getRol());
        System.out.println(String.format("- - - Menu %s - - -", rolLetras.toLowerCase()));
        Rol rol = usuario.getRol();
        switch (rol) {
            case ALUMNO:
                MenuAlumno.menu();
                break;
            case PROFESOR:
                MenuProfesor.menu();
                break;
            case COORDINADOR:
                MenuCoordinador.menu();
                break;
            default:
        }

        continuar();
    }

    public static String obtenerNumeroDeControl(){
        System.out.print("Numero de control: ");
        String num = scanner.next();
        return num;
    }

    // Este metodo buscara por cierta caracteristica a x usuario
    public static void buscar(Rol rol){
        //String rolLetras = String.valueOf(usuario.getRol());
        //System.out.println(String.format("%s - Buscar por: ", rolLetras.toLowerCase()));
        String action;
        do {
            System.out.println("\n1 - FORMA DE BUSCAR");
            System.out.println("2 - FORMA DE BUSCAR");
            System.out.println("3 - FORMA DE BUSCAR");
            System.out.println("4 - FORMA DE BUSCAR");
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
                    System.out.println("Regresando");
                    break;
                default:
                    System.out.println("Opcion inexistente");
            }
        } while (!action.equals("0"));
    }

    // Este metodo sera modificado para que muestre usuarios segun lo que queramos, de hecho habran varios xd
    private static void mostrarUsuarios(Rol rol) {
        if (!Sistema.usuarios.containsKey(rol)) {
            System.out.println(String.format("No hay %s registrados\n", String.valueOf(rol).toLowerCase()));
        } else {
            for (Usuario usuario : Sistema.usuarios.get(rol)) {
                System.out.println("algo");
            }
        }
    }

}