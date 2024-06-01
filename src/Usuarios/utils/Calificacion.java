package Usuarios.utils;

import Secciones.Materia;
import Usuarios.Alumno;
import Usuarios.Profesor;
import Usuarios.Usuario;
import mindbox.Sistema;
import mindbox.UsuarioEnSesion;

import java.time.LocalDate;


public class Calificacion {
    double calificacion;
    boolean aprobado = false;
    Materia materia;
    LocalDate fecha;//fecha en la que se registró la calificacion

    //, materia
    public Calificacion(double calificacion, Materia materia) {
        this.calificacion = calificacion;
        if (calificacion >= 70) {
            aprobado = true;
        }
        this.materia = materia;
        fecha=LocalDate.now();
    }

    public static void registrarCalificacion() {
       int mat=Profesor.numDeMateria();
       int alum=0;
       System.out.println("Alumnos: ");
       Profesor.mostrarAlumnosMateria(mat);

        while (alum < 1 || alum >= ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat).getGrupo().getAlumnos().size()) {
            System.out.println("Ingrese al alumno: ");
            alum = DatosComun.pedirNumero();
        }
        alum--;
        double cali=-4;
        while (cali<0||cali>100) {
            System.out.println("Ingresar Calificación: ");
             cali= DatosComun.pedirValorDouble();
        }
        Calificacion calificacion= new Calificacion (cali,((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat));
        for(Usuario usuario: Sistema.usuarios.get(Rol.ALUMNO)){
            if(((Alumno) usuario).getNumControl().equals(((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat).getGrupo().getAlumnos().get(alum).getNumControl())){
               /* ((Alumno)usuario).getCalificaciones().add(calificacion);*/
            }
        }

    }

    public static void modificarCalificacion() {

        //buscar alumno, verificando que el maestro en sesion tenga a ese alumno

    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }


}
