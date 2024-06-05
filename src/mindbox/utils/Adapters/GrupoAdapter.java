package mindbox.utils.Adapters;

import Secciones.Grupo;
import Secciones.Materia;
import Secciones.TipoGrupo;
import Secciones.utils.NombreCarrera;
import Usuarios.Alumno;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class GrupoAdapter implements JsonSerializer<Grupo>, JsonDeserializer<Grupo> {
    @Override
    public JsonElement serialize(Grupo src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", src.getId());
        jsonObject.addProperty("semestre", src.getSemestre());
        jsonObject.add("carrera", context.serialize(src.getCarrera()));
        jsonObject.add("tipoGrupo", context.serialize(src.getTipoGrupo()));
        jsonObject.add("alumnos", context.serialize(src.getAlumnos()));
        jsonObject.add("materia", context.serialize(src.getMateria()));
        jsonObject.addProperty("NUM_GRUPO", Grupo.NUM_GRUPO); // Serializar NUM_GRUPO
        return jsonObject;
    }

    @Override
    public Grupo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        NombreCarrera carrera = context.deserialize(jsonObject.get("carrera"), NombreCarrera.class);
        int semestre = jsonObject.get("semestre").getAsInt();
        TipoGrupo tipoGrupo = context.deserialize(jsonObject.get("tipoGrupo"), TipoGrupo.class);
        Grupo grupo = new Grupo(carrera, semestre, tipoGrupo);
        grupo.setId(jsonObject.get("id").getAsInt());
        grupo.setAlumnos(context.deserialize(jsonObject.get("alumnos"), new TypeToken<ArrayList<Alumno>>(){}.getType()));
        grupo.setMateria(context.deserialize(jsonObject.get("materia"), new TypeToken<HashMap<Integer, ArrayList<Materia>>>(){}.getType()));
        Grupo.NUM_GRUPO = jsonObject.get("NUM_GRUPO").getAsInt(); // Deserializar NUM_GRUPO
        return grupo;
    }
}