package menu;

import Secciones.Materia;
import Usuarios.Alumno;
import Usuarios.Profesor;
import mindbox.UsuarioEnSesion;

import java.util.Scanner;

public class MenuAlumno {
    private static Scanner scanner = new Scanner(System.in);
    public static void menu(){
        String id;
        String action;
        Alumno alumno = (Alumno) UsuarioEnSesion.getInstancia().getUsuarioActual();
        // Acciones:
        // Alumno puede ver todo lo que un alumno puede hacer en un sistema de gestion escolar
        do {
            System.out.println("\n1 - Ver mi perfil");
            System.out.println("2 - Ver grupo en curso");
            System.out.println("3 - Ver mis materias en curso");
            System.out.println("4 - Ver mis profesores en curso");
            System.out.println("5 - Ver mi historial");
            System.out.println("0 - Salir");
            System.out.print("Selección: ");
            action = scanner.next();
            switch (action){
                case "1":
                    mostrarPerfil(alumno);
                    break;
                case "2":
                    verGrupo(alumno);
                    break;
                case "3": verMaterias(alumno);
                    break;
                case "4": verProfesores(alumno);
                    break;
                case "5":
                    alumno.mostrarHistorial();
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
    public static void mostrarPerfil(Alumno alumno){
        String datos=String.format("Numero de control: %s, Promedio: %f,Carrera: %s",alumno.getNumControl(),alumno.getPromedio(),alumno.getCarrera());
        System.out.println(datos);
    }
    public static void verGrupo(Alumno alumno){
        String datos=String.format("id: %d, Tipo grupo: %s, Semestre: %d, Cantidad alumnos: %d",alumno.getGrupo().getId(),alumno.getGrupo().getTipoGrupo(),alumno.getGrupo().getSemestre(),alumno.getGrupo().getCantidadAlumnos());
        System.out.println(datos);
    }
    //todavia no esta listo
    public static void verProfesores(Alumno alumno){
        //Materia materia1= alumno.getGrupo().getMaterias()[0][0];
        //String datos=String.format("id: %d, Tipo grupo: %s, Semestre: %d, Cantidad alumnos: %d",alumno.getGrupo().getId(),alumno.getGrupo().getTipoGrupo(),alumno.getGrupo().getSemestre(),alumno.getGrupo().getCantidadAlumnos());
    }
    public static void verMaterias(Alumno alumno){
        //Materia materia1= alumno.getGrupo().getMaterias()[0][0];
        //String datos=String.format("id: %d, Tipo grupo: %s, Semestre: %d, Cantidad alumnos: %d",alumno.getGrupo().getId(),alumno.getGrupo().getTipoGrupo(),alumno.getGrupo().getSemestre(),alumno.getGrupo().getCantidadAlumnos());
    }
}
