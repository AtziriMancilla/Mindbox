package mindbox;

import Secciones.Grupo;
import Secciones.Semestre;
import Usuarios.Coordinador;
import Usuarios.utils.Rol;
import Usuarios.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sistema {
    // NECESARIO: GETTERS EN USUARIO + Hacer que coordinador sea clase hija de usuario

    public static final Map<Rol, ArrayList<Usuario>> usuarios = new HashMap<Rol, ArrayList<Usuario>>();
    public static Map<Integer, Grupo> grupos= new HashMap<>();

    public static Usuario verificarInicioSesion(String nombreUsuario, String contrasena){
        for (Map.Entry<Rol, ArrayList<Usuario>> entry : usuarios.entrySet()){
            for (Usuario usuarioActual : entry.getValue()){
                /*if (usuarioActual.getUsuario().equals(nombreUsuario) && usuarioActual.getContrasena().equals(contrasena)){
                    return usuarioActual;
                }*/
            }
        }
        return null;
    }
    public static void inicializar(){
        usuarios.put(Rol.ALUMNO, new ArrayList<>());
        usuarios.put(Rol.PROFESOR, new ArrayList<>());
        usuarios.put(Rol.COORDINADOR, new ArrayList<>());
        /*
        Coordinador c1 = new Coordinador();
        usuarios.get(Rol.COORDINADOR).add(c1);
        Coordinador c2 = new Coordinador();
        usuarios.get(Rol.COORDINADOR).add(c2);
        Coordinador c3 = new Coordinador();
        usuarios.get(Rol.COORDINADOR).add(c3);

         */
    }



}
