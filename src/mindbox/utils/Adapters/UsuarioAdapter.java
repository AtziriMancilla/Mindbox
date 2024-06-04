package mindbox.utils.Adapters;

import Usuarios.Alumno;
import Usuarios.Coordinador;
import Usuarios.Profesor;
import Usuarios.Usuario;
import Usuarios.utils.Rol;
import com.google.gson.*;

import java.lang.reflect.Type;

public class UsuarioAdapter implements JsonSerializer<Usuario>, JsonDeserializer<Usuario> {
    @Override
    public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Rol rol = Rol.valueOf(jsonObject.get("rol").getAsString());

        return switch (rol) {
            case COORDINADOR -> context.deserialize(jsonObject, Coordinador.class);
            case PROFESOR -> context.deserialize(jsonObject, Profesor.class);
            case ALUMNO -> context.deserialize(jsonObject, Alumno.class);
        };
    }

    @Override
    public JsonElement serialize(Usuario usuario, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(usuario);
    }
}
