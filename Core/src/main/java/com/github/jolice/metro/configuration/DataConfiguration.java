package com.github.jolice.metro.configuration;

import com.github.jolice.metro.data.deserializer.GsonDeserializer;
import com.github.jolice.metro.model.MetroData;
import com.github.jolice.metro.service.station.StationService;
import com.github.jolice.metro.data.StationNameConflictFix;
import com.github.jolice.metro.data.loader.DataDeserializer;
import com.github.jolice.metro.data.loader.JsonFileDeserializer;
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

