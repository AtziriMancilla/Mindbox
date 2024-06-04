package mindbox.utils.Adapters;

import Usuarios.utils.Rol;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class RolAdapter implements JsonDeserializer<Rol> {
    @Override
    public Rol deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String rolString = json.getAsString().toUpperCase();//Se obtiene el dato del json en formato String y se convierte a mayúsculas para garantizar su coincidencia con los valores enum de Rol.
        try {
            return Rol.valueOf(rolString);
        } catch (IllegalArgumentException e) {
            throw new JsonParseException(e.getMessage());
        }
    }
}
