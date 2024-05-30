package Usuarios.utils;

public class Calificacion {
    int calificacion;
    boolean aprobado=false;
    // String, Materia materia;

    //, materia
    public Calificacion(int calificacion) {
        this.calificacion=calificacion;
        if(calificacion>=70){
            aprobado=true;
        }
    }

    public static void registrarCalificacion(){
        System.out.println("Escoja al alumno: ");
        //buscar alumno, verificando que el maestro en sesion tenga a ese alumno
        //alumno.getCalificaciones.add(calificacion)?
        System.out.println("");

    }

    public static void modificarCalificacion(){
        System.out.println("Escoja al alumno: ");
        //buscar alumno, verificando que el maestro en sesion tenga a ese alumno

    }
}
