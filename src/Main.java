import menu.Menu;
import mindbox.Sistema;
import mindbox.utils.Generador;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        //Se crea un objeto File que buscará cada archivo json.
        File usuarios = new File("usuarios.json");
        File grupos = new File("grupos.json");//Para el archivo grupos,json
        File graduados = new File("graduados.json");//Para el archivo graduados.json
        /*Nota importante: Es necesario que los 3 archivos EXISTAN para que la condicional se active
        * y cargue los datos. De lo contrario, mejor se procede a inicializar los objetos como es costumbre, para evitar errores o caos en los datos del programa.*/
        if (usuarios.exists() && grupos.exists() && graduados.exists()) {//Si los archivos existen, se llama al deserializador para leer los datos.
            Generador.deserializarUsuariosJson();//Leerá los datos del archivo json y los asignará a Sistema.
            Generador.deserializarGruposJson();//Leerá los datos de grupos del archivo json y los asignará a Sistema.
            Generador.deserializarGraduadosJson();//Leerá los datos de graduados del archivo json y los asignará a Sistema.
            Menu.iniciarSesion();//Una vez obtenidos los datos, se inicia el menú.
        } else if (!usuarios.exists()) {//Si el archivo NO existe, se inicializan los objetos como es habitual y se inicia el menú.
            Sistema.inicializar();
            Menu.iniciarSesion();
        }
    }
}