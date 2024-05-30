package Usuarios;

import Usuarios.utils.Rol;

import java.time.LocalDate;

public class Profesor extends Trabajador {
    private int numControl;

    public Profesor(String nombre, String apellidoPaterno, String apellidoMaterno, int anioNacimiento, LocalDate fechaNacimiento, String ciudad, String estado, String direccion, String curp, LocalDate fechaRegistro, String usuario, String contrasena, Rol rol, String rfc, double salario, int numControl){
        super(nombre, apellidoPaterno, apellidoMaterno, anioNacimiento, fechaNacimiento, ciudad, estado, direccion, curp, fechaRegistro, usuario, contrasena, rol, rfc, salario);
        this.numControl = numControl;
    }
    @Override
    public String toString(){
        return String.format("%s, numero de Control: %s", super.toString(), numControl);
    }

    public int getNumControl() {
        return numControl;
    }

    public void setNumControl(int numControl) {
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
