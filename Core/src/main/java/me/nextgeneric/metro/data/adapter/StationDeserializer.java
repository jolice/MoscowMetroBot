package me.nextgeneric.metro.data.adapter;

import com.google.gson.*;
import me.nextgeneric.metro.model.Station;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class StationDeserializer implements JsonDeserializer<Station> {


    @Override
    public Station deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        int lineId = jsonObject.get("lineId").getAsInt() - 1;
        boolean transferStation = jsonObject.has("isTransferStation") && jsonObject.get("isTransferStation").getAsBoolean();
        Set<Integer> linkIds = intIteratorToSet(jsonObject.get("linkIds"), element -> element.getAsInt() - 1);
        Set<Integer> transfers = intIteratorToSet(jsonObject.get("boardInfo").getAsJsonObject().get("transfer"),
                obj -> obj.getAsJsonObject().get("toSt").getAsInt() - 1);
        return new Station(name, lineId, linkIds, transfers, transferStation);
    }

    private Set<Integer> intIteratorToSet(JsonElement jsonElement, Function<JsonElement, Integer> function) {
        Set<Integer> result = new HashSet<>();
        if (jsonElement != null) {
            for (JsonElement element : jsonElement.getAsJsonArray()) {
                result.add(function.apply(element));
            }
        }
        return result;
    }


}
