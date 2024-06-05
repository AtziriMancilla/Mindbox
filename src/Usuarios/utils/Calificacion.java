package Usuarios.utils;

import Secciones.Grupo;
import Secciones.Materia;
import Secciones.Semestre;
import Usuarios.Alumno;
import Usuarios.Profesor;
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
/*
    //Asigna las calificaciones de grupos a usuarios en Sistema
    public static void grupoUsuarios() {
        for (Grupo grupo : Sistema.grupos) {
            for (Alumno alumno : grupo.getAlumnos()) {
                for (Usuario alumno1 : Sistema.usuarios.get(Rol.ALUMNO)) {
                    if (alumno.getNumControl().equals(((Alumno) alumno1).getNumControl())) {
                        ((Alumno) alumno1).setCalificaciones(alumno.getCalificaciones());
                    }
                }
            }
        }
    }*/

    //También sirve para modificar
    public static void registrarCalificacion() {
        int mat = Profesor.numDeMateria();
        int alum = 4;
        System.out.println("Alumnos: ");
        Profesor.mostrarAlumnosMateria(mat, 1);
        Profesor profesor = (Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual();
        Materia materia1 = profesor.getMaterias().get(mat);
        Grupo grupo1 = Profesor.obtenerGrupoPorId(materia1.getGrupo());
        while (alum != -1 && mat != -1) {
            do {
                System.out.println("Ingrese el no. de alumno: ");
                System.out.println("Ingrese 0 para dejar de Calificar");
                alum = DatosComun.pedirNumero();
            } while (alum < 0 || alum > grupo1.getAlumnos().size());
            alum--;
            if (alum != -1) {
                double cali = -4;
                while (cali < 0 || cali > 100) {
                    System.out.println("Ingresar Calificación: ");
                    cali = DatosComun.pedirValorDouble();
                }
                Calificacion calificacion = new Calificacion(cali, ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).getMaterias().get(mat));
                for (Semestre semestre : Sistema.semestres) {
                    for (Grupo grupo : semestre.getGrupos()) {
                        Materia materia2 = calificacion.getMateria();
                        Grupo grupo2 = Profesor.obtenerGrupoPorId(materia2.getGrupo());
                            if (grupo.getId() == grupo2.getId()) {
                                for (Alumno alumno : grupo.getAlumnos()) {
                                    if ((alumno).getNumControl().equals(grupo1.getAlumnos().get(alum).getNumControl())) {
                                        boolean hayCalificaion = false;
                                        if (alumno.getCalificaciones().size() != 0) {
                                            for (Calificacion nota : (alumno.getCalificaciones())) {
                                                if (nota.getMateria().getNombre().equals(calificacion.getMateria().getNombre())) {
                                                    nota.setCalificacion(calificacion.getCalificacion());
                                                    hayCalificaion = true;
                                                    System.out.println("La calificación fue modificada");
                                                }
                                            }
                                        }
                                        if (!hayCalificaion) {//no se como cambiarlo ocupo aiuda mariana, te invocooo //Mariana.exe ha respondido
                                            alumno.getCalificaciones().add(calificacion);
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }


                }
            }
        }
        if (mat != -1) {
            ((Profesor) UsuarioEnSesion.getInstancia().getUsuarioActual()).asignarMaterias();
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

    @Override
    public String toString() {
        return
                "Calificacion:" + calificacion +
                        ", Materia:" + materia +
                        ", Fecha:" + fecha +
                        ' ';
    }

}
