package me.nextgeneric.metro.data.adapter;

import com.google.gson.*;
import me.nextgeneric.metro.graph.api.AssignableIndexed;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IndexedDeserializer<T extends AssignableIndexed> implements JsonDeserializer<Map<Integer, T>> {

    private Class<T> type;

    public IndexedDeserializer(Class<T> type) {
        this.type = type;
    }

    @Override
    public Map<Integer, T> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Set<String> keys = jsonObject.keySet();
        Map<Integer, T> map = new HashMap<>();
        for (String key : keys) {
            JsonObject element = jsonObject.get(key).getAsJsonObject();
            T entity = jsonDeserializationContext.deserialize(element, this.type);
            Integer index = Integer.parseInt(key) - 1;
            entity.setIndex(index);
            map.put(index, entity);
        }
        return map;
    }
}
