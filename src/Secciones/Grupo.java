package Secciones;

import Graduados.Graduado;
import Secciones.utils.NombreCarrera;
import Secciones.utils.NombreMaterias;
import Usuarios.Alumno;
import Usuarios.Coordinador;
import Usuarios.Profesor;

import Usuarios.Usuario;
import Usuarios.utils.Calificacion;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import Usuarios.utils.Historial;
import mindbox.Sistema;
import mindbox.UsuarioEnSesion;
import mindbox.utils.Generador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Grupo {
    private NombreCarrera carrera;
    private ArrayList<Alumno> alumnos = new ArrayList<>();
//    private int cantidadAlumnos;
    private HashMap<Integer, ArrayList<Materia>> materia= new HashMap<>();
    private int id;
    private int semestre;
    private TipoGrupo tipoGrupo;

    private static int NUM_GRUPO = 1;

    public Grupo(NombreCarrera carrera, int semestre, TipoGrupo tipoGrupo) {
        this.id = NUM_GRUPO;
        this.carrera = carrera;
//        this.cantidadAlumnos = 0;
        this.semestre = semestre;
        this.tipoGrupo = tipoGrupo;
        NUM_GRUPO++;
    }

    public NombreCarrera getCarrera() {
        return carrera;
    }

    public void setCarrera(NombreCarrera carrera) {
        this.carrera = carrera;
    }

//    public int getCantidadAlumnos() {
////        return cantidadAlumnos;
//    }
//
//    public void setCantidadAlumnos(int cantidadAlumnos) {
////        this.cantidadAlumnos = cantidadAlumnos;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public TipoGrupo getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(TipoGrupo tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public HashMap<Integer, ArrayList<Materia>> getMateria() {
        return materia;
    }

    public void setMateria(HashMap<Integer, ArrayList<Materia>> materia) {
        this.materia = materia;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString(){
        return String.format("ID: %s; Carrera: %s; Semestre: %s; Grupo: %s", id, carrera, semestre, tipoGrupo);
    }

    ///////////////////////////
    public static void avanzarGrupo(NombreCarrera carrera, Grupo grupo){
        if(grupo.getAlumnos().isEmpty()){
            System.out.println("No se puede avanzar el grupo porque no hay alumnos");
        }
        else {
            boolean band = true;
            //verifica si cada alumno tiene todas sus calificaciones del semestre
            for (Alumno alumno : grupo.alumnos) {
                if (!alumno.tieneTodasLasCalificaciones()) {
                    band = false;//si hay alguno que no tiene todas sus calificaciones lanza un booleano false
                }
            }
            //si sí empieza a cambiar a los alumnos de semestre y al grupo
            //solo a los que aprobaron y su semestre es menor a 3
            if (band && grupo.getSemestre() != 3) {
                for (Alumno alumno : grupo.alumnos) {
                    Historial.generarHistorial(alumno);
                    if (alumno.aproboSemestre()) {
                        cambiarSemestreGrupo(grupo);
                        alumno.setSemestre(grupo.semestre + 1);
                        //##cambiar materias de alumno al siguiente semestre## Aqui ta
                        addMateriasSemestre(grupo);
                    }
                    //a los que reprobaron los cambia de grupo y deja su semestre en el mismo año
                    else {
                        reprobarAlumno(grupo, alumno);
                    }
                }
                //avanza el grupo de semestres
                grupo.setSemestre(grupo.semestre + 1);
                //###falta cambiar las materias del grupo###
//                grupo.setCantidadAlumnos(grupo.getAlumnos().size());
            }
            //si todos tienen sus calificaciones y el semestre es igual a 3 se gradua un grupo
            if (band && grupo.getSemestre() == 3) {
                for (Alumno alumno : grupo.alumnos) {
                    Historial.generarHistorial(alumno);
                    //si el alumno aprobo lo gradua
                    if (alumno.aproboSemestre()) {
                        Graduado.registrarGraduado(alumno);//este metodo crea un graduado y lo agrega a la lista de graduados del sistema
                    }
                    //reprueba al alumno
                    else {
                        reprobarAlumno(grupo, alumno);
                    }
                }
                //##elimina al grupo de la lista de grupos porque ya se graduaron## no se si se pueda eliminar asi comom asi
                Sistema.grupos.remove(grupo);
            }
            if (!band) {
                System.out.println("No se puede avanzar este grupo");
            }
        }
    }

    //metodo que reprueba a un alumno(lo deja en su semestre y lo agrega a un grupo donde haya espacio)
    public static void reprobarAlumno(Grupo grupo, Alumno alumno) {
        boolean band=true;
        for (int i = 0; i < Sistema.grupos.size(); i++) {//ciclo que recorre los grupos buscando uno del mismo semestre
            Grupo grupoNuevo = Sistema.grupos.get(i);
            if (grupoNuevo.getSemestre() == (grupo.semestre) && grupoNuevo.getAlumnos().size() < 20 && band) {
                for(Calificacion calificacion: alumno.getCalificaciones()){//ciclo que busca las calificaciones del semestre
                    String nombreMateria=calificacion.getMateria().getNombre();//obtiene el nombre de la materia
                    char ultimoDigito= nombreMateria.charAt(nombreMateria.length()-1);//obtiene el ultimo digito(que es el numero de materia)
                    String numero=String.valueOf(ultimoDigito);//lo convierte a string
                    int s=Integer.parseInt(numero);//lo convierte a numero
                    if(s == grupo.getSemestre()){//lo compara con el semestre
                        alumno.getCalificaciones().remove(calificacion);//borra la calificacion del alumno
                    }
                }
                grupoNuevo.getAlumnos().add(alumno);//agrega al alumno al nuevo grupo
                grupo.getAlumnos().remove(alumno);//lo borra del grupo anterior
                band=false;
            }
        }
    }

    // CRUD grupo ----------------------------------------------------------------------------------------------------
    public static void crearGrupo(NombreCarrera carrera){
        // Pidiendo datos
        Grupo grupo;
        System.out.println("\nCrear grupo");
        if (Sistema.semestres.get(0).getGrupos().isEmpty()){
            grupo = new Grupo(carrera, 1, TipoGrupo.A);
            Sistema.grupos.add(grupo);
            Sistema.semestres.get(0).getGrupos().add(grupo);
            inicializarMaterias(grupo);
            addMateriasSemestre(grupo);
            System.out.println("Seleccione 3 alumnos para poder crear el grupo");
            for(int i=0;i<3;i++){
                //##aqui falta una comprobacion para no agregar 2 veces al mismo alumno y tambien para ver que no este en otros grupos
                Alumno alumno = Grupo.obtenerAlumnoGeneral(carrera);
                if (alumno.getGrupo() == null){
                    Grupo.addAlumno(alumno, grupo);
                } else {
                    System.out.println("Operacion cancelada, el alumno ya tiene un grupo");
                }
            }
            System.out.println("Grupo A agregado");

        } else if (Sistema.semestres.get(0).getGrupos().size() == 1 && Sistema.semestres.get(0).getGrupos().get(0).getAlumnos().size() >= 3){
            grupo = new Grupo(carrera, 1, TipoGrupo.B);
            Sistema.grupos.add(grupo);
            Sistema.semestres.get(0).getGrupos().add(grupo);
            inicializarMaterias(grupo);
            addMateriasSemestre(grupo);
            System.out.println("Seleccione 3 alumnos para poder crear el grupo");
            for(int i=0;i<3;i++){
                //##aqui falta una comprobacion para no agregar 2 veces al mismo alumno
                Alumno alumno = Grupo.obtenerAlumnoGeneral(carrera);
                Grupo.addAlumno(alumno, grupo);
            }
            System.out.println("Grupo B agregado");
        } else {
            System.out.println("Limite de grupos alcanzado");
        }

    }

    public static void modificarGrupo(){
        int act;
        if (hayGrupos()){
            Grupo grupo = obtenerGrupo();
            System.out.println("\nModificar grupo");
            System.out.println("1 - Modificar alumnos en grupo");
            System.out.println("2 - Modificar profesor en materia");
            System.out.println("0 - Cancelar");
            System.out.print("Accion: ");
            do {
                act = DatosComun.pedirNumero();
                if (act < 0 || act > 2){
                    System.out.println("Opcion inexistente");
                }
            } while (act < 0 || act > 2);
            if (act == 1){
                // llamar a metodo de pedir alumno
                // llamar metodo de modificar alumnos en grupo
            } else if (act == 2) {
                Profesor profesor = null;
                // Falta metodo para pedir profesor
                addProfeMateria(grupo, profesor);
            }
        } else {
            System.out.println("No hay grupos que eliminar");
        }

    }

    public static void eliminarGrupo(){
        System.out.println("\nEliminar grupo");
        if (hayGrupos()){
            Grupo grupo = obtenerGrupo();
            if (grupo.getAlumnos().isEmpty()){
                // llamar metodo que verifique que las materias tengan profe null
                Sistema.semestres.get(grupo.getSemestre()-1).getGrupos().remove(grupo);
                System.out.println("Grupo eliminado");
            } else {
                System.out.println("Grupo no eliminado, cantidad de alumnos mayor que 0");
            }
        } else {
            System.out.println("No hay grupos que eliminar");
        }

    }

    public static void mostrarGrupos(){
        System.out.println("\nMostrar grupos de todos los semestres");
        for (int i = 0; i < 3; i++) {
            for (Grupo grupo : Sistema.semestres.get(i).getGrupos()) {
                if (grupo.getCarrera() == ((Coordinador)UsuarioEnSesion.getInstancia().getUsuarioActual()).getCarrera()){
                    System.out.println(grupo.toString());
                }
            }
        }

    }

    // Metodos utiles -------------------------------------------------------------------------------------------------
    public static void cambiarSemestreGrupo(Grupo grupo){
        if (grupo.getSemestre()<3){
            Sistema.semestres.get(grupo.getSemestre()-1).getGrupos().remove(grupo);
            Sistema.semestres.get(grupo.getSemestre()).getGrupos().add(grupo);
        } else if (grupo.getSemestre() == 3) {
            Sistema.semestres.get(grupo.getSemestre()-1).getGrupos().remove(grupo);
            // Añadir grupo a graduados, o dejar así, esto es solo para quitar de 3er semestre
        }

    }

    public static Boolean hayGrupos(){
        boolean siHay = false;
        for (int i = 0; i < 3; i++) {
            if (!Sistema.semestres.get(i).getGrupos().isEmpty()){
                siHay = true;
            }
        }
        return siHay;
    }

    public static Grupo obtenerGrupo(){
        Grupo grupo=null;
        int id;
        mostrarGrupos();
        System.out.println("Seleccionar grupo por ID");
        do {
            id = DatosComun.pedirNumero();
            for (int i = 0; i < 3; i++) {
                for (Grupo gru : Sistema.semestres.get(i).getGrupos()) {
                    if (gru.getId() == id && gru.getCarrera() == ((Coordinador)UsuarioEnSesion.getInstancia().getUsuarioActual()).getCarrera()){
                        grupo = gru;
                    }
                }
            }
            if (grupo == null){
                System.out.println("Grupo no encontrado, intente de nuevo");
            }
        } while (grupo == null);
        return grupo;
    }



    // Metodos de materias pero relacionados con grupo ----------------------------------------------------------------

    // Este metodo será llamado cuando se quiera modificar el profesor de una materia al grupo
    public static void addProfeMateria(Grupo grupo, Profesor profesor){
        System.out.println("Asignar profesor");

        NombreCarrera carrera = grupo.getCarrera();
        NombreMaterias mateNomb = Materia.seleccionarMateria(carrera);
        String nombre = (mateNomb.toString().toLowerCase() + " " + grupo.getSemestre());

        // Obteniendo materia
        for (Materia mat : grupo.getMateria().get(grupo.getSemestre())) {
            if (mat.getNombre().equals(nombre)){
                asignarProfe(mat, profesor);
            }
        }
    }
    private static void asignarProfe(Materia materia, Profesor profesor){
        if (materia.getProfesor() == null){
            // Añadiendo profesor a materia - Caso: prof no estaba asignado
            materia.setProfesor(profesor);
        } else {
            System.out.println("El profesor asignado anteriormente será reemplazado, ¿Desea continuar?");
            System.out.println("1 - Sí\n2 - No");
            int act;
            do {
                act = DatosComun.pedirNumero();
            } while (act < 1 || act > 2);
            if (act == 1){
                // Añadiendo profesor a materia - Caso: prof ya estaba asignado
                materia.setProfesor(profesor);
            } else {
                System.out.println("Operación cancelada");
            }
        }
    }

    public static void modificarMateria(Grupo grupo){
        Materia materia = obtenerMateria(grupo);
        System.out.println("Modificar Materia (Profesor en materia)\n");
        // El unico atributo modificable es profesor
        Profesor.mostrarProfesores();
        Profesor profesor = (Profesor) Sistema.usuarios.get(Rol.PROFESOR).get(Profesor.pedirProfesor());
        asignarProfe(materia, profesor);
    }

    public static void mostrarMaterias(Grupo grupo){
        int semestre = grupo.getSemestre();
        System.out.println("Mostrar Materias del semestre " + semestre);
        for (Materia mat : grupo.materia.get(semestre)) {
            System.out.println(mat.toString());
        }
    }

    public static void inicializarMaterias(Grupo grupo){
        grupo.getMateria().put(1, new ArrayList<>());
        grupo.getMateria().put(2, new ArrayList<>());
        grupo.getMateria().put(3, new ArrayList<>());
    }
    public static void addMateriasSemestre(Grupo grupo){
        // Recopilando datos que ya estan guardados en grupo
        int semestre = grupo.getSemestre();
        NombreCarrera carrera = grupo.getCarrera();
        Materia m1, m2, m3;

        switch (carrera){
            case ISC:
                m1 = new Materia(NombreMaterias.PROGRAMACION, carrera, grupo, null);
                m2 = new Materia(NombreMaterias.PROBABILIDAD, carrera, grupo, null);
                m3 = new Materia(NombreMaterias.CALCULO, carrera, grupo, null);
                grupo.getMateria().get(grupo.getSemestre()).add(m1);
                grupo.getMateria().get(grupo.getSemestre()).add(m2);
                grupo.getMateria().get(grupo.getSemestre()).add(m3);
                Sistema.semestres.get(semestre-1).getMaterias().add(m1);
                Sistema.semestres.get(semestre-1).getMaterias().add(m2);
                Sistema.semestres.get(semestre-1).getMaterias().add(m3);
                break;
            case IMAT:
                m1 = new Materia(NombreMaterias.ESTADISTICA, carrera, grupo, null);
                m2 = new Materia(NombreMaterias.CONTABILIDAD, carrera, grupo, null);
                m3 = new Materia(NombreMaterias.CALCULO, carrera, grupo, null);
                grupo.getMateria().get(grupo.getSemestre()).add(m1);
                grupo.getMateria().get(grupo.getSemestre()).add(m2);
                grupo.getMateria().get(grupo.getSemestre()).add(m3);
                Sistema.semestres.get(semestre-1).getMaterias().add(m1);
                Sistema.semestres.get(semestre-1).getMaterias().add(m2);
                Sistema.semestres.get(semestre-1).getMaterias().add(m3);
                break;
            case ELC:
                m1 = new Materia(NombreMaterias.REDES, carrera, grupo, null);
                m2 = new Materia(NombreMaterias.CIRCUITOS, carrera, grupo, null);
                m3 = new Materia(NombreMaterias.CALCULO, carrera, grupo, null);
                grupo.getMateria().get(grupo.getSemestre()).add(m1);
                grupo.getMateria().get(grupo.getSemestre()).add(m2);
                grupo.getMateria().get(grupo.getSemestre()).add(m3);
                Sistema.semestres.get(semestre-1).getMaterias().add(m1);
                Sistema.semestres.get(semestre-1).getMaterias().add(m2);
                Sistema.semestres.get(semestre-1).getMaterias().add(m3);
                break;
            default:
        }
    }

    // Este metodo se usará para modificar materia
    public static Materia obtenerMateria(Grupo grupo){
        Materia materia=null;
        int id;
        mostrarMaterias(grupo);
        System.out.println("Seleccionar materia por ID");
        do {
            id = DatosComun.pedirNumero();
            for (Materia mat : grupo.getMateria().get(grupo.getSemestre())) {
                if (mat.getId() == id){
                    materia = mat;
                }
            }
            if (materia == null){
                System.out.println("Materia no encontrada, intente de nuevo");
            }
        } while (materia == null);

        return materia;
    }



    // este metodo se usa antes de addMaterias - ACTU: este metodo dejara de ser utilizado, se queda de momento por si acaso
    public static boolean materiaExistente(Grupo grupo, NombreMaterias materia){
        boolean yaEsta = false;
        String nombreProv = (materia.toString().toLowerCase() + " " + grupo.getSemestre());
        for (Materia mat : grupo.getMateria().get(grupo.getSemestre())) {
            if (mat.getNombre().equals(nombreProv)){
                yaEsta = true;
            }
        }
        return yaEsta;
    }


    // Metodos de alumnos para grupos --------------------------------------------------------------------------------

    public static Alumno obtenerAlumnoGeneral(NombreCarrera carrera){
        Alumno.mostrarAlumnos(carrera);
        Alumno alumno = (Alumno) Sistema.usuarios.get(Rol.ALUMNO).get(Alumno.pedirAlumno());
        return alumno;
    }

    public static void addAlumno(Alumno alumno, Grupo grupo){
        if (grupo.getAlumnos().size() >= 20){
            System.out.println("Limite de alumnos alcanzado");
        } else {
//            grupo.setCantidadAlumnos((grupo.getCantidadAlumnos()+1));
            grupo.getAlumnos().add(alumno);
            alumno.setGrupo(grupo);
            System.out.println("Alumno agregado");
        }
    }

    public static void modificarAlumno(Alumno alumno){
        int opt;
        do {
            System.out.println("¿Qué información deseas editar?");
            System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento\n 7)Contraseña\n 0)Salir/Regresar");
             opt = DatosComun.pedirNumero();

            switch (opt) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre: ");
                    alumno.setNombre(DatosComun.pedirDatoString());

                    String curpAntigua = alumno.getCurp();
                    char sexo = curpAntigua.charAt(10);
                    String nuevacurp = Generador.generarCURP(alumno.getNombre(), alumno.getApellidoPaterno(), alumno.getApellidoMaterno(), alumno.getFechaNacimiento(), sexo, alumno.getEstado());

                    alumno.setCurp(nuevacurp);
                    System.out.println("Nombre modificado");
                    break;

                case 2:
                    System.out.println("Ingrese el nuevo apellido Paterno: ");
                    alumno.setApellidoPaterno(DatosComun.pedirDatoString());
                    System.out.println("Ingrese el nuevo apellido Materno: ");
                    alumno.setApellidoMaterno(DatosComun.pedirDatoString());
                    String curpAntigua1 = alumno.getCurp();
                    char sexo1 = curpAntigua1.charAt(10);
                    String nuevaCurp = Generador.generarCURP(alumno.getNombre(), alumno.getApellidoPaterno(), alumno.getApellidoMaterno(), alumno.getFechaNacimiento(), sexo1, alumno.getEstado());

                    alumno.setCurp(nuevaCurp);

                    System.out.println("Apellido modificado");
                    break;

                case 3:
                    System.out.println("Ingrese nueva ciudad: ");
                    alumno.setCiudad(DatosComun.pedirDatoString());

                    System.out.println("Ciudad actualizada");
                    break;

                case 4:
                    System.out.println("Ingrese nuevo estado: ");
                    alumno.setEstado(DatosComun.pedirDatoString());

                    System.out.println("Estado actualizado");
                    break;

                case 5:
                    System.out.println("Ingrese nueva direccion: ");
                    alumno.setDireccion(DatosComun.pedirDireccion());

                    System.out.println("Dirección actualizada");
                    break;

                case 6:
                    System.out.println("Fecha de nacimiento");
                    LocalDate nuevaFechaNacimiento = DatosComun.obtenerFechaNacimiento();
                    alumno.setFechaNacimiento(nuevaFechaNacimiento);
                    int anioNacimiento = nuevaFechaNacimiento.getYear();
                    alumno.setAnioNacimiento(anioNacimiento);
                    String curpAntigua2 = alumno.getCurp();
                    char sexo2 = curpAntigua2.charAt(10);
                    String curpNueva2 = Generador.generarCURP(alumno.getNombre(), alumno.getApellidoPaterno(), alumno.getApellidoMaterno(), alumno.getFechaNacimiento(), sexo2, alumno.getEstado());

                    alumno.setCurp(curpNueva2);
                    System.out.println("Fecha Nacimiento Actualizada");
                    break;

                case 7:
                    System.out.println("Ingrese nueva contraseña");
                    String nuevaContrasena = DatosComun.pedirDatoString();
                    alumno.setContrasena(nuevaContrasena);
                    System.out.println("Contrasena Actualizada");
                    break;
                case 0:
                    System.out.println("Usted ha salido de modificar alumno. ");

                    break;
                default:
                    System.out.println("Opción no válida.\n");
                    break;
            }
        } while (opt != 0);
    }

    public static void eliminarAlumno(Alumno alumno, Grupo grupo){
        System.out.println("Eliminar Alumno de Grupo");
        System.out.println("1 - Eliminar alumno de grupo\n0 - Cancelar");
        int act;
        do {
            act = DatosComun.pedirNumero();
        } while (act < 0 || act > 1);
        if (act == 1){
//            grupo.setCantidadAlumnos((grupo.getCantidadAlumnos()-1));
            grupo.getAlumnos().remove(alumno);
            alumno.setGrupo(null);
            System.out.println("Alumno eliminado");
        } else {
            System.out.println("Operación cancelada");
        }
    }

    public static void mostrarAlumnos(Grupo grupo){
        System.out.println("Mostrar Alumnos");
        for (Alumno alumno : grupo.getAlumnos()) {
            System.out.println(alumno.toString());
        }
    }

    public static Alumno obtenerAlumnoGrupo(Grupo grupo){
        Alumno alumno = null;
        mostrarAlumnos(grupo);
        do {
            System.out.print("Ingrese numero de control: ");
            String num = DatosComun.pedirDatoString();
            for (Alumno al : grupo.getAlumnos()) {
                if (al.getNumControl().equals(num)){
                    alumno = al;
                }
            }
        } while (alumno==null);

        return alumno;
    }
}
