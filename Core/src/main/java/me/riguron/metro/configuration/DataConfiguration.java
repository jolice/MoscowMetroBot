package me.riguron.metro.configuration;

import me.riguron.metro.data.StationNameConflictFix;
import me.riguron.metro.data.deserializer.GsonDeserializer;
import me.riguron.metro.data.loader.DataDeserializer;
import me.riguron.metro.data.loader.JsonFileDeserializer;
import me.riguron.metro.model.MetroData;
import me.riguron.metro.service.station.StationService;
import me.riguron.metro.data.StationNameConflictFix;
import me.riguron.metro.data.deserializer.GsonDeserializer;
import me.riguron.metro.data.loader.DataDeserializer;
import me.riguron.metro.data.loader.JsonFileDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class DataConfiguration {

    @Bean
    public MetroData metroData(DataDeserializer<MetroData> dataDeserializer, StationNameConflictFix stationNameConflictFix, StationService stationService) {
        MetroData metroData = dataDeserializer.deserialize();
        stationNameConflictFix.apply(metroData);
        metroData.getStations().values().forEach(stationService::addStation);
        return metroData;
    }

    @Bean
    public DataDeserializer<MetroData> dataDeserializer(GsonDeserializer<MetroData> gsonDeserializer, @Value("moscow.json") Resource resource) {
        return new JsonFileDeserializer<>(gsonDeserializer, resource);
    }

}

