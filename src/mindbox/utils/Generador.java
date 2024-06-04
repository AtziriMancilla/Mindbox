package mindbox.utils;

import Graduados.Graduado;
import Secciones.Grupo;
import Secciones.Semestre;
import Secciones.utils.NombreCarrera;
import Usuarios.Usuario;
import Usuarios.utils.Rol;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import mindbox.Sistema;
import mindbox.utils.Adapters.LocalDateAdapter;
import mindbox.utils.Adapters.RolAdapter;
import mindbox.utils.Adapters.UsuarioAdapter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Generador {
    private static int ID_ALUMNO = 0;
    private static int ID_PROFESOR = 0;
    private static int ID_COORDINADOR = 0;
    //curp
    //rfc
    //numdecontrol(tipo,carrera)

    public static String generarRFC(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("YYMMdd"); //Formato para la fecha de nacimiento
        String fechaNacimientoS = formatoFecha.format(fechaNacimiento);
        Random r = new Random(); //Variable para los números aleatorios.
        int numero1 = r.nextInt(65,91); //Rango del 65 al 90 para las letras mayúsculas según código ASCII
        char caracter1 = (char) numero1; //Convertir a caracter
        String letra1 = Character.toString(caracter1); //Convertir a cadena
        int numero2 = r.nextInt(65,91);
        char caracter2 = (char) numero2;
        String letra2 = Character.toString(caracter2);
        int caracter3 = r.nextInt(10);
        String letra3 = Integer.toString(caracter3); //Conversión de entero a String.
        String cadena = "" + apellidoPaterno.charAt(0) + apellidoPaterno.charAt(1) + apellidoMaterno.charAt(0) + nombre.charAt(0) + fechaNacimientoS + letra1 + letra2 + letra3;//Concatenando los valores de todas las variables para crear una sola cadena String.
        String rfc = cadena.toUpperCase();//Conversión de minúsculas a Mayúsculas.
        return rfc;
    }
    public static String generarCURP(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, char sexo, String estado) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("YYMMdd");
        Random r = new Random();
        String fechaNacimientoS = formatoFecha.format(fechaNacimiento);
        int letra = estado.length() - 1; //Obtiene el largo de la palabra y se le resta 1 para obtener el último index correspondiente a la última letra
        int numero1 = r.nextInt(10);//Número aleatorio para el dígito 1
        int numero2 = r.nextInt(10);//Número aleatorio para el dígito 2
        String digito1 = Integer.toString(numero1);//Conversión a String. Luego se añaden los dos dígitos para completar la CURP
        String digito2 = Integer.toString(numero2);
        String cadena = "" + apellidoPaterno.charAt(0) + apellidoPaterno.charAt(1) + apellidoMaterno.charAt(0) + nombre.charAt(0) + fechaNacimientoS + sexo + estado.charAt(0) + estado.charAt(letra) + apellidoPaterno.charAt(2) + apellidoMaterno.charAt(2) + nombre.charAt(2) + digito1 + digito2;
        String curp = cadena.toUpperCase();
        return curp;
    }

    public static String generarNumControl(Rol rol, NombreCarrera carrera, String nombre, LocalDate fechaRegistro){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yy");
        String digitosAnio = pattern.format(fechaRegistro);
        char letra = nombre.charAt(0);
        String letra1 = Character.toString(letra).toUpperCase();
        String numControl = "";
        String nombreCarrera = carrera.toString();
        switch (rol) {
            case ALUMNO -> {
                ID_ALUMNO += 1;
                String indiceAlumno = String.format(String.valueOf(ID_ALUMNO));
                String idAlumno = "I";
                numControl += idAlumno + letra1 + digitosAnio + nombreCarrera + indiceAlumno;
            }
            case PROFESOR -> {
                ID_PROFESOR += 1;
                String indiceProfesor = String.format(String.valueOf(ID_PROFESOR));
                String idProfesor = "M";
                numControl += idProfesor + letra1 + digitosAnio + nombreCarrera + indiceProfesor;
            }
            case COORDINADOR -> {
                ID_COORDINADOR += 1;
                String indiceCoord = String.format(String.valueOf(ID_COORDINADOR));
                String idCoord = "C";
                numControl += idCoord + letra1 + digitosAnio + nombreCarrera + indiceCoord;
            }
        }
        return numControl;
    }
    //Método para serializar datos de usuario en un archivo json.
    public static void guardarUsuariosJson(HashMap<Rol, ArrayList<Usuario>> usuarios){
        Gson json = new GsonBuilder().setPrettyPrinting()//Recordar que setPrettyPrinting es para que el json no quede escrito en una sola línea y esté estéticamente mejor organizado con sus datos.
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())//Se inserta un nuevo TypeAdapter que viene desde la clase LocalDateAdapter, para que sepa manejar los objetos de clase LocalDate al serializar.
                .create();//Creará el archivo json.
        System.out.println("Guardando usuarios en archivo json...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.json"))) {//Con el BufferedWriter, se crea un nuevo archivo con extensión .json, con el nombre de usuarios
            json.toJson(usuarios, writer);//el objeto json escribirá los datos de usuarios en el nuevo archivo, usando el objeto writer.
            System.out.println("Usuarios guardados con éxito.\n");
        } catch (IOException error) {//El try catch es obligatorio para el FileWriter. Arrojará el mensaje de error en caso de que algo salga mal en la serialización.
            System.out.println("No se pudieron guardar los datos: "+ error.getMessage());
        }
    }
    //Método para deserializar (sacar) datos del archivo json.
    public static void deserializarUsuariosJson(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Rol.class, new RolAdapter())
                .registerTypeAdapter(Usuario.class, new UsuarioAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        System.out.println("Accediendo a datos de usuarios...");
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.json"))) {
            //Se crea un objeto Type que tendrá el esquema/modelo de cómo se estructuran los datos deserializados del json, es decir, convertirlos a su objeto original, HashMap
            Type usuarioMapType = new TypeToken<HashMap<Rol, ArrayList<Usuario>>>() {}.getType();
            //Se crea el HashMap cuyos datos serán los obtenidos del archivo json.
            HashMap<Rol, ArrayList<Usuario>> usuarios = gson.fromJson(reader, usuarioMapType);
            //Se asigna el nuevo valor al atributo usuarios (el HashMap) de Sistema, es decir, se le guardan los datos obtenidos del json.
            Sistema.setUsuarios(usuarios);
            System.out.println("Datos de usuarios recuperados con éxito.\n");
        } catch (IOException error) {//El try catch es obligatorio para manejar los objetos de BufferedReader.
            System.out.println("No se pudieron recuperar los datos: "+ error.getMessage());//Si sale algún error durante la deserialización, se imprime el mensaje.
        }
    } //Nota: Las funciones de los siguientes métodos son las mismas que expliqué anteriormente.
    //Método para serializar datos de los grupos en un archivo json.
    public static void guardarGruposJson(HashMap<Integer, Grupo> grupos) {
        Gson json = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        System.out.println("Guardando grupos en archivo json...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("grupos.json"))) {
            json.toJson(grupos, writer);
            System.out.println("Grupos guardados con éxito.\n");
        } catch (IOException error) {
            System.out.println("No se pudieron guardar los datos: "+ error.getMessage());
        }
    }
    //Método para deserializar (sacar) datos de los grupos del archivo json.
    public static void deserializarGruposJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        System.out.println("Accediendo a datos de grupos...");
        try (BufferedReader reader = new BufferedReader(new FileReader("grupos.json"))) {
            Type grupoMapType = new TypeToken<HashMap<Integer, Grupo>>() {}.getType();
            HashMap<Integer, Grupo> grupos = gson.fromJson(reader, grupoMapType);
            Sistema.setGrupos(grupos);
            System.out.println("Datos de grupos recuperados con éxito.\n");
        } catch (IOException error) {
            System.out.println("No se pudieron recuperar los datos: "+ error.getMessage());
        }
    }
    //Método para serializar datos de los graduados en un archivo json.
    public static void guardarGraduadosJson(ArrayList<Graduado> graduados) {
        Gson json = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        System.out.println("Guardando graduados en archivo json...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("graduados.json"))) {
            json.toJson(graduados, writer);
            System.out.println("Graduados guardados con éxito.\n");
        } catch (IOException error) {
            System.out.println("No se pudieron guardar los datos: "+ error.getMessage());
        }
    }
    //Método para deserializar (sacar) datos de los graduados del archivo json.
    public static void deserializarGraduadosJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        System.out.println("Accediendo a datos de graduados...");
        try (BufferedReader reader = new BufferedReader(new FileReader("graduados.json"))) {
            Type graduadoListType = new TypeToken<ArrayList<Graduado>>() {}.getType();
            ArrayList<Graduado> graduados = gson.fromJson(reader, graduadoListType);
            Sistema.setGraduados(graduados);
            System.out.println("Datos de graduados recuperados con éxito.\n");
        } catch (IOException error) {
            System.out.println("No se pudieron recuperar los datos: "+ error.getMessage());
        }
    }
    //Método para serializar datos de los semestres en un archivos json.
    public static void guardarSemestresJson(ArrayList<Semestre> semestres) {
        Gson json = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        System.out.println("Guardando semestres en archivo json...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("semestres.json"))) {
            json.toJson(semestres, writer);
            System.out.println("Semestres guardados con éxito.\n");
        } catch (IOException error) {
            System.out.println("No se pudieron guardar los datos: "+ error.getMessage());
        }
    }
    //Método para deserializar (sacar) datos de los graduados del archivo json.
    public static void deserializarSemestresJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        System.out.println("Accediendo a datos de semestres...");
        try (BufferedReader reader = new BufferedReader(new FileReader("semestres.json"))) {
            Type semestreListType = new TypeToken<ArrayList<Semestre>>() {}.getType();
            ArrayList<Semestre> semestres = gson.fromJson(reader, semestreListType);
            Sistema.setSemestres(semestres);
            System.out.println("Datos de semestres recuperados con éxito.\n");
        } catch (IOException error) {
            System.out.println("No se pudieron recuperar los datos: "+ error.getMessage());
        }
    }
}
