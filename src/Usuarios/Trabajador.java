package Usuarios;

import Usuarios.utils.Rol;

import java.time.LocalDate;

public class Trabajador extends Usuario{

    private String rfc;
   // private Materia materiasImparte;
    private double salario;
    public Trabajador(String nombre, String apellidoPaterno, String apellidoMaterno, int anioNacimiento, LocalDate fechaNacimiento, String ciudad, String estado, String direccion, String curp, LocalDate fechaRegistro, String usuario, String contrasena, Rol rol, String rfc, double salario) {
        super(nombre, apellidoPaterno, apellidoMaterno, anioNacimiento, fechaNacimiento, ciudad, estado, direccion, curp, fechaRegistro, usuario, contrasena, rol);
        this.salario= salario;
        this.rfc=rfc;
        //this.materiasImparte = materiasImparte;
    }

    @Override
    public String toString(){
        return String.format("%s, RFC: %s, Salario: %f Materias que imparte: ...", super.toString(), rfc,  salario);
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
