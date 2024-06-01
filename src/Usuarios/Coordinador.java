package Usuarios;

import Secciones.utils.NombreCarrera;
import Usuarios.utils.Rol;

import java.time.LocalDate;

public class Coordinador extends Trabajador{
    private NombreCarrera carrera;
    private String numControl;
    public Coordinador(String nombre, String apellidoPaterno, String apellidoMaterno, int anioNacimiento, LocalDate fechaNacimiento, String ciudad, String estado, String direccion, String curp, LocalDate fechaRegistro, String usuario, String contrasena, Rol rol, String rfc, double salario, String numControl, NombreCarrera carrera){
        super(nombre, apellidoPaterno, apellidoMaterno, anioNacimiento, fechaNacimiento, ciudad, estado, direccion, curp, fechaRegistro, usuario, contrasena, rol, rfc, salario);
        //this.
        this.numControl= numControl;
        this.carrera = carrera;
    }

    @Override
    public String toString(){
        return String.format("%s, numero de Control: %s", super.toString(), numControl);
    }

    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }
    /////////////////
    private void avanzarGrupoSemestre(){

    }
    private void crearProfesor(){

    }
    private void modificarProfesor(){

    }
    private void eliminarProfesor(){

    }
    private void buscarProfesor(){

    }

    public NombreCarrera getCarrera() {
        return carrera;
    }

    public void setCarrera(NombreCarrera carrera) {
        this.carrera = carrera;
    }
}
