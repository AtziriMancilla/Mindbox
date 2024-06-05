package mindbox.utils.Adapters;

import Secciones.Materia;
import Secciones.utils.NombreCarrera;
import Secciones.utils.NombreMaterias;
import Usuarios.Profesor;
import com.google.gson.*;

import java.lang.reflect.Type;

public class MateriaAdapter implements JsonSerializer<Materia>, JsonDeserializer<Materia> {
    @Override
    public JsonElement serialize(Materia src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", src.getId());
        jsonObject.addProperty("nombre", src.getNombre());

        // Serialize the enum directly as a string to avoid unnecessary complexity
        jsonObject.addProperty("materia", src.getMateria().toString());
        jsonObject.addProperty("carrera", src.getCarrera().toString());

        // Avoid direct serialization of the grupo field to prevent circular reference issues
        jsonObject.addProperty("grupo", src.getGrupo());

        // Serialize the professor in a safe way
        jsonObject.add("profesor", context.serialize(src.getProfesor()));

        // Serialize the static field NUM_MATERIA
        jsonObject.addProperty("NUM_MATERIA", Materia.NUM_MATERIA);

        return jsonObject;
    }

    @Override
    public Materia deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        NombreMaterias materia = NombreMaterias.valueOf(jsonObject.get("materia").getAsString());
        NombreCarrera carrera = NombreCarrera.valueOf(jsonObject.get("carrera").getAsString());
        int grupo = jsonObject.get("grupo").getAsInt();
        Profesor profesor = context.deserialize(jsonObject.get("profesor"), Profesor.class);

        Materia mat = new Materia(materia, carrera, grupo, profesor);
        mat.setId(jsonObject.get("id").getAsInt());
        mat.setNombre(jsonObject.get("nombre").getAsString());

        // Deserialize the static field NUM_MATERIA
        Materia.NUM_MATERIA = jsonObject.get("NUM_MATERIA").getAsInt();

        return mat;
    }
}
