package com.github.jolice.metro.data.loader;

import com.github.jolice.metro.data.deserializer.GsonDeserializer;
import com.google.gson.stream.JsonReader;
import org.springframework.core.io.Resource;

import java.io.*;

public class JsonFileDeserializer<T> implements DataDeserializer<T> {

    private GsonDeserializer<T> deserializer;
    private Resource resource;

    public JsonFileDeserializer(GsonDeserializer<T> deserializer, Resource resource) {
        this.deserializer = deserializer;
        this.resource = resource;
    }

    public T deserialize() {
        try (InputStream inputStream = resource.getInputStream()) {
            try (JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream))) {
                return deserializer.deserialize(jsonReader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
