package me.riguron.metro.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.riguron.metro.data.adapter.IndexedDeserializer;
import me.riguron.metro.data.adapter.LinkDeserializer;
import me.riguron.metro.data.adapter.StationDeserializer;
import me.riguron.metro.data.deserializer.GsonDeserializer;
import me.riguron.metro.data.deserializer.PlainDeserializer;
import me.riguron.metro.model.Line;
import me.riguron.metro.model.MetroData;
import me.riguron.metro.model.Station;
import me.riguron.metro.model.link.Link;
import me.riguron.metro.data.adapter.IndexedDeserializer;
import me.riguron.metro.data.adapter.LinkDeserializer;
import me.riguron.metro.data.adapter.StationDeserializer;
import me.riguron.metro.data.deserializer.GsonDeserializer;
import me.riguron.metro.data.deserializer.PlainDeserializer;
import me.riguron.metro.model.Line;
import me.riguron.metro.model.link.Link;
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
