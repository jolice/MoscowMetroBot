package me.nextgeneric.metro.configuration;

import me.nextgeneric.metro.data.StationNameConflictFix;
import me.nextgeneric.metro.data.deserializer.GsonDeserializer;
import me.nextgeneric.metro.data.loader.DataDeserializer;
import me.nextgeneric.metro.data.loader.JsonFileDeserializer;
import me.nextgeneric.metro.model.MetroData;
import me.nextgeneric.metro.service.station.StationService;
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

