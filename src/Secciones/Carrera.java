package Secciones;

import Secciones.utils.NombreCarrera;
import Usuarios.Alumno;
import Usuarios.Coordinador;
import Usuarios.Usuario;
import Usuarios.utils.Rol;
import mindbox.Sistema;

import java.time.LocalDate;
import java.util.Map;

public class Carrera {
    NombreCarrera carrera;
    int id, cantGrupo, cantAlum, cantMate = 9;
    LocalDate fechaCreacion;
    Coordinador cordinador;
    static int Cantidad = 1;

    public Carrera(Coordinador cordinador) {
        fechaCreacion = LocalDate.now();
        this.cordinador = cordinador;
        id = Cantidad;
        contar();
    }


    public void contar() {
        cantAlum = 0;
        cantGrupo = 0;
        if (Sistema.usuarios.get(Rol.ALUMNO) != null) {
            for (Usuario usuario : Sistema.usuarios.get(Rol.ALUMNO)) {
                if (((Alumno) usuario).getGrupo().getCarrera() == carrera) {
                    cantAlum++;
                }
            }
        }
        if (!Sistema.semestres.isEmpty()) {
            for(Semestre semestre:Sistema.semestres){
            for (Grupo grupo:semestre.getGrupos()) {
                if (grupo.getCarrera() == carrera) {
                    cantGrupo++;
                }}
            }
        }
    }

    @Override
    public String toString() {
        return "Carrera: " + carrera + ", id: " + id + ", Grupos :" + cantGrupo + ", Alumnos:" + cantAlum + "\nMaterias:" +
                cantMate + ", FechaCreacion: " + fechaCreacion + ", Coordinador: " + cordinador +
                '}';
    }
}
