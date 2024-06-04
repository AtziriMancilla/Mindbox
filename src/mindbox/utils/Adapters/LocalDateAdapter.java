package mindbox.utils.Adapters;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//Esta clase tiene el fin de guardar los métodos que necesita Gson para trabajar objetos LocalDate.
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //Estos métodos le enseñan a Gson cómo manejar la serialización y deserialización para objetos de tipo LocalDate.
    //Son necesarios debido a que Gson no sabe cómo trabajar con objetos LocalDate, y para evitar errores o que el programa se caiga, se le instruye cómo.
    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return LocalDate.parse(json.getAsString(), formatter);
    }
}
