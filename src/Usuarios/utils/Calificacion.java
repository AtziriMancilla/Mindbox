package Usuarios.utils;
import Secciones.Materia;
import Usuarios.Alumno;
import Usuarios.Usuario;
import mindbox.Sistema;
import mindbox.UsuarioEnSesion;

public class Calificacion {
    int calificacion;
    boolean aprobado=false;
    Materia materia;

    //, materia
    public Calificacion(int calificacion) {
        this.calificacion=calificacion;
        if(calificacion>=70){
            aprobado=true;
        }
    }

    public static void registrarCalificacion(){
        for (Usuario usuario : Sistema.usuarios.get(Rol.PROFESOR)) {
          //  if(UsuarioEnSesion.getInstancia().getUsuarioActual()==OBTENER A PROFESOR DE ALGUN GRUPO){

            //  }



        }

    }

    public static void modificarCalificacion(){
        System.out.println("Escoja al alumno: ");
        //buscar alumno, verificando que el maestro en sesion tenga a ese alumno

    }
}
