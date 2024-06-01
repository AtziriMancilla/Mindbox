import menu.Menu;
import mindbox.Sistema;
import mindbox.utils.Generador;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        //Se crea un objeto File que buscará el archivo json.
        File usuarios = new File("usuarios.json");
        if (usuarios.exists()) {//Si el archivo existe, se llama al deserializador para leer los datos.
            Generador.deserializarUsuariosJson();//Leerá los datos del archivo json y los asignará a Sistema.
            Menu.iniciarSesion();//Una vez obtenidos los datos, se inicia el menú.
        } else if (!usuarios.exists()) {//Si el archivo NO existe, se inicializan los objetos como es habitual y se inicia el menú.
            Sistema.inicializar();
            Menu.iniciarSesion();
        }
    }
}