package Graduados;

import Secciones.utils.NombreCarrera;
import Usuarios.Alumno;
import Usuarios.utils.DatosComun;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Graduado {
    //Registro de los alumnos que ya se graduaron

    //private ArrayList<Graduados> graduados; creo que esto debería ir en sistema
    private String alumnoNombre;
    private double alumnoPromedio;
    private NombreCarrera alumnoCarrera;

    private LocalDate fechaGraduacion;
    String generacionDeGraduacion;

    public Graduado(String alumnoNombre, double alumnoPromedio, NombreCarrera alumnoCarrera, LocalDate fechaGraduacion, String generacionDeGraduacion) {
        this.alumnoNombre = alumnoNombre;
        this.alumnoPromedio = alumnoPromedio;
        this.alumnoCarrera = alumnoCarrera;
        this.fechaGraduacion = fechaGraduacion;
        this.generacionDeGraduacion = generacionDeGraduacion;
    }

    @Override
    public String toString(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fechaGraduacionFormateada = fechaGraduacion.format(pattern);

        String promedio = String.valueOf(alumnoPromedio);
        String carrera = alumnoCarrera.toString();

        return String.format("Nombre:%s Promedio:%s Carrera:%s  Fecha de Graduacion: %s, Generacion: %s", alumnoNombre, promedio, carrera, fechaGraduacionFormateada, generacionDeGraduacion);
    }
    public void registrarGraduado(Alumno alumno){

        String alumnoNombre = alumno.getNombre()+" "+alumno.getApellidoPaterno()+" "+ alumno.getApellidoMaterno();
        double alumnoPromedio = alumno.getPromedio();
        NombreCarrera alumnoCarrera = alumno.getCarrera();
        //falta fecha de graduacion y generacion Generación de graduación. Ejemplo: Ene - Jun 2024, Ago - Dic 2024, etc.
        System.out.println("Registro Fecha de Graduación");
        LocalDate fechaGraduacion = DatosComun.obtenerFechaNacimiento();

        //generacion
        int mes = fechaGraduacion.getMonthValue();
        String meses ="";
        if(mes<7){
            meses= "Ene - Jun";
        }
        else {meses= "Ago - Dic";}
        String anio = String.valueOf(fechaGraduacion.getYear());
        String generacionDeGraduacion = meses+" "+anio;

        Graduado graduado = new Graduado(alumnoNombre, alumnoPromedio, alumnoCarrera, fechaGraduacion, generacionDeGraduacion);
        //agregar a una lista
    }

}
