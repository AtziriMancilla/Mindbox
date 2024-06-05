package mindbox;

import Graduados.Graduado;
import Secciones.Grupo;
import Secciones.Semestre;
import Secciones.TipoGrupo;
import Secciones.utils.NombreCarrera;
import Usuarios.Alumno;
import Usuarios.Coordinador;
import Usuarios.Profesor;
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
    public static ArrayList <Grupo> grupos=new ArrayList<>();
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

        Coordinador c1 = new Coordinador("Atziri", "Mancilla", "Cruz", 2000, LocalDate.now(), "Patzcuaro", "Michoacan", "Patzcuarito", "MACA960605MMNNRT02", LocalDate.now(), "Atziri", "Alan", Rol.COORDINADOR, "MACA960605MMN", 0, "CA24ISC0", NombreCarrera.ISC);
        usuarios.get(Rol.COORDINADOR).add(c1);
        Coordinador c2 = new Coordinador("Jafet", "Santoyo", "Benites", 2000, LocalDate.now(), "Morelia", "Michoacán", "Rey Ezequias #88", "SABJ970130HMNNNF09", LocalDate.now(), "jafiSan", "uwu", Rol.COORDINADOR, "SABJ970130HM1", 0, "CJ24IMAT0", NombreCarrera.IMAT);
        usuarios.get(Rol.COORDINADOR).add(c2);
        Coordinador c3 = new Coordinador("Sianya", "Garcia", "Medina", 2000, LocalDate.now(), "Hidalgo", "Michoacan", "3", "GAMS050720MMNRDNA4", LocalDate.now(), "sianyis", "contabilidadesmipasion", Rol.COORDINADOR, "GAMS050720MMN", 0, "CS24ELC0", NombreCarrera.ELC);
        usuarios.get(Rol.COORDINADOR).add(c3);

        Profesor prof1 = new Profesor("Alejandro","Montejano","Diaz", 2003, LocalDate.now(), "Morelia", "Michoacan", "Calle desconocida", "MODA030204HMNNZLA4", LocalDate.now(), "Elpro","1234", Rol.PROFESOR, "MODA030204HMN", 0, "MA24ISC0");
        Profesor prof2 = new Profesor("Mariana", "Paz", "Alfaro", 2005, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "PAAM050210MMNZLRA9", LocalDate.now(), "genshin", "amogenshin", Rol.PROFESOR,  "PAAM050210MMN", 0, "MM24ISC0");
        Profesor prof3 = new Profesor("Daniela", "Mancilla", "Cruz", 2000, LocalDate.now(), "Patzcuaro", "Michoacan", "Callechida", "MACA960605MMNNRT02", LocalDate.now(), "AtziriProfe", "Alan", Rol.PROFESOR, "LOME200522HMN", 0, "MD24ISC0" );

        Profesor prof4 = new Profesor("Pamela", "Cardoso", "Riveron", 2000, LocalDate.now(), "Patzcuaro", "Michoacan", "Calle municipal", "PEGJ850315MJCRRN07", LocalDate.now(), "gatitocosmico", "uwu", Rol.PROFESOR,  "PEGJ850315MJC", 0, "MP24IMAT0");
        Profesor prof5 = new Profesor("Manuel", "Mancilla", "Cruz", 2000, LocalDate.now(), "Morelia", "Michoacan", "Juan Alvarez", "MACM990516HMNNRN06", LocalDate.now(), "Wizdo", "wishes", Rol.PROFESOR,  "MACM990516HMN", 0, "MM24IMAT0");
        Profesor prof6 = new Profesor("Jafet", "Santoyo", "Benites", 2000, LocalDate.now(), "Patzcuaro", "Michoacan", "Calle municipal", "MACA960605MMNNRT02", LocalDate.now(), "jafiSanProfe", "uwu", Rol.PROFESOR,  "MACA960605MMN", 0, "MJ24IMAT0");

        Profesor prof7 = new Profesor("Alan", "Lopez", "Mancilla", 2000, LocalDate.now(), "Patzcuaro", "Michoacan", "Callechida", "LOME200522HMNPNDA5", LocalDate.now(), "Sonic", "amosonic", Rol.PROFESOR, "LOME200522HMN", 0, "MA24ELEC0" );
        Profesor prof8 = new Profesor("Sianya", "Garcia", "Medina", 2000, LocalDate.now(), "Patzcuaro", "Michoacan", "Calle municipal", "GAMS050720MMNRDNA4", LocalDate.now(), "sianyis", "contabilidadesmipasion",  Rol.PROFESOR,  "PEGJ850315MJC", 0, "MS24ELC0");
        Profesor prof9 = new Profesor("Paola Itzel", "Negrete", "Cobian", 2000, LocalDate.now(), "Patzcuaro", "Michoacan", "Calle municipal", "NECP970423MMNGBL03", LocalDate.now(), "Pao", "Draco", Rol.PROFESOR,  "NECP970423MMN", 0, "MP24ELEC0");

        usuarios.get(Rol.PROFESOR).add(prof1);
        usuarios.get(Rol.PROFESOR).add(prof2);
        usuarios.get(Rol.PROFESOR).add(prof3);
        usuarios.get(Rol.PROFESOR).add(prof4);
        usuarios.get(Rol.PROFESOR).add(prof5);
        usuarios.get(Rol.PROFESOR).add(prof6);
        usuarios.get(Rol.PROFESOR).add(prof7);
        usuarios.get(Rol.PROFESOR).add(prof8);
        usuarios.get(Rol.PROFESOR).add(prof9);

        Alumno al1 = new Alumno("Juan", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "JUAN030204HMNNZLA4", LocalDate.now(), "Juanito", "uwu", Rol.ALUMNO, NombreCarrera.ISC, "lJ24ISC0") ;
        Alumno al2 = new Alumno("Alejandro", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "ALEJ030204HMNNZLA4", LocalDate.now(), "Alejandro2", "uwu", Rol.ALUMNO, NombreCarrera.ISC, "lA24ISC0") ;
        Alumno al3 = new Alumno("Atziri", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "ATZI030204HMNNZLA4", LocalDate.now(), "Atziri2", "uwu", Rol.ALUMNO, NombreCarrera.ISC, "lA24ISC1") ;
        Alumno al4 = new Alumno("Sianya", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "SIAN030204HMNNZLA4", LocalDate.now(), "Sianya2", "uwu", Rol.ALUMNO, NombreCarrera.ISC, "lS24ISC0") ;
        Alumno al5 = new Alumno("Mariana", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "MARI030204HMNNZLA4", LocalDate.now(), "Mariana2", "uwu", Rol.ALUMNO, NombreCarrera.ELC, "lM24ELC0") ;
        Alumno al6 = new Alumno("Miranda", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "MIRA030204HMNNZLA4", LocalDate.now(), "Miranda2", "uwu", Rol.ALUMNO, NombreCarrera.ELC, "lM24ELC1") ;
        Alumno al7 = new Alumno("Carolina", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "CARO030204HMNNZLA4", LocalDate.now(), "Caro2", "uwu", Rol.ALUMNO, NombreCarrera.ELC, "lC24ELC0") ;
        Alumno al8 = new Alumno("Mariela", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "MAEL030204HMNNZLA4", LocalDate.now(), "Mariela2", "uwu", Rol.ALUMNO, NombreCarrera.ELC, "lM24ELC2") ;
        Alumno al9 = new Alumno("Sarahi", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "SARA030204HMNNZLA4", LocalDate.now(), "Sara2", "uwu", Rol.ALUMNO, NombreCarrera.IMAT, "lS24IMAT0") ;
        Alumno al10 = new Alumno("Paola", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "PAOL030204HMNNZLA4", LocalDate.now(), "Pao2", "uwu", Rol.ALUMNO, NombreCarrera.IMAT, "lP24IMAT0") ;
        Alumno al11 = new Alumno("Miguel", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "MIGU030204HMNNZLA4", LocalDate.now(), "Miguel2", "uwu", Rol.ALUMNO, NombreCarrera.IMAT, "lM24IMAT0") ;
        Alumno al12 = new Alumno("Jose", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "JOSE030204HMNNZLA4", LocalDate.now(), "Jose2", "uwu", Rol.ALUMNO, NombreCarrera.IMAT, "lJ24IMAT0") ;
        Alumno al13 = new Alumno("Alberto", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "ALBE030204HMNNZLA4", LocalDate.now(), "Alberto2", "uwu", Rol.ALUMNO, NombreCarrera.IMAT, "lA24IMAT0") ;
        Alumno al14 = new Alumno("Jorge", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "JORG030204HMNNZLA4", LocalDate.now(), "Jorge2", "uwu", Rol.ALUMNO, NombreCarrera.ELC, "lJ24ELC0") ;
        Alumno al15 = new Alumno("Javier", "Pérez", "Martínez", 2000, LocalDate.now(), "Morelia", "Michoacan", "Calle de Juanito", "JAVI030204HMNNZLA4", LocalDate.now(), "Javier2", "uwu", Rol.ALUMNO, NombreCarrera.ISC, "lJ24ISC1") ;

        usuarios.get(Rol.ALUMNO).add(al1);
        usuarios.get(Rol.ALUMNO).add(al2);
        usuarios.get(Rol.ALUMNO).add(al3);
        usuarios.get(Rol.ALUMNO).add(al4);
        usuarios.get(Rol.ALUMNO).add(al5);
        usuarios.get(Rol.ALUMNO).add(al6);
        usuarios.get(Rol.ALUMNO).add(al7);
        usuarios.get(Rol.ALUMNO).add(al8);
        usuarios.get(Rol.ALUMNO).add(al9);
        usuarios.get(Rol.ALUMNO).add(al10);
        usuarios.get(Rol.ALUMNO).add(al11);
        usuarios.get(Rol.ALUMNO).add(al12);
        usuarios.get(Rol.ALUMNO).add(al13);
        usuarios.get(Rol.ALUMNO).add(al14);
        usuarios.get(Rol.ALUMNO).add(al15);

        Semestre.inicializarSemestres();

        Grupo g1 = new Grupo(NombreCarrera.ISC, 1, TipoGrupo.A);
        g1.getAlumnos().add(al1);
        g1.getAlumnos().add(al2);
        g1.getAlumnos().add(al3);
        semestres.get(0).getGrupos().add(g1);
        Grupo.inicializarMaterias(g1);
        Grupo.addMateriasSemestre(g1);
        grupos.add(g1);

//        Grupo g2 = new Grupo(NombreCarrera.ELC, 3, TipoGrupo.A);
//        grupos.add(g2);
//        semestres.get(2).getGrupos().add(g2);
//        Grupo.inicializarMaterias(g2);
//        Grupo.addMateriasSemestre(g2);

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
    public static void setGrupos(ArrayList <Grupo> grupos) {
        Sistema.grupos = grupos;
    }
    //Setter para asignar los datos obtenidos del json al atributo ArrayList semestres.
    public static void setSemestres(ArrayList<Semestre> semestres) {
        Sistema.semestres = semestres;
    }
}
