package com.github.jolice.metro.data;

import com.github.jolice.metro.model.Line;
import com.github.jolice.metro.model.MetroData;
import com.github.jolice.metro.model.Station;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StationNameConflictFix {

    public void apply(MetroData metroData) {
        Map<Integer, Line> lines = metroData.getLines();
        Map<String, Set<Station>> stationsByName = metroData.getStations().values().stream().collect(Collectors.groupingBy(Station::getName, Collectors.toSet()));
        stationsByName.forEach((s, stations) -> {
            if (stations.size() > 1) {
                for (Station station : stations) {
                    Line line = lines.get(station.getLineId());
                    station.setName(String.format("%s (%s)", station.getName(), line.getAlias()));
                }
            }
        });
    }


}