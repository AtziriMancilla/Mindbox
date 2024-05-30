package Graduados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Graduados {
    private LocalDate fechaGraduacion;
    //private Carrera carrera;
    private ArrayList<String> alumnoPromedio;

    private LocalDate generacion;

    @Override
    public String toString(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fechaGraduacionFormateada = fechaGraduacion.format(pattern);
        String generacionFormateada = generacion.format(pattern);
        return String.format(" fechaGraduacion: %s, Generacion: %s", fechaGraduacionFormateada, generacionFormateada);
    }
}
