package Graduados;

import Usuarios.Alumno;
import Usuarios.utils.DatosComun;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Graduados {
    //Registro de los alumnos que ya se graduaron
    private LocalDate fechaGraduacion;
    //private Carrera carrera;
    private ArrayList<String> graduados;

    private LocalDate generacion;

    @Override
    public String toString(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fechaGraduacionFormateada = fechaGraduacion.format(pattern);
        String generacionFormateada = generacion.format(pattern);
        return String.format(" fechaGraduacion: %s, Generacion: %s", fechaGraduacionFormateada, generacionFormateada);
    }
    public void registrarGraduado(Alumno alumno){
        //necesito Alumno, fecha de graduacion .now, su carrera, su promedio
        //y la generación
        String alumnoNombre = alumno.getNombre()+alumno.getApellidoPaterno()+alumno.getApellidoMaterno();
        String alumnoPromedio = String.valueOf(alumno.getPromedio());
        String alumnoCarrera = String.valueOf(alumno.getCarrera());
        //falta fecha de graduacion y generacion Generación de graduación. Ejemplo: Ene - Jun 2024, Ago - Dic 2024, etc.
        System.out.println("Registro Fecha de Graduación");
        LocalDate fechaGraduacion = DatosComun.obtenerFechaNacimiento();
    }

}
