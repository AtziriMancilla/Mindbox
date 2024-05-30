package Usuarios;

import Usuarios.utils.Rol;

import java.time.LocalDate;

public class Profesor extends Trabajador {
    private String numControl;

    public Profesor(String nombre, String apellidoPaterno, String apellidoMaterno, int anioNacimiento, LocalDate fechaNacimiento, String ciudad, String estado, String direccion, String curp, LocalDate fechaRegistro, String usuario, String contrasena, Rol rol, String rfc, double salario, String numControl){
        super(nombre, apellidoPaterno, apellidoMaterno, anioNacimiento, fechaNacimiento, ciudad, estado, direccion, curp, fechaRegistro, usuario, contrasena, rol, rfc, salario);
        this.numControl = numControl;
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
    ////////////
    public void darDeAlta(){

    }
    private void verInformacion(){

    }
    public void actualizarInformacion(){

    }
    private void verGrupos(){

    }
    private void asignarCalificacion(){

    }
    private void modificarCalificacion(){

    }
}
