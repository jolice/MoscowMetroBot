package com.github.jolice.metro.configuration;

import com.github.jolice.metro.data.adapter.IndexedDeserializer;
import com.github.jolice.metro.data.adapter.LinkDeserializer;
import com.github.jolice.metro.data.adapter.StationDeserializer;
import com.github.jolice.metro.data.deserializer.GsonDeserializer;
import com.github.jolice.metro.model.Line;
import com.github.jolice.metro.model.MetroData;
import com.github.jolice.metro.model.Station;
import com.github.jolice.metro.model.link.Link;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.github.jolice.metro.data.deserializer.PlainDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class JsonConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder().
                registerTypeAdapter(new TypeToken<Map<Integer, Line>>() {
                }.getType(), new IndexedDeserializer<>(Line.class)).
                registerTypeAdapter(new TypeToken<Map<Integer, Link>>() {
                }.getType(), new IndexedDeserializer<>(Link.class)).
                registerTypeAdapter(new TypeToken<Map<Integer, Station>>() {
                }.getType(), new IndexedDeserializer<>(Station.class)).
                registerTypeAdapter(Station.class, new StationDeserializer()).
                registerTypeAdapter(Link.class, new LinkDeserializer())
                .create();
    }

    @Bean
    public GsonDeserializer<MetroData> gsonDeserializer(Gson gson) {
        return new PlainDeserializer<>(MetroData.class, gson);
    }

}
