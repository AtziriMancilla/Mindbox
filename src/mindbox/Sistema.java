package mindbox;

import Graduados.Graduado;
import Secciones.Grupo;
import Secciones.Semestre;
import Secciones.utils.NombreCarrera;
import Usuarios.Coordinador;
import Usuarios.utils.Rol;
import Usuarios.Usuario;
import mindbox.utils.Generador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sistema {
    // NECESARIO: GETTERS EN USUARIO + Hacer que coordinador sea clase hija de usuario

    public static HashMap<Rol, ArrayList<Usuario>> usuarios = new HashMap<Rol, ArrayList<Usuario>>();
    public static HashMap<Integer, Grupo> grupos= new HashMap<>();
    public static ArrayList<Graduado> graduados = new ArrayList<>();
    public static  ArrayList<Semestre> semestres = new ArrayList<Semestre>();

    public static Usuario verificarInicioSesion(String nombreUsuario, String contrasena){
        for (Map.Entry<Rol, ArrayList<Usuario>> entry : usuarios.entrySet()){
            for (Usuario usuarioActual : entry.getValue()){
                if (usuarioActual.getUsuario().equals(nombreUsuario) && usuarioActual.getContrasena().equals(contrasena)){
                    return usuarioActual;
                }
            }
        }
        return null;
    }
    public static void inicializar(){
        usuarios.put(Rol.ALUMNO, new ArrayList<>());
        usuarios.put(Rol.PROFESOR, new ArrayList<>());
        usuarios.put(Rol.COORDINADOR, new ArrayList<>());

        Coordinador c1 = new Coordinador("1", "1", "1", 2000, LocalDate.now(), "1", "1", "1", "1", LocalDate.now(), "1", "1", Rol.COORDINADOR, "1", 0, "1", NombreCarrera.ISC);
        usuarios.get(Rol.COORDINADOR).add(c1);
        Coordinador c2 = new Coordinador("2", "2", "2", 2000, LocalDate.now(), "2", "2", "2", "2", LocalDate.now(), "2", "2", Rol.COORDINADOR, "2", 0, "2", NombreCarrera.IMAT);
        usuarios.get(Rol.COORDINADOR).add(c2);
        Coordinador c3 = new Coordinador("3", "3", "3", 2000, LocalDate.now(), "3", "3", "3", "3", LocalDate.now(), "3", "3", Rol.COORDINADOR, "3", 0, "3", NombreCarrera.ELC);
        usuarios.get(Rol.COORDINADOR).add(c3);

    }
    //Setter para poder asignar los nuevos datos obtenidos del json al atributo HashMap usuarios.
    public static void setUsuarios(HashMap<Rol, ArrayList<Usuario>> usuarios) {
        Sistema.usuarios = usuarios;
    }
    //Setter para asignar los nuevos datos obtenidos del json al atributo ArrayList graduados.
    public static void setGraduados(ArrayList<Graduado> graduados) {
        Sistema.graduados = graduados;
    }
    //Setter para asignar los datos obtenidos del json al atributo HashMap grupos.
    public static void setGrupos(HashMap<Integer, Grupo> grupos) {
        Sistema.grupos = grupos;
    }
    //Setter para asignar los datos obtenidos del json al atributo ArrayList semestres.
    public static void setSemestres(ArrayList<Semestre> semestres) {
        Sistema.semestres = semestres;
    }
}
