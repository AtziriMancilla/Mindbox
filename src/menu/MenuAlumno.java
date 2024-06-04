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
    public static void verProfesores(Alumno alumno){
        Profesor profesor1= alumno.getGrupo().getMateria().get(alumno.getSemestre()).get(0).getProfesor();
        Materia materia1= alumno.getGrupo().getMateria().get(alumno.getSemestre()).get(0);
        Profesor profesor2= alumno.getGrupo().getMateria().get(alumno.getSemestre()).get(1).getProfesor();
        Materia materia2= alumno.getGrupo().getMateria().get(alumno.getSemestre()).get(1);
        Profesor profesor3= alumno.getGrupo().getMateria().get(alumno.getSemestre()).get(2).getProfesor();
        Materia materia3= alumno.getGrupo().getMateria().get(alumno.getSemestre()).get(2);
        String profesores=String.format("Profesor: %s Materia: %s\nProfesor: %s, Materia: %s\nProfesor: %s, Materia: %s",profesor1,materia1,profesor2,materia2,profesor3,materia3);
        System.out.println(profesores);
    }
    //ver como hacer si no hay calificaciuones
    public static void verMaterias(Alumno alumno){
        System.out.println(alumno.getGrupo().getMateria().get(alumno.getSemestre()).get(0).toString());
        //String datos=String.format("id: %d, Tipo grupo: %s, Semestre: %d, Cantidad alumnos: %d",alumno.getGrupo().getId(),alumno.getGrupo().getTipoGrupo(),alumno.getGrupo().getSemestre(),alumno.getGrupo().getCantidadAlumnos());
    }
}
