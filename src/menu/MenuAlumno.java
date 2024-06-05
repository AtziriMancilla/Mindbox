package menu;

import Secciones.Grupo;
import Secciones.Materia;
import Secciones.Semestre;
import Usuarios.Alumno;
import Usuarios.Profesor;
import mindbox.Sistema;
import mindbox.UsuarioEnSesion;

import java.util.ArrayList;
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
        Grupo grupo = obtenerGrupoPorID(alumno.getGrupo());
        if(alumno.getGrupo() == 0){
            System.out.println("Aun no estas registrado en un grupo");
        }
        else{
            String datos=String.format("id: %d, Tipo grupo: %s, Semestre: %d, Cantidad alumnos: %d", grupo.getId(),grupo.getTipoGrupo(),grupo.getSemestre(),grupo.getAlumnos().size());
            System.out.println(datos);
        }
    }
    public static void verProfesores(Alumno alumno){
        Grupo grupo = obtenerGrupoPorID(alumno.getGrupo());
        if(alumno.getGrupo() == 0){
            System.out.println("No tienes profesores asignados");
        }
        else {
            if (grupo.getMateria().get(alumno.getSemestre()).get(0) != null) {
                Profesor profesor1 = grupo.getMateria().get(alumno.getSemestre()).get(0).getProfesor();
                Materia materia1 = grupo.getMateria().get(alumno.getSemestre()).get(0);
                System.out.printf("Profesor 1: %s \n Materia que imparte: %s", profesor1, materia1);
            }
            if (grupo.getMateria().get(alumno.getSemestre()).get(1) != null) {
                Profesor profesor2 = grupo.getMateria().get(alumno.getSemestre()).get(1).getProfesor();
                Materia materia2 = grupo.getMateria().get(alumno.getSemestre()).get(1);
                System.out.printf("Profesor 2: %s \n Materia que imparte: %s", profesor2, materia2);
            }
            if (grupo.getMateria().get(alumno.getSemestre()).get(2) != null) {
                Profesor profesor3 = grupo.getMateria().get(alumno.getSemestre()).get(2).getProfesor();
                Materia materia3 = grupo.getMateria().get(alumno.getSemestre()).get(2);
                System.out.printf("Profesor 1: %s \n Materia que imparte: %s", profesor3, materia3);
            }
        }
    }
    //ver como hacer si no hay calificaciuones
    public static void verMaterias(Alumno alumno){
        Grupo grupo = obtenerGrupoPorID(alumno.getGrupo());
        if(alumno.getGrupo() != 0){
            ArrayList<Materia> materias= grupo.getMateria().get(alumno.getSemestre());
            for(Materia materia:materias){
                System.out.println(materia.toString());
            }
        }
        else {
            System.out.println("No hay materias asignadas");
        }
    }

    public static Grupo obtenerGrupoPorID(int id){
        Grupo grupo = null;
        for (int i = 0; i < 3; i++) {
            for(Grupo gr : Sistema.semestres.get(i).getGrupos()){
                if (gr.getId() == id){
                    grupo = gr;
                }
            }
        }
        return grupo;
    }
}
