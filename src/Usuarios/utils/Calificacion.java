package Usuarios.utils;

import Secciones.Grupo;
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


    public Calificacion(double calificacion, Materia materia) {
        this.calificacion = calificacion;
        if (calificacion >= 70) {
            aprobado = true;
        }
        this.materia = materia;
        fecha = LocalDate.now();
    }

    //También sirve para modificar
    public static void registrarCalificacion() {
        int mat = Profesor.numDeMateria();
        int alum = 0;
        System.out.println("Alumnos: ");
        Profesor.mostrarAlumnosMateria(mat, 1);
        while (alum != -1) {
            while (alum < 0 || alum >= ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat).getGrupo().getAlumnos().size()) {
                System.out.println("Ingrese el no. de alumno: ");
                System.out.println("Ingrese 0 para dejar de Calificar");
                alum = DatosComun.pedirNumero();
            }
            alum--;
            if (alum != -1) {
                double cali = -4;
                while (cali < 0 || cali > 100) {
                    System.out.println("Ingresar Calificación: ");
                    cali = DatosComun.pedirValorDouble();
                }
                Calificacion calificacion = new Calificacion(cali, ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat));
                for (Alumno alumno: Sistema.grupos.get(((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat).getGrupo().getSemestre()).getAlumnos()) {
                    if ((alumno).getNumControl().equals(((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat).getGrupo().getAlumnos().get(alum).getNumControl())) {
                        int caliRegistradas = 0;
                        boolean hayCalificaion = false;
                        if (alumno.getCalificaciones().size() != 0) {
                            for (Calificacion nota : (alumno.getCalificaciones())) {
                                if (nota.getMateria().getMateria().equals(calificacion.getMateria().getMateria())) {
                                    nota.setCalificacion(calificacion.getCalificacion());
                                    hayCalificaion = true;
                                    System.out.println("La calificación fue modificada");
                                }
                                caliRegistradas++;
                            }
                        }
                        if (!hayCalificaion) {//no se como cambiarlo ocupo aiuda mariana, te invocooo
                            alumno.getCalificaciones().get(caliRegistradas) = calificacion;
                        }
                        break;
                    }
                }
                ((Profesor)UsuarioEnSesion.getInstancia().getUsuarioActual()).asignarMaterias();
            }
        }
    }

    //GETTERS Y SETTERS
    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
        if (calificacion >= 70) {
            aprobado = true;
        } else {
            aprobado = false;
        }
        fecha = LocalDate.now();

    }

    public boolean isAprobado() {
        return aprobado;
    }

    public Materia getMateria() {
        return materia;
    }

}
