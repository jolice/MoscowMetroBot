package me.riguron.metro.data.deserializer;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class PlainDeserializer<T> implements GsonDeserializer<T> {

    private Class<T> type;
    private Gson gson;

    public PlainDeserializer(Class<T> type, Gson gson) {
        this.type = type;
        this.gson = gson;
    }

    @Override
    public T deserialize(JsonReader jsonReader) {
        return this.gson.fromJson(jsonReader, type);
    }
}
