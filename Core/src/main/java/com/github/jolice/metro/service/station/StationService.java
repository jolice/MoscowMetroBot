package com.github.jolice.metro.service.station;

import com.github.jolice.metro.model.Station;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class StationService {

    private Trie<String, Station> stations = new PatriciaTrie<>();
    private List<Station> randomStationStorage = new ArrayList<>();

    public void addStation(Station station) {

        this.stations.put(station.getName().toLowerCase(), station);
        this.randomStationStorage.add(station);
    }

    public SortedMap<String, Station> getStation(String name) {
        return stations.prefixMap(name.toLowerCase());
    }

    public Station getRandomStation() {
        return randomStationStorage.get(ThreadLocalRandom.current().nextInt(randomStationStorage.size()));
    }


}
