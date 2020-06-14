package com.github.jolice.metro.configuration;

import com.github.jolice.metro.graph.PlainAdjacencyMatrix;
import com.github.jolice.metro.graph.WeightedGraph;
import com.github.jolice.metro.graph.api.Graph;
import com.github.jolice.metro.model.MetroData;
import com.github.jolice.metro.model.Station;
import com.github.jolice.metro.data.MetroDataGraphMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphConfiguration {

    @Bean
    public Graph<Station> graph(MetroData metroData) {
        Graph<Station> graph = new WeightedGraph<>(new PlainAdjacencyMatrix(metroData.getStations().size()));
        MetroDataGraphMapper metroDataGraphMapper = new MetroDataGraphMapper(metroData, graph);
        metroDataGraphMapper.map();
        return graph;
    }


}
